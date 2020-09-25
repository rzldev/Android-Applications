package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.MostViewedResult;

import java.util.List;

public class MostViewedRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<MostViewedResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<MostViewedResult> getResult() {
        return result;
    }

    public void setResult(List<MostViewedResult> result) {
        this.result = result;
    }

}
