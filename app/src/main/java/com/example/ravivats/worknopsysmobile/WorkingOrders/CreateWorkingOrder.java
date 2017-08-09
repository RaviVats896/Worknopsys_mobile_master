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

import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class CreateWorkingOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText createWoStartDate, createWoAddress, createWoResources;
    Spinner createWoProjectID, createWoTaskID, createWoCustomerID;
    DatePickerDialog.OnDateSetListener createWoStartDateListener;
    Button createWoButton;
    Map<String, String> taskMap, custMap;
    ArrayList<String> taskNameList, custNameList;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_working_order);

        createWoProjectID = (Spinner) findViewById(R.id.create_wo_spinner_pID);
        createWoStartDate = (EditText) findViewById(R.id.create_wo_editText_sDate);
        createWoAddress = (EditText) findViewById(R.id.create_wo_editText_address);
        createWoTaskID = (Spinner) findViewById(R.id.create_wo_spinner_tID);
        createWoCustomerID = (Spinner) findViewById(R.id.create_wo_spinner_cID);
        createWoResources = (EditText) findViewById(R.id.create_wo_editText_resources);
        createWoButton = (Button) findViewById(R.id.create_wo_final_button);

        cal = Calendar.getInstance();
        taskMap = Constants.getTaskMap();
        custMap = Constants.getCustomerMap();
        taskNameList = new ArrayList<String>();
        custNameList = new ArrayList<String>();
        for (Map.Entry<String, String> pairs : taskMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            taskNameList.add(key);
        }

        for (Map.Entry<String, String> pairs : custMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            custNameList.add(key);
        }

        ArrayAdapter<String> taskSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskNameList);
        taskSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoTaskID.setAdapter(taskSpinnerAdapter);

        ArrayAdapter<String> custSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, custNameList);
        custSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        createWoCustomerID.setAdapter(custSpinnerAdapter);

        createWoTaskID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = taskMap.get(createWoTaskID.getItemAtPosition(position).toString());
                Toast.makeText(CreateWorkingOrder.this, "" + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        createWoCustomerID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String value = custMap.get(createWoCustomerID.getItemAtPosition(position).toString());
                Toast.makeText(CreateWorkingOrder.this, "" + value, Toast.LENGTH_SHORT).show();
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