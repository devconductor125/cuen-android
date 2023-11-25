package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dependency extends BaseDialogListItem implements Parcelable {

    public Dependency() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected Dependency(Parcel in) {
        super(in);
    }

    public static final Creator<Dependency> CREATOR = new Creator<Dependency>() {
        @Override
        public Dependency createFromParcel(Parcel source) {
            return new Dependency(source);
        }

        @Override
        public Dependency[] newArray(int size) {
            return new Dependency[size];
        }
    };
}
