package com.example.mlakumlaku.Databases;

import com.example.mlakumlaku.Responses.LoginResponse;
import com.example.mlakumlaku.Responses.RegisterResponse;
import com.example.mlakumlaku.Responses.UserResponse;
import com.example.mlakumlaku.Responses.WisataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface APIPackages {

    @Multipart
    @POST("register.php")
    Call<RegisterResponse> REGISTER_RESPONSE_CALL(@Field("username") String username,
                                                  @Field("email") String email,
                                                  @Field("password") String password,
                                                  @Field("birth_date") String birthDate,
                                                  @Field("address") String address,
                                                  @Field("facebook") String facebook,
                                                  @Field("instagram") String instagram,
                                                  @Field("events") int events,
                                                  @Field("profile_title") String profileTitle,
                                                  @Field("profile_image") String profileImage,
                                                  @Field("bg_title") String bgTitle,
                                                  @Field("bg_image") String bgImage);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> LOGIN_RESPONSE_CALL(@Field("email") String email,
                                            @Field("password") String password);

    @FormUrlEncoded
    @POST("get_user.php")
    Call<UserResponse> USER_RESPONSE_CALL(@Field("id") int id);

    @GET("get_wisata.php")
    Call<WisataResponse> WISATA_RESPONSE_CALL();

    @FormUrlEncoded
    @POST("get_single_wisata.php")
    Call<WisataResponse> SINGLE_WISATA_RESPONSE_CALL(@Field("id") int id);
}
