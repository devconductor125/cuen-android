package com.orquitech.development.cuencaVerde.data.model.geoJson;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeatureProperties implements Parcelable {

    public static final String COMMENT = "comentario";
    public static final String TREE = "Árbol sin especie";

    @SerializedName("OBJECTID")
    @Expose
    public int objectId;
    @SerializedName("AccionId")
    @Expose
    private String accionId;
    @SerializedName("ACCIONES")
    @Expose
    public String accionesTitle;
    @SerializedName("ACCIONES_P")
    @Expose
    public String accionesPTitle;
    @SerializedName("Pol")
    @Expose
    public String pol;
    @SerializedName("MaterialId")
    @Expose
    public String materialId;
    @SerializedName("Material")
    @Expose
    public String materialTitle;
    @SerializedName("Color")
    @Expose
    public String color;
    @SerializedName("FillColor")
    @Expose
    public String fillColor;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("feature_type")
    @Expose
    private String featureType;
    @SerializedName("CUENCA")
    @Expose
    public String cUENCA;
    @SerializedName("COD_MUNICI")
    @Expose
    public String cODMUNICI;
    @SerializedName("MUNICIPIO")
    @Expose
    public String mUNICIPIO;
    @SerializedName("VEREDA")
    @Expose
    public String vEREDA;
    @SerializedName("NOMBRE_PRE")
    @Expose
    public String nOMBREPRE;
    @SerializedName("MICROCUENC")
    @Expose
    public String mICROCUENC;
    @SerializedName("ESTADO_GP")
    @Expose
    public String eSTADOGP;
    @SerializedName("SOCIO_COLA")
    @Expose
    public String sOCIOCOLA;
    @SerializedName("A\u00d1O_ACUER")
    @Expose
    public String aOACUER;
    @SerializedName("PK_PREDIO")
    @Expose
    public String pKPREDIO;
    @SerializedName("FECHA_INGR")
    @Expose
    public String fECHAINGR;
    @SerializedName("ID_PREDIO")
    @Expose
    public Double iDPREDIO;
    @SerializedName("X")
    @Expose
    public Double x;
    @SerializedName("Y")
    @Expose
    public Double y;
    @SerializedName("CONTRATO")
    @Expose
    public String cONTRATO;
    @SerializedName("Shape_Leng")
    @Expose
    public double shapeLength;
    @SerializedName("Shape_Area")
    @Expose
    public double shapeArea;
    @SerializedName("AREA_HA")
    @Expose
    public double areaHa;
    @SerializedName("POLIGONO")
    @Expose
    public double POLIGONO;
    @SerializedName("TIPO_ALAMBRE")
    @Expose
    public String tipoAlambre;
    @SerializedName("TIPO_ALAMB")
    @Expose
    public String tipoAlamb;
    @SerializedName("TIPO_CERCO_VIVO")
    @Expose
    public String tipoCercoVivo;
    @SerializedName("LONGITUD_M")
    @Expose
    public double longitudM;
    @SerializedName("comment")
    @Expose
    public String comment;
    @SerializedName("tree")
    @Expose
    public boolean tree;
    @SerializedName("samplingPoint")
    @Expose
    public boolean samplingPoint;

    public FeatureProperties() {
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getAccionTitle() {
        return accionesTitle;
    }

    public void setAccionTitle(String acciones) {
        this.accionesTitle = acciones;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public double getShapeLength() {
        return shapeLength;
    }

    public void setShapeLength(double shapeLength) {
        this.shapeLength = shapeLength;
    }

    public double getShapeArea() {
        return shapeArea;
    }

    public void setShapeArea(double shapeArea) {
        this.shapeArea = shapeArea;
    }

    public String getAccionesTitle() {
        if (isComment()) {
            return COMMENT;
        } else if (isTree()) {
            return TREE;
        } else {
            return accionesTitle;
        }
    }

    public boolean isComment() {
        return accionesTitle.contains("Comentario");
    }

    public void setAccionesTitle(String accionesTitle) {
        this.accionesTitle = accionesTitle;
    }

    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFillColor() {
        if (!TextUtils.isEmpty(fillColor) && fillColor.length() == 7) {
            String rawColor = fillColor.substring(1, fillColor.length());
            fillColor = "#59" + rawColor;
        } else {
            if (TextUtils.isEmpty(fillColor)) {
                fillColor = "#59000000";
            }
        }
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getAccionId() {
        if (TextUtils.isEmpty(accionId)) {
            accionId = "-1";
        }
        return accionId;
    }

    public void setAccionId(String accionId) {
        this.accionId = accionId;
    }

    public String getMaterialTitle() {
        if (isComment()) {
            return accionesTitle.substring(accionesTitle.indexOf(' ') + 1);
        } else {
            return materialTitle != null ? materialTitle : "N/A";
        }
    }

    public void setMaterialTitle(String materialTitle) {
        this.materialTitle = materialTitle;
    }

    public String getFeatureType() {
        if (TextUtils.isEmpty(featureType)) {
            featureType = "";
        }
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public String getHash() {
        return hash;
    }

    public void setAccionHash(String hash) {
        this.hash = hash;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isTree() {
        return tree;
    }

    public void setAsTree() {
        this.tree = true;
    }

    public boolean isSamplingPoint() {
        return samplingPoint;
    }

    public void setSamplingPoint(boolean samplingPoint) {
        this.samplingPoint = samplingPoint;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.objectId);
        dest.writeString(this.accionId);
        dest.writeString(this.accionesTitle);
        dest.writeString(this.pol);
        dest.writeString(this.materialId);
        dest.writeString(this.materialTitle);
        dest.writeString(this.color);
        dest.writeString(this.fillColor);
        dest.writeString(this.hash);
        dest.writeString(this.featureType);
        dest.writeString(this.cUENCA);
        dest.writeString(this.cODMUNICI);
        dest.writeString(this.mUNICIPIO);
        dest.writeString(this.vEREDA);
        dest.writeString(this.nOMBREPRE);
        dest.writeString(this.mICROCUENC);
        dest.writeString(this.eSTADOGP);
        dest.writeString(this.sOCIOCOLA);
        dest.writeString(this.aOACUER);
        dest.writeString(this.pKPREDIO);
        dest.writeString(this.fECHAINGR);
        dest.writeValue(this.iDPREDIO);
        dest.writeValue(this.x);
        dest.writeValue(this.y);
        dest.writeString(this.cONTRATO);
        dest.writeDouble(this.shapeLength);
        dest.writeDouble(this.shapeArea);
        dest.writeDouble(this.areaHa);
        dest.writeDouble(this.POLIGONO);
        dest.writeString(this.tipoAlambre);
        dest.writeString(this.tipoCercoVivo);
        dest.writeDouble(this.longitudM);
        dest.writeString(this.comment);
        dest.writeByte(this.tree ? (byte) 1 : (byte) 0);
        dest.writeByte(this.samplingPoint ? (byte) 1 : (byte) 0);
    }

    protected FeatureProperties(Parcel in) {
        this.objectId = in.readInt();
        this.accionId = in.readString();
        this.accionesTitle = in.readString();
        this.pol = in.readString();
        this.materialId = in.readString();
        this.materialTitle = in.readString();
        this.color = in.readString();
        this.fillColor = in.readString();
        this.hash = in.readString();
        this.featureType = in.readString();
        this.cUENCA = in.readString();
        this.cODMUNICI = in.readString();
        this.mUNICIPIO = in.readString();
        this.vEREDA = in.readString();
        this.nOMBREPRE = in.readString();
        this.mICROCUENC = in.readString();
        this.eSTADOGP = in.readString();
        this.sOCIOCOLA = in.readString();
        this.aOACUER = in.readString();
        this.pKPREDIO = in.readString();
        this.fECHAINGR = in.readString();
        this.iDPREDIO = (Double) in.readValue(Double.class.getClassLoader());
        this.x = (Double) in.readValue(Double.class.getClassLoader());
        this.y = (Double) in.readValue(Double.class.getClassLoader());
        this.cONTRATO = in.readString();
        this.shapeLength = in.readDouble();
        this.shapeArea = in.readDouble();
        this.areaHa = in.readDouble();
        this.POLIGONO = in.readDouble();
        this.tipoAlambre = in.readString();
        this.tipoCercoVivo = in.readString();
        this.longitudM = in.readDouble();
        this.comment = in.readString();
        this.tree = in.readByte() != 0;
        this.samplingPoint = in.readByte() != 0;
    }

    public static final Creator<FeatureProperties> CREATOR = new Creator<FeatureProperties>() {
        @Override
        public FeatureProperties createFromParcel(Parcel source) {
            return new FeatureProperties(source);
        }

        @Override
        public FeatureProperties[] newArray(int size) {
            return new FeatureProperties[size];
        }
    };
}
