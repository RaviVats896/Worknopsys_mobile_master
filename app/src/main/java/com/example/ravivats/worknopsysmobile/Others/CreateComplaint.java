package com.example.ravivats.worknopsysmobile.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ravivats.worknopsysmobile.R;

public class CreateComplaint extends AppCompatActivity {
    Spinner categorySpinner, noOfPeopleSpinner, facingSinceSpinner;
    ArrayAdapter<CharSequence> categoryAdapter,noOfPeopleAdapter, facingSinceAdapter;
    Button  ccSubmitButton;
    EditText complaintTitle, complaintDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint);
        categorySpinner = (Spinner) findViewById(R.id.cc_category_spinner);
        noOfPeopleSpinner = (Spinner) findViewById(R.id.cc_number_of_people_spinner);
        facingSinceSpinner = (Spinner) findViewById(R.id.cc_facing_since_spinner);
        ccSubmitButton = (Button) findViewById(R.id.cc_submit_button);
        complaintTitle = (EditText) findViewById(R.id.cc_title_edit_text);
        complaintDesc = (EditText) findViewById(R.id.cc_description_edit_text);

        categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
        categorySpinner.setAdapter(categoryAdapter);

        noOfPeopleAdapter = ArrayAdapter.createFromResource(this, R.array.no_of_people_array, android.R.layout.simple_spinner_item);
        noOfPeopleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
        noOfPeopleSpinner.setAdapter(noOfPeopleAdapter);

        facingSinceAdapter = ArrayAdapter.createFromResource(this, R.array.facing_since_array, android.R.layout.simple_spinner_item);
        facingSinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
        facingSinceSpinner.setAdapter(facingSinceAdapter);
    }
}
