package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataAntrian;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPendaftaran;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelAntrian;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaran;
import com.kadazi.poliklinikapps.Model.ResponseModelAntrian;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaran;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity {
    private BottomNavigationView ba;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelPendaftaran> listdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Button button= (Button) findViewById(R.id.daftar_baru);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(DaftarActivity.this,DaftarBaruActivity.class));
            }
        });
        rvData = findViewById(R.id.list_daftar);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        tampilData();
    }

    public void tampilData() {
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPendaftaran> tampilData = arData.ListDaftar(1);

        tampilData.enqueue(new Callback<ResponseModelPendaftaran>() {
            @Override
            public void onResponse(Call<ResponseModelPendaftaran> call, Response<ResponseModelPendaftaran> response) {
                boolean status = response.body().isSuccess();
                String message = response.body().getMessage();

                Log.d("true", message);
                listdata = response.body().getData();
                Log.d("berhasil", "berhasil");
                adData = new AdapterDataPendaftaran(DaftarActivity.this, listdata);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPendaftaran> call, Throwable t) {
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
                        startActivity(new Intent(getApplicationContext(), ResepActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(), DaftarActivity.class));
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
    }
}