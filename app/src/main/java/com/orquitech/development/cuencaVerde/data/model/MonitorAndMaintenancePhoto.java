package com.orquitech.development.cuencaVerde.data.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class MonitorAndMaintenancePhoto extends BaseItem implements Parcelable {

    private String monitorAndMaintenanceCommentPointId;
    private String photoName;
    private Bitmap bitmap;

    public String getMonitorAndMaintenanceCommentPointId() {
        return monitorAndMaintenanceCommentPointId;
    }

    public void setMonitorAndMaintenanceCommentPointId(String monitorAndMaintenanceId) {
        this.monitorAndMaintenanceCommentPointId = monitorAndMaintenanceId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public MonitorAndMaintenancePhoto() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.monitorAndMaintenanceCommentPointId);
        dest.writeString(this.photoName);
        dest.writeParcelable(this.bitmap, flags);
    }

    protected MonitorAndMaintenancePhoto(Parcel in) {
        this.monitorAndMaintenanceCommentPointId = in.readString();
        this.photoName = in.readString();
        this.bitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<MonitorAndMaintenancePhoto> CREATOR = new Creator<MonitorAndMaintenancePhoto>() {
        @Override
        public MonitorAndMaintenancePhoto createFromParcel(Parcel source) {
            return new MonitorAndMaintenancePhoto(source);
        }

        @Override
        public MonitorAndMaintenancePhoto[] newArray(int size) {
            return new MonitorAndMaintenancePhoto[size];
        }
    };
}
