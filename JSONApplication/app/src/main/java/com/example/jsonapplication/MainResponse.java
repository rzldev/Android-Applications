package com.example.jsonapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {
    @SerializedName("androidapi")
    @Expose
    private List<MainRequest> androidapi = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<MainRequest> getAndroidapi() {
        return androidapi;
    }

    public void setAndroidapi(List<MainRequest> androidapi) {
        this.androidapi = androidapi;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
