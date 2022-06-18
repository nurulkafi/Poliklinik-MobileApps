package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataDetailsResep;
import com.kadazi.poliklinikapps.Adapter.AdapterDataResep;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelResep;
import com.kadazi.poliklinikapps.Model.DataModelResepDetails;
import com.kadazi.poliklinikapps.Model.ResponseModelResepDetails;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailResepActivity extends AppCompatActivity {
    TextView Nama_dokter;
    TextView Nama_poli;
    TextView tgl_pendaftaran;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelResepDetails> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_resep);
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
        rvData = findViewById(R.id.list_details);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        tampilData(Integer.parseInt(terima.getStringExtra("id")));
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
                        return true;
                    case R.id.page_4:
                        startActivity(new Intent(getApplicationContext(),PengaturanActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                }
                return false;
            }
        });
    }
    public void tampilData(int id){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelResepDetails> tampilData = arData.DetailS_Resep(id);

        tampilData.enqueue(new Callback<ResponseModelResepDetails>() {
            @Override
            public void onResponse(Call<ResponseModelResepDetails> call, Response<ResponseModelResepDetails> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                listData = response.body().getData();
                Log.d("berhasil","berhasil");
                adData = new AdapterDataDetailsResep(DetailResepActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseModelResepDetails> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}

