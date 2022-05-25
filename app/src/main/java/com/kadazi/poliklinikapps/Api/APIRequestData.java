package com.kadazi.poliklinikapps.Api;

import com.kadazi.poliklinikapps.Model.ResponseModelResep;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("resep")
    Call<ResponseModelResep> listResep();
}
