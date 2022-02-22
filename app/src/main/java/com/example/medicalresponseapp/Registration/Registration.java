package com.example.medicalresponseapp.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medicalresponseapp.Activities.Dashboard;
import com.example.medicalresponseapp.R;
import com.example.medicalresponseapp.Classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Registration extends AppCompatActivity implements View.OnClickListener
{
    private FirebaseAuth mAuth;
    private EditText etEmailAddress, etPersonName, etPersonSurname, etGender, etIdentityNumber,
            etPostalAddress, etPassword, etPasswordRetype;
    private Button btnLogin, btnRegister;
    private ProgressBar progressBar;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseFirestore firestore;

    String stringID, userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Firebase authentication
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        etGender = (EditText)findViewById(R.id.etGender);
        etEmailAddress = (EditText)findViewById(R.id.etEmailAddress);
        etPassword = findViewById(R.id.etPassword);
        etPasswordRetype = findViewById(R.id.etPasswordRetype);
        etPersonName = findViewById(R.id.etPersonName);
        etPersonSurname = findViewById(R.id.etPersonSurname);
        etPostalAddress = findViewById(R.id.etPostalAddress);
        etIdentityNumber = findViewById(R.id.etIdentityNumber);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        progressBar=findViewById(R.id.progressBar);

        //BtnPic.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

        //Firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.BtnPic:
//                startActivity(new Intent(this, Login2.class));
//                break;
            case R.id.btnRegister:
                btnRegister();
                break;
        }

    }

    private void btnRegister() {
        String id = etIdentityNumber.getText().toString().trim();
        String name = etPersonName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String gender = etGender.getText().toString().trim();
        String surname = etPersonSurname.getText().toString().trim();
        String address = etPostalAddress.getText().toString().trim();
        String passwordRetype = etPasswordRetype.getText().toString().trim();
        String email = etEmailAddress.getText().toString().trim();

        if (name.isEmpty()){
            etPersonName.setError("Full name is required!");
            etPersonName.requestFocus();
            return;
        }

        if (id.isEmpty()){
            etIdentityNumber.setError("Identity number is required!");
            etIdentityNumber.requestFocus();
            return;
        }

        if (password.isEmpty()){
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }

        if (gender.isEmpty()){
            etGender.setError("Gender is required!");
            etGender.requestFocus();
            return;
        }

        if (surname.isEmpty()){
            etPersonSurname.setError("Surname is required!");
            etPersonSurname.requestFocus();
            return;
        }

        if (address.isEmpty()){
            etPostalAddress.setError("Address is required!");
            etPostalAddress.requestFocus();
            return;
        }
        if (passwordRetype.isEmpty()){
            etPasswordRetype.setError("Please retype your password!");
            etPasswordRetype.requestFocus();
            return;
        }

        if (password.length()<6)
        {
            etPassword.setError("Min password length should be 6 characters!");
            etPassword.requestFocus();
            return;

        }

        if (email.isEmpty()){
            etEmailAddress.setError("Email is required!");
            etEmailAddress.requestFocus();
            return;
        }

        /*if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmailAddress.setError("Please provide a valid email address!");
            edtEmailAddress.requestFocus();
            return;
        }*/

        btnRegister.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            //gets the user id of the current logged in user
                            userID = mAuth.getCurrentUser().getUid();
                            //id = userID;
                            stringID = userID;
                            User user = new User(stringID, name, surname, id, address, gender, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registration.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to Dashboard layout!
                                        startActivity(new Intent(Registration.this, Dashboard.class));

                                    } else {
                                        Toast.makeText(Registration.this, "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });


                        } else {
                            Toast.makeText(Registration.this, "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }

}