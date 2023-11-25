package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BaseDialogListItem extends BaseItem implements Parcelable {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public BaseDialogListItem() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }

    protected BaseDialogListItem(Parcel in) {
        this.title = in.readString();
    }

    public static final Creator<BaseDialogListItem> CREATOR = new Creator<BaseDialogListItem>() {
        @Override
        public BaseDialogListItem createFromParcel(Parcel source) {
            return new BaseDialogListItem(source);
        }

        @Override
        public BaseDialogListItem[] newArray(int size) {
            return new BaseDialogListItem[size];
        }
    };
}
