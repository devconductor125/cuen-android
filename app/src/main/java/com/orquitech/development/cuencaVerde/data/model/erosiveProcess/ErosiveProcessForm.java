package com.orquitech.development.cuencaVerde.data.model.erosiveProcess;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.data.model.AppDate;
import com.orquitech.development.cuencaVerde.data.model.BaseItem;
import com.orquitech.development.cuencaVerde.data.model.geoJson.Feature;

public class ErosiveProcessForm extends BaseItem implements Parcelable {

    private AppDate identificationDate;
    private String basin;
    private String municipality;
    private String lane;
    private String hydrologicalSource;
    private String status;
    private String comments;
    private boolean laFe;
    private boolean rioGrande;
    private Feature feature;
    private String hash;

    public AppDate getIdentificationDate() {
        return identificationDate;
    }

    public void setIdentificationDate(AppDate identificationDate) {
        this.identificationDate = identificationDate;
    }

    public String getBasin() {
        return basin;
    }

    public void setBasin(String basin) {
        this.basin = basin;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getHydrologicalSource() {
        return hydrologicalSource;
    }

    public void setHydrologicalSource(String hydrologicalSource) {
        this.hydrologicalSource = hydrologicalSource;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isLaFe() {
        return laFe;
    }

    public void setLaFe(boolean laFe) {
        this.laFe = laFe;
    }

    public boolean isRioGrande() {
        return rioGrande;
    }

    public void setRioGrande(boolean rioGrande) {
        this.rioGrande = rioGrande;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public boolean isValid() {
        return !TextUtils.isEmpty(getIdentificationDate().getString()) && (isLaFe() || isRioGrande());
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public ErosiveProcessForm() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeParcelable(this.identificationDate, flags);
        dest.writeString(this.basin);
        dest.writeString(this.municipality);
        dest.writeString(this.lane);
        dest.writeString(this.hydrologicalSource);
        dest.writeString(this.status);
        dest.writeString(this.comments);
        dest.writeByte(this.laFe ? (byte) 1 : (byte) 0);
        dest.writeByte(this.rioGrande ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.feature, flags);
        dest.writeString(this.hash);
    }

    protected ErosiveProcessForm(Parcel in) {
        this.id = in.readString();
        this.identificationDate = in.readParcelable(AppDate.class.getClassLoader());
        this.basin = in.readString();
        this.municipality = in.readString();
        this.lane = in.readString();
        this.hydrologicalSource = in.readString();
        this.status = in.readString();
        this.comments = in.readString();
        this.laFe = in.readByte() != 0;
        this.rioGrande = in.readByte() != 0;
        this.feature = in.readParcelable(Feature.class.getClassLoader());
        this.hash = in.readString();
    }

    public static final Creator<ErosiveProcessForm> CREATOR = new Creator<ErosiveProcessForm>() {
        @Override
        public ErosiveProcessForm createFromParcel(Parcel source) {
            return new ErosiveProcessForm(source);
        }

        @Override
        public ErosiveProcessForm[] newArray(int size) {
            return new ErosiveProcessForm[size];
        }
    };
}
