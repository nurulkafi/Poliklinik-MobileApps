package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import com.kadazi.poliklinikapps.Adapter.AdapterDataPembayaran;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPembayaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaran;
import com.kadazi.poliklinikapps.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelPembayaran> data = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        rvData = findViewById(R.id.list_media_pembayaran);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        srlData = findViewById(R.id.srlData);
        tampilData("1");
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                tampilData("1");
                srlData.setRefreshing(false);
            }
        });
    }
    public void tampilData(String id_pasien){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPembayaran> tampilData = arData.pembayaran(id_pasien);

        tampilData.enqueue(new Callback<ResponseModelPembayaran>() {
            @Override
            public void onResponse(Call<ResponseModelPembayaran> call, Response<ResponseModelPembayaran> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                data = response.body().getData();
                adData = new AdapterDataPembayaran(PembayaranActivity.this, data);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModelPembayaran> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}