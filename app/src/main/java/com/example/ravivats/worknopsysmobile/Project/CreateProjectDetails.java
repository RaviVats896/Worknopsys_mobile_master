package com.example.ravivats.worknopsysmobile.Project;

import android.app.DatePickerDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.AboutActivity;
import com.example.ravivats.worknopsysmobile.ConfigurationActivity;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Customer.CreateCustomer;
import com.example.ravivats.worknopsysmobile.DatePickerFragment;
import com.example.ravivats.worknopsysmobile.HoursReviewActivity;
import com.example.ravivats.worknopsysmobile.LoginActivity;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.domain.Authorization;

public class CreateProjectDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener {
    EditText cpDetailsStartDatePickerBtn,cpDetailsCustName,cpDetailsProjName,cpDetailsStreet,cpDetailsCity,cpDetailsZip;
    Button cpDetailsNxtBtn;
    EditText cpDetailsFax,cpDetailsContact,cpDetailsEmail;
    Spinner salutationSpin;
    String projSalutation;
    Spinner cpDetailsProStatusSpinner;
    String ProjectStatus;
    String status[]={"In process","Completed"};
    String salutations[]={"Mr","Dr","Mrs","Ms"};
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
        salutationSpin = (Spinner) findViewById(R.id.cp_details_salutation_spinner);
        cpDetailsNxtBtn = (Button) findViewById(R.id.cp_details_nxt_btn);
        cpDetailsStartDatePickerBtn = (EditText) findViewById(R.id.cp_details_st_date_edit_text);
        cpDetailsProStatusSpinner = (Spinner) findViewById(R.id.cp_details_project_status_spinner);
        cpDetailsCustName =(EditText) findViewById(R.id.cp_details_cust_name_edit_text);
        cpDetailsProjName= (EditText) findViewById(R.id.cp_details_pro_name_edit_text);
        cpDetailsStreet= (EditText) findViewById(R.id.cp_details_street_edit_text);
        cpDetailsCity=(EditText) findViewById(R.id.cp_details_city_edit_text);
        cpDetailsZip=(EditText) findViewById(R.id.cp_details_zip_edit_text);
        cpDetailsFax =(EditText) findViewById(R.id.cp_details_fax_edit_text);
        cpDetailsEmail=(EditText) findViewById(R.id.cp_details_email_edit_text);
        cpDetailsContact=(EditText) findViewById(R.id.cp_details_contact_edit_text);
        Authorization auth=Constants.getAUTH();
        if(auth.getAdmEmpProjCreate()) {
            cpDetailsNxtBtn.setClickable(false);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.salutation_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        salutationSpin.setAdapter(adapter);
        salutationSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                projSalutation = salutations[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                projSalutation = salutations[0];
            }
        });
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.project_status_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        cpDetailsProStatusSpinner.setAdapter(adapter1);
        cpDetailsProStatusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProjectStatus = status[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ProjectStatus = status[0];
            }
        });
        cpDetailsStartDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
        cpDetailsNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(CreateProjectDetails.this, CreateProjectPictures.class);
                if(ProjectStatus==null){ProjectStatus=status[0];}
                in.putExtra("CustomerName",cpDetailsCustName.getText().toString());
                in.putExtra("ProjectName",cpDetailsProjName.getText().toString());
                in.putExtra("ProjectStartDate",cpDetailsStartDatePickerBtn.getText().toString());
                in.putExtra("StreetDetails",cpDetailsStreet.getText().toString());
                in.putExtra("StreetCity",cpDetailsCity.getText().toString());
                in.putExtra("StreetZip",cpDetailsZip.getText().toString());
                in.putExtra("ProjectStatus",ProjectStatus);
                if(projSalutation==null){ projSalutation=salutations[0];}
                in.putExtra("Salutation",projSalutation);
                in.putExtra("Fax",cpDetailsFax.getText().toString());
                in.putExtra("Email",cpDetailsEmail.getText().toString());
                in.putExtra("Contact",cpDetailsContact.getText().toString());
                startActivity(in);
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
        Authorization auth1=Constants.getAUTH();
        int id = item.getItemId();

        if (id == R.id.nav_daily_overview) {
            // Handle the camera action
        } else if (id == R.id.nav_working_orders) {

        } else if (id == R.id.nav_create_customer) {
            startActivity(new Intent(CreateProjectDetails.this, CreateCustomer.class));
        } else if (id == R.id.nav_create_project) {

        } else if (id == R.id.nav_mgmt_working_orders) {

        } else if (id == R.id.nav_hours_review) {
            startActivity(new Intent(CreateProjectDetails.this, HoursReviewActivity.class));
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(CreateProjectDetails.this, ConfigurationActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(CreateProjectDetails.this, LoginActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(CreateProjectDetails.this, AboutActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        cpDetailsStartDatePickerBtn.setText(new StringBuilder().append(dayOfMonth).append("/")
                .append(month+1).append("/").append(year));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
