package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ravivats.worknopsysmobile.WorkingOrderObject;
import com.example.ravivats.worknopsysmobile.WorkingOrderViewAdapter;
import com.example.ravivats.worknopsysmobile.R;

import java.util.ArrayList;

public class MyOrdersActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "MyOrdersActivity";
    FloatingActionButton createWO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new WorkingOrderViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
        createWO= (FloatingActionButton) findViewById(R.id.create_new_wo_button);
        createWO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyOrdersActivity.this,CreateWorkingOrder.class));
            }
        });
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
        if(id==R.id.create_working_orders){
            startActivity(new Intent(MyOrdersActivity.this,CreateWorkingOrder.class));
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
            }
        });
    }

    private ArrayList<WorkingOrderObject> getDataSet() {
        ArrayList results = new ArrayList<WorkingOrderObject>();
        for (int index = 0; index < 6; index++) {
            WorkingOrderObject obj = new WorkingOrderObject("Saturday ,","17th December 2016","Monteu Las Vegas Road","Monteu SA","Sankt, 22, 6989A");
            results.add(index, obj);
        }
        return results;
    }

}
