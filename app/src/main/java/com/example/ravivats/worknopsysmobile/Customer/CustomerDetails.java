package com.example.ravivats.worknopsysmobile.Customer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Spinner;

import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.Domain.Customer;
import com.google.gson.Gson;

public class CustomerDetails extends AppCompatActivity {
    String selectedCustomer;
    TextView CustomerName, DebNumber, PhoneNumber, Fax, Email, Website, Address, City, Zip, CountryCode;
    Customer SelectedCustomer;
    Spinner Salutation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        selectedCustomer = getIntent().getStringExtra("selectedCustomer");
        Gson gson = new Gson();
        SelectedCustomer = gson.fromJson(selectedCustomer, Customer.class);

        CustomerName = (TextView) findViewById(R.id.cd_customers_cust_name_edit_text);
        DebNumber = (TextView) findViewById(R.id.cd_customers_cust_debno_edit_text);
        Salutation = (Spinner) findViewById(R.id.cd_customers_salutation_spinner);
        PhoneNumber = (TextView) findViewById(R.id.cd_customers_phone_edit_text);
        Fax = (TextView) findViewById(R.id.cd_customers_fax_edit_text);
        Email = (TextView) findViewById(R.id.cd_customers_email_edit_text);
        Website = (TextView) findViewById(R.id.cd_customers_website_edit_text);
        Address = (TextView) findViewById(R.id.cd_customers_address_edit_text);
        City = (TextView) findViewById(R.id.cd_customers_city_edit_text);
        Zip = (TextView) findViewById(R.id.cd_customers_zip_edit_text);
        CountryCode = (TextView) findViewById(R.id.cd_customers_ccode_edit_text);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.salutation_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Salutation.setAdapter(adapter);
        Salutation.setEnabled(false);

        CustomerName.setText(SelectedCustomer.getCName());
        DebNumber.setText(SelectedCustomer.getDebitorNumber());
        String salutation = SelectedCustomer.getSalutation();
        switch (salutation) {
            case "Mr":
                Salutation.setSelection(0);
                break;
            case "Dr":
                Salutation.setSelection(1);
                break;
            case "Mrs":
                Salutation.setSelection(2);
                break;
            case "Ms":
                Salutation.setSelection(3);
                break;
        }

        PhoneNumber.setText(SelectedCustomer.getPhone());
        Fax.setText(SelectedCustomer.getFax());
        Email.setText(SelectedCustomer.getEmail());
        Website.setText(SelectedCustomer.getWebsite());
        Address.setText(SelectedCustomer.getAddress().getAddress());
        City.setText(SelectedCustomer.getAddress().getCity());
        Zip.setText(SelectedCustomer.getAddress().getPostcode());
        CountryCode.setText(SelectedCustomer.getAddress().getCountryCode());
    }
}
