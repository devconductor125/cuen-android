package com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MultiPolygonPoint extends ArrayList<Double> implements Parcelable {

    public MultiPolygonPoint() {
        super();
    }

    protected MultiPolygonPoint(Parcel in) {
        in.readList(this, Double.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }

    public static final Creator<MultiPolygonPoint> CREATOR =
            new Creator<MultiPolygonPoint>() {
                public MultiPolygonPoint createFromParcel(Parcel in) {
                    return new MultiPolygonPoint(in);
                }

                public MultiPolygonPoint[] newArray(int size) {
                    return new MultiPolygonPoint[size];
                }
            };
}
