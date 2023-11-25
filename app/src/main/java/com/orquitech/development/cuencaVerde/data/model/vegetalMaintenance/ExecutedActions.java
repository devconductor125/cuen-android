package com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance;

import android.os.Parcel;
import android.os.Parcelable;

public class ExecutedActions implements Parcelable {

    private String fertilization;
    private String Reseeding;

    public String getFertilization() {
        return fertilization;
    }

    public void setFertilization(String fertilization) {
        this.fertilization = fertilization;
    }

    public String getReseeding() {
        return Reseeding;
    }

    public void setReseeding(String reseeding) {
        Reseeding = reseeding;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fertilization);
        dest.writeString(this.Reseeding);
    }

    public ExecutedActions() {
    }

    protected ExecutedActions(Parcel in) {
        this.fertilization = in.readString();
        this.Reseeding = in.readString();
    }

    public static final Creator<ExecutedActions> CREATOR = new Creator<ExecutedActions>() {
        @Override
        public ExecutedActions createFromParcel(Parcel source) {
            return new ExecutedActions(source);
        }

        @Override
        public ExecutedActions[] newArray(int size) {
            return new ExecutedActions[size];
        }
    };
}
