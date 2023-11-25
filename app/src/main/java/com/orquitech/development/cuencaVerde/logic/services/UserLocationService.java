package com.orquitech.development.cuencaVerde.logic.services;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;

import com.orquitech.development.cuencaVerde.R;
import com.orquitech.development.cuencaVerde.data.model.Action;
import com.orquitech.development.cuencaVerde.data.model.ExecutionTask;
import com.orquitech.development.cuencaVerde.data.model.MonitorAndMaintenance;
import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.logic.Bus;
import com.orquitech.development.cuencaVerde.logic.LocationManager;
import com.orquitech.development.cuencaVerde.logic.ProceduresManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.logic.utils.GeoUtils;
import com.orquitech.development.cuencaVerde.presentation.utils.TextUtil;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapExecutionActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapMonitorAndMaintenanceActivity;
import com.orquitech.development.cuencaVerde.presentation.views.activities.map.MapTaskActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class UserLocationService extends BaseService {

    private static final String CUENCA_CHANNEL = "cuencaChannel";
    private static final String CUENCA_SERVICE = "cuencaService";
    public static String STOP_TRACKING = "stopTrackingReceiver";
    public static String FORCE_STOP_TRACKING = "forceStopTrackingReceiver";
    public static String ACTION = "action";
    private ServiceHandler serviceHandler;
    private int LOCATION_NOTIFICATION = 999;

    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 3500;
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates;
    private CompositeDisposable disposables;

    private static UserLocationService instance;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private Task task;
    private Action action;
    private boolean receiverRegistered;
    private static boolean shouldStartService;
    private boolean isPause;

    @Inject
    Bus bus;

    @Inject
    LocationManager locationManager;

    @Inject
    ProceduresManager proceduresManager;

    public static boolean isSameTask(String taskId) {
        return instance != null && instance.task.getId().equals(taskId);
    }

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {

        private ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(UserLocationService.this);
            createLocationCallback();
            createLocationRequest();
            buildLocationSettingsRequest();
            startUpdates();
        }
    }

    public static boolean isInstanceCreated() {
        return instance != null;
    }

    public static void setShouldStartService() {
        shouldStartService = true;
    }

    BroadcastReceiver stopTrackingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String actionName = intent.getAction();
            if (actionName != null && actionName.equals(STOP_TRACKING)) {
                isPause = false;
                proceduresManager.addFeatureToGeoJson(task.getId(), action);

                Bundle bundleFromUserLocationService = proceduresManager.getBundleFromUserLocationService();
                bundleFromUserLocationService.putBoolean(RxBus.PENDING_MAP_SAVE, true);
                proceduresManager.setBundleFromUserLocationService(bundleFromUserLocationService);

                Class activityClass = getActivityClass(task);
                if (activityClass != null) {
                    Intent activityIntent = new Intent(UserLocationService.this, activityClass);
                    activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // TODO send bundle with saved feature = true
                    startActivity(activityIntent);
                }

                releaseServiceResources();
                stopForeground(true);
                Log.d(getClass().getSimpleName(), "Service done");
            }
        }
    };

    BroadcastReceiver forceStopTrackingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String actionName = intent.getAction();
            if (actionName != null && actionName.equals(FORCE_STOP_TRACKING)) {
                releaseServiceResources();
                stopForeground(true);
                Log.d(getClass().getSimpleName(), "Service done (forced)");
            }
        }
    };

    private Class<?> getActivityClass(Task task) {
        Class<?> result = null;
        if (task instanceof MonitorAndMaintenance) {
            result = MapMonitorAndMaintenanceActivity.class;
        } else if (task instanceof ExecutionTask) {
            result = MapExecutionActivity.class;
        } else if (task != null) {
            result = MapTaskActivity.class;
        }
        return result;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(getClass().getSimpleName(), "Service onCreate");
        disposables = new CompositeDisposable();
        getComponent().inject(this);
        requestingLocationUpdates = false;

        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        Looper serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(getClass().getSimpleName(), "Service onStartCommand " + "ShouldOpen: " + shouldStartService);

        if (intent != null && shouldStartService) {

            instance = this;
            requestingLocationUpdates = false;

            Bundle bundle = intent.getBundleExtra(RxBus.PAYLOAD);
            if (bundle != null) {
                action = bundle.getParcelable(ACTION);
                task = bundle.getParcelable(RxBus.PAYLOAD);

                Bundle bundleFromService = new Bundle();
                bundleFromService.putParcelable(RxBus.TASK, instance.task);
                proceduresManager.setBundleFromUserLocationService(bundleFromService);
            }

            Log.d(getClass().getSimpleName(), "Service fragment_task_detail: " + task.getId());

            Message msg = serviceHandler.obtainMessage();
            msg.arg1 = startId;
            serviceHandler.sendMessage(msg);

            notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            Intent notificationIntent = new Intent(this, getActivityClass(task));
            notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

            Intent stopIntent = new Intent(STOP_TRACKING);
            PendingIntent stopPendingIntent = PendingIntent.getBroadcast(this, 0, stopIntent, 0);

            String channelId = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                channelId = createNotificationChannel();
            }

            builder = new NotificationCompat.Builder(this, channelId)
                    .setContentTitle(TextUtil.getString(this, R.string.track))
                    .setContentText(TextUtil.getString(this, R.string.waiting_for_location))
                    .setAutoCancel(false)
                    .setChannelId(CUENCA_CHANNEL)
                    .setOngoing(true)
                    .setOnlyAlertOnce(true)
                    .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                    .setSmallIcon(R.drawable.ic_sieeve_brand_imago_white)
                    .setContentIntent(pendingIntent)
                    .setColorized(true);

            notificationManager.notify(LOCATION_NOTIFICATION, builder.build());

            startForeground(LOCATION_NOTIFICATION, builder.build());
            subscribeToObservables();
            registerReceiver(stopTrackingReceiver, new IntentFilter(STOP_TRACKING));
            registerReceiver(forceStopTrackingReceiver, new IntentFilter(FORCE_STOP_TRACKING));
            receiverRegistered = true;
        } else {
            stopSelf();
        }
        return START_REDELIVER_INTENT;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        NotificationChannel chan = new NotificationChannel(CUENCA_CHANNEL, CUENCA_SERVICE, NotificationManager.IMPORTANCE_HIGH);
        chan.setLightColor(Color.GREEN);
        chan.setImportance(NotificationManager.IMPORTANCE_HIGH);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (service != null) {
            service.createNotificationChannel(chan);
        }
        return CUENCA_CHANNEL;
    }

    private void subscribeToObservables() {
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.PAUSE_TRACK:
                            isPause = true;
                            break;
                        case RxBus.RESUME_TRACK:
                            isPause = false;
                            break;
                    }
                }).subscribe());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // We don't provide binding, so return null
        return null;
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Location lastAddedLocation = proceduresManager.getLastAddedTrackedLocation();
                if (GeoUtils.isFurtherThan(locationResult.getLastLocation(), lastAddedLocation, 3)) { // TODO MOVE GeoUtils.isCloserThan(locationResult.getLastLocation(), lastAddedLocation, 10) to smoothing function
                    if (!isPause) {
                        proceduresManager.addNewTrackedLocation(locationResult.getLastLocation());
                        broadcastLocation(locationResult.getLastLocation());
                        updateNotification(locationResult.getLastLocation());
                    } else {
                        broadcastLocationNoNewPoint(locationResult.getLastLocation());
                    }
                }
            }
        };
    }

    private void broadcastLocation(Location location) {
        if (location != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(RxBus.CODE, RxBus.LOCATION_CHANGED);
            bundle.putParcelable(RxBus.PAYLOAD, task);
            bus.publishBehavior(bundle);

            Bundle accuracyBundle = new Bundle();
            if (location.getAccuracy() > 10) {
                accuracyBundle.putFloat(RxBus.PAYLOAD, location.getAccuracy());
                accuracyBundle.putInt(RxBus.CODE, RxBus.LOCATION_HIGH_ACCURACY_ERROR);
            } else {
                accuracyBundle.putInt(RxBus.CODE, RxBus.LOCATION_LOW_ACCURACY_ERROR);
            }
            bus.publishBehavior(accuracyBundle);
        }
    }

    private void broadcastLocationNoNewPoint(Location location) {
        if (location != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(RxBus.CODE, RxBus.LOCATION_CHANGED_NO_NEW_POINT);
            bundle.putParcelable(RxBus.PAYLOAD, task);
            bus.publishBehavior(bundle);
        }
    }

    private void updateNotification(Location lastLocation) {
        List<LatLng> coordinatesList = new ArrayList<>();
        List<List<Location>> trackedLocations = new ArrayList<>(proceduresManager.getTrackedLocations());
        for (List<Location> trackedLocation : trackedLocations) {
            for (Location location : trackedLocation) {
                LatLng point = new LatLng(location.getLatitude(), location.getLongitude());
                coordinatesList.add(point);
            }
        }
        String locationValues = TextUtil.getStringFromDouble(this, R.string.location_notification, GeoUtils.getTrackLength(coordinatesList));
        String locationValuesAndAccuracy = locationValues + TextUtil.getString(this, R.string.location_notification_accuracy, lastLocation.getAccuracy());
        builder.setContentText(locationValuesAndAccuracy);
        notificationManager.notify(LOCATION_NOTIFICATION, builder.build());
    }

    private void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);
        builder.build();
    }

    @SuppressLint("MissingPermission")
    private void startUpdates() {
        if (!requestingLocationUpdates) {
            requestingLocationUpdates = true;
            fusedLocationClient.requestLocationUpdates(locationRequest,
                    locationCallback, Looper.myLooper());
        }
    }

    @Override
    public void onDestroy() {
        releaseServiceResources();
        Log.d(getClass().getSimpleName(), "Service onDestroy");
    }

    private void releaseServiceResources() {
        instance = null;
        shouldStartService = false;
        disposables.clear();
        if (notificationManager != null) {
            notificationManager.cancel(LOCATION_NOTIFICATION);
        }
        stopUpdates();
        if (receiverRegistered) {
            receiverRegistered = false;
            unregisterReceiver(stopTrackingReceiver);
        }
    }

    private void stopUpdates() {
        if (!requestingLocationUpdates) {
            Log.d(getClass().getSimpleName(), "StopUpdates: updates never requested, no-op.");
            return;
        }
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        fusedLocationClient.removeLocationUpdates(locationCallback).addOnCompleteListener(task -> requestingLocationUpdates = false);
    }
}
