package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kadazi.poliklinikapps.Adapter.AdapterDataJadwal;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPoli;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelJadwal;
import com.kadazi.poliklinikapps.Model.DataModelPoli;
import com.kadazi.poliklinikapps.Model.ResponseModelDetailJadwal;
import com.kadazi.poliklinikapps.Model.ResponseModelPoli;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailJadwalDokter extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelJadwal> data = new ArrayList<>();
    private TextView tv_poli;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal_dokter);
        rvData = findViewById(R.id.list_jadwal_dokter);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        String kode_poli = getIntent().getStringExtra("kode_poli");
        tv_poli = findViewById(R.id.textView4);
        tampilData(kode_poli);
    }
    public void tampilData(String kode_poli){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelDetailJadwal> tampilData = arData.detail_jadwal(kode_poli);

        tampilData.enqueue(new Callback<ResponseModelDetailJadwal>() {
            @Override
            public void onResponse(Call<ResponseModelDetailJadwal> call, Response<ResponseModelDetailJadwal> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                tv_poli.setText(response.body().getPoli());
                data = response.body().getData();
                adData = new AdapterDataJadwal(DetailJadwalDokter.this, data);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelDetailJadwal> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}