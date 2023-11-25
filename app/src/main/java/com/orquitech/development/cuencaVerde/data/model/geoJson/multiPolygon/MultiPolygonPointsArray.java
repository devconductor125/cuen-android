package com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MultiPolygonPointsArray extends ArrayList<MultiPolygonPoint> implements Parcelable {

    public MultiPolygonPointsArray() {
        super();
    }

    protected MultiPolygonPointsArray(Parcel in) {
        in.readList(this, MultiPolygonPoint.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }

    public static final Creator<MultiPolygonPointsArray> CREATOR =
            new Creator<MultiPolygonPointsArray>() {
                public MultiPolygonPointsArray createFromParcel(Parcel in) {
                    return new MultiPolygonPointsArray(in);
                }

                public MultiPolygonPointsArray[] newArray(int size) {
                    return new MultiPolygonPointsArray[size];
                }
            };
}
