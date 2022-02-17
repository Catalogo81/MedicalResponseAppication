package com.example.medicalresponseapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalresponseapp.Login2;
import com.example.medicalresponseapp.R;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    ImageView ivProfilePhoto, ivLocationIcon;
    TextView tvUserName, tvRegister, tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        ivProfilePhoto = findViewById(R.id.ivProfilePhoto);
        ivLocationIcon = findViewById(R.id.ivLocationIcon);
        tvUserName = findViewById(R.id.tvUserName);
        tvRegister = findViewById(R.id.tvRegister);
        tvLogin = findViewById(R.id.tvLogin);

        ivProfilePhoto.setOnClickListener(view ->
                Toast.makeText(Dashboard.this, "Profile Clicked", Toast.LENGTH_SHORT).show());

        ivLocationIcon.setOnClickListener(view ->
                Toast.makeText(Dashboard.this, "Location Clicked", Toast.LENGTH_SHORT).show());

        tvRegister.setOnClickListener(view ->
                Toast.makeText(Dashboard.this, "Register Clicked", Toast.LENGTH_SHORT).show());

        tvLogin.setOnClickListener(view ->
              //  Toast.makeText(Dashboard.this, "Login Clicked", Toast.LENGTH_SHORT).show());
        startActivity(new Intent(this, Login2.class)));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvLogin:
                startActivity(new Intent(this, Login2.class));
                break;

        }
    }
}