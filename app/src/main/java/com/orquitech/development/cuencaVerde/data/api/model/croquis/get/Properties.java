package com.orquitech.development.cuencaVerde.data.api.model.croquis.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("description")
    @Expose
    public Object description;
    @SerializedName("altitudeMode")
    @Expose
    public String altitudeMode;
    @SerializedName("SECTOR")
    @Expose
    public String sECTOR;
    @SerializedName("OBJECTID_1")
    @Expose
    public String oBJECTID1;
    @SerializedName("TIPO")
    @Expose
    public String tIPO;
    @SerializedName("NUMERORADI")
    @Expose
    public String nUMERORADI;
    @SerializedName("CORREGIMIE")
    @Expose
    public String cORREGIMIE;
    @SerializedName("TOTAL_UNID")
    @Expose
    public String tOTALUNID;
    @SerializedName("PK_PREDIOS")
    @Expose
    public String pKPREDIOS;
    @SerializedName("MANZANA_VE")
    @Expose
    public String mANZANAVE;
    @SerializedName("PREDIOS")
    @Expose
    public String pREDIOS;
    @SerializedName("BARRIO")
    @Expose
    public String bARRIO;
    @SerializedName("MUNICIPIO")
    @Expose
    public String mUNICIPIO;
}
