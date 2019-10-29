package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, baseUrl + api, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // 2.a. response logic - store response to a vector array
                    Vector newsData = new Vector();
                    JSONArray array = response.getJSONArray("articles");

                    for (int i = 0; i < array.length(); i++) {
                        NewsData news = new NewsData();
                        news.setData(array.getJSONObject(i));
                        newsData.add(news);
                    }
                    setView(newsData);
                } catch (Exception e) {
                    System.out.println("Logged from JsonObjectRequest() in NewsRestApi.java: " + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });
        // 3. add request to request queue
        requestQueue.add(jsonObjectRequest);
    }
}
