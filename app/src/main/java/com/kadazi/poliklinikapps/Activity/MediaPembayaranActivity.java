package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataMediaPembayaran;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPoli;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelMediaPembayaran;
import com.kadazi.poliklinikapps.Model.DataModelPoli;
import com.kadazi.poliklinikapps.Model.ResponseModelMediaPembayaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPoli;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaPembayaranActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelMediaPembayaran> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_pembayaran);
        rvData = findViewById(R.id.list_media_pembayaran);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        tampilData();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.page_1:
                        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                    case R.id.page_2:
                        startActivity(new Intent(getApplicationContext(),AntrianActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(),RiwayatPemeriksaanActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                    case R.id.page_4:
                        startActivity(new Intent(getApplicationContext(),PengaturanActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                }
                return false;
            }
        });
        ImageButton btn = findViewById(R.id.btn_kembali);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SwipeRefreshLayout srlData = findViewById(R.id.srlData);
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                tampilData();
                srlData.setRefreshing(false);
            }
        });

    }
    public void tampilData(){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelMediaPembayaran> tampilData = arData.media_pembayaran();

        tampilData.enqueue(new Callback<ResponseModelMediaPembayaran>() {
            @Override
            public void onResponse(Call<ResponseModelMediaPembayaran> call, Response<ResponseModelMediaPembayaran> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                data = response.body().getData();
                adData = new AdapterDataMediaPembayaran(MediaPembayaranActivity.this, data);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelMediaPembayaran> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}