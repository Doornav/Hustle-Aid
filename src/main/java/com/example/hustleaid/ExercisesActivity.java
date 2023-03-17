package com.example.hustleaid;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import java.net.URI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ExercisesActivity extends AppCompatActivity implements Serializable, RecyclerViewInterface {

    ArrayList<ExerciseDataModel> exerciseDataModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        Button toMainButton = findViewById(R.id.exerciseToMainButton);
        userInfo user1 = (userInfo) getIntent().getSerializableExtra("user1pass");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);





        getApiInfo();


        toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class).putExtra("user1pass",user1));
            }
        });
    }

    public void getApiInfo() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(ExercisesActivity.this);
        String url = "https://api.api-ninjas.com/v1/exercises?muscle=biceps";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               // System.out.println(("Response is: " + response.toString()));
                String[][] exerciseData = new String[response.length()][5];
                String[] exerciseDataTypes = {"name", "type", "equipment", "difficulty", "instructions"};
                try{
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject exerciseValues = (JSONObject) response.get(i);
                        for(int y = 0; y < 5; y++){
                            exerciseData[i][y] = (String) exerciseValues.get(exerciseDataTypes[y]);
                          //  System.out.println(exerciseData[i][y]);
                        }
                    }

                    setUpExerciseModels(exerciseData);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("SOMETHING WRONG" + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("X-Api-Key", "YtEOX5vNGTo01fBbnkpgRg==2aTciI3kMyg6U2fG");
                return params;
            }
        };

        queue.add(request);
    }

    private void setUpExerciseModels(String[][] exerciseData) {
        for (int i = 0; i < exerciseData.length; i++){
            exerciseDataModels.add(new ExerciseDataModel(exerciseData[i][0],exerciseData[i][1], exerciseData[i][3]));
        }
        RecyclerView recyclerView = findViewById(R.id.exerciseRecyclerView);
        ED_RecyclerViewAdapter adapter = new ED_RecyclerViewAdapter(this, exerciseDataModels, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position) {

    }
}