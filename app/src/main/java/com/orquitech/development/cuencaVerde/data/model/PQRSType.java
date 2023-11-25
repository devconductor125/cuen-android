package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PQRSType extends BaseDialogListItem implements Parcelable {

    private String dependencyId;

    public String getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(String dependencyId) {
        this.dependencyId = dependencyId;
    }

    @Override
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public PQRSType() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.dependencyId);
    }

    protected PQRSType(Parcel in) {
        super(in);
        this.dependencyId = in.readString();
    }

    public static final Creator<PQRSType> CREATOR = new Creator<PQRSType>() {
        @Override
        public PQRSType createFromParcel(Parcel source) {
            return new PQRSType(source);
        }

        @Override
        public PQRSType[] newArray(int size) {
            return new PQRSType[size];
        }
    };
}
