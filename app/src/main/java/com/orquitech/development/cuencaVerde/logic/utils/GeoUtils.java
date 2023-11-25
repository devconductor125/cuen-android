package com.orquitech.development.cuencaVerde.logic.utils;

import android.location.Location;
import android.util.Log;

import com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString.MultiLineStringPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon.MultiPolygonPoint;
import com.orquitech.development.cuencaVerde.data.model.geoJson.polygon.PolygonPoint;
import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GeoUtils {

    private static String TAG = "GeoUtils";

    public static List<LatLng> sortCoordinatesByProximity(List<LatLng> coordinates) {
        List<LatLng> sortedCoordinates = new ArrayList<>();
        if (coordinates.size() > 1) {
            LatLng currentCoordinate;
            LatLng closestCoordinate = null;
            for (int i = 0; i < coordinates.size(); i++) {
                currentCoordinate = coordinates.get(i);
                if (sortedCoordinates.size() == 0) {
                    sortedCoordinates.add(currentCoordinate);
                }
                if (closestCoordinate == null) {
                    closestCoordinate = coordinates.get(i + 1);
                }
                for (int j = i + 1; j < coordinates.size(); j++) {
                    LatLng coordinateToCompare = coordinates.get(j);
                    if (isCoordinateToCompareCloserThanClosest(currentCoordinate, closestCoordinate, coordinateToCompare)) {
                        closestCoordinate = coordinateToCompare;
                    }
                }
                sortedCoordinates.add(closestCoordinate);
            }
        }
        return sortedCoordinates;
    }

    private static boolean isCoordinateToCompareCloserThanClosest(LatLng currentCoordinate, LatLng closestCoordinate, LatLng coordinateToCompare) {
        Location currentLocation = new Location("");
        currentLocation.setLatitude(currentCoordinate.latitude);
        currentLocation.setLongitude(currentCoordinate.longitude);

        Location closestLocation = new Location("");
        closestLocation.setLatitude(closestCoordinate.latitude);
        closestLocation.setLongitude(closestCoordinate.longitude);

        Location locationToCompare = new Location("");
        locationToCompare.setLatitude(coordinateToCompare.latitude);
        locationToCompare.setLongitude(coordinateToCompare.longitude);

        float distanceInMetersToClosestLocation = currentLocation.distanceTo(closestLocation);
        float distanceInMetersToLocationToCompare = currentLocation.distanceTo(locationToCompare);

        return distanceInMetersToLocationToCompare <= distanceInMetersToClosestLocation;
    }

    private static ArrayList<LatLng> sortLocations(ArrayList<LatLng> locations, final double myLatitude, final double myLongitude) {
        Comparator<LatLng> comp = (location1, location2) -> {
            float[] result1 = new float[3];
            Location.distanceBetween(myLatitude, myLongitude, location1.longitude, location1.latitude, result1);
            Float distance1 = result1[0];

            float[] result2 = new float[3];
            Location.distanceBetween(myLatitude, myLongitude, location2.longitude, location2.latitude, result2);
            Float distance2 = result2[0];

            return distance1.compareTo(distance2);
        };
        Collections.sort(locations, comp);
        return locations;
    }

    public static double getTrackLength(List<LatLng> currentTrack) {
        double trackLength = 0;
        for (int i = 0; i < currentTrack.size(); i++) {
            if (i + 1 < currentTrack.size()) {
                Location currentLocation = new Location("");
                currentLocation.setLatitude(currentTrack.get(i).latitude);
                currentLocation.setLongitude(currentTrack.get(i).longitude);

                Location nextLocation = new Location("");
                nextLocation.setLatitude(currentTrack.get(i + 1).latitude);
                nextLocation.setLongitude(currentTrack.get(i + 1).longitude);

                trackLength += currentLocation.distanceTo(nextLocation);
            }
        }
        return trackLength;
    }

    public static boolean isFurtherThan(LatLng newLng, LatLng lastLng, int meters) {
        Location newLocation = LocationUtils.latLngToLocation(newLng);
        Location lastLocation = LocationUtils.latLngToLocation(lastLng);
        Log.d(TAG, "Distance to last location: " + newLocation.distanceTo(lastLocation) + " m");
        return newLocation.distanceTo(lastLocation) >= meters;
    }

    public static boolean isFurtherThan(Location newLocation, Location lastLocation, int meters) {
        return lastLocation == null || newLocation.distanceTo(lastLocation) >= meters;
    }

    public static boolean isCloserThan(Location newLocation, Location lastLocation, int meters) {
        return lastLocation == null || newLocation.distanceTo(lastLocation) <= meters;
    }

    public static boolean isCloserThan(MultiLineStringPoint location1, MultiLineStringPoint location2, int meters) {

        Location newLocation1 = new Location("");
        newLocation1.setLatitude(location1.get(1));
        newLocation1.setLongitude(location1.get(0));

        Location newLocation2 = new Location("");
        newLocation2.setLatitude(location2.get(1));
        newLocation2.setLongitude(location2.get(0));

        return newLocation1.distanceTo(newLocation2) <= meters;
    }

    public static boolean isCloserThan(PolygonPoint location1, PolygonPoint location2, int meters) {

        Location newLocation1 = new Location("");
        newLocation1.setLatitude(location1.get(1));
        newLocation1.setLongitude(location1.get(0));

        Location newLocation2 = new Location("");
        newLocation2.setLatitude(location2.get(1));
        newLocation2.setLongitude(location2.get(0));

        return newLocation1.distanceTo(newLocation2) <= meters;
    }

    public static boolean isCloserThan(MultiPolygonPoint location1, MultiPolygonPoint location2, int meters) {

        Location newLocation1 = new Location("");
        newLocation1.setLatitude(location1.get(1));
        newLocation1.setLongitude(location1.get(0));

        Location newLocation2 = new Location("");
        newLocation2.setLatitude(location2.get(1));
        newLocation2.setLongitude(location2.get(0));

        return newLocation1.distanceTo(newLocation2) <= meters;
    }

    private static class SortPlaces implements Comparator<LatLng> {
        LatLng currentLoc;

        public SortPlaces(LatLng current) {
            currentLoc = current;
        }

        @Override
        public int compare(final LatLng place1, final LatLng place2) {
            double lat1 = place1.latitude;
            double lon1 = place1.longitude;
            double lat2 = place2.latitude;
            double lon2 = place2.longitude;

            double distanceToPlace1 = distance(currentLoc.longitude, currentLoc.latitude, lat1, lon1);
            double distanceToPlace2 = distance(currentLoc.longitude, currentLoc.latitude, lat2, lon2);
            return (int) (distanceToPlace1 - distanceToPlace2);
        }

        private double distance(double fromLat, double fromLon, double toLat, double toLon) {
            double radius = 6378137;   // approximate Earth radius, *in meters*
            double deltaLat = toLat - fromLat;
            double deltaLon = toLon - fromLon;
            double angle = 2 * Math.asin(Math.sqrt(
                    Math.pow(Math.sin(deltaLat / 2), 2) +
                            Math.cos(fromLat) * Math.cos(toLat) *
                                    Math.pow(Math.sin(deltaLon / 2), 2)));
            return radius * angle;
        }
    }
}
