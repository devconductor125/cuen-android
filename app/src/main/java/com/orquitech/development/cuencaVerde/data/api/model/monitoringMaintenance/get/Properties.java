
package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties {

    @SerializedName("AccionId")
    @Expose
    public String accionId;
    @SerializedName("Acciones")
    @Expose
    public String acciones;
    @SerializedName("Color")
    @Expose
    public String color;
    @SerializedName("hash")
    @Expose
    public String hash;
    @SerializedName("MaterialId")
    @Expose
    public Object materialId;
    @SerializedName("Material")
    @Expose
    public Object material;
    @SerializedName("OBJECTID")
    @Expose
    public Integer oBJECTID;
    @SerializedName("Pol")
    @Expose
    public Object pol;
    @SerializedName("SHAPE_Area")
    @Expose
    public Integer sHAPEArea;
    @SerializedName("SHAPE_Leng")
    @Expose
    public Integer sHAPELeng;

}
