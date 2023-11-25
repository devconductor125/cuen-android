package com.orquitech.development.cuencaVerde;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.Task;
import com.orquitech.development.cuencaVerde.data.model.cartaIntencion.CartaIntencion;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.AppComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.component.DaggerAppComponent;
import com.orquitech.development.cuencaVerde.dependencyInjection.module.AppModule;
import com.orquitech.development.cuencaVerde.dependencyInjection.module.ViewsModule;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.factories.Item;
import com.orquitech.development.cuencaVerde.presentation.views.activities.TaskDetailActivity;
import com.onesignal.OSNotificationAction;
import com.onesignal.OneSignal;
import com.squareup.leakcanary.LeakCanary;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CuencaVerdeApp extends Application implements App {

    private static App app;

    private AppComponent appComponent;
    private static final String CUENCA_CHANNEL = "cuencaChannelAlerts";
    private static final String CUENCA_ALERT_NOTIFICATION_NAME = "cuencaChannelAlerts";
    private OneSignal.NotificationOpenedHandler oneSignalNotificationsHandler;
    private String notificationTaskId;

    private static final String NOTIFICATION_TASK = "task";

    protected CompositeDisposable disposables;

    private BroadcastReceiver connectivityListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isConnected = isNetworkConnected(context);
            appComponent.connectivityManager().setIsConnected(isConnected);
            getObjectsInBackground(isConnected);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        if (app == null) {
            app = this;
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);

        initOneSignal();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .viewsModule(new ViewsModule())
                .build();

        registerReceiver(connectivityListener, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        disposables = new CompositeDisposable();

        subscribeToObservables();
    }

    @Override
    public void initOneSignal() {
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        initOneSignalNotificationHandler();

        OneSignal.startInit(this)
                .setNotificationOpenedHandler(oneSignalNotificationsHandler)
                .init();
    }

    private void initOneSignalNotificationHandler() {
        oneSignalNotificationsHandler = result -> {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            try {
                String type = (String) data.get("type");
                Integer entityId = (Integer) data.get("entity_id");
                switch (type) {
                    case NOTIFICATION_TASK:
                        notificationTaskId = String.valueOf(entityId);
                        this.appComponent.proceduresManager().getProjectTasks(null).subscribe();
                        break;
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        };
    }

    public static App getApp() {
        return app;
    }

    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public ContentResolver getContentResolverObject() {
        return getContentResolver();
    }

    @Override
    public void notifyUser(String title, String message) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelId = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channelId = createNotificationChannel();
        }

        NotificationCompat.Builder builder;

        builder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setChannelId(CUENCA_CHANNEL)
                .setOnlyAlertOnce(true)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_sieeve_brand_imago_white)
                .setColorized(true);

        long time = new Date().getTime();
        String tmpStr = String.valueOf(time);
        String last4Str = tmpStr.substring(tmpStr.length() - 5);
        int notificationId = Integer.valueOf(last4Str);

        notificationManager.notify(notificationId, builder.build());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel() {
        NotificationChannel chan = new NotificationChannel(CUENCA_CHANNEL, CUENCA_ALERT_NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH);
        chan.setLightColor(Color.GREEN);
        chan.setImportance(NotificationManager.IMPORTANCE_HIGH);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (service != null) {
            service.createNotificationChannel(chan);
        }
        return CUENCA_CHANNEL;
    }

    @Override
    public boolean isNetworkConnected(Context context) {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            isConnected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        }
        return isConnected;
    }

    @Override
    public void getObjectsInBackground(boolean isConnected) {
        if (isConnected && appComponent.userAccountManager().hasAccessToken()) {
            disposables.add(appComponent.accionesManager().getOfflineData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(onAccionesManagerOfflineData -> appComponent.proceduresManager().getOfflineData())
                    .subscribe(result -> {
                        appComponent.prediosManager().getProvinces();
                        appComponent.prediosManager().getPrediosManagementLayer();
                        appComponent.prediosManager().checkForPQRS();
                        appComponent.prediosManager().getPredios();
                        appComponent.prediosManager().checkForStardFormToBeSent();
                        // appComponent.prediosManager().checkForNewPrediosToBeSent();
                        appComponent.prediosManager().checkForVegetalMaintenanceFormToBeSent();
                        appComponent.proceduresManager().getProgramsFromService();
                        appComponent.prediosManager().checkForPredioFollowUpFormToBeSent();
                        // appComponent.prediosManager().checkForStardSheetFormToBeSent(); // TODO Implement
                        // appComponent.prediosManager().checkForSurveyToBeSent(); // TODO Implement
                    }));
        }
    }

    private void subscribeToObservables() {
        disposables.add(appComponent.proceduresManager().getTaskObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(tasks -> {
                    if (!TextUtils.isEmpty(notificationTaskId)) {
                        for (Item item : tasks) {
                            Bundle bundle = new Bundle();
                            if (item instanceof Task && ((Task) item).getId().equals(notificationTaskId)) {
                                bundle.putParcelable(RxBus.PAYLOAD, (Task) item);
                            } else if (item instanceof CartaIntencion && ((CartaIntencion) item).getId().equals(notificationTaskId)) {
                                bundle.putParcelable(RxBus.PAYLOAD, (CartaIntencion) item);
                            }
                            notificationTaskId = null;
                            Intent intent = new Intent(this, TaskDetailActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                }).subscribe());
    }
}
