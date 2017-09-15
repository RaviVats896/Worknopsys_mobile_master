package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.ravivats.worknopsysmobile.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateWorkingOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static final String createWO_URL = "http://worknopsys.ml:5000/api/woapp/create";
    EditText createWoStartDate, createWoAddress;
    Spinner createWoProjectID, createWoTaskID, createWoCustomerID, createWoResourceID;
    DatePickerDialog.OnDateSetListener createWoStartDateListener;
    Button createWoButton;
    String taskValue, projectValue, customerValue, resourceValue;
    Map<String, String> taskMap, custMap, projectMap, resourceMap;
    ArrayList<String> taskNameList, custNameList, projectNameList, resourceNameList;
    Calendar cal;
    StringRequest createWORequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_working_order);

        createWoProjectID = (Spinner) findViewById(R.id.create_wo_spinner_pID);
        createWoStartDate = (EditText) findViewById(R.id.create_wo_editText_sDate);
        createWoAddress = (EditText) findViewById(R.id.create_wo_editText_address);
        createWoTaskID = (Spinner) findViewById(R.id.create_wo_spinner_tID);
        createWoCustomerID = (Spinner) findViewById(R.id.create_wo_spinner_cID);
        createWoResourceID = (Spinner) findViewById(R.id.create_wo_spinner_rID);
        createWoButton = (Button) findViewById(R.id.create_wo_final_button);
        final RequestQueue requestWOQueue = Volley.newRequestQueue(this);
        cal = Calendar.getInstance();
        taskMap = Constants.getTaskMap();
        custMap = Constants.getCustomerMap();
        projectMap = Constants.getProjectMap();
        resourceMap =  Constants.getResourceMap();
        taskNameList = new ArrayList<String>();
        custNameList = new ArrayList<String>();
        projectNameList = new ArrayList<String>();
        resourceNameList = new ArrayList<String>();
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

        for (Map.Entry<String, String> pairs : resourceMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            resourceNameList.add(key);
        }

        createWoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (createWoStartDate.getText().toString().equals("") || createWoAddress.getText().toString().equals("")) {
                    Toast.makeText(CreateWorkingOrder.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                } else {
                    final String startDate = createWoStartDate.getText().toString();
                    final String address = createWoAddress.getText().toString();
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
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("startdate", startDate);
                            params.put("task", taskValue);
                            params.put("project", projectValue);
                            params.put("address", address);
                            params.put("customer", customerValue);
                            params.put("employee", Constants.getEMPLOYEE().getId());
                            params.put("resources", resourceValue);

                            return params;
                        }

                    };

                    requestWOQueue.add(createWORequest);
                }

            }
        });

        ArrayAdapter<String> taskSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskNameList);
        taskSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoTaskID.setAdapter(taskSpinnerAdapter);

        ArrayAdapter<String> resourceSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resourceNameList);
        resourceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoResourceID.setAdapter(resourceSpinnerAdapter);

        ArrayAdapter<String> custSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, custNameList);
        custSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoCustomerID.setAdapter(custSpinnerAdapter);

        ArrayAdapter<String> projectSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, projectNameList);
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

        createWoResourceID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                resourceValue = resourceMap.get(createWoResourceID.getItemAtPosition(position).toString());
                Toast.makeText(CreateWorkingOrder.this, "" + resourceValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                createWoStartDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

}