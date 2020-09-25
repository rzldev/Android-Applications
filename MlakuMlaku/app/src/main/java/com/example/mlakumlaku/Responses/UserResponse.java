package com.example.mlakumlaku.Responses;

import com.example.mlakumlaku.Requests.UserRequest;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("user")
    @Expose
    private List<UserRequest> user = null;
    @SerializedName("success")
    @Expose
    private Integer success;

    public List<UserRequest> getUser() {
        return user;
    }

    public void setUser(List<UserRequest> user) {
        this.user = user;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }
}
