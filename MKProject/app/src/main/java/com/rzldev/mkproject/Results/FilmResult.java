package com.rzldev.mkproject.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilmResult {

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

}
