package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class PredioFollowUp implements Parcelable {

    private String id;
    private String basin;
    private String municipality;
    private String lane;
    private String propertyName;
    private String program;
    private String project;
    private String object;
    private AppDate executionEndDate;
    private String comments;
    private String monitorAndMaintenanceId;
    private boolean pendingToBeSent;

    public String getId() {
        if (TextUtils.isEmpty(id)) {
            id = "-1";
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppDate getExecutionEndDate() {
        return executionEndDate;
    }

    public void setExecutionEndDate(AppDate executionEndDate) {
        this.executionEndDate = executionEndDate;
    }

    public String getMonitorAndMaintenanceId() {
        return monitorAndMaintenanceId;
    }

    public void setMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        this.monitorAndMaintenanceId = monitorAndMaintenanceId;
    }

    public String getBasin() {
        return basin;
    }

    public void setBasin(String basin) {
        this.basin = basin;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isNew() {
        return Integer.parseInt(getId()) <= 0;
    }

    public void initNullFields() {
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean hasValidComments() {
        return !TextUtils.isEmpty(comments);
    }

    public PredioFollowUp() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.basin);
        dest.writeString(this.municipality);
        dest.writeString(this.lane);
        dest.writeString(this.propertyName);
        dest.writeString(this.program);
        dest.writeString(this.project);
        dest.writeString(this.object);
        dest.writeParcelable(this.executionEndDate, flags);
        dest.writeString(this.comments);
        dest.writeString(this.monitorAndMaintenanceId);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
    }

    protected PredioFollowUp(Parcel in) {
        this.id = in.readString();
        this.basin = in.readString();
        this.municipality = in.readString();
        this.lane = in.readString();
        this.propertyName = in.readString();
        this.program = in.readString();
        this.project = in.readString();
        this.object = in.readString();
        this.executionEndDate = in.readParcelable(AppDate.class.getClassLoader());
        this.comments = in.readString();
        this.monitorAndMaintenanceId = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
    }

    public static final Creator<PredioFollowUp> CREATOR = new Creator<PredioFollowUp>() {
        @Override
        public PredioFollowUp createFromParcel(Parcel source) {
            return new PredioFollowUp(source);
        }

        @Override
        public PredioFollowUp[] newArray(int size) {
            return new PredioFollowUp[size];
        }
    };
}
