
package com.example.medicalresponseapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.medicalresponseapp.R;
import com.example.medicalresponseapp.Registration.Registration;

public class Login extends AppCompatActivity {

    EditText edtLIdentityNumber, edtPassword;
    TextView tvLForgotPassword, tvLRegister;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLIdentityNumber = findViewById(R.id.edtLIdentityNumber);
        edtPassword = findViewById(R.id.edtPassword);
        tvLForgotPassword = findViewById(R.id.tvLForgotPassword);
        tvLRegister = findViewById(R.id.tvLRegister);
        btnLogin = findViewById(R.id.btnLogin);


        tvLRegister.setOnClickListener(view ->
                startActivity(new Intent(Login.this, Registration.class)));

    }

}