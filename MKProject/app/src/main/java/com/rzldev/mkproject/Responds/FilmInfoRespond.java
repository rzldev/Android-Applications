package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.FilmInfoResult;

import java.util.List;

public class FilmInfoRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<FilmInfoResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<FilmInfoResult> getResult() {
        return result;
    }

    public void setResult(List<FilmInfoResult> result) {
        this.result = result;
    }


}
