package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelRiwayat;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.SQLite.DataHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_login;
    EditText email;
    EditText password;
    protected Cursor cursor;
    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbcenter = new DataHelper(this);

        try {
            SQLiteDatabase db = dbcenter.getReadableDatabase();
            cursor = db.rawQuery("SELECT * FROM login LIMIT 1", null);
            cursor.moveToFirst();
            if(cursor.getCount() < 1){
                setContentView(R.layout.activity_login);
                btn_login = findViewById(R.id.button_login);
                email = findViewById(R.id.email);
                password = findViewById(R.id.password);
                btn_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        login(email.getText().toString(),password.getText().toString());
                    }
                });
            }else{
                cursor.moveToPosition(0);
                String em = cursor.getString(1).toString();
                String pw = cursor.getString(2).toString();
                login2(em,pw);
            }
        }catch (Exception e){
            Log.d("exception",e.getMessage());
        }
//



    }
    public void login(String email,String password){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = arData.login(email,password);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();
                String id = response.body().getId();

                SQLiteDatabase db = dbcenter.getWritableDatabase();
                String sql = "INSERT INTO login (id, email,password) VALUES ('"+id+"', '"+email+"', '"+password+"');";
                db.execSQL(sql);

                if (status){
                    Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
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
    public void login2(String email,String password){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = arData.login(email,password);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                if (status){
                    Intent intent = new Intent(LoginActivity.this,MenuActivity.class);
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
