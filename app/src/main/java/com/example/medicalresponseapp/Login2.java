package com.example.medicalresponseapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medicalresponseapp.Registration.Registration;

public class Login2 extends AppCompatActivity implements View.OnClickListener {

    EditText edtLIdentityNumber, edtPassword;
    private TextView tvLForgotPassword,tvLRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edtLIdentityNumber=findViewById(R.id.edtLIdentityNumber);
        edtPassword=findViewById(R.id.edtLIdentityNumber);
        tvLForgotPassword=findViewById(R.id.tvLForgotPassword);
        tvLRegister=findViewById(R.id.tvLRegister);
        btnLogin=findViewById(R.id.btnLogin);


        tvLRegister.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.tvLRegister:
                startActivity(new Intent(this, Registration.class));
                break;
        }

    }
}