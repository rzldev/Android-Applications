package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.AdminHistoryResult;

import java.util.List;

public class AdminHistoryRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<AdminHistoryResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<AdminHistoryResult> getResult() {
        return result;
    }

    public void setResult(List<AdminHistoryResult> result) {
        this.result = result;
    }

}
