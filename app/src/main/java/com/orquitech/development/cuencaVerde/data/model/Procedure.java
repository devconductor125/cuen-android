package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Procedure extends BaseItem implements Parcelable {

    private String name;

    public Procedure() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Procedure clone() {
        Parcel p = Parcel.obtain();
        p.writeValue(this);
        p.setDataPosition(0);
        Procedure projectCopy = (Procedure) p.readValue(Procedure.class.getClassLoader());
        p.recycle();
        return projectCopy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
    }

    protected Procedure(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Creator<Procedure> CREATOR = new Creator<Procedure>() {
        @Override
        public Procedure createFromParcel(Parcel source) {
            return new Procedure(source);
        }

        @Override
        public Procedure[] newArray(int size) {
            return new Procedure[size];
        }
    };
}
