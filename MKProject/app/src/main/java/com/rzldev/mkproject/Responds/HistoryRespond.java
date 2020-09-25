package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.HistoryResult;

import java.util.List;

public class HistoryRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<HistoryResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<HistoryResult> getResult() {
        return result;
    }

    public void setResult(List<HistoryResult> result) {
        this.result = result;
    }

}
