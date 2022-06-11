package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.R;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPasienActivity extends AppCompatActivity {
    EditText nik,nama,tgl_lahir,jk,alamat,no_hp;
    Button daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_pasien);

        nik = findViewById(R.id.nik);
        nama = findViewById(R.id.nama);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        alamat = findViewById(R.id.alamat);
        jk = findViewById(R.id.jk);
        no_hp = findViewById(R.id.no_hp);
        daftar = findViewById(R.id.daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftars();
            }
        });

    }
    public void daftars(){
        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        String niks = nik.getText().toString();
        String namas = nama.getText().toString();
        String tgl_lahirs = tgl_lahir.getText().toString();
        String alamats = alamat.getText().toString();
        String jks = jk.getText().toString();
        String no_hps = no_hp.getText().toString();

        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = arData.Daftar(email,password,niks,namas,tgl_lahirs,alamats,jks,no_hps);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                if (status){
                    Intent intent = new Intent(SignUpPasienActivity.this,SignUpSuccesActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Email / Password Salah!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t){
                Log.d("ggl",t.getMessage());
            }
        });
    }


}