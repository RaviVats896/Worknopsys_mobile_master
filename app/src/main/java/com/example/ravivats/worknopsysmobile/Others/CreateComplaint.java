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

import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;

import java.util.ArrayList;
import java.util.Map;

public class CreateComplaint extends AppCompatActivity {
    Spinner categorySpinner, noOfPeopleSpinner, facingSinceSpinner, citySpinner;
    String categoryValue, noOfPeopleValue, facingSinceValue, cityValue;
    Map<String, String> categoryMap, noOfPeopleMap, facingSinceMap, cityMap;
    ArrayList<String> categoryNameList, noOfPeopleNameList, facingSinceNameList, cityNameList;
    ArrayAdapter<CharSequence> noOfPeopleAdapter, facingSinceAdapter;
    Button ccSubmitButton;
    EditText complaintTitle, complaintDesc;

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
    }
}
