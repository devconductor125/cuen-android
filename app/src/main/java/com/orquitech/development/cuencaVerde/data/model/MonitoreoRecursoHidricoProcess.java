package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

public class MonitoreoRecursoHidricoProcess extends Task implements Parcelable {

    private LatLng location;

    public LatLng getLocation() {
        return location;
    }

    public void setLatLng(LatLng location) {
        this.location = location;
    }

    public MonitoreoRecursoHidricoProcess() {
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

    protected MonitoreoRecursoHidricoProcess(Parcel in) {
        super(in);
        this.location = in.readParcelable(LatLng.class.getClassLoader());
    }

    public static final Creator<MonitoreoRecursoHidricoProcess> CREATOR = new Creator<MonitoreoRecursoHidricoProcess>() {
        @Override
        public MonitoreoRecursoHidricoProcess createFromParcel(Parcel source) {
            return new MonitoreoRecursoHidricoProcess(source);
        }

        @Override
        public MonitoreoRecursoHidricoProcess[] newArray(int size) {
            return new MonitoreoRecursoHidricoProcess[size];
        }
    };
}
