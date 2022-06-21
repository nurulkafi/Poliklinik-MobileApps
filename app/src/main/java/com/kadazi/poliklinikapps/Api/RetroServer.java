package com.kadazi.poliklinikapps.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {

    private static final String baseURL ="https://kadazi-klinik.herokuapp.com/api/";


    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro == null){

            retro = new Retrofit.Builder().
                    baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retro;
    }
}
