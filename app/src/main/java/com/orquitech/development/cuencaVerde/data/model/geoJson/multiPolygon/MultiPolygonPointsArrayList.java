package com.orquitech.development.cuencaVerde.data.model.geoJson.multiPolygon;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MultiPolygonPointsArrayList extends ArrayList<MultiPolygonPointsArray> implements Parcelable {

    public MultiPolygonPointsArrayList() {
        super();
    }

    protected MultiPolygonPointsArrayList(Parcel in) {
        in.readList(this, MultiPolygonPointsArray.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }

    public static final Creator<MultiPolygonPointsArrayList> CREATOR =
            new Creator<MultiPolygonPointsArrayList>() {
                public MultiPolygonPointsArrayList createFromParcel(Parcel in) {
                    return new MultiPolygonPointsArrayList(in);
                }

                public MultiPolygonPointsArrayList[] newArray(int size) {
                    return new MultiPolygonPointsArrayList[size];
                }
            };
}
