package com.rzldev.mkproject.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilmInfoResult {

    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("total")
    @Expose
    private String total;
    @SerializedName("total_tiket")
    @Expose
    private String totalTiket;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalTiket() {
        return totalTiket;
    }

    public void setTotalTiket(String totalTiket) {
        this.totalTiket = totalTiket;
    }

}
