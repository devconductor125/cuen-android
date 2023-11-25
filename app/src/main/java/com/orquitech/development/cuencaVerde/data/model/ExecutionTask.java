package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ExecutionTask extends Task implements Parcelable {

    private List<MonitorAndMaintenanceCommentPoint> commentPoints;

    public ExecutionTask() {
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    protected ExecutionTask(Parcel in) {
        super(in);
    }

    public static final Creator<ExecutionTask> CREATOR = new Creator<ExecutionTask>() {
        @Override
        public ExecutionTask createFromParcel(Parcel source) {
            return new ExecutionTask(source);
        }

        @Override
        public ExecutionTask[] newArray(int size) {
            return new ExecutionTask[size];
        }
    };
}
