package com.example.ravivats.worknopsysmobile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class CreateProjectDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button cpDetailsNxtBtn;
    DatePicker cpDetailsStartDatePicker;
    TextView cpDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cpDetailsNxtBtn = (Button) findViewById(R.id.cp_details_nxt_btn);
        cpDetailsTextView=(TextView ) findViewById(R.id.cp_customers_cust_name_label);
        cpDetailsStartDatePicker=(DatePicker) findViewById(R.id.cp_details_st_date_picker);
        cpDetailsNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long s = cpDetailsStartDatePicker.getMaxDate();
                cpDetailsTextView.setText(""+s);
                Toast.makeText(getApplicationContext(),""+s,Toast.LENGTH_LONG).show();
                //Intent in = new Intent(CreateProjectDetails.this, CreateProjectPictures.class);
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
            startActivity(new Intent(CreateProjectDetails.this, CreateCustomer.class));

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
}
