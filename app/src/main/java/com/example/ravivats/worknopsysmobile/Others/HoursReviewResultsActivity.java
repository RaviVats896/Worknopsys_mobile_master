package com.example.ravivats.worknopsysmobile.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ravivats.worknopsysmobile.R;

import java.util.ArrayList;

public class HoursReviewResultsActivity extends AppCompatActivity {
    ListView hoursReviewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_review_results);
        hoursReviewList = (ListView) findViewById(R.id.hoursReviewList);

        // Refer parseJsonData() in ViewCustomers.java for detailed explanation.
        ArrayList<String> hoursReviewData = new ArrayList();
        for (int i = 0; i < 10; ++i) {
            hoursReviewData.add("31/08/2017 Wednesday 19:00 pm");
        }
        ArrayAdapter hoursReviewAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hoursReviewData);
        hoursReviewList.setAdapter(hoursReviewAdapter);
    }
}
