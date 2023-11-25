package com.orquitech.development.cuencaVerde.data.model.geoJson.multiLineString;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MultiLineStringPointsArray extends ArrayList<MultiLineStringPoint> implements Parcelable {

    public MultiLineStringPointsArray() {
        super();
    }

    protected MultiLineStringPointsArray(Parcel in) {
        in.readList(this, MultiLineStringPoint.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }

    public static final Creator<MultiLineStringPointsArray> CREATOR =
            new Creator<MultiLineStringPointsArray>() {
                public MultiLineStringPointsArray createFromParcel(Parcel in) {
                    return new MultiLineStringPointsArray(in);
                }

                public MultiLineStringPointsArray[] newArray(int size) {
                    return new MultiLineStringPointsArray[size];
                }
            };
}
