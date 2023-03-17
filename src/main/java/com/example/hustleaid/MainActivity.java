package com.example.hustleaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button exerciseButton = findViewById(R.id.exerciseButton);
        Button calorieButton = findViewById(R.id.calorieButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        userInfo user1 = (userInfo) getIntent().getSerializableExtra("user1pass");

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class).putExtra("user1pass",user1));
            }
        });
        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ExercisesActivity.class).putExtra("user1pass",user1));
            }
        });
        calorieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CalorieActivity.class).putExtra("user1pass",user1));
            }
        });


    }
}