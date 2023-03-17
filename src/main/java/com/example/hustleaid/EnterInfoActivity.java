package com.example.hustleaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hustleaid.userInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EnterInfoActivity extends AppCompatActivity implements Serializable{



    FirebaseAuth firebase_auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_info);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextHeightFeet = findViewById(R.id.editTextHeightFeet);
        EditText editTextHeightInches = findViewById(R.id.editTextHeightInches);
        EditText editTextWeight = findViewById(R.id.editTextWeight);
        CheckBox checkBoxMale = findViewById(R.id.checkBoxMale);
        CheckBox checkBoxFemale = findViewById(R.id.checkBoxFemale);
        Button confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               userInfo user1 = (userInfo) getIntent().getSerializableExtra("user1pass");

                user1.setAge(user1.errorCheck(editTextAge.getText().toString().trim()));
                user1.setHeightFeet(user1.errorCheck(editTextHeightFeet.getText().toString().trim()));
                user1.setHeightInches(user1.errorCheck(editTextHeightInches.getText().toString().trim()));
                user1.setWeight(user1.errorCheck(editTextWeight.getText().toString().trim()));
                user1.setIsMale(checkBoxMale.isChecked());
                user1.setIsFemale(checkBoxFemale.isChecked());


                System.out.println(user1.getName());
                System.out.println(user1.getAge());
                System.out.println(user1.getHeightFeet() + "ft. " + user1.getHeightInches() + "in.");
                System.out.println(user1.getWeight());
                System.out.println(user1.isMale);


               if (!user1.errorCheck()){
                   user1.pushtoDatabase();
                   startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("user1pass",user1));
               } else {
                   Toast.makeText(EnterInfoActivity.this, "Please correct the following fields: " + user1.getErrorMessage(), Toast.LENGTH_SHORT).show();
                   user1.resetErrorMessage();
              }

            }
        });

        }

}

