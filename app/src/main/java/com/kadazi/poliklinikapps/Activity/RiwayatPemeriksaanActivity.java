package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataRiwayat;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelRiwayat;
import com.kadazi.poliklinikapps.Model.ResponseModelRiwayat;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPemeriksaanActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelRiwayat> listData = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemeriksaan);
        rvData = findViewById(R.id.list_riwayat);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        tampilData();
    }
    public void tampilData(){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelRiwayat> tampilData = arData.listRiwayat(1);

        tampilData.enqueue(new Callback<ResponseModelRiwayat>() {
            @Override
            public void onResponse(Call<ResponseModelRiwayat> call, Response<ResponseModelRiwayat> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                listData = response.body().getData();
                Log.d("berhasil","berhasil");
                adData = new AdapterDataRiwayat(RiwayatPemeriksaanActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
                BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch(item.getItemId())
                        {
                            case R.id.page_1:
                                return false;
                            case R.id.page_2:
                                startActivity(new Intent(getApplicationContext(),ResepActivity.class));
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
            }

            @Override
            public void onFailure(Call<ResponseModelRiwayat> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}