package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Feature implements Parcelable {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("properties")
    @Expose
    public FeatureProperties properties;

    public Feature() {
    }

    public String getType() {
        return type;
    }

    public FeatureProperties getProperties() {
        if (properties == null) {
            properties = new FeatureProperties();
        }
        return properties;
    }

    public void setProperties(FeatureProperties properties) {
        this.properties = properties;
    }

    public boolean isFromManagementLayer() {
        return !TextUtils.isEmpty(getProperties().eSTADOGP);
    }

    public boolean isTree() {
        return getProperties().isTree();
    }

    public boolean isSamplingPoint() {
        return getProperties().isSamplingPoint();
    }

    public void setManagementLayerColors() {
        if (getProperties() != null) {
            switch (getProperties().eSTADOGP) {
                case "EJECUTADO":
                    getProperties().setColor("#006100");
                    getProperties().setFillColor("#40006100");
                    break;
                case "EN EJECUCION":
                    getProperties().setColor("#498A00");
                    getProperties().setFillColor("#40498A00");
                    break;
                case "ACUERDO":
                    getProperties().setColor("#8BB500");
                    getProperties().setFillColor("#408BB500");
                    break;
                case "MEDIDO":
                    getProperties().setColor("#D6E600");
                    getProperties().setFillColor("#40D6E600");
                    break;
                case "CARTA DE INTENCION":
                    getProperties().setColor("#FFE500");
                    getProperties().setFillColor("#40FFE500");
                    break;
                case "ENCUESTADO":
                    getProperties().setColor("#FFA600");
                    getProperties().setFillColor("#40FFA600");
                    break;
                case "DESISTIO":
                    getProperties().setColor("#FF6F00");
                    getProperties().setFillColor("#40FF6F00");
                    break;
                case "RETIRADO":
                    getProperties().setColor("#FF2200");
                    getProperties().setFillColor("#40FF2200");
                    break;
            }
        }
    }

    public boolean isComment() {
        boolean isComment = false;
        FeatureProperties properties = getProperties();
        if (properties != null) {
            isComment = properties.isComment();
        }
        return isComment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeParcelable(this.properties, flags);
    }

    protected Feature(Parcel in) {
        this.type = in.readString();
        this.properties = in.readParcelable(FeatureProperties.class.getClassLoader());
    }

    public static final Creator<Feature> CREATOR = new Creator<Feature>() {
        @Override
        public Feature createFromParcel(Parcel source) {
            return new Feature(source);
        }

        @Override
        public Feature[] newArray(int size) {
            return new Feature[size];
        }
    };
}
