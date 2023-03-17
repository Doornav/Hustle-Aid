package com.example.hustleaid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PresentExerciseInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_exercise_infro);
        TextView editTextName = findViewById(R.id.textViewDisplayExerciseName);
        TextView editTextType = findViewById(R.id.textViewDisplayType);
        TextView editTextDescription = findViewById(R.id.textViewDisplayDescription);
        TextView editTextDifficulty = findViewById(R.id.textViewDisplayDifficulty);
        Button backToExerciseButton = findViewById(R.id.backToExerciseButton);


        String name = getIntent().getStringExtra("NAME");
        String type = getIntent().getStringExtra("TYPE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String difficulty = getIntent().getStringExtra("DIFFICULTY");

        editTextName.setText(name);
        editTextType.setText(type);
        editTextDescription.setText(description);
        editTextDifficulty.setText(difficulty);

        backToExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getApplicationContext(), ExercisesActivity.class));
            }
        });
    }
}