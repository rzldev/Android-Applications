package com.example.jsonapplication;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainRequest {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("item")
    @Expose
    private String item;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
