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
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.SQLite.DataHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity {
    private BottomNavigationView ba;
    protected Cursor cursor;
    EditText email,password;
    ImageButton back;
    Button submit;
    DataHelper dbcenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        dbcenter = new DataHelper(this);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM login LIMIT 1", null);
        cursor.moveToFirst();
        cursor.moveToPosition(0);
        String pasien_id = cursor.getString(0).toString();
        String emails = cursor.getString(1).toString();
        email.setText(emails);
        submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ubahuser(pasien_id);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.page_1:
                        overridePendingTransition(0,0);
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
                        return true;
                }
                return false;
            }
        });
        back = findViewById(R.id.back_edit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void ubahuser(String pasien_id){

        String emails = email.getText().toString();
        String passwords = password.getText().toString();


        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = arData.ubah_user(pasien_id,emails,passwords);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                if (status){
                    dbcenter.getWritableDatabase().execSQL("update login set email='" + email.getText().toString() + "', password='" + password.getText().toString() + "' where id='" + Integer.parseInt(pasien_id) + "'");
                    Toast.makeText(getApplicationContext(), "Perubahan Berhasil Disimpan!", Toast.LENGTH_SHORT).show();
                    finish();
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