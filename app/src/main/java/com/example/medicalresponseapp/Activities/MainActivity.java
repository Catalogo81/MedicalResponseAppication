package com.example.medicalresponseapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicalresponseapp.Login.Login;
import com.example.medicalresponseapp.R;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    //Animation variables
    Animation topAnim, bottomAnim;

    //TextViews
    TextView tvEmResponseLogo, tvSlogan;

    //ImageViews
    ImageView ivEmergencyBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //TextView Hooks
        tvEmResponseLogo = findViewById(R.id.tvEmResponseLogo);
        tvSlogan = findViewById(R.id.tvSlogan);

        //ImageView Hooks
        ivEmergencyBox = findViewById(R.id.ivEmergencyBox);

        //Assigning the animations
        ivEmergencyBox.setAnimation(topAnim);
        tvEmResponseLogo.setAnimation(bottomAnim);
        tvSlogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }
}