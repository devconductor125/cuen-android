package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.orquitech.development.cuencaVerde.logic.AppAccionesManager;

public class Action extends BaseDialogListItem implements Parcelable {

    private String accionType = "";
    private Material material;
    private String color;
    private String fillColor;
    private boolean tree;
    private boolean samplingPoint;
    private boolean onMeasurement;

    public String getAccionType() {
        return accionType;
    }

    public void setAccionType(String accionType) {
        this.accionType = accionType;
    }

    public String getColor() {
        if (accionType.equals(AppAccionesManager.AREAS)) {
            if (!TextUtils.isEmpty(fillColor) && fillColor.length() == 7) {
                String rawColor = fillColor.substring(1, fillColor.length());
                return fillColor = "#59" + rawColor;
            } else if (!TextUtils.isEmpty(fillColor) && fillColor.length() == 9) {
                return fillColor;
            }
        } else {
            return color;
        }
        return null;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public boolean isTree() {
        return tree;
    }

    public void setTree(boolean tree) {
        this.tree = tree;
    }

    public boolean isSamplingPoint() {
        return samplingPoint;
    }

    public void setSamplingPoint(boolean samplingPoint) {
        this.samplingPoint = samplingPoint;
    }

    public Action() {
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public boolean isOnMeasurement() {
        return onMeasurement;
    }

    public void setOnMeasurement(boolean onMeasurement) {
        this.onMeasurement = onMeasurement;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.id);
        dest.writeString(this.accionType);
        dest.writeParcelable(this.material, flags);
        dest.writeString(this.color);
        dest.writeString(this.fillColor);
        dest.writeByte(this.tree ? (byte) 1 : (byte) 0);
        dest.writeByte(this.samplingPoint ? (byte) 1 : (byte) 0);
        dest.writeByte(this.onMeasurement ? (byte) 1 : (byte) 0);
    }

    protected Action(Parcel in) {
        super(in);
        this.id = in.readString();
        this.accionType = in.readString();
        this.material = in.readParcelable(Material.class.getClassLoader());
        this.color = in.readString();
        this.fillColor = in.readString();
        this.tree = in.readByte() != 0;
        this.samplingPoint = in.readByte() != 0;
        this.onMeasurement = in.readByte() != 0;
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel source) {
            return new Action(source);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}
