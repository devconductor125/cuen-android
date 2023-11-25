package com.orquitech.development.cuencaVerde.logic;

import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Observable;

public interface LocationManager {

    void initGoogleApiClient();

    void stopGoogleApiClient();

    void startLocationTracking(Bundle bundle);

    Observable<Boolean> getLocationPermissionSubject();

    void onLocationPermissionSuccess();

    boolean hasPermission();

    void setRequestingLocationUpdates(boolean value);

    boolean isRequestingLocationUpdates();

    void startUpdates();

    void stopUpdates();

    void initFusedLocationClient();

    FusedLocationProviderClient getFusedLocationClient();

    LatLng getLastKnownLocation();

    void setLastKnownLocation(Location lastKnownLocation);
}
