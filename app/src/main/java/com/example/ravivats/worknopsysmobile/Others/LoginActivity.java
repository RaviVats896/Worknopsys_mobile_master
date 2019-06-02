package com.example.ravivats.worknopsysmobile.Others;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.WorkingOrders.CreateWorkingOrder;
import com.example.ravivats.worknopsysmobile.Domain.Employee;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    static final String REGISTER_URL = "http://207.154.200.101:5000/api/employees/auth";
    static final String EMPLOYEE_URL = "http://207.154.200.101:5000/api/employees/";
    static final String RESOURCES_URL = "http://207.154.200.101:5000/api/resources";
    static final String KEY_USERNAME = "employeephone";
    static final String KEY_PASSWORD = "employeepassword";
    Map<String, String> resourceMap, resourceInvMap;
    Switch locationSwitch;
    String h1, h2;
    EditText loginPersonalNoEditText;
    EditText loginPasswordEditText;
    Button loginBtn;
    StringRequest stringRequest;
    JsonObjectRequest jsEmpRequest;
    JsonArrayRequest resourceRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        locationSwitch = (Switch) findViewById(R.id.loginLocationSwitch);
        loginPersonalNoEditText = (EditText) findViewById(R.id.loginPersonalNoEditText);
        loginPasswordEditText = (EditText) findViewById(R.id.loginPasswordEditText);
        loginBtn = (Button) findViewById(R.id.loginLoginBtn);
        loginPersonalNoEditText.getText().clear();
        loginPasswordEditText.getText().clear();
        locationSwitch.setChecked(false);
        resourceMap = new HashMap<>();
        resourceInvMap = new HashMap<>();
        Context context = getApplicationContext();
        final SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        h1 = sharedPref.getString(KEY_USERNAME, "--");
        if (!h1.equals("--")) {
            loginPersonalNoEditText.setText(h1);
        }
        h2 = sharedPref.getString(KEY_PASSWORD, "--");
        if (!h1.equals("--")) {
            loginPasswordEditText.setText(h2);
        }
        locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplicationContext(), "Geo-location services enabled ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Geo-location services disabled ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String employeePhone = loginPersonalNoEditText.getText().toString().trim();
                        final String employeePassword = loginPasswordEditText.getText().toString().trim();
                        SharedPreferences.Editor editor = sharedPref.edit();
                        if (h1.equals("--")) {
                            editor.putString(KEY_USERNAME, employeePhone);
                            editor.putString(KEY_PASSWORD, employeePassword);
                            editor.apply();
                        }
                        stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equalsIgnoreCase("true")) {
                                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(LoginActivity.this, HoursReviewActivity.class));
                                        } else if (response.equalsIgnoreCase("false")) {
                                            Toast.makeText(LoginActivity.this, "Login failed! Please check login credentials.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                        Toast.makeText(LoginActivity.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                                        /* * *
                                         * Used in case of web-server failures to test the application
                                         * startActivity(new Intent(LoginActivity.this, CreateWorkingOrder.class));
                                         */
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<>();
                                params.put(KEY_USERNAME, employeePhone);
                                params.put(KEY_PASSWORD, employeePassword);

                                return params;
                            }
                        };
                        jsEmpRequest = new JsonObjectRequest
                                (Request.Method.GET, EMPLOYEE_URL + employeePhone, null, new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Gson gson = new Gson();
                                        Employee emp = gson.fromJson(response.toString(), Employee.class);
                                        Constants.setEMPLOYEE(emp);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                    }
                                });
                        resourceRequest = new JsonArrayRequest(Request.Method.GET, RESOURCES_URL, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                try {
                                    for (int i = 2; i < response.length(); i++) {
                                        JSONObject last = response.getJSONObject(i);
                                        resourceMap.put(last.getString("RListDesc1")+", "+last.getString("RListDesc2")+", "+last.getString("RListDesc3"), last.getString("_id"));
                                        resourceInvMap.put(last.getString("_id"), last.getString("RListDesc1")+", "+last.getString("RListDesc2")+", "+last.getString("RListDesc3"));
                                    }
                                    Constants.setResourceMap(resourceMap);
                                    Constants.setResourceInvMap(resourceInvMap);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(LoginActivity.this, "Error in resource retrieval.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, "Error in resource retrieval.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        requestQueue.add(stringRequest);
                        requestQueue.add(jsEmpRequest);
                        requestQueue.add(resourceRequest);
                    }
                });
    }

    @Override
    public void onBackPressed() {}
}
