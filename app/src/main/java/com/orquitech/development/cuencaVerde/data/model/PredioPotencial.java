package com.orquitech.development.cuencaVerde.data.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

public class PredioPotencial extends BaseItem implements Parcelable {

    private String name;
    private Location location;
    private long remoteId;

    public void setId(long id) {
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }

    public long getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(long remoteId) {
        this.remoteId = remoteId;
    }

    public PredioPotencial() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(this.remoteId);
        dest.writeString(this.name);
        dest.writeParcelable(this.location, flags);
    }

    protected PredioPotencial(Parcel in) {
        this.id = in.readString();
        this.remoteId = in.readLong();
        this.name = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
    }

    public static final Creator<PredioPotencial> CREATOR = new Creator<PredioPotencial>() {
        @Override
        public PredioPotencial createFromParcel(Parcel source) {
            return new PredioPotencial(source);
        }

        @Override
        public PredioPotencial[] newArray(int size) {
            return new PredioPotencial[size];
        }
    };
}
