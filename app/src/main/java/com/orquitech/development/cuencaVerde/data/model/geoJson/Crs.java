package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crs implements Parcelable {

    public static final Parcelable.Creator<Crs> CREATOR = new Parcelable.Creator<Crs>() {
        @Override
        public Crs createFromParcel(Parcel source) {
            return new Crs(source);
        }

        @Override
        public Crs[] newArray(int size) {
            return new Crs[size];
        }
    };

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("properties")
    @Expose
    public Properties properties;

    public Crs(Parcel in) {
        this.type = in.readString();
        this.properties = in.readParcelable(Properties.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.type);
        out.writeParcelable(this.properties, flags);
    }
}
