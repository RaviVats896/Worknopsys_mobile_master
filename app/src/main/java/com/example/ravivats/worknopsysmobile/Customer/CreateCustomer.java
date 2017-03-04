package com.example.ravivats.worknopsysmobile.Customer;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Project.CreateProjectDetails;
import com.example.ravivats.worknopsysmobile.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateCustomer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {
    Spinner salutationSpinner;
    Button createCustomerNxtBtn;
    EditText CustomerName,CustomerDebNumber,CustomerPhoneNumber,CustomerFax,CustomerEmail,CustomerWebsite;
    EditText CustomerAddress,CustomerCity,CustomerPostCode,CustomerCCode;
    String ckname,cdebnum,ccdate,ceditedon,ceditedby,csalutation, ccname,cphone,cfax,cwebsite,cemail,cacity,caadd,capostcode,caccode;
    Bundle customerInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        salutationSpinner = (Spinner) findViewById(R.id.cp_customers_salutation_spinner);
        customerInfo= new Bundle();
        createCustomerNxtBtn = (Button) findViewById(R.id.cp_customers_nxt_btn);
        CustomerName= (EditText)  findViewById(R.id.cp_customers_cust_name_edit_text);
        CustomerDebNumber= (EditText)  findViewById(R.id.cp_customers_cust_debno_edit_text);
        CustomerPhoneNumber =(EditText)  findViewById(R.id.cp_customers_phone_edit_text);
        CustomerFax =(EditText) findViewById(R.id.cp_customers_fax_edit_text);
        CustomerEmail=(EditText) findViewById(R.id.cp_customers_email_edit_text);
        CustomerWebsite =(EditText)  findViewById(R.id.cp_customers_website_edit_text);
        CustomerAddress= (EditText)  findViewById(R.id.cp_customers_address_edit_text);
        CustomerCity=(EditText)  findViewById(R.id.cp_customers_city_edit_text);
        CustomerPostCode=(EditText)  findViewById(R.id.cp_customers_zip_edit_text);
        CustomerCCode= (EditText)  findViewById(R.id.cp_customers_ccode_edit_text);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.salutation_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        salutationSpinner.setAdapter(adapter);
        salutationSpinner.setOnItemSelectedListener(this);
        createCustomerNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckname= Constants.getEMPLOYEE().getFirstName()+" "+Constants.getEMPLOYEE().getLastName();
                cdebnum=CustomerDebNumber.getText().toString().trim();
                ceditedon=getDate();
                ccdate=getDate();
                ceditedby=ckname;
                ccname =CustomerName.getText().toString().trim();
                cphone=CustomerPhoneNumber.getText().toString().trim();
                cwebsite=CustomerWebsite.getText().toString().trim();
                cfax=CustomerFax.getText().toString().trim();
                cemail=CustomerEmail.getText().toString().trim();
                cacity=CustomerCity.getText().toString().trim();
                caadd=CustomerAddress.getText().toString().trim();
                capostcode=CustomerPostCode.getText().toString().trim();
                caccode=CustomerCCode.getText().toString().trim();
                customerInfo.putString("cdebnum",cdebnum);
                customerInfo.putString("ccdate",ccdate);
                customerInfo.putString("ceditedon",ceditedon);
                customerInfo.putString("ceditedby",ceditedby);
                customerInfo.putString("csalutation",csalutation);
                customerInfo.putString("ccname", ccname);
                customerInfo.putString("cphone",cphone);
                customerInfo.putString("cfax",cfax);
                customerInfo.putString("cemail",cemail);
                customerInfo.putString("cwebsite",cwebsite);
                customerInfo.putString("cacity",cacity);
                customerInfo.putString("caadd",caadd);
                customerInfo.putString("capostcode",capostcode);
                customerInfo.putString("caccode",caccode);
                Intent i=new Intent(CreateCustomer.this,CreateCustomerBankDetails.class);
                i.putExtras(customerInfo);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.view_customers)
        {
            startActivity(new Intent(CreateCustomer.this, ViewCustomers.class));
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_daily_overview) {
            // Handle the camera action
        } else if (id == R.id.nav_working_orders) {

        } else if (id == R.id.nav_create_customer) {

        } else if (id == R.id.nav_create_project) {
            startActivity(new Intent(CreateCustomer.this, CreateProjectDetails.class));

        } else if (id == R.id.nav_mgmt_working_orders) {

        } else if (id == R.id.nav_hours_review) {

        } else if (id == R.id.nav_config) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_about) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        csalutation = salutationSpinner.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }
    private String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh-mm", Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

}
