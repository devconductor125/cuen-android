package com.orquitech.development.cuencaVerde.data.model.siembraDetailForm;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;

public class ActivityOptions extends BaseItem implements Parcelable {

    private boolean hillside;
    private boolean riverbank;
    private boolean spring;

    public boolean isHillside() {
        return hillside;
    }

    public void setHillside(boolean hillside) {
        this.hillside = hillside;
    }

    public boolean isRiverbank() {
        return riverbank;
    }

    public void setRiverbank(boolean riverbank) {
        this.riverbank = riverbank;
    }

    public boolean isSpring() {
        return spring;
    }

    public void setSpring(boolean spring) {
        this.spring = spring;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.hillside ? (byte) 1 : (byte) 0);
        dest.writeByte(this.riverbank ? (byte) 1 : (byte) 0);
        dest.writeByte(this.spring ? (byte) 1 : (byte) 0);
    }

    public ActivityOptions() {
    }

    protected ActivityOptions(Parcel in) {
        this.hillside = in.readByte() != 0;
        this.riverbank = in.readByte() != 0;
        this.spring = in.readByte() != 0;
    }

    public static final Creator<ActivityOptions> CREATOR = new Creator<ActivityOptions>() {
        @Override
        public ActivityOptions createFromParcel(Parcel source) {
            return new ActivityOptions(source);
        }

        @Override
        public ActivityOptions[] newArray(int size) {
            return new ActivityOptions[size];
        }
    };
}
