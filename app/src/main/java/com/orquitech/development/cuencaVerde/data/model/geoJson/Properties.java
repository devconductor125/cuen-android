package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties implements Parcelable {

    @SerializedName("name")
    @Expose
    public String name;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public Properties() {
    }

    protected Properties(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<Properties> CREATOR = new Creator<Properties>() {
        @Override
        public Properties createFromParcel(Parcel source) {
            return new Properties(source);
        }

        @Override
        public Properties[] newArray(int size) {
            return new Properties[size];
        }
    };
}
