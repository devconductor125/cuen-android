package com.orquitech.development.cuencaVerde.data.model.geoJson.polygon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PolygonPointsArray extends ArrayList<PolygonPoint> implements Parcelable {

    public PolygonPointsArray() {
        super();
    }

    protected PolygonPointsArray(Parcel in) {
        in.readList(this, PolygonPoint.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }

    public static final Creator<PolygonPointsArray> CREATOR =
            new Creator<PolygonPointsArray>() {
                public PolygonPointsArray createFromParcel(Parcel in) {
                    return new PolygonPointsArray(in);
                }

                public PolygonPointsArray[] newArray(int size) {
                    return new PolygonPointsArray[size];
                }
            };
}
