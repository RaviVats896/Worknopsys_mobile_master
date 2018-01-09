package com.example.ravivats.worknopsysmobile.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateComplaint extends AppCompatActivity {
    static final String createComplaint_URL = "http://207.154.200.101:5000/api/complaint/create";
    Spinner categorySpinner, noOfPeopleSpinner, facingSinceSpinner, citySpinner;
    String categoryValue, noOfPeopleValue, facingSinceValue, cityValue;
    Map<String, String> categoryMap, cityMap;
    ArrayList<String> categoryNameList, cityNameList;
    ArrayAdapter<CharSequence> noOfPeopleAdapter, facingSinceAdapter;
    Button ccSubmitButton;
    EditText complaintTitle, complaintDesc;
    String facingSinceArray[] = {"1–20 days", "21–50 days", "50+ days"};
    String noOfPeopleArray[] = {"1–20 people", "21–50 people", "50+ people"};
    StringRequest createComplaintRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        categorySpinner = (Spinner) findViewById(R.id.cc_category_spinner);
        citySpinner = (Spinner) findViewById(R.id.cc_city_spinner);
        noOfPeopleSpinner = (Spinner) findViewById(R.id.cc_number_of_people_spinner);
        facingSinceSpinner = (Spinner) findViewById(R.id.cc_facing_since_spinner);
        ccSubmitButton = (Button) findViewById(R.id.cc_submit_button);
        complaintTitle = (EditText) findViewById(R.id.cc_title_edit_text);
        complaintDesc = (EditText) findViewById(R.id.cc_description_edit_text);
        final RequestQueue requestComplaintQueue = Volley.newRequestQueue(this);
        cityMap = Constants.getCityMap();
        categoryMap = Constants.getCategoryMap();

        cityNameList = new ArrayList<String>();
        categoryNameList = new ArrayList<String>();

        for (Map.Entry<String, String> pairs : cityMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            cityNameList.add(key);
        }

        for (Map.Entry<String, String> pairs : categoryMap.entrySet()) {
            String key = pairs.getKey(); //String value = pairs.getValue();
            categoryNameList.add(key);
        }

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categoryNameList);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityNameList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        noOfPeopleAdapter = ArrayAdapter.createFromResource(this, R.array.no_of_people_array, android.R.layout.simple_spinner_item);
        noOfPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
        noOfPeopleSpinner.setAdapter(noOfPeopleAdapter);

        facingSinceAdapter = ArrayAdapter.createFromResource(this, R.array.facing_since_array, android.R.layout.simple_spinner_item);
        facingSinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
        facingSinceSpinner.setAdapter(facingSinceAdapter);

        facingSinceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                facingSinceValue = facingSinceArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                facingSinceValue = facingSinceArray[0];
            }
        });

        noOfPeopleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                noOfPeopleValue = noOfPeopleArray[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                noOfPeopleValue = noOfPeopleArray[0];
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoryValue = categoryMap.get(categorySpinner.getItemAtPosition(position).toString());
                Toast.makeText(CreateComplaint.this, "" + categoryValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityValue = cityMap.get(citySpinner.getItemAtPosition(position).toString());
                Toast.makeText(CreateComplaint.this, "" + cityValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ccSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (facingSinceValue== null || noOfPeopleValue ==null  || complaintTitle.getText().toString().equals("")|| complaintDesc.getText().toString().equals("")) {
                    Toast.makeText(CreateComplaint.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                } else {
                    final String title = complaintTitle.getText().toString();
                    final String description = complaintDesc.getText().toString();
                    createComplaintRequest = new StringRequest(Request.Method.POST, createComplaint_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(CreateComplaint.this, "Complaint submission successful!" + response, Toast.LENGTH_SHORT).show();
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
                            params.put("workerid", Constants.getEMPLOYEE().getId());
                            params.put("title", title);
                            params.put("description", description);
                            params.put("people", noOfPeopleValue);
                            params.put("days", facingSinceValue);
                            params.put("categoryid", categoryValue);
                            params.put("cityid", cityValue);

                            return params;
                        }
                    };

                    requestComplaintQueue.add(createComplaintRequest);
                }
            }
        });
    }
}
