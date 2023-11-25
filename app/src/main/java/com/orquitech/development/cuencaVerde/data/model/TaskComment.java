package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TaskComment extends BaseItem implements Parcelable {

    private String content;
    private String author;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.content);
        dest.writeString(this.author);
    }

    public TaskComment() {
    }

    protected TaskComment(Parcel in) {
        this.content = in.readString();
        this.author = in.readString();
    }

    public static final Creator<TaskComment> CREATOR = new Creator<TaskComment>() {
        @Override
        public TaskComment createFromParcel(Parcel source) {
            return new TaskComment(source);
        }

        @Override
        public TaskComment[] newArray(int size) {
            return new TaskComment[size];
        }
    };
}
