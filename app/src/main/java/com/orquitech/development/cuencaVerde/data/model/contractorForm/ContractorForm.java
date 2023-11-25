package com.orquitech.development.cuencaVerde.data.model.contractorForm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;

public class ContractorForm extends BaseItem implements Parcelable {

    private WorkForce workForce;
    private boolean pendingToBeSent;
    private String men;
    private String women;
    private AppDate startDate;
    private AppDate reportDate;
    private AppDate completionDate;
    private String barbedWire;
    private String plainWire;
    private String woodArms;
    private String seedlings;
    private AppDate sowingDate;
    private AppDate maintenanceDate;
    private String stardsNumber;
    private String spruesNumber;
    private String waterTanksNumber;
    private String comments;
    private String contractorSiembraId;

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(getContractorSiembraId()) && startDate != null && completionDate != null;
    }

    public void initNullFields() {
        if (this.workForce == null) {
            this.workForce = new WorkForce();
        }
    }

    public ContractorForm() {
        initNullFields();
    }

    public WorkForce getWorkForce() {
        return workForce;
    }

    public void setWorkForce(WorkForce workForce) {
        this.workForce = workForce;
    }

    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
    }

    public String getWomen() {
        return women;
    }

    public void setWomen(String women) {
        this.women = women;
    }

    public AppDate getStartDate() {
        return startDate;
    }

    public void setStartDate(AppDate startDate) {
        this.startDate = startDate;
    }

    public AppDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(AppDate reportDate) {
        this.reportDate = reportDate;
    }

    public AppDate getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(AppDate completionDate) {
        this.completionDate = completionDate;
    }

    public String getBarbedWire() {
        return barbedWire;
    }

    public void setBarbedWire(String barbedWire) {
        this.barbedWire = barbedWire;
    }

    public String getPlainWire() {
        return plainWire;
    }

    public void setPlainWire(String plainWire) {
        this.plainWire = plainWire;
    }

    public String getWoodArms() {
        return woodArms;
    }

    public void setWoodArms(String woodArms) {
        this.woodArms = woodArms;
    }

    public String getSeedlings() {
        return seedlings;
    }

    public void setSeedlings(String seedlings) {
        this.seedlings = seedlings;
    }

    public AppDate getSowingDate() {
        return sowingDate;
    }

    public void setSowingDate(AppDate sowingDate) {
        this.sowingDate = sowingDate;
    }

    public AppDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(AppDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getStardsNumber() {
        return stardsNumber;
    }

    public void setStardsNumber(String stardsNumber) {
        this.stardsNumber = stardsNumber;
    }

    public String getSpruesNumber() {
        return spruesNumber;
    }

    public void setSpruesNumber(String spruesNumber) {
        this.spruesNumber = spruesNumber;
    }

    public String getWaterTanksNumber() {
        return waterTanksNumber;
    }

    public void setWaterTanksNumber(String waterTanksNumber) {
        this.waterTanksNumber = waterTanksNumber;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getContractorSiembraId() {
        return contractorSiembraId;
    }

    public void setContractorSiembraId(String contractorSiembraId) {
        this.contractorSiembraId = contractorSiembraId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.workForce, flags);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
        dest.writeString(this.men);
        dest.writeString(this.women);
        dest.writeParcelable(this.startDate, flags);
        dest.writeParcelable(this.reportDate, flags);
        dest.writeParcelable(this.completionDate, flags);
        dest.writeString(this.barbedWire);
        dest.writeString(this.plainWire);
        dest.writeString(this.woodArms);
        dest.writeString(this.seedlings);
        dest.writeParcelable(this.sowingDate, flags);
        dest.writeParcelable(this.maintenanceDate, flags);
        dest.writeString(this.stardsNumber);
        dest.writeString(this.spruesNumber);
        dest.writeString(this.waterTanksNumber);
        dest.writeString(this.comments);
        dest.writeString(this.contractorSiembraId);
    }

    protected ContractorForm(Parcel in) {
        this.workForce = in.readParcelable(WorkForce.class.getClassLoader());
        this.pendingToBeSent = in.readByte() != 0;
        this.men = in.readString();
        this.women = in.readString();
        this.startDate = in.readParcelable(AppDate.class.getClassLoader());
        this.reportDate = in.readParcelable(AppDate.class.getClassLoader());
        this.completionDate = in.readParcelable(AppDate.class.getClassLoader());
        this.barbedWire = in.readString();
        this.plainWire = in.readString();
        this.woodArms = in.readString();
        this.seedlings = in.readString();
        this.sowingDate = in.readParcelable(AppDate.class.getClassLoader());
        this.maintenanceDate = in.readParcelable(AppDate.class.getClassLoader());
        this.stardsNumber = in.readString();
        this.spruesNumber = in.readString();
        this.waterTanksNumber = in.readString();
        this.comments = in.readString();
        this.contractorSiembraId = in.readString();
    }

    public static final Creator<ContractorForm> CREATOR = new Creator<ContractorForm>() {
        @Override
        public ContractorForm createFromParcel(Parcel source) {
            return new ContractorForm(source);
        }

        @Override
        public ContractorForm[] newArray(int size) {
            return new ContractorForm[size];
        }
    };
}
