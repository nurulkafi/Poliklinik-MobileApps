package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kadazi.poliklinikapps.R;

public class SignUpSuccesActivity extends AppCompatActivity {
    Button lanjut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_succes);
        lanjut = findViewById(R.id.lanjutkan);

        lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpSuccesActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}