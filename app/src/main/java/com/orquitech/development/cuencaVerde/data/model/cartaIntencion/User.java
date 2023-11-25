package com.orquitech.development.cuencaVerde.data.model.cartaIntencion;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;

public class User extends BaseItem implements Parcelable {

    private String names;
    private String lastNames;
    private String name;
    private String email;
    private int state;
    private int roleId;
    private String createdAt;
    private String updatedAt;

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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
        dest.writeString(this.names);
        dest.writeString(this.lastNames);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeInt(this.state);
        dest.writeInt(this.roleId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.names = in.readString();
        this.lastNames = in.readString();
        this.name = in.readString();
        this.email = in.readString();
        this.state = in.readInt();
        this.roleId = in.readInt();
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
