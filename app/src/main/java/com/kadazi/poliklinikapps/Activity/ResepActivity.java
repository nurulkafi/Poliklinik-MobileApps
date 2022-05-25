package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.kadazi.poliklinikapps.Adapter.AdapterDataResep;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelResep;
import com.kadazi.poliklinikapps.Model.ResponseModelResep;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResepActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelResep> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resep);
        rvData = findViewById(R.id.list_resep);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        tampilData();
    }
    public void tampilData(){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelResep> tampilData = arData.listResep();

        tampilData.enqueue(new Callback<ResponseModelResep>() {
            @Override
            public void onResponse(Call<ResponseModelResep> call, Response<ResponseModelResep> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                listData = response.body().getData();
                Log.d("berhasil","berhasil");
                adData = new AdapterDataResep(ResepActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelResep> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}