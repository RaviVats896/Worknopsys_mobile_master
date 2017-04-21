package com.example.ravivats.worknopsysmobile.Project;

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
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.AboutActivity;
import com.example.ravivats.worknopsysmobile.ConfigurationActivity;
import com.example.ravivats.worknopsysmobile.CpOrderDatePickerFragment;
import com.example.ravivats.worknopsysmobile.CpOrderTimePickerFragment;
import com.example.ravivats.worknopsysmobile.Customer.CreateCustomer;
import com.example.ravivats.worknopsysmobile.HoursReviewActivity;
import com.example.ravivats.worknopsysmobile.LoginActivity;
import com.example.ravivats.worknopsysmobile.R;

import java.util.HashMap;
import java.util.Map;

public class CreateProjectOrders extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    public static final String CREATE_URL = "http://worknopsys.ml/api/projectapp/create";
    Button cpOrdersSubmitBtn;
    EditText cpOrdersDatePickerButton,cpOrdersActivity,cpOrdersShortText,cpOrdersActivityStatus;
    EditText cpOrdersTimePickerButton,cpOrdersLongtext;
    Bundle totalBundle;
    StringRequest postRequest1;

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
        totalBundle=getIntent().getExtras();
        final RequestQueue createProjectQueue = Volley.newRequestQueue(this);
        cpOrdersSubmitBtn = (Button) findViewById(R.id.cp_orders_nxt_button);
        cpOrdersDatePickerButton = (EditText) findViewById(R.id.cp_orders_date_edit_text);
        cpOrdersTimePickerButton = (EditText) findViewById(R.id.cp_orders_time1_edit_text);
        cpOrdersActivity=(EditText) findViewById(R.id.cp_orders_activity_edit_text);
        cpOrdersLongtext=(EditText) findViewById(R.id.cp_orders_long_txt_edit_text);
        cpOrdersShortText=(EditText) findViewById(R.id.cp_orders_short_txt_edit_text);
        cpOrdersActivityStatus=(EditText) findViewById(R.id.cp_orders_activity_status_edit_text);

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
        cpOrdersSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRequest1 = new StringRequest(Request.Method.POST, CREATE_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equalsIgnoreCase("true")) {
                                    Toast.makeText(CreateProjectOrders.this, "Project Creation successful!", Toast.LENGTH_LONG).show();

                                } else{
                                    Toast.makeText(CreateProjectOrders.this, "Project Creation failed!", Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(CreateProjectOrders.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("customername", totalBundle.getString("CustomerName"));
                        params.put("fileurl", totalBundle.getString("PictureId"));
                        params.put("projectname", totalBundle.getString("ProjectName"));
                        params.put("startdate", totalBundle.getString("ProjectStartDate"));
                        params.put("status", totalBundle.getString("ProjectStatus"));
                        params.put("salutation", totalBundle.getString("Salutation"));
                        params.put("contactp", totalBundle.getString("Contact"));
                        params.put("fax", totalBundle.getString("Fax"));
                        params.put("email", totalBundle.getString("Email"));
                        params.put("street", totalBundle.getString("StreetDetails"));
                        params.put("city", totalBundle.getString("StreetCity"));
                        params.put("zip", totalBundle.getString("StreetZip"));
                        params.put("activity", cpOrdersActivity.getText().toString());
                        params.put("shorttext", cpOrdersShortText.getText().toString());
                        params.put("actstatus", cpOrdersActivityStatus.getText().toString());
                        params.put("longtext", cpOrdersLongtext.getText().toString());
                        params.put("date", cpOrdersDatePickerButton.getText().toString());
                        params.put("time", cpOrdersTimePickerButton.getText().toString());
                        return params;
                    }
                };
                createProjectQueue.add(postRequest1);
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
            startActivity(new Intent(CreateProjectOrders.this, CreateProjectDetails.class));
        } else if (id == R.id.nav_mgmt_working_orders) {

        } else if (id == R.id.nav_hours_review) {
            startActivity(new Intent(CreateProjectOrders.this, HoursReviewActivity.class));
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(CreateProjectOrders.this, ConfigurationActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(CreateProjectOrders.this, LoginActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(CreateProjectOrders.this, AboutActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.v("Date",year+"  "+month+"  "+dayOfMonth);
        cpOrdersDatePickerButton.setText(new StringBuilder().append(dayOfMonth).append("/")
                .append(month+1).append("/").append(year));
    }

    @Override
    public void  onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Log.v("Time",hourOfDay+"  "+minute);
        cpOrdersTimePickerButton.setText(new StringBuilder().append(hourOfDay).append(":")
                .append(minute));
    }
}
