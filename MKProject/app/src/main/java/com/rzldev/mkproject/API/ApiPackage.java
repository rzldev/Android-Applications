package com.rzldev.mkproject.API;

import com.rzldev.mkproject.Responds.AdminHistoryRespond;
import com.rzldev.mkproject.Responds.HistoryRespond;
import com.rzldev.mkproject.Responds.LoginRespond;
import com.rzldev.mkproject.Responds.MostViewedRespond;
import com.rzldev.mkproject.Responds.PlayingRespond;
import com.rzldev.mkproject.Responds.InsertUpdateDeleteRespond;
import com.rzldev.mkproject.Responds.ProfileRespond;
import com.rzldev.mkproject.Responds.ScheduleRespond;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiPackage {

    @GET("TixTix/showlistfilm.php")
    Call<PlayingRespond> playingrespond();

    @FormUrlEncoded
    @POST("TixTix/showhistory.php")
    Call<HistoryRespond> historyrespond(@Field("username") String username);

    @FormUrlEncoded
    @POST("TixTix/login_user.php")
    Call<LoginRespond> loginRespond(@Field("username") String username,
                                    @Field("password") String password);

    @GET("TixTix/showmostviewed.php")
    Call<MostViewedRespond> mostviewedrespond();

    @FormUrlEncoded
    @POST("TixTix/showprofile.php")
    Call<ProfileRespond> profilerespond(@Field("username") String username);

    @GET("TixTix/showlistfilm.php")
    Call<PlayingRespond> adminplayingrespond();

    @GET("TixTix/showallhistory.php")
    Call<AdminHistoryRespond> adminhistoryrespond();

    @POST("TixTix/insertakun.php")
    Call<InsertUpdateDeleteRespond> insertakunrespond(@Field("username") String username,
                                                      @Field("password") String password,
                                                      @Field("Status") String status,
                                                      @Field("saldo") int saldo,
                                                      @Field("telp") String telp);

    @FormUrlEncoded
    @POST("TixTix/insertfillm.php")
    Call<InsertUpdateDeleteRespond> insertfilmrespond(@Field("judul") String judul,
                                                      @Field("sinopsis") String sinopsis,
                                                      @Field("harga") int harga,
                                                      @Field("durasi") int durasi);

    @FormUrlEncoded
    @POST("TixTix/insertpembelian.php")
    Call<InsertUpdateDeleteRespond> insertpembelianrespond(@Field("username") String username,
                                                           @Field("judul") String judul,
                                                           @Field("tiket_dibeli") int tiket_dibeli,
                                                           @Field("total_harga") int total_harga,
                                                           @Field("waktu") String waktu);

    @FormUrlEncoded
    @POST("TixTix/updatesaldo.php")
    Call<InsertUpdateDeleteRespond> updatesaldorespond(@Field("username") String username,
                                                       @Field("saldo") int saldo);

    @FormUrlEncoded
    @POST("TixTix/deletefilm.php")
    Call<InsertUpdateDeleteRespond> deletefilmrespond(@Field("judul") String judul);

    @FormUrlEncoded
    @POST("TixTix/updatefillm.php")
    Call<InsertUpdateDeleteRespond> updatefilmrespond(@Field("judul") String judul,
                                                      @Field("sinopsis") String sinopsis,
                                                      @Field("harga") int harga,
                                                      @Field("durasi") int durasi);

}
