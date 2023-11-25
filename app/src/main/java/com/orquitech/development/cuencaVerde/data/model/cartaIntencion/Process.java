package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;

public class Process extends BaseItem {

    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public Process() {
    }

    protected Process(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<Process> CREATOR = new Creator<Process>() {
        @Override
        public Process createFromParcel(Parcel source) {
            return new Process(source);
        }

        @Override
        public Process[] newArray(int size) {
            return new Process[size];
        }
    };
}
