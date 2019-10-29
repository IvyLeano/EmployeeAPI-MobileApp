package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import org.json.JSONObject;
import java.util.Vector;
import android.os.Bundle;
import org.json.JSONArray;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;


public class MainActivity extends AppCompatActivity {

    public String baseUrl = "https://glacial-shelf-53509.herokuapp.com/employees";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 3 tasks to call a REST API
        // 1. create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // 2. construct request
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, baseUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("Logged from onErrorResponse() in MainActivity.java " + error);
                    }
                });
        // 3. add request to request queue
        requestQueue.add(jsonArrayRequest);
    }
}
