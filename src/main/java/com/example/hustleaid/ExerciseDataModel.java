package com.example.hustleaid;

public class ExerciseDataModel {
    String exerciseName;
    String exerciseType;
    String exerciseDifficulty;


    public ExerciseDataModel(String exerciseName, String exerciseType, String exerciseDifficulty) {
        this.exerciseName = exerciseName;
        this.exerciseType = exerciseType;
        this.exerciseDifficulty = exerciseDifficulty;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public String getExerciseType() {
        return exerciseType;
    }

    public String getExerciseDifficulty() {
        return exerciseDifficulty;
    }
}
