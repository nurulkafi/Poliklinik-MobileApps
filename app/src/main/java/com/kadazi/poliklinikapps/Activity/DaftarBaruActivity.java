package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.kadazi.poliklinikapps.Adapter.AdapterDataPendaftaran;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPendaftaranBaru;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaran;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaranBaru;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaranBaru;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarBaruActivity extends AppCompatActivity {
    private BottomNavigationView ba;
    private ImageButton back;
    private SwipeRefreshLayout srlData;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelPendaftaranBaru> listdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_baru);

        srlData = findViewById(R.id.srlData);
        rvData = findViewById(R.id.list_daftar_baru);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        tampilData();
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                tampilData();
                srlData.setRefreshing(false);
            }
        });
    }

    public void tampilData() {
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPendaftaranBaru> tampilData = arData.ListDaftarBaru();

        tampilData.enqueue(new Callback<ResponseModelPendaftaranBaru>() {
            @Override
            public void onResponse(Call<ResponseModelPendaftaranBaru> call, Response<ResponseModelPendaftaranBaru> response) {
                boolean status = response.body().isSuccess();
                String message = response.body().getMessage();

                Log.d("true", message);
                listdata = response.body().getData();
                Log.d("berhasil", "berhasil");
                adData = new AdapterDataPendaftaranBaru(DaftarBaruActivity.this, listdata);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPendaftaranBaru> call, Throwable t) {
                Log.d("ggl", t.getMessage());
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.page_1:
                        return false;
                    case R.id.page_2:
                        startActivity(new Intent(getApplicationContext(), AntrianActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(), RiwayatPemeriksaanActivity.class));
                        overridePendingTransition(0, 0);
                        return false;
                    case R.id.page_4:
                        startActivity(new Intent(getApplicationContext(), PengaturanActivity.class));
                        overridePendingTransition(0, 0);
                        return false;
                }
                return false;
            }
        });
        back = findViewById(R.id.back_daftar_baru);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}