package com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString;

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

public class MultiLineString extends Geometry implements Parcelable {

    @SerializedName("coordinates")
    @Expose
    public List<MultiLineStringPointsArray> coordinates;

    public MultiLineString() {
        this.coordinates = new ArrayList<>();
    }

    public List<List<LatLng>> getCoordinates() {
        List<List<LatLng>> result = new ArrayList<>();
        for (MultiLineStringPointsArray multiLineStringCoordinates : coordinates) {
            List<LatLng> segmentCoordinates = new ArrayList<>();
            for (MultiLineStringPoint multiLineStringPoint : multiLineStringCoordinates) {
                LatLng coordinate = new LatLng(multiLineStringPoint.get(1), multiLineStringPoint.get(0));
                segmentCoordinates.add(coordinate);
            }
            result.add(segmentCoordinates);
        }
        return result;
    }

    public void setCoordinates(List<List<Location>> pointsArray) {
        List<MultiLineStringPointsArray> coordinates = new ArrayList<>();
        for (List<Location> points : pointsArray) {
            MultiLineStringPointsArray multiLineStringPointsArray = new MultiLineStringPointsArray();
            for (Location point : new ArrayList<>(points)) {
                MultiLineStringPoint multiLineStringPoint = new MultiLineStringPoint();
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
            MultiLineStringPointsArray cleanedMultiLineStringCoordinates = new MultiLineStringPointsArray();
            MultiLineStringPointsArray multiLineStringCoordinates = coordinates.get(0);

            if (multiLineStringCoordinates.size() > 2) {
                for (int i = multiLineStringCoordinates.size() - 1; i > 1; i--) {
                    MultiLineStringPoint point = multiLineStringCoordinates.get(i);
                    MultiLineStringPoint nextPoint = multiLineStringCoordinates.get(i - 1);
                    if (GeoUtils.isCloserThan(point, nextPoint, 5)) {
                        cleanedMultiLineStringCoordinates.add(point);
                    }
                }

                coordinates.set(0, cleanedMultiLineStringCoordinates);
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

    protected MultiLineString(Parcel in) {
        super(in);
        this.coordinates = in.createTypedArrayList(MultiLineStringPointsArray.CREATOR);
    }

    public static final Creator<MultiLineString> CREATOR = new Creator<MultiLineString>() {
        @Override
        public MultiLineString createFromParcel(Parcel source) {
            return new MultiLineString(source);
        }

        @Override
        public MultiLineString[] newArray(int size) {
            return new MultiLineString[size];
        }
    };
}
