package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ContractSiembra extends Task implements Parcelable {

    private AppDate startingDate;
    private AppDate reportDate;
    private AppDate completionDate;
    private boolean pendingToBeSent;

    public boolean isValid() {
        return startingDate != null;
    }

    public void initNullFields() {
        /*if (this.process == null) {
            this.process = new Process();
        }*/
    }

    public void setAsPendingToBeSent() {
        pendingToBeSent = true;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public AppDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(AppDate startingDate) {
        this.startingDate = startingDate;
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

    public void setPendingToBeSent(boolean pendingToBeSent) {
        this.pendingToBeSent = pendingToBeSent;
    }

    public ContractSiembra() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(this.startingDate, flags);
        dest.writeParcelable(this.reportDate, flags);
        dest.writeParcelable(this.completionDate, flags);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
    }

    protected ContractSiembra(Parcel in) {
        super(in);
        this.startingDate = in.readParcelable(AppDate.class.getClassLoader());
        this.reportDate = in.readParcelable(AppDate.class.getClassLoader());
        this.completionDate = in.readParcelable(AppDate.class.getClassLoader());
        this.pendingToBeSent = in.readByte() != 0;
    }

    public static final Creator<ContractSiembra> CREATOR = new Creator<ContractSiembra>() {
        @Override
        public ContractSiembra createFromParcel(Parcel source) {
            return new ContractSiembra(source);
        }

        @Override
        public ContractSiembra[] newArray(int size) {
            return new ContractSiembra[size];
        }
    };
}
