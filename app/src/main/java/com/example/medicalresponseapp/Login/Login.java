
package com.example.medicalresponseapp.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicalresponseapp.Activities.Dashboard;
import com.example.medicalresponseapp.R;
import com.example.medicalresponseapp.Registration.Registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    TextView tvLForgotPassword, tvSignUp;
    Button btnLogin;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //tests if there is a user is already active and email is verified
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null && firebaseUser.isEmailVerified())
        {
            startActivity(new Intent(Login.this, Dashboard.class));
        }

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        tvLForgotPassword = findViewById(R.id.tvLForgotPassword);
        tvSignUp = findViewById(R.id.tvSignUp);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            Authentication();
        });

        tvSignUp.setOnClickListener(view ->
                startActivity(new Intent(Login.this, Registration.class)));

        tvLForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPassword.class));
            }
        });

    }

    private void Authentication()
    {
        //                                                                                                                                                                                                                                                                                                                                                String email = edtLIdentityNumber.getText().toString();
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();


            //use a progress bar
//            progressDialog.setMessage("Please wait while Logging in...");
//            progressDialog.setTitle("Login");
//            progressDialog.setCanceledOnTouchOutside(false);
//            progressDialog.show();

            //authentication progress
            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        //check if the user is verified and has clicked on the verification link
//                        if(firebaseAuth.getCurrentUser().isEmailVerified())
//                        {
//                            //progressDialog.dismiss();
                            sendUserToHomeActivity();
                            Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                        //}
//                        else
//                        {
//                            //progressDialog.dismiss();
//                            Toast.makeText(Login.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
//                        }
                    }
                    else
                    {
                        //progressDialog.dismiss();
                        Toast.makeText(Login.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    private void sendUserToHomeActivity()
    {
        //this intent will activate when the user successfully registers
        Intent intent = new Intent (Login.this, Dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //closes the activity when the user presses the phone 'back' button
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}