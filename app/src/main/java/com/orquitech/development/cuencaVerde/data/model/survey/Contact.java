package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {

    private String contactName;
    private String contactIdCardNumber;
    private String contactLandLineNumber;
    private String contactMobileNumber;
    private String contactEmail;

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactIdCardNumber() {
        return contactIdCardNumber;
    }

    public void setContactIdCardNumber(String contactIdCardNumber) {
        this.contactIdCardNumber = contactIdCardNumber;
    }

    public String getContactLandLineNumber() {
        return contactLandLineNumber;
    }

    public void setContactLandLineNumber(String contactLandLineNumber) {
        this.contactLandLineNumber = contactLandLineNumber;
    }

    public String getContactMobileNumber() {
        return contactMobileNumber;
    }

    public void setContactMobileNumber(String contactMobileNumber) {
        this.contactMobileNumber = contactMobileNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public boolean isValid() {
        // return FormUtils.isEmailValid(contactEmail);
        return true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contactName);
        dest.writeString(this.contactIdCardNumber);
        dest.writeString(this.contactLandLineNumber);
        dest.writeString(this.contactMobileNumber);
        dest.writeString(this.contactEmail);
    }

    public Contact() {
    }

    protected Contact(Parcel in) {
        this.contactName = in.readString();
        this.contactIdCardNumber = in.readString();
        this.contactLandLineNumber = in.readString();
        this.contactMobileNumber = in.readString();
        this.contactEmail = in.readString();
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
