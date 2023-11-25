package com.orquitech.development.cuencaVerde.data.model.geoJson.polygon;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Geometry;
import com.orquitech.development.cuencaVerde.logic.utils.GeoUtils;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends Geometry implements Parcelable {

    @SerializedName("coordinates")
    @Expose
    public List<PolygonPointsArray> coordinates;

    public Polygon() {
        this.coordinates = new ArrayList<>();
    }

    public List<List<LatLng>> getCoordinates() {
        List<List<LatLng>> result = new ArrayList<>();
        for (PolygonPointsArray multiLineStringCoordinates : coordinates) {
            List<LatLng> segmentCoordinates = new ArrayList<>();
            for (PolygonPoint multiLineStringPoint : multiLineStringCoordinates) {
                LatLng coordinate = new LatLng(multiLineStringPoint.get(1), multiLineStringPoint.get(0));
                segmentCoordinates.add(coordinate);
            }
            result.add(segmentCoordinates);
        }
        return result;
    }

    public void setCoordinates(List<List<Location>> pointsArray) {
        List<PolygonPointsArray> coordinates = new ArrayList<>();
        for (List<Location> points : pointsArray) {
            PolygonPointsArray multiLineStringPointsArray = new PolygonPointsArray();
            for (Location point : new ArrayList<>(points)) {
                PolygonPoint multiLineStringPoint = new PolygonPoint();
                multiLineStringPoint.add(point.getLongitude());
                multiLineStringPoint.add(point.getLatitude());
                multiLineStringPointsArray.add(multiLineStringPoint);
            }
            coordinates.add(multiLineStringPointsArray);
        }
        this.coordinates = coordinates;
    }

    public void clearSpikes() {
        if (coordinates.size() > 0) {
            PolygonPointsArray cleanedPolygonPoints = new PolygonPointsArray();
            PolygonPointsArray polygonPoints = coordinates.get(0);

            if (polygonPoints.size() > 2) {
                for (int i = polygonPoints.size() - 1; i > 1; i--) {
                    PolygonPoint point = polygonPoints.get(i);
                    PolygonPoint nextPoint = polygonPoints.get(i - 1);
                    if (GeoUtils.isCloserThan(point, nextPoint, 5)) {
                        cleanedPolygonPoints.add(point);
                    }
                }

                coordinates.set(0, cleanedPolygonPoints);
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.coordinates);
    }

    protected Polygon(Parcel in) {
        super(in);
        this.coordinates = in.createTypedArrayList(PolygonPointsArray.CREATOR);
    }

    public static final Creator<Polygon> CREATOR = new Creator<Polygon>() {
        @Override
        public Polygon createFromParcel(Parcel source) {
            return new Polygon(source);
        }

        @Override
        public Polygon[] newArray(int size) {
            return new Polygon[size];
        }
    };
}
