package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Role implements Parcelable {

    private final int GUARDA_CUENCA = 4;
    private final int CONTRACTOR = 5;
    private final int TECHNICIAN = 14;

    private long roleValue;

    public Role() {
    }

    public long getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(long roleValue) {
        this.roleValue = roleValue;
    }

    public boolean hasNoQuota() {
        return roleValue != GUARDA_CUENCA;
    }

    public boolean cantViewPredios() {
        return roleValue == CONTRACTOR || roleValue == TECHNICIAN;
    }

    public boolean cantSendPqrs() {
        return roleValue == CONTRACTOR || roleValue == TECHNICIAN;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.roleValue);
    }

    public Role(Parcel in) {
        this.roleValue = in.readLong();
    }

    public static final Creator<Role> CREATOR = new Creator<Role>() {
        @Override
        public Role createFromParcel(Parcel in) {
            return new Role(in);
        }

        @Override
        public Role[] newArray(int size) {
            return new Role[size];
        }
    };
}
