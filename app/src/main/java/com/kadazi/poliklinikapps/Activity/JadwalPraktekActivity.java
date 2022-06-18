package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.kadazi.poliklinikapps.Adapter.AdapterDataPoli;
import com.kadazi.poliklinikapps.Adapter.AdapterDataResep;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPoli;
import com.kadazi.poliklinikapps.Model.ResponseModelPoli;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalPraktekActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private  List<DataModelPoli> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_praktek);
        rvData = findViewById(R.id.list_poli);
        lmData = new GridLayoutManager(this,3);
        rvData.setLayoutManager(lmData);
        tampilData();
    }
    public void tampilData(){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPoli> tampilData = arData.poli();

        tampilData.enqueue(new Callback<ResponseModelPoli>() {
            @Override
            public void onResponse(Call<ResponseModelPoli> call, Response<ResponseModelPoli> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                data = response.body().getData();
                adData = new AdapterDataPoli(JadwalPraktekActivity.this, data);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPoli> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}