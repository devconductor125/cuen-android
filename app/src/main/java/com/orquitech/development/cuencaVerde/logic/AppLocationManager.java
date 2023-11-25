package com.orquitech.development.cuencaVerde.logic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

import com.orquitech.development.cuencaVerde.App;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.orquitech.development.cuencaVerde.data.utils.OsUtils;
import com.orquitech.development.cuencaVerde.logic.services.UserLocationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class AppLocationManager implements LocationManager, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public static final int UPDATE_INTERVAL_IN_MILLISECONDS = 2500;
    public static final int FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private final App app;
    private final Bus bus;

    private GoogleApiClient googleApiClient;
    private final BehaviorSubject<Boolean> locationPermissionSubject = BehaviorSubject.create();
    private boolean hasLocationPermission;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean requestingLocationUpdates;
    private Location lastKnownLocation;

    public AppLocationManager(App app, Bus bus) {
        this.app = app;
        this.bus = bus;
        initLocationCallback();
    }

    private void initLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                setLastKnownLocation(locationResult.getLastLocation());

                Bundle bundle = new Bundle();
                bundle.putInt(RxBus.CODE, RxBus.ON_LOCATION_RESULT);
                bundle.putParcelable(RxBus.PAYLOAD, locationResult);
                bus.publish(bundle);
            }
        };
    }

    @SuppressLint("MissingPermission")
    @Override
    public void initFusedLocationClient() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(app.getApplicationContext());
        createLocationRequest();
        buildLocationSettingsRequest();
        startUpdates();
    }

    @Override
    public FusedLocationProviderClient getFusedLocationClient() {
        return fusedLocationClient;
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
    public void startUpdates() {
        if (!requestingLocationUpdates) {
            requestingLocationUpdates = true;
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    @Override
    public void stopUpdates() {
        if (!requestingLocationUpdates) {
            Log.d(getClass().getSimpleName(), "stopLocationUpdates: updates never requested, no-op.");
            return;
        }
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        fusedLocationClient.removeLocationUpdates(locationCallback);
        requestingLocationUpdates = false;
    }


    @Override
    public void initGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(app.getApplicationContext())
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        googleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt(RxBus.CODE, RxBus.LOCATION_MANAGER_CONNECTED);
        bus.publish(bundle);
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(getClass().getSimpleName(), "Connection failed: ConnectionResult.getErrorCode() = " + connectionResult.getErrorCode());
    }

    @Override
    public void stopGoogleApiClient() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
            googleApiClient.unregisterConnectionCallbacks(this);
            googleApiClient.unregisterConnectionFailedListener(this);
            googleApiClient = null;
        }
    }

    @Override
    public void startLocationTracking(Bundle bundle) {
        Intent intent = new Intent(app.getApplicationContext(), UserLocationService.class);
        UserLocationService.setShouldStartService();
        intent.putExtra(RxBus.PAYLOAD, bundle);
        if (OsUtils.isAtLeastOreo()) {
            app.getApplicationContext().startForegroundService(intent);
        } else {
            app.getApplicationContext().startService(intent);
        }
    }

    @Override
    public Observable<Boolean> getLocationPermissionSubject() {
        return locationPermissionSubject;
    }

    @Override
    public void onLocationPermissionSuccess() {
        hasLocationPermission = true;
        locationPermissionSubject.onNext(true);
    }

    @Override
    public boolean hasPermission() {
        return hasLocationPermission;
    }

    @Override
    public void setRequestingLocationUpdates(boolean value) {
        this.requestingLocationUpdates = value;
    }

    @Override
    public boolean isRequestingLocationUpdates() {
        return requestingLocationUpdates;
    }

    @Override
    public LatLng getLastKnownLocation() {
        if (lastKnownLocation == null) {
            lastKnownLocation = new Location("");
            lastKnownLocation.setLatitude(0);
            lastKnownLocation.setLongitude(0);
        }
        return LocationUtils.locationToLatLng(lastKnownLocation);
    }

    @Override
    public void setLastKnownLocation(Location lastKnownLocation) {
        this.lastKnownLocation = lastKnownLocation;
    }
}
