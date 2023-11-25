package com.orquitech.development.cuencaVerde.presentation.views.widgets;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ParcelableLocationArrayList extends ArrayList<Location> implements Parcelable {

    public ParcelableLocationArrayList() {
        super();
    }

    protected ParcelableLocationArrayList(Parcel in) {
        in.readList(this, Location.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this);
    }

    public static final Creator<ParcelableLocationArrayList> CREATOR =
            new Creator<ParcelableLocationArrayList>() {
                public ParcelableLocationArrayList createFromParcel(Parcel in) {
                    return new ParcelableLocationArrayList(in);
                }

                public ParcelableLocationArrayList[] newArray(int size) {
                    return new ParcelableLocationArrayList[size];
                }
            };
}
