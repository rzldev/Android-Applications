package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.FilmResult;

import java.util.List;

public class FilmRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<FilmResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<FilmResult> getResult() {
        return result;
    }

    public void setResult(List<FilmResult> result) {
        this.result = result;
    }

}
