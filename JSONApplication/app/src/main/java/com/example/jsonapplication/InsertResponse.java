package com.example.jsonapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsertResponse {
    @SerializedName("success")
    @Expose
    private Integer success;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
