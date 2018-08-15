package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.domain.WorkingOrder;

import java.util.ArrayList;
import java.util.Map;

public class MyWorkingOrderDetails extends AppCompatActivity {
    ArrayList<WorkingOrder> workingOrders;
    WorkingOrder workingOrder;
    int workingOrderIndex;
    TextView editWOResources, editWOCustomerName, editWOTaskName, editWOProjectName, editWOStartDate, editWOAddress;
    Button editWOTimingsBtn, editWOResourcesBtn;
    Map<String, String> taskInvMap, custInvMap, projectInvMap, resourceInvMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_working_order_details);
        workingOrderIndex = getIntent().getIntExtra("position", 0);
        workingOrders = new ArrayList<WorkingOrder>();
        workingOrders = Constants.getWorkingOrders();
        workingOrder = workingOrders.get(workingOrderIndex);
        taskInvMap = Constants.getTaskInvMap();
        custInvMap = Constants.getCustomerInvMap();
        projectInvMap = Constants.getProjectInvMap();
        resourceInvMap = Constants.getResourceInvMap();

        editWOResources = (TextView) findViewById(R.id.edit_wo_editText_resources);
        editWOCustomerName = (TextView) findViewById(R.id.edit_wo_spinner_cID);
        editWOTaskName = (TextView) findViewById(R.id.edit_wo_spinner_tID);
        editWOProjectName = (TextView) findViewById(R.id.edit_wo_spinner_pID);
        editWOStartDate = (TextView) findViewById(R.id.edit_wo_editText_sDate);
        editWOAddress = (TextView) findViewById(R.id.edit_wo_editText_address);
        editWOResourcesBtn = (Button) findViewById(R.id.edit_wo_resources_button);
        editWOTimingsBtn = (Button) findViewById(R.id.edit_wo_timings_button);


        //TODO:  Look into why resourceInvMap is getting null values. Maybe something is wrong with setResourceInvMap call.
        //editWOResources.setText("Resources: " + resourceInvMap.get(workingOrder.getResources()));
        editWOStartDate.setText("Date: " + workingOrder.getStartDate());
        editWOAddress.setText("Address: " + workingOrder.getAddress());
        editWOProjectName.setText(projectInvMap.get(workingOrder.getProject()));
        editWOCustomerName.setText(custInvMap.get(workingOrder.getCustomer()));
        editWOTaskName.setText(taskInvMap.get(workingOrder.getTask()));

        editWOResourcesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyWorkingOrderDetails.this, WOResources.class).putExtra("woPosition", workingOrderIndex));
            }
        });

        editWOTimingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyWorkingOrderDetails.this, TimingsEvidence.class);
                i.putExtra("woPosition", workingOrderIndex);
                i.putExtra("woCustomerId", workingOrder.getCustomer());
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MyWorkingOrderDetails.this, MyWorkingOrders.class));
    }
}
