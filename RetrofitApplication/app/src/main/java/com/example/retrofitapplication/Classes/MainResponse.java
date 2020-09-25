package com.example.retrofitapplication.Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MainResponse {
    @SerializedName("mahasiswa")
    @Expose
    private List<MainRequest> mahasiswa = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<MainRequest> getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(List<MainRequest> mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
