package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;

public class AddPhoto extends BaseItem {

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public AddPhoto() {
    }

    protected AddPhoto(Parcel in) {
    }

    public static final Creator<AddPhoto> CREATOR = new Creator<AddPhoto>() {
        @Override
        public AddPhoto createFromParcel(Parcel source) {
            return new AddPhoto(source);
        }

        @Override
        public AddPhoto[] newArray(int size) {
            return new AddPhoto[size];
        }
    };
}
