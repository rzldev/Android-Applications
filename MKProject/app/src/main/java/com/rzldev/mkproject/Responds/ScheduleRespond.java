package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.ScheduleResult;

import java.util.List;

public class ScheduleRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<ScheduleResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ScheduleResult> getResult() {
        return result;
    }

    public void setResult(List<ScheduleResult> result) {
        this.result = result;
    }

}
