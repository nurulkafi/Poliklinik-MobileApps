package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.kadazi.poliklinikapps.R;

public class SignUpEmailActivity extends AppCompatActivity {
    EditText email,password;
    Button lanjutkan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        lanjutkan = findViewById(R.id.lanjutkan);

        lanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpEmailActivity.this,SignUpPasienActivity.class);
                intent.putExtra("email", email.getText());
                intent.putExtra("password",password.getText());
                startActivity(intent);
            }
        });


    }

}