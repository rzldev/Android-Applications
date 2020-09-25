package com.rzldev.mkproject.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleResult {

    @SerializedName("waktu")
    @Expose
    private String waktu;

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

}
