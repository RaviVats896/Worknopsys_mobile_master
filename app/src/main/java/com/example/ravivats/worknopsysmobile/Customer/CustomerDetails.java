package com.example.ravivats.worknopsysmobile.Customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ravivats.worknopsysmobile.R;

public class CustomerDetails extends AppCompatActivity {
    String selectedCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        selectedCustomer = getIntent().getStringExtra("selectedCustomer");
    }
}
