package com.example.ravivats.worknopsysmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.domain.Employee;
import com.google.gson.Gson;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    public static final String REGISTER_URL = "http://worknopsys.ml/api/employees/auth";
    public static final String EMPLOYEE_URL = "http://worknopsys.ml/api/employees/";
    public static final String KEY_USERNAME = "employeephone";
    public static final String KEY_PASSWORD = "employeepassword";
    Switch locationSwitch;
    EditText loginPersonalNoEditText;
    EditText loginPasswordEditText;
    Button loginBtn;
    StringRequest stringRequest;
    JsonObjectRequest jsEmpRequest;

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
                        stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equalsIgnoreCase("true")) {
                                            Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(LoginActivity.this, HoursReviewActivity.class));
                                        } else if (response.equalsIgnoreCase("false")) {
                                            Toast.makeText(LoginActivity.this, "Login failed! Please check login credentials.", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        error.printStackTrace();
                                        Toast.makeText(LoginActivity.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_LONG).show();
                                    }
                                }) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
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

                        requestQueue.add(stringRequest);
                        requestQueue.add(jsEmpRequest);

                    }
                });
    }
}
