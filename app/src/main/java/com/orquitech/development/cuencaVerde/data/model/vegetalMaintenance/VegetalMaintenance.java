package com.orquitech.development.cuencaVerde.data.model.vegetalMaintenance;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.survey.Contact;

public class VegetalMaintenance implements Parcelable {

    private String id;
    private String propertyName;
    private Contact contact;
    private String lane;
    private String municipality;
    private String contractNumber;
    private String contractor;
    private ExecutedActions executedActions;
    private AppDate executionStartDate;
    private AppDate executionEndDate;
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public ExecutedActions getExecutedActions() {
        return executedActions;
    }

    public void setExecutedActions(ExecutedActions executedActions) {
        this.executedActions = executedActions;
    }

    public AppDate getExecutionStartDate() {
        return executionStartDate;
    }

    public void setExecutionStartDate(AppDate executionStartDate) {
        this.executionStartDate = executionStartDate;
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

    public boolean isNew() {
        return Integer.parseInt(getId()) <= 0;
    }

    public void initNullFields() {
        if (contact == null) {
            setContact(new Contact());
        }
        if (executedActions == null) {
            setExecutedActions(new ExecutedActions());
        }
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean hasValidName() {
        return !TextUtils.isEmpty(getContact().getContactName());
    }

    public boolean hasValidPredioName() {
        return !TextUtils.isEmpty(getPropertyName());
    }

    public VegetalMaintenance() {
        setContact(new Contact());
        setExecutedActions(new ExecutedActions());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.propertyName);
        dest.writeParcelable(this.contact, flags);
        dest.writeString(this.lane);
        dest.writeString(this.municipality);
        dest.writeString(this.contractNumber);
        dest.writeString(this.contractor);
        dest.writeParcelable(this.executedActions, flags);
        dest.writeParcelable(this.executionStartDate, flags);
        dest.writeParcelable(this.executionEndDate, flags);
        dest.writeString(this.monitorAndMaintenanceId);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
    }

    protected VegetalMaintenance(Parcel in) {
        this.id = in.readString();
        this.propertyName = in.readString();
        this.contact = in.readParcelable(Contact.class.getClassLoader());
        this.lane = in.readString();
        this.municipality = in.readString();
        this.contractNumber = in.readString();
        this.contractor = in.readString();
        this.executedActions = in.readParcelable(ExecutedActions.class.getClassLoader());
        this.executionStartDate = in.readParcelable(AppDate.class.getClassLoader());
        this.executionEndDate = in.readParcelable(AppDate.class.getClassLoader());
        this.monitorAndMaintenanceId = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
    }

    public static final Creator<VegetalMaintenance> CREATOR = new Creator<VegetalMaintenance>() {
        @Override
        public VegetalMaintenance createFromParcel(Parcel source) {
            return new VegetalMaintenance(source);
        }

        @Override
        public VegetalMaintenance[] newArray(int size) {
            return new VegetalMaintenance[size];
        }
    };
}
