package com.example.mlakumlaku.Responses;

import com.example.mlakumlaku.Requests.WisataRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WisataResponse {
    @SerializedName("wisata")
    @Expose
    private List<WisataRequest> wisata = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<WisataRequest> getWisata() {
        return wisata;
    }

    public void setWisata(List<WisataRequest> wisata) {
        this.wisata = wisata;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
