package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPembayaran;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPembayaran;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaran;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.SQLite.DataHelper;

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
    private  CardView cr;
    protected Cursor cursor;
    DataHelper dbcenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran);
        rvData = findViewById(R.id.list_media_pembayaran);
        lmData = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvData.setLayoutManager(lmData);
        srlData = findViewById(R.id.srlData);
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM login LIMIT 1", null);
        cursor.moveToFirst();
        cursor.moveToPosition(0);
        String pasien_id = cursor.getString(3).toString();
        tampilData(pasien_id);

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
        SwipeRefreshLayout srlData = findViewById(R.id.srlData);
        srlData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlData.setRefreshing(true);
                tampilData(pasien_id);
                srlData.setRefreshing(false);
            }
        });
        cr = findViewById(R.id.pembayaran_kosong);
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
                if (data.size() < 1){
                    cr.setVisibility(View.VISIBLE);
                }else{
                    cr.setVisibility(View.GONE);
                    adData = new AdapterDataPembayaran(PembayaranActivity.this, data);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<ResponseModelPembayaran> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}