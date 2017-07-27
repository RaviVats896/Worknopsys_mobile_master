package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ravivats.worknopsysmobile.R;

import java.util.Calendar;

public class CreateWorkingOrder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText createWoStartDate, createWoAddress, createWoResources;
    Spinner createWoProjectID, createWoTaskID, createWoCustomerID;
    DatePickerDialog.OnDateSetListener createWoStartDateListener;
    Button createWoButton;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_working_order);

        createWoProjectID = (Spinner) findViewById(R.id.create_wo_editText_pID);
        createWoStartDate = (EditText) findViewById(R.id.create_wo_editText_sDate);
        createWoAddress = (EditText) findViewById(R.id.create_wo_editText_address);
        createWoTaskID = (Spinner) findViewById(R.id.create_wo_editText_tID);
        createWoCustomerID = (Spinner) findViewById(R.id.create_wo_editText_cID);
        createWoResources = (EditText) findViewById(R.id.create_wo_editText_resources);
        createWoButton = (Button) findViewById(R.id.create_wo_final_button);
        cal = Calendar.getInstance();

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