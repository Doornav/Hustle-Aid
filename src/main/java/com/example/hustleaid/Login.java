package com.example.hustleaid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText Email, Password;
    Button Login_btn;
    TextView toSignup_btn;
    FirebaseAuth firebase_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.editTextLoginEmail);
        Password = findViewById(R.id.editTextLoginPassword);
        Login_btn = findViewById(R.id.loginButton);
        toSignup_btn = findViewById(R.id.toSignupButton);
        firebase_auth = FirebaseAuth.getInstance();

        toSignup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Signup.class));
            }
        });

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("E-mail required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Email.setError("Password required");
                    return;
                }
                if (password.length() < 8) {
                    Password.setError("Invalid Password length");
                    return;
                }

                firebase_auth.signInWithEmailAndPassword(email, password) .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login.this, "Welcome Back", Toast.LENGTH_LONG) .show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Login.this, "Error password:" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




    }
}