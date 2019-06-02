package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Others.LoginActivity;
import com.example.ravivats.worknopsysmobile.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateWorkingOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText createWoStartDate, createWoHouseNumber, createWoStreet, createWoCity, createWoState, createWoCountry, createWoZipCode;
    Spinner createWoProjectID, createWoTaskID, createWoCustomerID;
    // createWoResourceID;
    DatePickerDialog.OnDateSetListener createWoStartDateListener;
    Button createWoButton;
    String taskValue, projectValue, customerValue;
    // resourceValue;
    Map<String, String> taskMap, custMap, projectMap;
    // resourceMap;
    ArrayList<String> taskNameList, custNameList, projectNameList;
    // resourceNameList;
    Calendar cal;
    StringRequest createWORequest;
    RequestQueue requestWOQueue;
    public static final String KEY_USERNAME = "employeephone";
    public static final String KEY_PASSWORD = "employeepassword";
    static final String LOGOUT_URL = "http://207.154.200.101:5000/api/employees/logout";
    static final String createWO_URL = "http://207.154.200.101:5000/api/woapp/create";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_working_order);

        createWoProjectID = (Spinner) findViewById(R.id.create_wo_spinner_pID);
        createWoStartDate = (EditText) findViewById(R.id.create_wo_editText_sDate);
        createWoHouseNumber = (EditText) findViewById(R.id.create_wo_editText_house_number);
        createWoStreet = (EditText) findViewById(R.id.create_wo_editText_street);
        createWoCity = (EditText) findViewById(R.id.create_wo_editText_city);
        createWoState = (EditText) findViewById(R.id.create_wo_editText_state);
        createWoCountry = (EditText) findViewById(R.id.create_wo_editText_country);
        createWoZipCode = (EditText) findViewById(R.id.create_wo_editText_zip_code);
        createWoTaskID = (Spinner) findViewById(R.id.create_wo_spinner_tID);
        createWoCustomerID = (Spinner) findViewById(R.id.create_wo_spinner_cID);
        // createWoResourceID = (Spinner) findViewById(R.id.create_wo_spinner_rID);
        createWoButton = (Button) findViewById(R.id.create_wo_final_button);
        requestWOQueue = Volley.newRequestQueue(this);
        cal = Calendar.getInstance();
        taskMap = Constants.getTaskMap();
        custMap = Constants.getCustomerMap();
        projectMap = Constants.getProjectMap();
        // resourceMap = Constants.getResourceMap();
        taskNameList = new ArrayList<>();
        custNameList = new ArrayList<>();
        projectNameList = new ArrayList<>();
        // resourceNameList = new ArrayList<>();
        for (Map.Entry<String, String> pairs : taskMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            taskNameList.add(key);
        }

        for (Map.Entry<String, String> pairs : custMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            custNameList.add(key);
        }

        for (Map.Entry<String, String> pairs : projectMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            projectNameList.add(key);
        }

//        for (Map.Entry<String, String> pairs : resourceMap.entrySet()) {
//            String key = pairs.getKey(); //String value = pairs.getValue();
//            resourceNameList.add(key);
//        }

        createWoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createWoStartDate.getText().toString().equals("") || createWoHouseNumber.getText().toString().equals("") || createWoStreet.getText().toString().equals("") || createWoCity.getText().toString().equals("") || createWoState.getText().toString().equals("") || createWoCountry.getText().toString().equals("") || createWoZipCode.getText().toString().equals("")) {
                    Toast.makeText(CreateWorkingOrder.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                } else {
                    final String startDate = createWoStartDate.getText().toString();
                    final String address = createWoHouseNumber.getText().toString() + ", " + createWoStreet.getText().toString() + ", " + createWoCity.getText().toString() + ", " + createWoState.getText().toString() + ", " + createWoCountry.getText().toString() + ". Zip: " + createWoZipCode.getText().toString();
                    createWORequest = new StringRequest(Request.Method.POST, createWO_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(CreateWorkingOrder.this, "Working order creation successful!" + response, Toast.LENGTH_SHORT).show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("startdate", startDate);
                            params.put("task", taskValue);
                            params.put("project", projectValue);
                            params.put("address", address);
                            params.put("customer", customerValue);
                            params.put("employee", Constants.getEMPLOYEE().getId());
                            // params.put("resources", resourceValue);
                            return params;
                        }
                    };

                    requestWOQueue.add(createWORequest);
                }

            }
        });

        ArrayAdapter<String> taskSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskNameList);
        taskSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoTaskID.setAdapter(taskSpinnerAdapter);

//        ArrayAdapter<String> resourceSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resourceNameList);
//        resourceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        createWoResourceID.setAdapter(resourceSpinnerAdapter);

        ArrayAdapter<String> custSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, custNameList);
        custSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoCustomerID.setAdapter(custSpinnerAdapter);

        ArrayAdapter<String> projectSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projectNameList);
        projectSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoProjectID.setAdapter(projectSpinnerAdapter);

        createWoTaskID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                taskValue = taskMap.get(createWoTaskID.getItemAtPosition(position).toString());
                Toast.makeText(CreateWorkingOrder.this, "" + taskValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        createWoResourceID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                resourceValue = resourceMap.get(createWoResourceID.getItemAtPosition(position).toString());
//                Toast.makeText(CreateWorkingOrder.this, "" + resourceValue, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//            }
//        });

        createWoCustomerID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerValue = custMap.get(createWoCustomerID.getItemAtPosition(position).toString());
                Toast.makeText(CreateWorkingOrder.this, "" + customerValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        createWoProjectID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                projectValue = projectMap.get(createWoProjectID.getItemAtPosition(position).toString());
                Toast.makeText(CreateWorkingOrder.this, "" + projectValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        createWoStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp1 = new DatePickerDialog(CreateWorkingOrder.this, createWoStartDateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dp1.show();
            }
        });

        createWoStartDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                Date date = new Date(year - 1900, month, dayOfMonth);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                createWoStartDate.setText(formatter.format(date));
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_working_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.choose_resources) {
            //startActivity(new Intent(CreateWorkingOrder.this, ViewCustomers.class));
        } else if (id == R.id.logout) {
            logoutFunction();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void logoutFunction() {
        StringRequest logoutRequest = new StringRequest(Request.Method.POST, LOGOUT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("true")) {
                            Toast.makeText(CreateWorkingOrder.this, "Logout successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CreateWorkingOrder.this, LoginActivity.class));
                        } else if (response.equalsIgnoreCase("false")) {
                            Toast.makeText(CreateWorkingOrder.this, "Logout failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(CreateWorkingOrder.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_USERNAME, Constants.getEMPLOYEE().getPhone());
                params.put(KEY_PASSWORD, Constants.getEMPLOYEE().getPassword());

                return params;
            }

        };
        requestWOQueue.add(logoutRequest);
    }

}