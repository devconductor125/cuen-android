package com.orquitech.development.cuencaVerde.presentation.views;

import androidx.annotation.NonNull;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

import java.lang.ref.WeakReference;

public class WeakLocationListener extends LocationCallback {

    private final WeakReference<LocationCallback> locationListenerRef;

    public WeakLocationListener(@NonNull LocationCallback locationCallback) {
        locationListenerRef = new WeakReference<>(locationCallback);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        if (locationListenerRef.get() == null) {
            return;
        }
        locationListenerRef.get().onLocationResult(locationResult);
    }
}
