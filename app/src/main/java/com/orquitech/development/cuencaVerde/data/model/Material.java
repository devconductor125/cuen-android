package com.orquitech.development.cuencaVerde.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Material extends BaseDialogListItem implements Parcelable {

    private String price;
    private String measurement;
    private String materialType;
    private String unit;
    private List<String> actions;

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public List<String> getActionIds() {
        return actions;
    }

    public void addActionId(String action) {
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
        this.actions.add(action);
    }

    public void setActionIds(List<String> actions) {
        if (this.actions == null) {
            this.actions = new ArrayList<>();
        }
        this.actions.addAll(actions);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Material() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.id);
        dest.writeString(this.price);
        dest.writeString(this.measurement);
        dest.writeString(this.materialType);
        dest.writeString(this.unit);
        dest.writeStringList(this.actions);
    }

    protected Material(Parcel in) {
        super(in);
        this.id = in.readString();
        this.price = in.readString();
        this.measurement = in.readString();
        this.materialType = in.readString();
        this.unit = in.readString();
        this.actions = in.createStringArrayList();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel source) {
            return new Material(source);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };
}
