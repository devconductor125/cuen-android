package com.orquitech.development.cuencaVerde.data.model.survey;

import android.os.Parcel;
import android.os.Parcelable;

public class NaturalEcosystemsInTheProperty implements Parcelable {

    private boolean riverbankForest;
    private boolean riverbankArea;
    private boolean mountainsideForest;
    private boolean spring;
    private String riverbankForestScore;
    private String riverbankAreaScore;
    private String mountainsideForestScore;
    private String springScore;
    private boolean protectedSource;
    private String protectedSourceComments;
    private boolean unProtected;
    private String unProtectedComments;
    private boolean contaminated;
    private String contaminatedComments;
    private boolean erosion;
    private String erosionComments;

    public boolean isRiverbankForest() {
        return riverbankForest;
    }

    public void setRiverbankForest(boolean riverbankForest) {
        this.riverbankForest = riverbankForest;
    }

    public String getRiverbankForestScore() {
        return riverbankForestScore;
    }

    public void setRiverbankForestScore(String riverbankForestScore) {
        this.riverbankForestScore = riverbankForestScore;
    }

    public String getRiverbankAreaScore() {
        return riverbankAreaScore;
    }

    public void setRiverbankAreaScore(String riverbankAreaScore) {
        this.riverbankAreaScore = riverbankAreaScore;
    }

    public String getMountainsideForestScore() {
        return mountainsideForestScore;
    }

    public void setMountainsideForestScore(String mountainsideForestScore) {
        this.mountainsideForestScore = mountainsideForestScore;
    }

    public String getSpringScore() {
        return springScore;
    }

    public void setSpringScore(String springScore) {
        this.springScore = springScore;
    }

    public boolean isRiverbankArea() {
        return riverbankArea;
    }

    public void setRiverbankArea(boolean riverbankArea) {
        this.riverbankArea = riverbankArea;
    }

    public boolean isMountainsideForest() {
        return mountainsideForest;
    }

    public void setMountainsideForest(boolean mountainsideForest) {
        this.mountainsideForest = mountainsideForest;
    }

    public boolean isSpring() {
        return spring;
    }

    public void setSpring(boolean spring) {
        this.spring = spring;
    }

    public boolean isProtectedSource() {
        return protectedSource;
    }

    public void setProtectedSource(boolean protectedSource) {
        this.protectedSource = protectedSource;
    }

    public String getProtectedSourceComments() {
        return protectedSourceComments;
    }

    public void setProtectedSourceComments(String protectedSourceComments) {
        this.protectedSourceComments = protectedSourceComments;
    }

    public boolean isUnProtected() {
        return unProtected;
    }

    public void setUnProtected(boolean unProtected) {
        this.unProtected = unProtected;
    }

    public String getUnProtectedComments() {
        return unProtectedComments;
    }

    public void setUnProtectedComments(String unProtectedComments) {
        this.unProtectedComments = unProtectedComments;
    }

    public boolean isContaminated() {
        return contaminated;
    }

    public void setContaminated(boolean contaminated) {
        this.contaminated = contaminated;
    }

    public String getContaminatedComments() {
        return contaminatedComments;
    }

    public void setContaminatedComments(String contaminatedComments) {
        this.contaminatedComments = contaminatedComments;
    }

    public boolean isErosion() {
        return erosion;
    }

    public void setErosion(boolean erosion) {
        this.erosion = erosion;
    }

    public String getErosionComments() {
        return erosionComments;
    }

    public void setErosionComments(String erosionComments) {
        this.erosionComments = erosionComments;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.riverbankForest ? (byte) 1 : (byte) 0);
        dest.writeByte(this.riverbankArea ? (byte) 1 : (byte) 0);
        dest.writeByte(this.mountainsideForest ? (byte) 1 : (byte) 0);
        dest.writeByte(this.spring ? (byte) 1 : (byte) 0);
        dest.writeString(this.riverbankForestScore);
        dest.writeString(this.riverbankAreaScore);
        dest.writeString(this.mountainsideForestScore);
        dest.writeString(this.springScore);
        dest.writeByte(this.protectedSource ? (byte) 1 : (byte) 0);
        dest.writeString(this.protectedSourceComments);
        dest.writeByte(this.unProtected ? (byte) 1 : (byte) 0);
        dest.writeString(this.unProtectedComments);
        dest.writeByte(this.contaminated ? (byte) 1 : (byte) 0);
        dest.writeString(this.contaminatedComments);
        dest.writeByte(this.erosion ? (byte) 1 : (byte) 0);
        dest.writeString(this.erosionComments);
    }

    public NaturalEcosystemsInTheProperty() {
    }

    protected NaturalEcosystemsInTheProperty(Parcel in) {
        this.riverbankForest = in.readByte() != 0;
        this.riverbankArea = in.readByte() != 0;
        this.mountainsideForest = in.readByte() != 0;
        this.spring = in.readByte() != 0;
        this.riverbankForestScore = in.readString();
        this.riverbankAreaScore = in.readString();
        this.mountainsideForestScore = in.readString();
        this.springScore = in.readString();
        this.protectedSource = in.readByte() != 0;
        this.protectedSourceComments = in.readString();
        this.unProtected = in.readByte() != 0;
        this.unProtectedComments = in.readString();
        this.contaminated = in.readByte() != 0;
        this.contaminatedComments = in.readString();
        this.erosion = in.readByte() != 0;
        this.erosionComments = in.readString();
    }

    public static final Creator<NaturalEcosystemsInTheProperty> CREATOR = new Creator<NaturalEcosystemsInTheProperty>() {
        @Override
        public NaturalEcosystemsInTheProperty createFromParcel(Parcel source) {
            return new NaturalEcosystemsInTheProperty(source);
        }

        @Override
        public NaturalEcosystemsInTheProperty[] newArray(int size) {
            return new NaturalEcosystemsInTheProperty[size];
        }
    };
}
