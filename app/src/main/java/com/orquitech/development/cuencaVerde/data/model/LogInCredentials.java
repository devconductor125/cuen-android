package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LogInCredentials implements Parcelable {

    private String username;
    private String password;

    public LogInCredentials() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    protected LogInCredentials(Parcel in) {
        this.username = in.readString();
        this.password = in.readString();
    }

    public static final Creator<LogInCredentials> CREATOR = new Creator<LogInCredentials>() {
        @Override
        public LogInCredentials createFromParcel(Parcel source) {
            return new LogInCredentials(source);
        }

        @Override
        public LogInCredentials[] newArray(int size) {
            return new LogInCredentials[size];
        }
    };
}
