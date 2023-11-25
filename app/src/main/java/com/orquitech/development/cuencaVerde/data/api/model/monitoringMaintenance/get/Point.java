package com.orquitech.development.cuencaVerde.data.api.model.monitoringMaintenance.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Point {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("coordinate")
    @Expose
    public String coordinate;
    @SerializedName("images")
    @Expose
    public List<Image> images = null;
    @SerializedName("comments")
    @Expose
    public List<Comment> comments = null;
}
