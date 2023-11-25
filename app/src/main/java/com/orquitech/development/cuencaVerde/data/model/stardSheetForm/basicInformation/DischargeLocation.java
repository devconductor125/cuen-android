package com.orquitech.development.cuencaVerde.data.model.stardSheetForm.basicInformation;

import android.os.Parcel;
import android.os.Parcelable;

public class DischargeLocation implements Parcelable {

    private String river;
    private String stream;
    private String sewer;
    private String soil;
    private String openSky;
    private String sink;
    private String other;

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getSewer() {
        return sewer;
    }

    public void setSewer(String sewer) {
        this.sewer = sewer;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getOpenSky() {
        return openSky;
    }

    public void setOpenSky(String openSky) {
        this.openSky = openSky;
    }

    public String getSink() {
        return sink;
    }

    public void setSink(String sink) {
        this.sink = sink;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.river);
        dest.writeString(this.stream);
        dest.writeString(this.sewer);
        dest.writeString(this.soil);
        dest.writeString(this.openSky);
        dest.writeString(this.sink);
        dest.writeString(this.other);
    }

    public DischargeLocation() {
    }

    protected DischargeLocation(Parcel in) {
        this.river = in.readString();
        this.stream = in.readString();
        this.sewer = in.readString();
        this.soil = in.readString();
        this.openSky = in.readString();
        this.sink = in.readString();
        this.other = in.readString();
    }

    public static final Creator<DischargeLocation> CREATOR = new Creator<DischargeLocation>() {
        @Override
        public DischargeLocation createFromParcel(Parcel source) {
            return new DischargeLocation(source);
        }

        @Override
        public DischargeLocation[] newArray(int size) {
            return new DischargeLocation[size];
        }
    };
}
