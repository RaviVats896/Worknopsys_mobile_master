package com.example.ravivats.worknopsysmobile.Customer;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.Others.AboutActivity;
import com.example.ravivats.worknopsysmobile.Others.BrowserActivity;
import com.example.ravivats.worknopsysmobile.Others.ConfigurationActivity;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Others.HoursReviewActivity;
import com.example.ravivats.worknopsysmobile.Others.LoginActivity;
import com.example.ravivats.worknopsysmobile.WorkingOrders.ManagementWorkingOrders;
import com.example.ravivats.worknopsysmobile.WorkingOrders.MyWorkingOrders;
import com.example.ravivats.worknopsysmobile.Project.CreateProjectDetails;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.domain.Authorization;


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
        Authorization auth=Constants.getAUTH();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.salutation_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        salutationSpinner.setAdapter(adapter);
        salutationSpinner.setOnItemSelectedListener(this);
        if(auth.getAdmEmpCustCreate()){
           createCustomerNxtBtn.setClickable(false);
        }
        createCustomerNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ckname= Constants.getEMPLOYEE().getFirstName()+" "+Constants.getEMPLOYEE().getLastName();
                cdebnum=CustomerDebNumber.getText().toString().trim();
                ceditedon=Constants.getDate();
                ccdate=Constants.getDate();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_customers, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.view_customers) {
            if(Constants.getAUTH().getAdmEmpCustView())
            startActivity(new Intent(CreateCustomer.this, ViewCustomers.class));
            else{
                Toast.makeText(CreateCustomer.this,"You aren't authorized for this feature.", Toast.LENGTH_SHORT).show();
            }
        }
        if (id == R.id.logout) {
            startActivity(new Intent(CreateCustomer.this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Authorization auth1=Constants.getAUTH();
        if (id == R.id.nav_daily_overview) {

        } else if (id == R.id.nav_working_orders) {
            startActivity(new Intent(CreateCustomer.this,MyWorkingOrders.class));
        } else if(id==R.id.nav_facebook){
            startActivity(new Intent(CreateCustomer.this,BrowserActivity.class).putExtra("choice",1));
        } else if(id==R.id.nav_youtube){
            startActivity(new Intent(CreateCustomer.this,BrowserActivity.class).putExtra("choice",2));
        } else if(id==R.id.nav_whatsapp){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_whatsapp));
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else if (id == R.id.nav_create_customer) {

        } else if (id == R.id.nav_create_project) {
            if(auth1.getAdmEmpProjCreate())
            startActivity(new Intent(CreateCustomer.this, CreateProjectDetails.class));
            else{
                Toast.makeText(CreateCustomer.this,"You aren't authorized for this feature.", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_mgmt_working_orders) {
            startActivity(new Intent(CreateCustomer.this, ManagementWorkingOrders.class));
        } else if (id == R.id.nav_hours_review) {
            if(auth1.getHoursEdit())
            startActivity(new Intent(CreateCustomer.this, HoursReviewActivity.class));
            else{
                Toast.makeText(CreateCustomer.this,"You aren't authorized for this feature.", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(CreateCustomer.this, ConfigurationActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(CreateCustomer.this, LoginActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(CreateCustomer.this, AboutActivity.class));
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


}
