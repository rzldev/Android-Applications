package com.rzldev.mkproject.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MostViewedResult {

    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("Sinopsis")
    @Expose
    private String sinopsis;
    @SerializedName("harga")
    @Expose
    private String harga;
    @SerializedName("durasi")
    @Expose
    private String durasi;
    @SerializedName("total_tiket")
    @Expose
    private String totalTiket;
    @SerializedName("total_pendapatan")
    @Expose
    private String totalPendapatan;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }

    public String getTotalTiket() {
        return totalTiket;
    }

    public void setTotalTiket(String totalTiket) {
        this.totalTiket = totalTiket;
    }

    public String getTotalPendapatan() {
        return totalPendapatan;
    }

    public void setTotalPendapatan(String totalPendapatan) {
        this.totalPendapatan = totalPendapatan;
    }

}
