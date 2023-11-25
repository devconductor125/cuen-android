package com.orquitech.development.cuencaVerde.data.model;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.utils.LocationUtils;
import com.google.android.gms.maps.model.LatLng;

public class MonitorAndMaintenanceCommentPoint extends BaseItem implements Parcelable {

    private String monitorAndMaintenanceId;
    private String comment;
    private Location location;
    private boolean pendingForSave;
    private boolean isNew;
    private boolean fromService;

    public String getMonitorAndMaintenanceId() {
        return monitorAndMaintenanceId;
    }

    public void setMonitorAndMaintenanceId(String monitorAndMaintenanceId) {
        this.monitorAndMaintenanceId = monitorAndMaintenanceId;
    }

    public String getComment() {
        if (TextUtils.isEmpty(comment)) {
            comment = "";
        }
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Location getLocation() {
        return location;
    }

    public LatLng getLatLngPosition() {
        return LocationUtils.locationToLatLng(location);
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPendingForSave() {
        pendingForSave = true;
    }

    public boolean isNotGoingToBeSaved() {
        return !pendingForSave;
    }

    public void setIsNew() {
        this.isNew = true;
    }

    public boolean isNew() {
        return isNew;
    }

    public boolean isFromService() {
        return fromService;
    }

    public void setFromService() {
        fromService = true;
    }

    public String getCleanedId() {
        String result;
        if (getId().contains("monitoreo_")) {
            result =  getId().substring(getId().length() - 1, getId().length());
        } else if (getId().contains("execution_")) {
            result =  getId().substring(getId().length() - 1, getId().length());
        } else {
            result =  getId();
        }
        return result;
    }

    public MonitorAndMaintenanceCommentPoint() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.monitorAndMaintenanceId);
        dest.writeString(this.comment);
        dest.writeParcelable(this.location, flags);
        dest.writeByte(this.fromService ? (byte) 1 : (byte) 0);
    }

    protected MonitorAndMaintenanceCommentPoint(Parcel in) {
        this.id = in.readString();
        this.monitorAndMaintenanceId = in.readString();
        this.comment = in.readString();
        this.location = in.readParcelable(Location.class.getClassLoader());
        this.fromService = in.readByte() != 0;
    }

    public static final Creator<MonitorAndMaintenanceCommentPoint> CREATOR = new Creator<MonitorAndMaintenanceCommentPoint>() {
        @Override
        public MonitorAndMaintenanceCommentPoint createFromParcel(Parcel source) {
            return new MonitorAndMaintenanceCommentPoint(source);
        }

        @Override
        public MonitorAndMaintenanceCommentPoint[] newArray(int size) {
            return new MonitorAndMaintenanceCommentPoint[size];
        }
    };
}
