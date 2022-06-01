package com.kadazi.poliklinikapps.Api;

import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelResep;
import com.kadazi.poliklinikapps.Model.ResponseModelRiwayat;

import retrofit2.Call;
<<<<<<< HEAD
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
=======
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
>>>>>>> 0ee63dc5c06efa1bfa246c2f3d43bf40a76a96bd

public interface APIRequestData {
    @GET("resep")
    Call<ResponseModelResep> listResep();
<<<<<<< HEAD

    @FormUrlEncoded
    @POST("auth")
    Call<ResponseModel> login(
            @Field("email") String email,
            @Field("password") String password
    );
=======
    @GET("riwayat_pemeriksaan/{id}")
    Call<ResponseModelRiwayat> listRiwayat(@Path("id") int id);
>>>>>>> 0ee63dc5c06efa1bfa246c2f3d43bf40a76a96bd
}
