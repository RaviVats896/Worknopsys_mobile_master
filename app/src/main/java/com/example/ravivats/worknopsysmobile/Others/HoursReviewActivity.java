package com.example.ravivats.worknopsysmobile.Others;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Customer.CreateCustomer;
import com.example.ravivats.worknopsysmobile.Customer.ViewCustomers;
import com.example.ravivats.worknopsysmobile.Project.CreateProjectDetails;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.WorkingOrders.MyWorkingOrders;
import com.example.ravivats.worknopsysmobile.Domain.Authorization;
import com.example.ravivats.worknopsysmobile.Domain.Customer;
import com.example.ravivats.worknopsysmobile.Domain.Task;
import com.example.ravivats.worknopsysmobile.Domain.WorkingOrder;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class HoursReviewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DatePickerDialog.OnDateSetListener {

    public static final String KEY_USERNAME = "employeephone";
    public static final String KEY_PASSWORD = "employeepassword";
    public static final String KEY_AUTHID = "userid";
    Map<String, String> taskMap, taskInvMap, customerMap, customerInvMap, projectMap, projectInvMap, categoryMap, categoryInvMap, cityMap, cityInvMap;
    TextView navDrawerNumber, navDrawerName;
    EditText hoursReviewDateID;
    Spinner hoursReviewCustomerID, hoursReviewProjectID;
    Button hoursReviewNextButton;
    DatePickerDialog.OnDateSetListener hoursReviewDateListener;
    ArrayList<String> customerNameList, projNameList;
    String projValue, custValue;
    Calendar mCal;
    String IMAGE_URL;
    ImageView mImageView;
    RequestQueue queue;
    ArrayAdapter<String> hoursReviewCustomerAdapter, hoursReviewProjectAdapter;
    final static String task_url = "http://207.154.200.101:5000/api/tasks";
    final static String customer_url = "http://207.154.200.101:5000/api/customers";
    final static String category_url = "http://207.154.200.101:5000/api/categories";
    final static String city_url = "http://207.154.200.101:5000/api/cities";
    final static String project_url = "http://207.154.200.101:5000/api/projectapp";
    final static String auth_url = "http://207.154.200.101:5000/api/auth/user";
    final static String LOGOUT_URL = "http://207.154.200.101:5000/api/employees/logout";
    final static String wo_url = "http://207.154.200.101:5000/api/woapp";


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
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"abc@gmail.com"});
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(HoursReviewActivity.this, "There are no email clients installed. Action can't be performed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        taskMap = new HashMap<>();
        customerMap = new HashMap<>();
        projectMap = new HashMap<>();
        taskInvMap = new HashMap<>();
        customerInvMap = new HashMap<>();
        projectInvMap = new HashMap<>();
        categoryMap = new HashMap<>();
        categoryInvMap = new HashMap<>();
        cityMap = new HashMap<>();
        cityInvMap = new HashMap<>();
        queue = Volley.newRequestQueue(this);
        customerNameList = new ArrayList<>();
        projNameList = new ArrayList<>();

        mCal = Calendar.getInstance();

        hoursReviewCustomerID = (Spinner) findViewById(R.id.hours_review_customer_spinner);
        hoursReviewProjectID = (Spinner) findViewById(R.id.hours_review_project_spinner);
        hoursReviewDateID = (EditText) findViewById(R.id.hours_review_date_spinner);
        hoursReviewNextButton = (Button) findViewById(R.id.hours_review_next_button);

        hoursReviewDateID.setKeyListener(null);

        hoursReviewCustomerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        hoursReviewCustomerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hoursReviewCustomerID.setAdapter(hoursReviewCustomerAdapter);


        hoursReviewProjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        hoursReviewProjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hoursReviewProjectID.setAdapter(hoursReviewProjectAdapter);


        hoursReviewDateID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp1 = new DatePickerDialog(HoursReviewActivity.this, hoursReviewDateListener, mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH), mCal.get(Calendar.DAY_OF_MONTH));
                dp1.show();
            }
        });

        hoursReviewNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HoursReviewActivity.this, HoursReviewResultsActivity.class);
                intent.putExtra("projValue", projValue);
                intent.putExtra("custValue", custValue);
                intent.putExtra("dateValue", hoursReviewDateID.getText().toString());
                startActivity(intent);
            }
        });

        hoursReviewDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCal.set(Calendar.YEAR, year);
                mCal.set(Calendar.MONTH, month);
                mCal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                hoursReviewDateID.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };

        hoursReviewCustomerID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                custValue = customerMap.get(hoursReviewCustomerID.getItemAtPosition(position).toString());
                Toast.makeText(HoursReviewActivity.this, "" + custValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        hoursReviewProjectID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                projValue = projectMap.get(hoursReviewProjectID.getItemAtPosition(position).toString());
                Toast.makeText(HoursReviewActivity.this, "" + projValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        StringRequest authRequest = new StringRequest(Request.Method.POST, auth_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String response1 = response.replace("[", "").replace("]", "").trim();
                        Gson gson = new Gson();
                        Authorization auth = gson.fromJson(response1, Authorization.class);
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
                Map<String, String> params = new HashMap<>();
                params.put(KEY_AUTHID, Constants.getEMPLOYEE().getId());
                return params;
            }

        };
        JsonArrayRequest taskRetRequest = new JsonArrayRequest(Request.Method.GET, task_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject last = response.getJSONObject(i);
                        Gson gson = new Gson();
                        Task currentTask = gson.fromJson(last.toString(), Task.class);
                        taskMap.put(currentTask.getTask(), currentTask.getId());
                        taskInvMap.put(currentTask.getId(), currentTask.getTask());
                    }
                    Constants.setTaskMap(taskMap);
                    Constants.setTaskInvMap(taskInvMap);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoursReviewActivity.this, "Error occurred in fetching tasks!", Toast.LENGTH_SHORT).show();

            }
        });
        JsonArrayRequest custRetRequest = new JsonArrayRequest(Request.Method.GET, customer_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject last = response.getJSONObject(i);
                        Gson gson = new Gson();
                        Customer currentCustomer = gson.fromJson(last.toString(), Customer.class);
                        customerMap.put(currentCustomer.getSalutation() + " " + currentCustomer.getCName(), currentCustomer.getId());
                        customerInvMap.put(currentCustomer.getId(), currentCustomer.getSalutation() + " " + currentCustomer.getCName());
                    }
                    Constants.setCustomerMap(customerMap);
                    Constants.setCustomerInvMap(customerInvMap);
                    for (Map.Entry<String, String> pairs : customerMap.entrySet()) {
                        String key = pairs.getKey(); //String value = pairs.getValue();
                        hoursReviewCustomerAdapter.add(key);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoursReviewActivity.this, "Error occurred in fetching customers!", Toast.LENGTH_SHORT).show();

            }
        });
        JsonArrayRequest projectRetRequest = new JsonArrayRequest(Request.Method.GET, project_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject last = response.getJSONObject(i);
                        //Gson gson = new Gson();
                        //Customer currentCustomer = gson.fromJson(last.toString(), Customer.class);
                        projectMap.put(last.getString("ProjectName"), last.getString("_id"));
                        projectInvMap.put(last.getString("_id"), last.getString("ProjectName"));
                    }
                    Constants.setProjectMap(projectMap);
                    Constants.setProjectInvMap(projectInvMap);
                    for (Map.Entry<String, String> pairs : projectMap.entrySet()) {
                        String key = pairs.getKey(); //String value = pairs.getValue();
                        hoursReviewProjectAdapter.add(key);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoursReviewActivity.this, "Error occurred in fetching projects!", Toast.LENGTH_SHORT).show();
            }
        });

        JsonArrayRequest woRetRequest = new JsonArrayRequest(Request.Method.GET, wo_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    ArrayList<WorkingOrder> results = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject last = response.getJSONObject(i);
                        Gson gson = new Gson();
                        WorkingOrder workingOrder = gson.fromJson(last.toString(), WorkingOrder.class);
                        results.add(i, workingOrder);
                    }
                    Constants.setWorkingOrders(results);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoursReviewActivity.this, "Error occurred in fetching WOs!", Toast.LENGTH_SHORT).show();
            }
        });

        JsonArrayRequest categoryRetRequest = new JsonArrayRequest(Request.Method.GET, category_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject last = response.getJSONObject(i);
                        categoryMap.put(last.getString("name"), last.getString("id"));
                        categoryInvMap.put(last.getString("id"), last.getString("name"));
                    }
                    Constants.setCategoryMap(categoryMap);
                    Constants.setCategoryInvMap(categoryInvMap);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoursReviewActivity.this, "Error occurred in fetching categories!", Toast.LENGTH_SHORT).show();
            }
        });

        JsonArrayRequest cityRetRequest = new JsonArrayRequest(Request.Method.GET, city_url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject last = response.getJSONObject(i);
                        cityMap.put(last.getString("name"), last.getString("id"));
                        cityInvMap.put(last.getString("id"), last.getString("name"));
                    }
                    Constants.setCityMap(cityMap);
                    Constants.setCityInvMap(cityInvMap);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(HoursReviewActivity.this, "Error occurred in fetching cities!", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(authRequest);
        queue.add(categoryRetRequest);
        queue.add(taskRetRequest);
        queue.add(custRetRequest);
        queue.add(projectRetRequest);
        queue.add(woRetRequest);
        queue.add(cityRetRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_customers, menu);
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
        if (id == R.id.nav_hours_review) {
        } else if (id == R.id.nav_working_orders) {
            startActivity(new Intent(HoursReviewActivity.this, MyWorkingOrders.class));
        } else if (id == R.id.nav_facebook) {
            startActivity(new Intent(HoursReviewActivity.this, BrowserActivity.class).putExtra("choice", 1));
        } else if (id == R.id.nav_youtube) {
            startActivity(new Intent(HoursReviewActivity.this, BrowserActivity.class).putExtra("choice", 2));
        } else if (id == R.id.nav_whatsapp) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_whatsapp));
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else if (id == R.id.nav_qr_code) {
            startActivity(new Intent(HoursReviewActivity.this, QRScanActivity.class));
        } else if (id == R.id.nav_create_customer) {
            startActivity(new Intent(HoursReviewActivity.this, CreateCustomer.class));
        } else if (id == R.id.nav_create_complaint) {
            startActivity(new Intent(HoursReviewActivity.this, CreateComplaint.class));
        } else if (id == R.id.nav_create_project) {
            startActivity(new Intent(HoursReviewActivity.this, CreateProjectDetails.class));
        }
//        else if (id == R.id.nav_mgmt_working_orders) {
//            startActivity(new Intent(HoursReviewActivity.this, ManagementWorkingOrders.class));
//        }
        else if (id == R.id.nav_config) {
            startActivity(new Intent(HoursReviewActivity.this, ConfigurationActivity.class));
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
                            Toast.makeText(HoursReviewActivity.this, "Logout failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(HoursReviewActivity.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(KEY_USERNAME, Constants.getEMPLOYEE().getPhone());
                params.put(KEY_PASSWORD, Constants.getEMPLOYEE().getPassword());

                return params;
            }

        };
        queue.add(logoutRequest);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }
}
