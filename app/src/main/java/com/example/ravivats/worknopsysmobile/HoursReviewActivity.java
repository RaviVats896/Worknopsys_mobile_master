package com.example.ravivats.worknopsysmobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Customer.CreateCustomer;
import com.example.ravivats.worknopsysmobile.Customer.ViewCustomers;
import com.example.ravivats.worknopsysmobile.Project.CreateProjectDetails;
import com.example.ravivats.worknopsysmobile.domain.Authorization;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

public class HoursReviewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String KEY_USERNAME = "employeephone";
    public static final String KEY_PASSWORD = "employeepassword";
    public static final String KEY_AUTHID = "userid";
    TextView navDrawerNumber;
    TextView navDrawerName;
    TextView txtView1;
    String IMAGE_URL;
    ImageView mImageView;
    RequestQueue queue;
    final static String url = "http://worknopsys.ml/api/tasks";
    final static String auth_url = "http://worknopsys.ml/api/auth/user";
    final static String LOGOUT_URL = "http://worknopsys.ml/api/employees/logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        txtView1 = (TextView) findViewById(R.id.textview1);
        queue = Volley.newRequestQueue(this);

         StringRequest authRequest = new StringRequest(Request.Method.POST, auth_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String response1=response.replace("[","").replace("]","").trim();
                        Gson gson = new Gson();
                        Authorization auth= gson.fromJson(response1, Authorization.class);
                        Constants.setAUTH(auth);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_AUTHID, Constants.getEMPLOYEE().getId());
                return params;
            }

        };
        queue.add(authRequest);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        //RequestQueue iQueue = Volley.newRequestQueue(this);
        mImageView = (ImageView) findViewById(R.id.navDrawerImageView);
        navDrawerNumber = (TextView) findViewById(R.id.navDrawerTxtViewNumber);
        navDrawerName = (TextView) findViewById(R.id.navDrawerTxtViewName);
        navDrawerNumber.setText(Constants.getEMPLOYEE().getPhone());
        navDrawerName.setText(Constants.getEMPLOYEE().getFirstName() + " " + Constants.getEMPLOYEE().getLastName());
        IMAGE_URL = Constants.getEMPLOYEE().getFileName();
        ImageRequest imageRequest = new ImageRequest(IMAGE_URL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        mImageView.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        mImageView.setImageResource(R.drawable.ic_assignment_black_24dp);
                    }
                });
        queue.add(imageRequest);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.view_customers) {
            startActivity(new Intent(HoursReviewActivity.this, ViewCustomers.class));
        }
        if (id == R.id.logout) {
            logoutFunction();
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_daily_overview) {
            // Handle the camera action
            startActivity(new Intent(HoursReviewActivity.this, ViewCustomers.class));
        } else if (id == R.id.nav_working_orders) {

        } else if (id == R.id.nav_create_customer) {
            startActivity(new Intent(HoursReviewActivity.this, CreateCustomer.class));
        } else if (id == R.id.nav_create_project) {

            startActivity(new Intent(HoursReviewActivity.this, CreateProjectDetails.class));
        } else if (id == R.id.nav_mgmt_working_orders) {

        } else if (id == R.id.nav_hours_review) {

        } else if (id == R.id.nav_config) {

        } else if (id == R.id.nav_logout) {
            logoutFunction();
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(HoursReviewActivity.this, AboutActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutFunction() {
        StringRequest logoutRequest = new StringRequest(Request.Method.POST, LOGOUT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("true")) {
                            Toast.makeText(HoursReviewActivity.this, "Logout successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(HoursReviewActivity.this, LoginActivity.class));
                        } else if (response.equalsIgnoreCase("false")) {
                            Toast.makeText(HoursReviewActivity.this, "Logout failed.", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(HoursReviewActivity.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME, Constants.getEMPLOYEE().getPhone());
                params.put(KEY_PASSWORD, Constants.getEMPLOYEE().getPassword());

                return params;
            }

        };
        queue.add(logoutRequest);
    }
}
