package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class ContractorEvaluation implements Parcelable {

    private String id;
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
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean hasValidComments() {
        /*return !TextUtils.isEmpty(comments);*/
        return true;
    }

    public ContractorEvaluation() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.monitorAndMaintenanceId);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
    }

    protected ContractorEvaluation(Parcel in) {
        this.id = in.readString();
        this.monitorAndMaintenanceId = in.readString();
        this.pendingToBeSent = in.readByte() != 0;
    }

    public static final Creator<ContractorEvaluation> CREATOR = new Creator<ContractorEvaluation>() {
        @Override
        public ContractorEvaluation createFromParcel(Parcel source) {
            return new ContractorEvaluation(source);
        }

        @Override
        public ContractorEvaluation[] newArray(int size) {
            return new ContractorEvaluation[size];
        }
    };
}
