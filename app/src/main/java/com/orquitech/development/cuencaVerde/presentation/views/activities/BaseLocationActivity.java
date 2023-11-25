package com.orquitech.development.cuencaVerde.presentation.views.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.logic.AppLocationManager;
import com.orquitech.development.cuencaVerde.logic.LocationManager;
import com.orquitech.development.cuencaVerde.logic.RxBus;
import com.orquitech.development.cuencaVerde.presentation.views.MapViewHelper;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

public abstract class BaseLocationActivity extends BaseActivity {

    protected static final String MAP_DIALOG = "dialog";
    protected static final String SEND_MAP_DIALOG = "dialog";
    protected static final String SAVE_MAP_DIALOG = "sendMapDialog";
    private static final int ACCESS_FINE_LOCATION_INTENT_ID = 3;
    private int locationTimeInterval = AppLocationManager.UPDATE_INTERVAL_IN_MILLISECONDS;

    @Inject
    public LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(MapViewHelper.REQUESTING_LOCATION_UPDATES)) {
                locationManager.setRequestingLocationUpdates(savedInstanceState.getBoolean(MapViewHelper.REQUESTING_LOCATION_UPDATES));
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.initGoogleApiClient();
        if (locationManager.isRequestingLocationUpdates()) {
            locationManager.startUpdates();
        }
        subscribeToObservables();
    }

    @Override
    protected void onPause() {
        locationManager.stopGoogleApiClient();
        locationManager.stopUpdates();
        super.onPause();
    }

    protected void subscribeToObservables() {
        super.subscribeToObservables();
        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.ON_LOCATION_RESULT:
                            LocationResult locationResult = (LocationResult) bundle.get(RxBus.PAYLOAD);
                            if (locationResult != null) {
                                LatLng lastLocation = new LatLng(locationResult.getLastLocation().getLatitude(), locationResult.getLastLocation().getLongitude());
                                onLocationResult(LocationUtils.latLngToLocation(lastLocation));
                            }
                            break;
                    }
                })
                .subscribe());

        disposables.add(bus.getObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(bundle -> {
                    switch (bundle.getInt(RxBus.CODE)) {
                        case RxBus.LOCATION_MANAGER_CONNECTED:
                            getLocation();
                            break;
                    }
                })
                .subscribe());

        disposables.add(locationManager.getLocationPermissionSubject()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(isConnected -> {
                    if (isConnected) {
                        locationManager.initFusedLocationClient();
                    }
                })
                .subscribe());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(MapViewHelper.REQUESTING_LOCATION_UPDATES, locationManager.isRequestingLocationUpdates());
        super.onSaveInstanceState(outState);
    }

    public void getLocation() {
        requestLocationPermission();
    }

    public void setLocationTimeInterval(int milliseconds) {
        this.locationTimeInterval = milliseconds;
    }

    protected abstract void onLocationResult(Location location);

    protected void requestLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.e(getClass().getSimpleName(), "Show an explanation to the user *asynchronously* -- don't block");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_INTENT_ID);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_INTENT_ID);
            }
        } else {
            requestTurnOnLocation();
        }
    }

    private void requestTurnOnLocation() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(locationTimeInterval);
        locationRequest.setFastestInterval(locationTimeInterval / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this).checkLocationSettings(builder.build());

        result.addOnCompleteListener(task -> {
            try {
                LocationSettingsResponse response = task.getResult(ApiException.class);
                // All location settings are satisfied. The client can initialize location
                // requests here.
                Log.i(getClass().getSimpleName(), "All location settings are satisfied.");
                locationManager.onLocationPermissionSuccess();
            } catch (ApiException exception) {
                switch (exception.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.i(getClass().getSimpleName(), "All location settings are satisfied.");
                        locationManager.onLocationPermissionSuccess();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.i(getClass().getSimpleName(), "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) exception;
                            resolvable.startResolutionForResult(BaseLocationActivity.this, ACCESS_FINE_LOCATION_INTENT_ID);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e(getClass().getSimpleName(), "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.e(getClass().getSimpleName(), "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_INTENT_ID: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestTurnOnLocation();
                } else {
                    Log.e(getClass().getSimpleName(), "onRequestLocationPermissionsResult -> Permission not granted!");
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_INTENT_ID:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        Log.i(getClass().getSimpleName(), "Auth agreed to make required location settings changes.");
                        locationManager.onLocationPermissionSuccess();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.e(getClass().getSimpleName(), "Auth chose not to make required location settings changes.");
                        requestLocationPermission();
                        break;
                }
                break;
        }
    }

    protected void showManagementLayerPropertyInfo(Feature feature) {
        showToast("Predio: " + feature.properties.nOMBREPRE + ", " + "estado: " + feature.properties.eSTADOGP);
    }
}
