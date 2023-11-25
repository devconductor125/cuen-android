package com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MultiLineStringPoint extends ArrayList<Double> implements Parcelable {

    public MultiLineStringPoint() {
        super();
    }

    protected MultiLineStringPoint(Parcel in) {
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

    public static final Parcelable.Creator<MultiLineStringPoint> CREATOR =
            new Parcelable.Creator<MultiLineStringPoint>() {
                public MultiLineStringPoint createFromParcel(Parcel in) {
                    return new MultiLineStringPoint(in);
                }

                public MultiLineStringPoint[] newArray(int size) {
                    return new MultiLineStringPoint[size];
                }
            };
}
