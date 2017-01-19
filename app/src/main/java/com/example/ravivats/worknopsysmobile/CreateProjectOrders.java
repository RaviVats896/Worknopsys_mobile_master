package com.example.ravivats.worknopsysmobile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;

public class CreateProjectOrders extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    Button cpOrdersNxtBtn;
    ImageButton cpOrdersDatePickerButton;
    ImageButton cpOrdersTimePickerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project_orders);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cpOrdersNxtBtn = (Button) findViewById(R.id.cp_orders_nxt_button);
        cpOrdersDatePickerButton = (ImageButton) findViewById(R.id.cp_orders_date_button);
        cpOrdersTimePickerButton = (ImageButton) findViewById(R.id.cp_orders_time1_button);
        cpOrdersTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CpOrderTimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
        cpOrdersDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new CpOrderDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        cpOrdersNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent in = new Intent(CreateProjectPictures.this, CreateProjectOrders.class);
                //startActivity(in);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_daily_overview) {
            // Handle the camera action
        } else if (id == R.id.nav_working_orders) {

        } else if (id == R.id.nav_create_customer) {
            startActivity(new Intent(CreateProjectOrders.this, CreateCustomer.class));
        } else if (id == R.id.nav_create_project) {
            //Intent i=new Intent(CreateProject.this,CreateProject.class);
            //startActivity(i);

        } else if (id == R.id.nav_mgmt_working_orders) {

        } else if (id == R.id.nav_hours_review) {

        } else if (id == R.id.nav_config) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_about) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.v("Date",year+"  "+month+"  "+dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.v("Time",hourOfDay+"  "+minute);
    }
}
