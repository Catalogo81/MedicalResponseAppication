package com.example.medicalresponseapp.Registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalresponseapp.Login2;
import com.example.medicalresponseapp.R;
import com.example.medicalresponseapp.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener
{
    private FirebaseAuth mAuth;
    private EditText edtEmailAddress, edtPersonName,edtPersonSurname,edtGender,edtIdentityNumber,edtPostalAddress,edtPassword,edtPasswordRetype;
    private Button btnRegister;
    private ImageButton BtnPic;
    private ProgressBar progressBar;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();



        edtGender=(EditText)findViewById(R.id.edtGender);
        edtEmailAddress=(EditText)findViewById(R.id.edtEmailAddress);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        edtPasswordRetype=(EditText)findViewById(R.id.edtPasswordRetype);
        edtPersonName=(EditText)findViewById(R.id.edtPersonName);
        edtPersonSurname=(EditText)findViewById(R.id.edtPersonSurname);
        edtPostalAddress=(EditText)findViewById(R.id.edtPostalAddress);
        edtIdentityNumber=(EditText)findViewById(R.id.edtIdentityNumber);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        BtnPic=findViewById(R.id.BtnPic);

        progressBar=findViewById(R.id.progressBar);

        BtnPic.setOnClickListener(this);
        btnRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.BtnPic:
                startActivity(new Intent(this, Login2.class));
                break;
            case R.id.btnRegister:
                btnRegister();
                break;
        }

    }

    private void btnRegister() {
        String ID= edtIdentityNumber.getText().toString().trim();
        String name= edtPersonName.getText().toString().trim();
        String password= edtPassword.getText().toString().trim();
        String gender= edtGender.getText().toString().trim();
        String surname= edtPersonSurname.getText().toString().trim();
        String address=edtPostalAddress.getText().toString().trim();
        String passwordretype= edtPasswordRetype.getText().toString().trim();
        String email=edtEmailAddress.getText().toString().trim();

        if (name.isEmpty()){
            edtPersonName.setError("Full name is required!");
            edtPersonName.requestFocus();
            return;
        }

        if (ID.isEmpty()){
            edtIdentityNumber.setError("Identity number is required!");
            edtIdentityNumber.requestFocus();
            return;
        }

        if (password.isEmpty()){
            edtPassword.setError("Password is required!");
            edtPassword.requestFocus();
            return;
        }

        if (gender.isEmpty()){
            edtGender.setError("Gender is required!");
            edtGender.requestFocus();
            return;
        }

        if (surname.isEmpty()){
            edtPersonSurname.setError("Surname is required!");
            edtPersonSurname.requestFocus();
            return;
        }

        if (address.isEmpty()){
            edtPostalAddress.setError("Address is required!");
            edtPostalAddress.requestFocus();
            return;
        }
        if (passwordretype.isEmpty()){
            edtPasswordRetype.setError("Please retype your password!");
            edtPasswordRetype.requestFocus();
            return;
        }

        if (password.length()<6)
        {
            edtPassword.setError("Min password length should be 6 characters!");
            edtPassword.requestFocus();
            return;

        }

        if (email.isEmpty()){
            edtEmailAddress.setError("Email is required!");
            edtEmailAddress.requestFocus();
            return;
        }

        /*if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtEmailAddress.setError("Please provide a valid email address!");
            edtEmailAddress.requestFocus();
            return;
        }*/






        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            User user = new User(name, surname, gender, password, passwordretype, address, ID, email);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registration.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                        //redirect to login layout!

                                    } else {
                                        Toast.makeText(Registration.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                }
                            });


                        } else {
                            Toast.makeText(Registration.this, "Failed to register! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }

}