package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataJadwal;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPembayaranBiaya;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPembayaranResep;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelJadwal;
import com.kadazi.poliklinikapps.Model.DataModelPembayaranBiaya;
import com.kadazi.poliklinikapps.Model.DataModelPembayaranResep;
import com.kadazi.poliklinikapps.Model.ResponseModelDetailJadwal;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaranBiaya;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaranResep;
import com.kadazi.poliklinikapps.Model.Temp;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPembayaran extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelPembayaranResep> data = new ArrayList<>();
    private RecyclerView rvData2;
    private RecyclerView.Adapter adData2;
    private RecyclerView.LayoutManager lmData2;
    private List<DataModelPembayaranBiaya> data2 = new ArrayList<>();
    private TextView total;
    AppCompatButton bayar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembayaran);
        rvData = findViewById(R.id.list_details_biaya_obat);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        rvData2 = findViewById(R.id.list_details_biaya);
        lmData2 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData2.setLayoutManager(lmData2);
        String id = getIntent().getStringExtra("id");
        tampildataresep(id);
        tampildatabiaya(id);
        total = findViewById(R.id.total);
        bayar=findViewById(R.id.btn_bayar);
        total.setText("Rp. " + getIntent().getStringExtra("total"));
        if(getIntent().getStringExtra("status").equals("Belum Bayar")){
            bayar.setVisibility(View.VISIBLE);
            bayar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),MediaPembayaranActivity.class);
                    Temp.id = id;
                    startActivity(intent);
                }
            });
        }else{
            bayar.setVisibility(View.GONE);
        }
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
    }
    public void tampildataresep(String id_pendaftaran){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPembayaranResep> tampilData = arData.pembayaran_resep(id_pendaftaran);

        tampilData.enqueue(new Callback<ResponseModelPembayaranResep>() {
            @Override
            public void onResponse(Call<ResponseModelPembayaranResep> call, Response<ResponseModelPembayaranResep> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                data = response.body().getData();
                adData = new AdapterDataPembayaranResep(DetailPembayaran.this, data);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPembayaranResep> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
    public void tampildatabiaya(String id_pendaftaran){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPembayaranBiaya> tampilData = arData.pembayaran_biaya(id_pendaftaran);

        tampilData.enqueue(new Callback<ResponseModelPembayaranBiaya>() {
            @Override
            public void onResponse(Call<ResponseModelPembayaranBiaya> call, Response<ResponseModelPembayaranBiaya> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                data2 = response.body().getData();
                adData2 = new AdapterDataPembayaranBiaya(DetailPembayaran.this, data2);
                rvData2.setAdapter(adData2);
                adData2.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPembayaranBiaya> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}