package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Program extends BaseItem implements Parcelable {

    protected String name;
    protected boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    public Program() {
    }

    protected Program(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<Program> CREATOR = new Creator<Program>() {
        @Override
        public Program createFromParcel(Parcel source) {
            return new Program(source);
        }

        @Override
        public Program[] newArray(int size) {
            return new Program[size];
        }
    };
}
