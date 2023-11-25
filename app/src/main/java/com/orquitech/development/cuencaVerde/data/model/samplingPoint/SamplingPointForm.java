package com.orquitech.development.cuencaVerde.data.model.samplingPoint;

import android.os.Parcel;
import android.os.Parcelable;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;

public class SamplingPointForm extends BaseItem implements Parcelable {

    private boolean pendingToBeSent;
    private String comments;
    private String taskId;
    private String commonName;
    private String monitoringNumber;
    private String pointClassification;
    private String monitoringComponent;
    private String monitoringScale;
    private String sampleId;
    private Feature feature;
    private String hour;
    private String phUOfPH;
    private String waterTemperature;
    private String od;
    private String satOd;
    private String conductivity;
    private String sdt;
    private String roomTemperature;
    private String turbidity;
    private String redoxPotential;
    private String altitude;
    private String hash;

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean isValid() {
        return true;
    }

    public void setPendingToBeSent(boolean pendingToBeSent) {
        this.pendingToBeSent = pendingToBeSent;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getMonitoringNumber() {
        return monitoringNumber;
    }

    public void setMonitoringNumber(String monitoringNumber) {
        this.monitoringNumber = monitoringNumber;
    }

    public String getPointClassification() {
        return pointClassification;
    }

    public void setPointClassification(String pointClassification) {
        this.pointClassification = pointClassification;
    }

    public String getMonitoringComponent() {
        return monitoringComponent;
    }

    public void setMonitoringComponent(String monitoringComponent) {
        this.monitoringComponent = monitoringComponent;
    }

    public String getMonitoringScale() {
        return monitoringScale;
    }

    public void setMonitoringScale(String monitoringScale) {
        this.monitoringScale = monitoringScale;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPhUOfPH() {
        return phUOfPH;
    }

    public void setPhUOfPH(String phUOfPH) {
        this.phUOfPH = phUOfPH;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getOd() {
        return od;
    }

    public void setOd(String od) {
        this.od = od;
    }

    public String getSatOd() {
        return satOd;
    }

    public void setSatOd(String satOd) {
        this.satOd = satOd;
    }

    public String getConductivity() {
        return conductivity;
    }

    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getRoomTemperature() {
        return roomTemperature;
    }

    public void setRoomTemperature(String roomTemperature) {
        this.roomTemperature = roomTemperature;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public String getRedoxPotential() {
        return redoxPotential;
    }

    public void setRedoxPotential(String redoxPotential) {
        this.redoxPotential = redoxPotential;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public SamplingPointForm() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
        dest.writeString(this.comments);
        dest.writeString(this.taskId);
        dest.writeString(this.commonName);
        dest.writeString(this.monitoringNumber);
        dest.writeString(this.pointClassification);
        dest.writeString(this.monitoringComponent);
        dest.writeString(this.monitoringScale);
        dest.writeString(this.sampleId);
        dest.writeParcelable(this.feature, flags);
        dest.writeString(this.hour);
        dest.writeString(this.phUOfPH);
        dest.writeString(this.waterTemperature);
        dest.writeString(this.od);
        dest.writeString(this.satOd);
        dest.writeString(this.conductivity);
        dest.writeString(this.sdt);
        dest.writeString(this.roomTemperature);
        dest.writeString(this.turbidity);
        dest.writeString(this.redoxPotential);
        dest.writeString(this.altitude);
        dest.writeString(this.hash);
    }

    protected SamplingPointForm(Parcel in) {
        this.pendingToBeSent = in.readByte() != 0;
        this.comments = in.readString();
        this.taskId = in.readString();
        this.commonName = in.readString();
        this.monitoringNumber = in.readString();
        this.pointClassification = in.readString();
        this.monitoringComponent = in.readString();
        this.monitoringScale = in.readString();
        this.sampleId = in.readString();
        this.feature = in.readParcelable(Feature.class.getClassLoader());
        this.hour = in.readString();
        this.phUOfPH = in.readString();
        this.waterTemperature = in.readString();
        this.od = in.readString();
        this.satOd = in.readString();
        this.conductivity = in.readString();
        this.sdt = in.readString();
        this.roomTemperature = in.readString();
        this.turbidity = in.readString();
        this.redoxPotential = in.readString();
        this.altitude = in.readString();
        this.hash = in.readString();
    }

    public static final Creator<SamplingPointForm> CREATOR = new Creator<SamplingPointForm>() {
        @Override
        public SamplingPointForm createFromParcel(Parcel source) {
            return new SamplingPointForm(source);
        }

        @Override
        public SamplingPointForm[] newArray(int size) {
            return new SamplingPointForm[size];
        }
    };
}
