package com.orquitech.development.cuencaVerde.data.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class PhotographicRegistryPhoto extends BaseItem implements Parcelable {

    private String taskId;
    private String photoName;
    private Uri uri;
    private String comment;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String monitorAndMaintenanceId) {
        this.taskId = monitorAndMaintenanceId;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PhotographicRegistryPhoto() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.taskId);
        dest.writeString(this.photoName);
        dest.writeParcelable(this.uri, flags);
        dest.writeString(this.comment);
    }

    protected PhotographicRegistryPhoto(Parcel in) {
        this.taskId = in.readString();
        this.photoName = in.readString();
        this.uri = in.readParcelable(Uri.class.getClassLoader());
        this.comment = in.readString();
    }

    public static final Creator<PhotographicRegistryPhoto> CREATOR = new Creator<PhotographicRegistryPhoto>() {
        @Override
        public PhotographicRegistryPhoto createFromParcel(Parcel source) {
            return new PhotographicRegistryPhoto(source);
        }

        @Override
        public PhotographicRegistryPhoto[] newArray(int size) {
            return new PhotographicRegistryPhoto[size];
        }
    };
}
