package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Municipality extends BaseItem implements Parcelable {

    private String name;
    private String provinceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.provinceId);
    }

    public Municipality() {
    }

    protected Municipality(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.provinceId = in.readString();
    }

    public static final Creator<Municipality> CREATOR = new Creator<Municipality>() {
        @Override
        public Municipality createFromParcel(Parcel source) {
            return new Municipality(source);
        }

        @Override
        public Municipality[] newArray(int size) {
            return new Municipality[size];
        }
    };
}
