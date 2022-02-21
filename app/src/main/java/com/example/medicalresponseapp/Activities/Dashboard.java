package com.example.medicalresponseapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalresponseapp.Classes.User;
import com.example.medicalresponseapp.Login.Login;
import com.example.medicalresponseapp.R;
import com.example.medicalresponseapp.Registration.Registration;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    ImageView ivProfilePhoto, ivLocationIcon;
    TextView tvUserName, tvRegister, tvLogin;
    CardView cvHospital;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    String userID;
    DatabaseReference databaseReference;
    String stringID, name, surname, id, address, gender, email;

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
        cvHospital = findViewById(R.id.cvHospital);

        /*--------------------Firebase Instance retrievals----------------------*/
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        //gets the user that is currently logged in
        userID = firebaseAuth.getCurrentUser().getUid();

        //method that reads the current users data
        readUserData();

        ivProfilePhoto.setOnClickListener(view ->
                Toast.makeText(Dashboard.this, "Profile Clicked", Toast.LENGTH_SHORT).show());

        ivLocationIcon.setOnClickListener(view ->
                Toast.makeText(Dashboard.this, "Location Clicked", Toast.LENGTH_SHORT).show());

        tvRegister.setOnClickListener(view ->
                //Toast.makeText(Dashboard.this, "Register Clicked", Toast.LENGTH_SHORT).show());
        startActivity(new Intent(Dashboard.this, Registration.class)));

        tvLogin.setOnClickListener(view ->
              //  Toast.makeText(Dashboard.this, "Login Clicked", Toast.LENGTH_SHORT).show());
        startActivity(new Intent(Dashboard.this, Login.class)));

        cvHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(Dashboard.this, "Hospital clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Dashboard.this, Login.class));
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tvLogin:
                startActivity(new Intent(Dashboard.this, Login.class));
                break;

        }
    }

    private void readUserData()
    {
        /*---------------Reads the data entered when the user registered----------------*/
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    User users = snapshot1.getValue(User.class);
                    assert users != null;

                    //data retrieval variables
                    stringID = users.getStringID();
                    id = users.getId();
                    name = users.getName();
                    surname = users.getSurname();
                    email = users.getEmail();
                    address = users.getAddress();
                    gender = users.getGender();

                    if(userID.equals(stringID))
                    {
                        tvUserName.setText(name + " " + surname);
                    }
                    else
                    {
                        Toast.makeText(Dashboard.this, "no data to display", Toast.LENGTH_LONG).show();
                    }


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }
}