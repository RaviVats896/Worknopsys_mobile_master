package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.ravivats.worknopsysmobile.MgtWoAdapter;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.WorkingOrderObject;

import java.util.ArrayList;

public class ManagementWorkingOrders extends AppCompatActivity {
    private RecyclerView mRecyclerViewMgt;
    private RecyclerView.Adapter mAdapterMgt;
    private RecyclerView.LayoutManager mLayoutManagerMgt;
    private static String LOG_TAG = "MgtWoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management_working_orders);
        mRecyclerViewMgt = (RecyclerView) findViewById(R.id.my_recycler_view_mgt);
        mRecyclerViewMgt.setHasFixedSize(true);
        mLayoutManagerMgt = new LinearLayoutManager(this);
        mRecyclerViewMgt.setLayoutManager(mLayoutManagerMgt);
        mAdapterMgt = new MgtWoAdapter(getDataSet());
        mRecyclerViewMgt.setAdapter(mAdapterMgt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MgtWoAdapter) mAdapterMgt).setOnItemClickListener(new MgtWoAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }

    private ArrayList<WorkingOrderObject> getDataSet() {
        ArrayList results = new ArrayList<WorkingOrderObject>();
        for (int index = 0; index < 6; index++) {
            WorkingOrderObject obj = new WorkingOrderObject("Saturday ,", "17th December 2016", "Monteu Las Vegas Road", "Street Cleaning", "Monteu SA", "Sankt, 22, 6989A");
            results.add(index, obj);
        }
        return results;
    }
}
