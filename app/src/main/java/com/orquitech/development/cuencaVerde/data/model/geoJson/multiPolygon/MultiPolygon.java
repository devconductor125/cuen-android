package com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon;

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

public class MultiPolygon extends Geometry implements Parcelable {

    @SerializedName("coordinates")
    @Expose
    public List<MultiPolygonPointsArrayList> coordinates;

    public MultiPolygon() {
        this.coordinates = new ArrayList<>();
    }

    public List<List<LatLng>> getCoordinates() {
        List<List<LatLng>> result = new ArrayList<>();
        for (MultiPolygonPointsArrayList multiPolygonPointsArrayList : coordinates) {
            for (MultiPolygonPointsArray multiPolygonCoordinates : multiPolygonPointsArrayList) {
                List<LatLng> segmentCoordinates = new ArrayList<>();
                for (MultiPolygonPoint multiPolygonPoint : multiPolygonCoordinates) {
                    LatLng coordinate = new LatLng(multiPolygonPoint.get(1), multiPolygonPoint.get(0));
                    segmentCoordinates.add(coordinate);
                }
                result.add(segmentCoordinates);
            }
        }
        return result;
    }

    public void setCoordinates(List<List<Location>> pointsArray) {
        List<MultiPolygonPointsArrayList> coordinates = new ArrayList<>();
        for (List<Location> points : pointsArray) {
            MultiPolygonPointsArrayList multiPolygonPointsArrayList = new MultiPolygonPointsArrayList();
            for (Location point : new ArrayList<>(points)) {
                MultiPolygonPointsArray multiPolygonPointsArray = new MultiPolygonPointsArray();
                MultiPolygonPoint multiPolygonPoint = new MultiPolygonPoint();
                multiPolygonPoint.add(point.getLongitude());
                multiPolygonPoint.add(point.getLatitude());
                multiPolygonPointsArray.add(multiPolygonPoint);
                multiPolygonPointsArrayList.add(multiPolygonPointsArray);
            }
            coordinates.add(multiPolygonPointsArrayList);
        }
        this.coordinates = coordinates;
    }

    public void clearSpikes() {
        if (coordinates.size() > 0) {
            MultiPolygonPointsArrayList multiPolygonPointsArrays = coordinates.get(0);

            if (multiPolygonPointsArrays.size() > 0) {
                MultiPolygonPointsArray cleanedMultiPolygonPointsArray= new MultiPolygonPointsArray();
                MultiPolygonPointsArray multiPolygonPointsArray = multiPolygonPointsArrays.get(0);

                if (multiPolygonPointsArray.size() > 2) {
                    for (int i = multiPolygonPointsArray.size() - 1; i > 1; i--) {
                        MultiPolygonPoint point = multiPolygonPointsArray.get(i);
                        MultiPolygonPoint nextPoint = multiPolygonPointsArray.get(i - 1);
                        if (GeoUtils.isCloserThan(point, nextPoint, 5)) {
                            cleanedMultiPolygonPointsArray.add(point);
                        }
                    }

                    coordinates.get(0).set(0, cleanedMultiPolygonPointsArray);
                }
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

    protected MultiPolygon(Parcel in) {
        super(in);
        this.coordinates = in.createTypedArrayList(MultiPolygonPointsArrayList.CREATOR);
    }

    public static final Creator<MultiPolygon> CREATOR = new Creator<MultiPolygon>() {
        @Override
        public MultiPolygon createFromParcel(Parcel source) {
            return new MultiPolygon(source);
        }

        @Override
        public MultiPolygon[] newArray(int size) {
            return new MultiPolygon[size];
        }
    };
}
