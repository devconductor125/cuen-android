package com.orquitech.development.cuencaVerde.data.model.stardSheetForm;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation.BasicInformation;

public class StardSheetForm extends BaseItem implements Parcelable {

    private GeneralData generalData;
    private BasicInformation basicInformation;
    private boolean pendingToBeSent;
    private boolean isValidObject;
    private String comments;

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }

    public BasicInformation getBasicInformation() {
        return basicInformation;
    }

    public void setBasicInformation(BasicInformation basicInformation) {
        this.basicInformation = basicInformation;
    }

    public boolean isPendingToBeSent() {
        return pendingToBeSent;
    }

    public void setAsPendingToBeSent() {
        this.pendingToBeSent = true;
    }

    public boolean isValidObject() {
        return isValidObject;
    }

    public void setValidObject(boolean validObject) {
        isValidObject = validObject;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(generalData.getMunicipality()) &&
                !TextUtils.isEmpty(generalData.getPropertyName());
    }

    public boolean isPart1Valid() {
        return !TextUtils.isEmpty(generalData.getMunicipality()) &&
                !TextUtils.isEmpty(generalData.getPropertyName());
    }

    public boolean isPart2Valid() {
        return true;
    }

    public void initNullFields() {
        if (this.generalData == null) {
            this.generalData = new GeneralData();
        }
        if (this.basicInformation == null) {
            this.basicInformation = new BasicInformation();
        }
        this.isValidObject = true;
    }

    public StardSheetForm() {
        initNullFields();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.generalData, flags);
        dest.writeParcelable(this.basicInformation, flags);
        dest.writeByte(this.pendingToBeSent ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isValidObject ? (byte) 1 : (byte) 0);
        dest.writeString(this.comments);
    }

    protected StardSheetForm(Parcel in) {
        this.id = in.readString();
        this.generalData = in.readParcelable(GeneralData.class.getClassLoader());
        this.basicInformation = in.readParcelable(BasicInformation.class.getClassLoader());
        this.pendingToBeSent = in.readByte() != 0;
        this.isValidObject = in.readByte() != 0;
        this.comments = in.readString();
    }

    public static final Creator<StardSheetForm> CREATOR = new Creator<StardSheetForm>() {
        @Override
        public StardSheetForm createFromParcel(Parcel source) {
            return new StardSheetForm(source);
        }

        @Override
        public StardSheetForm[] newArray(int size) {
            return new StardSheetForm[size];
        }
    };
}
