package com.example.ravivats.worknopsysmobile.Others;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HoursReviewResultsActivity extends AppCompatActivity {
    Bundle extras;
    ListView hoursReviewList;
    ProgressDialog progressDialog;
    StringRequest createTimingsRequest;
    JSONArray allTimings;
    static final String createTimings_URL = "http://207.154.200.101:5000/api/timings/find";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours_review_results);
        hoursReviewList = (ListView) findViewById(R.id.hoursReviewList);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        extras = getIntent().getExtras();

        // Refer parseJsonData() in ViewCustomers.java for detailed explanation.

        createTimingsRequest = new StringRequest(Request.Method.POST, createTimings_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJsonData(response);
                        try {
                            allTimings = new JSONArray(response);
                            // Constants.setCUSTOMERS(allCustomers);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HoursReviewResultsActivity.this, "Some error occurred!!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("project", extras.getString("projValue"));
                params.put("customer", extras.getString("custValue"));
                params.put("startdate", extras.getString("dateValue"));
                return params;
            }
        };

        requestQueue.add(createTimingsRequest);


//        ArrayList<String> hoursReviewData = new ArrayList<String>();
//
//        for (int i = 0; i < 10; ++i) {
//            hoursReviewData.add("31/08/2017 Wednesday 19:00 pm");
//        }
//        ArrayAdapter hoursReviewAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hoursReviewData);
//        hoursReviewList.setAdapter(hoursReviewAdapter);
    }

    void parseJsonData(String jsonString) {
        try {
            JSONArray customersArray = new JSONArray(jsonString);
            ArrayList<String> al = new ArrayList<String>();

            for (int i = 0; i < customersArray.length(); ++i) {
                if (customersArray.getJSONObject(i).getString("CName") != null) {
                    al.add(customersArray.getJSONObject(i).getString("CName"));
                }
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
            hoursReviewList.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(HoursReviewResultsActivity.this, "Retrieval successful. ", Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
    }
}
