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
import com.kadazi.poliklinikapps.Adapter.AdapterDataAntrian;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPembayaran;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPendaftaran;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelAntrian;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaran;
import com.kadazi.poliklinikapps.Model.ResponseModelAntrian;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaran;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.SQLite.DataHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarActivity extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModelPendaftaran> listdata = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private CardView cr;
    protected Cursor cursor;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        rvData = findViewById(R.id.list_daftar);
        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM login LIMIT 1", null);
        cursor.moveToFirst();
        cursor.moveToPosition(0);
        String pasien_id = cursor.getString(3).toString();
        Button button= (Button) findViewById(R.id.daftar_baru);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(DaftarActivity.this,DaftarBaruActivity.class));
            }
        });

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
        ImageButton back = findViewById(R.id.back_daftar);
        back.setOnClickListener(new View.OnClickListener() {
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
        cr = findViewById(R.id.daftar_kosong);
        cr.setVisibility(View.GONE);
        tampilData(pasien_id);
    }

    public void tampilData(String id_pasien) {
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPendaftaran> tampilData = arData.ListDaftar(id_pasien);

        tampilData.enqueue(new Callback<ResponseModelPendaftaran>() {
            @Override
            public void onResponse(Call<ResponseModelPendaftaran> call, Response<ResponseModelPendaftaran> response) {
                boolean status = response.body().isSuccess();
                String message = response.body().getMessage();

                listdata = response.body().getData();
                if (listdata.size() < 1){
                    cr.setVisibility(View.VISIBLE);
                }else{
                    cr.setVisibility(View.GONE);
                    adData = new AdapterDataPendaftaran(DaftarActivity.this, listdata);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<ResponseModelPendaftaran> call, Throwable t) {
                Log.d("ggl", t.getMessage());
            }
        });
    }
}