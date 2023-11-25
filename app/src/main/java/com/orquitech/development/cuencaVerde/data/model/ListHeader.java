package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;

public class ListHeader extends BaseItem {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.text);
    }

    public ListHeader() {
    }

    protected ListHeader(Parcel in) {
        this.text = in.readString();
    }

    public static final Creator<ListHeader> CREATOR = new Creator<ListHeader>() {
        @Override
        public ListHeader createFromParcel(Parcel source) {
            return new ListHeader(source);
        }

        @Override
        public ListHeader[] newArray(int size) {
            return new ListHeader[size];
        }
    };
}
