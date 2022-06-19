package com.kadazi.poliklinikapps.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.R;
import com.kadazi.poliklinikapps.SQLite.DataHelper;

public class PengaturanActivity extends AppCompatActivity {
    private BottomNavigationView ba;
    TextView logout,editakun,laporkanmasalah,editdatapasien,helpsupport;

    DataHelper dbcenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        dbcenter = new DataHelper(this);
        logout = findViewById(R.id.logout);
        editakun = findViewById(R.id.editakun);
        editdatapasien = findViewById(R.id.editdatapasien);
        helpsupport = findViewById(R.id.helpsupport);
        laporkanmasalah = findViewById(R.id.laporkanmasalah);



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
                        startActivity(new Intent(getApplicationContext(),ResepActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                    case R.id.page_3:
                        startActivity(new Intent(getApplicationContext(),RiwayatPemeriksaanActivity.class));
                        overridePendingTransition(0,0);
                        return false;
                    case R.id.page_4:
                        return true;
                }
                return false;
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbcenter.getWritableDatabase();
                db.execSQL("delete from login");
                Intent intent = new Intent(PengaturanActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        editdatapasien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengaturanActivity.this,EditPasienActivity.class);
                startActivity(intent);
            }
        });

        editakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengaturanActivity.this,EditAkunActivity.class);
                startActivity(intent);
            }
        });

        helpsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengaturanActivity.this,HelpSupportActivity.class);
                startActivity(intent);
            }
        });

        laporkanmasalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengaturanActivity.this,LaporkanMasalahActivity.class);
                startActivity(intent);
            }
        });


    }

}