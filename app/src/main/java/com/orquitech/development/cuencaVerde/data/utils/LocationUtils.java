package com.orquitech.development.cuencaVerde.data.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class LocationUtils {

    public static Location latLngToLocation(LatLng location) {
        Location result = new Location("");
        result.setLatitude(location.latitude);
        result.setLongitude(location.longitude);
        return result;
    }

    public static LatLng locationToLatLng(Location location) {
        return new LatLng(location.getLatitude(), location.getLongitude());
    }
}
