package com.example.hustleaid;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.LinkedList;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;

public class userInfo extends AppCompatActivity implements Serializable {

    FirebaseAuth firebase_auth;
    static transient FirebaseFirestore firestore;

    String email;
    String name;
    Integer age;
    Integer heightFeet;
    Integer heightInches;
    Integer weight;
    Boolean isMale;

    Boolean isFemale;
    String errorMessage = "";
    String userID;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }
    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }
    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public void setIsMale(Boolean isMale){
        this.isMale = isMale;
    }
    public void setIsFemale(Boolean isFemale){
        this.isFemale = isFemale;
    }
    public void setUserID(String userID){
        this.userID = userID;
    }

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }

    public Integer getAge(){
        return this.age;
    }
    public Integer getHeightFeet(){
        return this.heightFeet;
    }
    public Integer getHeightInches(){
        return this.heightInches;
    }
    public Integer getWeight(){
        return this.weight;
    }
    public Boolean getIsMale(){
        return this.isMale;
    }
    public String getUserID(){
        return this.userID;
    }

    public String getErrorMessage(){return this.errorMessage;}


        public static int errorCheck(String input) {
            return parseInt(input, 0, 0);
        }

     /*  public static Boolean errorCheck(Boolean male, Boolean female){
            if (male = female) {
                return null;
            }
            else return female;
        } */

        public void resetErrorMessage() {
        errorMessage = "";
        }

        public Boolean errorCheck(){

            Boolean hasError = false;
            Queue <String> errorStringBuilder = new LinkedList<>();

            if(age == -1 || age == 0){
                errorStringBuilder.add("age");
                hasError = true;
            }
            if(heightFeet == -1 || heightFeet == 0){
                errorStringBuilder.add("height");
                hasError = true;
            }
            if(heightInches == -1){
                errorStringBuilder.add("inches");
                hasError = true;
            }
            if(weight == -1 || weight == 0){
                errorStringBuilder.add("weight");
                hasError = true;
            }
            if(isMale == isFemale){
                errorStringBuilder.add("sex");
                hasError = true;
            }

            while(!errorStringBuilder.isEmpty()){
                errorMessage = errorMessage + errorStringBuilder.remove() + ", ";
            }
            System.out.println(userID);
            return hasError;
        }


        public static Integer parseInt(String input, Integer index, Integer result){

            if (index < input.length()){
                char ch = input.charAt(index);

                if (ch >= '0' && ch <= '9') {
                    int digit = ch - '0';
                    result = result * 10 + digit;
                    return parseInt(input, index + 1, result);
                } else {
                    System.out.println("Error: Input is not an integer.");
                    // Handle the error here
                    // For example, you can return a default value or throw an exception
                    return -1; // returning -1 as default value
                }
            }
            return result;
        }

        public void pushtoDatabase(){
            firestore = FirebaseFirestore.getInstance();
            Map<String, Object> user = new HashMap<>();
            user.put("Age",age);
            user.put("heightFeet", heightFeet);
            user.put("heightInches", heightInches);
            user.put("weight", weight);
            user.put("isMale", isMale);
            user.put("email", email);
            DocumentReference docRef = firestore.collection("users").document(userID);
            docRef.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    System.out.println("LESSGO");
                }
            });

        }




}
