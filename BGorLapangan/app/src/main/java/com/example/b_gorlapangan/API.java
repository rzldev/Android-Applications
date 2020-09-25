package com.example.b_gorlapangan;

import com.example.b_gorlapangan.Models.Agency;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

public interface API {

    @Multipart
    @POST
    Call<Agency> agency(@Field("name") String name,
                        @Field("phone") String phone,
                        @Field("email") String email,
                        @Field("username") String username,
                        @Field("password") String password,
                        @Field("owner_name") String owner_name,
                        @Field("owner_phone") String owner_phone,
                        @Field("bank") String bank,
                        @Field("account") String account,
                        @Field("ktp") String ktp
    );
}
