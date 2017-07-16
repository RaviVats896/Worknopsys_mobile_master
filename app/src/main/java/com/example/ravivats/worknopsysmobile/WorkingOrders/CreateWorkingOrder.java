package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ravivats.worknopsysmobile.R;

public class CreateWorkingOrder extends AppCompatActivity {
    EditText createWoProjectName, createWoStartDate, createWoAddress, createWoTaskName, createWoCustomerName;
    EditText createWoPersonal, createWoResources, createWoPostingTime, createWoBreakTime;
    Button createWoTravelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_working_order);

        createWoProjectName = (EditText) findViewById(R.id.create_wo_editText_pName);
        createWoStartDate = (EditText) findViewById(R.id.create_wo_editText_sDate);
        createWoAddress = (EditText) findViewById(R.id.create_wo_editText_address);
        createWoTaskName = (EditText) findViewById(R.id.create_wo_editText_tName);
        createWoCustomerName = (EditText) findViewById(R.id.create_wo_editText_cName);
        createWoPersonal = (EditText) findViewById(R.id.create_wo_editText_personal);
        createWoResources = (EditText) findViewById(R.id.create_wo_editText_resources);
        createWoPostingTime = (EditText) findViewById(R.id.create_wo_editText_postingTime);
        createWoBreakTime = (EditText) findViewById(R.id.create_wo_editText_breakTime);
        createWoTravelBtn = (Button) findViewById(R.id.create_wo_editText_travelBtn);

        createWoTravelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateWorkingOrder.this,TimingsEvidence.class));
            }
        });
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
}