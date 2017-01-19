package com.example.ravivats.worknopsysmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CreateProjectDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener {
    ImageButton cpDetailsStartDatePickerBtn;
    Button cpDetailsNxtBtn;
    EditText cpDetailsEditText;
    Spinner cpDetailsProStatusSpinner;

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
        cpDetailsStartDatePickerBtn = (ImageButton) findViewById(R.id.cp_details_st_date_button);
        cpDetailsEditText =(EditText) findViewById(R.id.cp_details_pro_name_edit_text);
        cpDetailsProStatusSpinner = (Spinner) findViewById(R.id.cp_details_project_status_spinner);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.project_status_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        cpDetailsProStatusSpinner.setAdapter(adapter1);
        cpDetailsProStatusSpinner.setOnItemSelectedListener(this);


        cpDetailsNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CreateProjectDetails.this, CreateProjectPictures.class);
                startActivity(in);
            }
        });

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");

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


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //cpDetailsEditText.setText(year+" "+month+" "+dayOfMonth);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
