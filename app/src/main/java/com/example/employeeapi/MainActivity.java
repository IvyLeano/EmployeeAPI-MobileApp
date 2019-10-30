package com.example.employeeapi;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import org.json.JSONObject;
import java.util.Vector;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
                        try {
                            // 2.a. response logic - store response to a vector array
                            Vector employees = new Vector();

                            for (int i = 0; i < response.length(); i++) {
                                EmployeeData employee = new EmployeeData();
                                employee.setData(response.getJSONObject(i));
                                employees.add(employee);
                            }
                            setView(employees);
                        } catch(Exception e) {
                            System.out.println("Logged from JsonArrayRequest() in MainActivity.java: " + e);
                        }
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

    //TODO 1: fix the setView to add data to table view
    public void setView(Vector employees) {
        TableLayout tableLayout = findViewById(R.id.table_layout);
        for(int i = 0; i < employees.size(); i++){
            EmployeeData employee = (EmployeeData) employees.get(i);
            TableRow tableRow = new TableRow(this);

            TextView empNum = new TextView(this);
            empNum.setText(employee.getEmployeeID());
            empNum.setPadding(20, 20, 20, 20);

            TextView empName = new TextView(this);
            empName.setText(employee.getFullName());
            empName.setGravity(Gravity.LEFT);
            empName.setPadding(3,3,3,3);

            tableRow.addView(empNum);
            tableRow.addView(empName);

            tableLayout.addView(tableRow);

//                ToDo 4: add button for more employee info activity for content
//                ToDo 5: add button for add new employee

        }
    }
}
