package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.ProfileResult;

import java.util.List;

import javax.xml.transform.Result;

public class ProfileRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<ProfileResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ProfileResult> getResult() {
        return result;
    }

    public void setResult(List<ProfileResult> result) {
        this.result = result;
    }

}
