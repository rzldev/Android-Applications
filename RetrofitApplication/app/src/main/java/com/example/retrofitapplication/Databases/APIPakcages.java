package com.example.retrofitapplication.Databases;

import com.example.retrofitapplication.Classes.InsertResponse;
import com.example.retrofitapplication.Classes.MainResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIPakcages {

    @GET("get_mahasiswa.php")
    Call<MainResponse> MAIN_RESPONSE_CALL();

    @FormUrlEncoded
    @POST("insert_hewan.php")
    Call<InsertResponse> INSERT_RESPONSE_CALL(@Field("nama") String nama,
                                              @Field("nim") String nim);
}
