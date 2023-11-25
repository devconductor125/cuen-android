package com.orquitech.development.cuencaVerde.data.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuencaVerdeResponse {

    @SerializedName("code")
    @Expose
    public int code;
    @SerializedName("data")
    @Expose
    public String data;
    @SerializedName("response_code")
    @Expose
    public int responseCode;
    @SerializedName("errors")
    @Expose
    public String errors;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
