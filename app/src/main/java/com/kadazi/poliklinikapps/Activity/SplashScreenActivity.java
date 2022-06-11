package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.kadazi.poliklinikapps.Adapter.AdapterDataRiwayat;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelRiwayat;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {
    private int time = 2500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(SplashScreenActivity.this,LoginActivity.class);
                startActivity(login);
                finish();
            }
        },time);
    }
}
