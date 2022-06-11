package com.kadazi.poliklinikapps.Api;


import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelAntrian;
import com.kadazi.poliklinikapps.Model.ResponseModelDetailPemeriksaan;
import com.kadazi.poliklinikapps.Model.ResponseModelResep;
import com.kadazi.poliklinikapps.Model.ResponseModelResepDetails;
import com.kadazi.poliklinikapps.Model.ResponseModelRiwayat;

import java.util.Date;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface APIRequestData {
    @FormUrlEncoded
    @POST("auth")
    Call<ResponseModel> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("resep/{id}")
    Call<ResponseModelResep> listResep(@Path("id")int id);
    @GET("riwayat_pemeriksaan/{id}")
    Call<ResponseModelRiwayat> listRiwayat(@Path("id") int id);
    @GET("detail_pemeriksaan/{id}")
    Call<ResponseModelDetailPemeriksaan> Detail(@Path("id") int id);
    @GET("details_resep/{id}")
    Call<ResponseModelResepDetails> DetailS_Resep(@Path("id") int id);
    @GET("antrian_daftar")
    Call<ResponseModelAntrian> ListAntrian();

    @FormUrlEncoded
    @POST("pasien")
    Call<ResponseModel> Daftar(
            @Field("email") String email,
            @Field("password") String password,
            @Field("nik") String nik,
            @Field("nama") String nama,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jk") String jk,
            @Field("alamat") String alamat,
            @Field("no_hp") String no_hp
    );

}
