package com.rzldev.mkproject.Responds;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.PlayingResult;

public class PlayingRespond {

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("result")
    @Expose
    private List<PlayingResult> result = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<PlayingResult> getResult() {
        return result;
    }

    public void setResult(List<PlayingResult> result) {
        this.result = result;
    }

}