package com.kadazi.poliklinikapps.Api;

import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelResep;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("resep")
    Call<ResponseModelResep> listResep();

    @FormUrlEncoded
    @POST("auth")
    Call<ResponseModel> login(
            @Field("email") String email,
            @Field("password") String password
    );
}
