package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class ErosiveProcess extends Task implements Parcelable {

    private LatLng location;

    public LatLng getLocation() {
        return location;
    }

    public void setLatLng(LatLng location) {
        this.location = location;
    }

    public ErosiveProcess() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.location, flags);
    }

    protected ErosiveProcess(Parcel in) {
        super(in);
        this.location = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<ErosiveProcess> CREATOR = new Creator<ErosiveProcess>() {
        @Override
        public ErosiveProcess createFromParcel(Parcel source) {
            return new ErosiveProcess(source);
        }

        @Override
        public ErosiveProcess[] newArray(int size) {
            return new ErosiveProcess[size];
        }
    };
}
