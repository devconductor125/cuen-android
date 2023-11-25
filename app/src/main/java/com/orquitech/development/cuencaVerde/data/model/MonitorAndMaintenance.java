package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;

import java.util.ArrayList;
import java.util.List;

public class MonitorAndMaintenance extends Task implements Parcelable {

    public static final String STARD = "stard";
    public static final String VEGETAL_MAINTENANCE = "vegetableMaintenance";
    public static final String PREDIO_FOLLOW_UP = "predioFollowUp"; // TODO change for real feature name
    private String title;
    private String procedureId;
    private String dueDate;
    private Feature feature;
    private List<MonitorAndMaintenanceCommentPoint> monitorAndMaintenanceList;
    private String jsonFeature;
    private boolean pendingToBeSent;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getDueDateStatus() {
        return dueDateStatus;
    }

    public void setDueDateStatus(int dueDateStatus) {
        this.dueDateStatus = dueDateStatus;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public String getJsonFeature() {
        return jsonFeature;
    }

    public void setJsonFeature(String jsonFeature) {
        this.jsonFeature = jsonFeature;
    }

    public void addPoint(MonitorAndMaintenanceCommentPoint monitorAndMaintenanceCommentPoint) {
        if (monitorAndMaintenanceList == null) {
            monitorAndMaintenanceList = new ArrayList<>();
        }
        monitorAndMaintenanceList.add(monitorAndMaintenanceCommentPoint);
    }

    public boolean hasPoints() {
        if (monitorAndMaintenanceList == null) {
            monitorAndMaintenanceList = new ArrayList<>();
        }
        return monitorAndMaintenanceList.size() > 0;
    }

    public List<MonitorAndMaintenanceCommentPoint> getPoints() {
        if (monitorAndMaintenanceList == null) {
            monitorAndMaintenanceList = new ArrayList<>();
        }
        return monitorAndMaintenanceList;
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean hasFillableForm() {
        //return jsonFeature != null && jsonFeature.toLowerCase().contains(STARD);
        return true;
    }

    public String getFormType() {
        if (jsonFeature.toLowerCase().contains(STARD)) {
            return STARD;
        } else if (jsonFeature.toLowerCase().contains(VEGETAL_MAINTENANCE)) {
            return VEGETAL_MAINTENANCE;
        }
        return "";
        //return PREDIO_FOLLOW_UP; // TODO switch case all form types
    }

    public MonitorAndMaintenance() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.procedureId);
        dest.writeString(this.dueDate);
        dest.writeInt(this.dueDateStatus);
        dest.writeParcelable(this.feature, flags);
        dest.writeString(this.jsonFeature);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
    }

    protected MonitorAndMaintenance(Parcel in) {
        super(in);
        this.id = in.readString();
        this.title = in.readString();
        this.procedureId = in.readString();
        this.dueDate = in.readString();
        this.dueDateStatus = in.readInt();
        this.feature = in.readParcelable(Feature.class.getClassLoader());
        this.jsonFeature = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
    }

    public static final Creator<MonitorAndMaintenance> CREATOR = new Creator<MonitorAndMaintenance>() {
        @Override
        public MonitorAndMaintenance createFromParcel(Parcel source) {
            return new MonitorAndMaintenance(source);
        }

        @Override
        public MonitorAndMaintenance[] newArray(int size) {
            return new MonitorAndMaintenance[size];
        }
    };
}
