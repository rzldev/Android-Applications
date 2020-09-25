package com.example.jsonweather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyAPI {

    @GET("weather?q=London,uk&APPID=011b681d3730a1c4faf38de86bf31c3a")
    Call<MainRespond> MAIN_RESPOND_CALL();
}
