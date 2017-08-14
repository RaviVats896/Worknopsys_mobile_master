package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.R;

public class MyWorkingOrderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_working_order_details);
        int position= getIntent().getIntExtra("position",0);
        Toast.makeText(this, "This is "+position, Toast.LENGTH_SHORT).show();
    }
}
