package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GeometryComment extends BaseItem implements Parcelable {

    private String taskId = "";
    private String hash;
    private String comment;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.taskId);
        dest.writeString(this.hash);
        dest.writeString(this.comment);
    }

    public GeometryComment() {
    }

    protected GeometryComment(Parcel in) {
        this.id = in.readString();
        this.taskId = in.readString();
        this.hash = in.readString();
        this.comment = in.readString();
    }

    public static final Creator<GeometryComment> CREATOR = new Creator<GeometryComment>() {
        @Override
        public GeometryComment createFromParcel(Parcel source) {
            return new GeometryComment(source);
        }

        @Override
        public GeometryComment[] newArray(int size) {
            return new GeometryComment[size];
        }
    };
}
