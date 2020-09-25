package com.example.mlakumlaku.Responses;

import com.example.mlakumlaku.Requests.LoginRequest;
import com.example.mlakumlaku.Requests.UserRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {
    @SerializedName("user")
    @Expose
    private List<LoginRequest> user = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<LoginRequest> getUser() {
        return user;
    }

    public void setUser(List<LoginRequest> user) {
        this.user = user;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
