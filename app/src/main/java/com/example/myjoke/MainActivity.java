package com.example.myjoke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    RequestQueue queue ;
    String url ="https://official-joke-api.appspot.com/random_joke";
    TextView txtJoke,txtID,txtSetup,txtType,txtPunch;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       queue=Volley.newRequestQueue(this);
       txtJoke = findViewById(R.id.txtJoke);
        txtID = findViewById(R.id.txtID);
        txtSetup = findViewById(R.id.txtSetup);
        txtType = findViewById(R.id.txtType);
        txtPunch = findViewById(R.id.txtPunch);

        progressBar=findViewById(R.id.progressBar);

    }

    public void getJoke(View view) {

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int ID = 0;
                        String Type,Setup,Punch;
                        try {
                             ID=response.getInt("id");
                             Type=response.getString("type");
                             Setup=response.getString("setup");
                             Punch=response.getString("punchline");
                             joke joke = new joke(ID,Type,Setup,Punch);
                             txtID.setText( joke.getID()+"");
                             txtID.setVisibility(View.VISIBLE);
                            txtType.setText(joke.getType()+"");
                            txtType.setVisibility(View.VISIBLE);
                            txtSetup.setText(joke.getSetup()+"");
                            txtSetup.setVisibility(View.VISIBLE);
                            txtPunch.setText(joke.getPunchLine()+"");
                            txtPunch.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        txtJoke.setText("Response:" + ID);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String err = error.toString();
                        txtJoke.setText("Cannot Get Data:" + error.toString());

                    }
                });

        queue.add(jsonObjectRequest);






    }

}