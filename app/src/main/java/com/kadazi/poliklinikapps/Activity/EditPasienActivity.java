package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Adapter.AdapterDataPembayaran;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelPasienEdit;
import com.kadazi.poliklinikapps.Model.ResponseModelPembayaran;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.SQLite.DataHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditPasienActivity extends AppCompatActivity {
    private BottomNavigationView ba;
    EditText nik,nama,tgl_lahir,jk,alamat,no_hp;
    Button daftar;
    protected Cursor cursor;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pasien);
        nik = findViewById(R.id.nik);
        nama = findViewById(R.id.nama);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        jk = findViewById(R.id.jk);
        no_hp = findViewById(R.id.no_hp);
        daftar = findViewById(R.id.daftar);
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM login LIMIT 1", null);
        cursor.moveToFirst();
        cursor.moveToPosition(0);
        String pasien_id = cursor.getString(3).toString();
        daftar = findViewById(R.id.submit_edit);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftars(pasien_id);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        tampilData(pasien_id);
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
                        return true;
                }
                return false;
            }
        });

    }
    public void tampilData(String id_pasien){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelPasienEdit> tampilData = arData.edit_pasien(id_pasien);

        tampilData.enqueue(new Callback<ResponseModelPasienEdit>() {
            @Override
            public void onResponse(Call<ResponseModelPasienEdit> call, Response<ResponseModelPasienEdit> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                nik.setText(response.body().getNik());
                nama.setText(response.body().getNama());
                tgl_lahir.setText(response.body().getTgl_lahir());
                jk.setText(response.body().getJenis_kelamin());
                no_hp.setText(response.body().getNo_hp());
                alamat.setText(response.body().getAlamat());



            }

            @Override
            public void onFailure(Call<ResponseModelPasienEdit> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
    public void daftars(String pasien_id){

        String niks = nik.getText().toString();
        String namas = nama.getText().toString();
        String tgl_lahirs = tgl_lahir.getText().toString();
        String alamats = alamat.getText().toString();
        String jks = jk.getText().toString();
        String no_hps = no_hp.getText().toString();


        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = arData.edit_pasien(pasien_id,niks,namas,tgl_lahirs,alamats,jks,no_hps);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                if (status){
                    Intent intent = new Intent(EditPasienActivity.this,PengaturanActivity.class);
                    Toast.makeText(getApplicationContext(), "Perubahan Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Perubahan Gagal Disimpan!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t){
                Log.d("ggl",t.getMessage());
            }
        });
    }
}