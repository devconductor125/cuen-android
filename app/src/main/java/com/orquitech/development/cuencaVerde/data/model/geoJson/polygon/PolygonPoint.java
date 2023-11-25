package com.orquitech.development.cuencaVerde.data.model.geoJson.polygon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PolygonPoint extends ArrayList<Double> implements Parcelable {

    public PolygonPoint() {
        super();
    }

    protected PolygonPoint(Parcel in) {
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

    public static final Creator<PolygonPoint> CREATOR =
            new Creator<PolygonPoint>() {
                public PolygonPoint createFromParcel(Parcel in) {
                    return new PolygonPoint(in);
                }

                public PolygonPoint[] newArray(int size) {
                    return new PolygonPoint[size];
                }
            };
}
