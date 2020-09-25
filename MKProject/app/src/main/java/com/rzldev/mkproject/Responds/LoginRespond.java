package com.rzldev.mkproject.Responds;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rzldev.mkproject.Results.LoginResult;

import java.util.List;

public class LoginRespond {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("saldo")
    @Expose
    private String saldo;
    @SerializedName("telp")
    @Expose
    private String telp;
    @SerializedName("result")
    @Expose
    private List<LoginResult> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public List<LoginResult> getResult() {
        return result;
    }

    public void setResult(List<LoginResult> result) {
        this.result = result;
    }

}
