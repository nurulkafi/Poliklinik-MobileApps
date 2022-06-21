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
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPemeriksaanResep;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPemeriksaanResep;
import com.kadazi.poliklinikapps.Model.ResponseModelPemeriksaanResep;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPemeriksaanResepActivity extends AppCompatActivity {
    TextView Nama_dokter;
    TextView Nama_poli;
    TextView tgl_pendaftaran;
    ImageButton back;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelPemeriksaanResep> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_pemeriksaan_resep);

        rvData = findViewById(R.id.pemeriksaan_resep);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        String id = getIntent().getStringExtra("id");
        tampilriwayatresep(id);

        Nama_dokter = findViewById(R.id.detail_pemeriksaan_dokter);
        Nama_poli = findViewById(R.id.detail_pemeriksaan_poliklinik);
        tgl_pendaftaran = findViewById(R.id.detail_pemeriksaan_tgl);

        Intent terima = getIntent();
        String xnama_dokter = terima.getStringExtra("nama");
        String xnama_poli = terima.getStringExtra("poli");
        String xtgl_pendaftaran = terima.getStringExtra("tgl_pendaftaran");

        Nama_dokter.setText(xnama_dokter);
        Nama_poli.setText(xnama_poli);
        tgl_pendaftaran.setText(xtgl_pendaftaran);

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
        back = findViewById(R.id.btn_kembali);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void tampilriwayatresep(String id_pemeriksaan){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPemeriksaanResep> tampilData = arData.Riwayat_resep("1");

        tampilData.enqueue(new Callback<ResponseModelPemeriksaanResep>() {
            @Override
            public void onResponse(Call<ResponseModelPemeriksaanResep> call, Response<ResponseModelPemeriksaanResep> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                data = response.body().getData();
                adData = new AdapterDataPemeriksaanResep(RiwayatPemeriksaanResepActivity.this, data);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPemeriksaanResep> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}