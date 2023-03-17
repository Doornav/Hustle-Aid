package com.example.hustleaid;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.example.hustleaid.Maps;
import com.example.hustleaid.userInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class Signup extends AppCompatActivity implements Serializable{
    EditText Name, Email, Password;
    Button Signup_btn;
    TextView toLogin_btn;
    FirebaseAuth firebase_auth;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Name = findViewById(R.id.editTextSignupName);
        Email = findViewById(R.id.editTextSignupEmail);
        Password = findViewById(R.id.editTextSignupPassword);
        Signup_btn = findViewById(R.id.signupButton);
        toLogin_btn = findViewById(R.id.toLoginButton);
        firebase_auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        toLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        Signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String name = Name.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    Name.setError("Enter a name");
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    Email.setError("E-mail required");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Password.setError("Password required");
                    return;
                }
                if(password.length() < 8) {
                    Password.setError("Weak Password");
                    return;
                }

                firebase_auth.createUserWithEmailAndPassword(email, password). addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            userInfo user1 = new userInfo();
                            Toast.makeText(Signup.this, "User Created", Toast.LENGTH_LONG) .show();
                            user1.setUserID(firebase_auth.getCurrentUser().getUid());
                            user1.setName(name);
                            user1.setEmail(email);
                            user1.pushtoDatabase();
                            startActivity(new Intent(getApplicationContext(), EnterInfoActivity.class).putExtra("user1pass",user1));
                        } else {
                            Toast.makeText(Signup.this, "Error:" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}