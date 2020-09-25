package com.example.jsonapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @GET("API/read_all.php")
    Call<MainResponse> MAIN_RESPONSE_CALL();

    @FormUrlEncoded
    @POST("API/take_order.php")
    Call<InsertResponse> INSERT_RESPONSE_CALL(@Field("item_name")String itemName);
}
