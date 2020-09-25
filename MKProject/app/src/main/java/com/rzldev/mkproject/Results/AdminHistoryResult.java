package com.rzldev.mkproject.Results;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminHistoryResult {

    @SerializedName("kd_history")
    @Expose
    private String kdHistory;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("judul")
    @Expose
    private String judul;
    @SerializedName("tiket_dibeli")
    @Expose
    private String tiketDibeli;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("waktu")
    @Expose
    private String waktu;
    @SerializedName("total")
    @Expose
    private String total;

    public String getKdHistory() {
        return kdHistory;
    }

    public void setKdHistory(String kdHistory) {
        this.kdHistory = kdHistory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTiketDibeli() {
        return tiketDibeli;
    }

    public void setTiketDibeli(String tiketDibeli) {
        this.tiketDibeli = tiketDibeli;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
