package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Others.LoginActivity;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.WorkingOrderObject;
import com.example.ravivats.worknopsysmobile.WorkingOrderViewAdapter;
import com.example.ravivats.worknopsysmobile.domain.WorkingOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyWorkingOrders extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MyWorkingOrders";
    FloatingActionButton createWO;
    ArrayList<WorkingOrder> workingOrders;
    Map<String, String> taskInvMap, custInvMap, projectInvMap;
    RequestQueue myWOQueue;
    public static final String KEY_USERNAME = "employeephone";
    public static final String KEY_PASSWORD = "employeepassword";
    static final String LOGOUT_URL = "http://207.154.200.101:5000/api/employees/logout";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_working_orders);
        workingOrders = new ArrayList<WorkingOrder>();
        workingOrders = Constants.getWorkingOrders();
        taskInvMap = Constants.getTaskInvMap();
        custInvMap = Constants.getCustomerInvMap();
        projectInvMap = Constants.getProjectInvMap();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WorkingOrderViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        myWOQueue = Volley.newRequestQueue(this);
        createWO = (FloatingActionButton) findViewById(R.id.create_new_wo_button);
        createWO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyWorkingOrders.this, CreateWorkingOrder.class));
            }
        });
        Constants.setEvidenceWTime1("--:--");
        Constants.setEvidenceWTime2("--:--");
        Constants.setEvidenceBTime1("--:--");
        Constants.setEvidenceBTime2("--:--");
        Constants.setEvidenceGTime1("--:--");
        Constants.setEvidenceGTime2("--:--");
        Constants.setEvidenceRTime1("--:--");
        Constants.setEvidenceRTime2("--:--");
        Constants.setEvidenceWorkDate("--/--/----");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_working_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.create_working_orders) {
            startActivity(new Intent(MyWorkingOrders.this, CreateWorkingOrder.class));
        } else if(id == R.id.create_wo_logout){
            logoutFunction();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((WorkingOrderViewAdapter) mAdapter).setOnItemClickListener(new WorkingOrderViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
                startActivity(new Intent(MyWorkingOrders.this, MyWorkingOrderDetails.class).putExtra("position",position));
            }
        });
    }

    private void logoutFunction() {
        StringRequest logoutRequest = new StringRequest(Request.Method.POST, LOGOUT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equalsIgnoreCase("true")) {
                            Toast.makeText(MyWorkingOrders.this, "Logout successful.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MyWorkingOrders.this, LoginActivity.class));
                        } else if (response.equalsIgnoreCase("false")) {
                            Toast.makeText(MyWorkingOrders.this, "Logout failed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MyWorkingOrders.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
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
        myWOQueue.add(logoutRequest);
    }

    private ArrayList<WorkingOrderObject> getDataSet() {
        ArrayList results = new ArrayList<WorkingOrderObject>();
        for (int index = 0; index < workingOrders.size(); index++) {
            WorkingOrder workingOrder = workingOrders.get(index);
            WorkingOrderObject obj = new WorkingOrderObject(taskInvMap.get(workingOrder.getTask()), workingOrder.getStartDate(),projectInvMap.get(workingOrder.getProject()),  custInvMap.get(workingOrder.getCustomer()), workingOrder.getAddress());
            results.add(index, obj);
        }
        return results;
    }

}
