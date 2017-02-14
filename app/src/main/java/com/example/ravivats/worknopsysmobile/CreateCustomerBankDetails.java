package com.example.ravivats.worknopsysmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CreateCustomerBankDetails extends AppCompatActivity {
    public static final String REGISTER_URL = "http://worknopsys.ml/api/customers/create";
    EditText ccPostboxName,ccPostboxCity,ccPostboxZip,ccBankName,ccBankCode,ccBankAccount,ccBankUser,ccBankIBAN,ccBankBIC,ccBankCurrency;
    String  PostboxName,PostboxCity,PostboxZip,BankName,BankCode,BankAccount,BankUser,BankIBAN,BankBIC,BankCurrency;
    Button createCustomerBtn;
    Bundle customerDetails;
    StringRequest postRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer_bank_details);
        ccPostboxName=(EditText) findViewById(R.id.cp_customers_bank_street_edit_text);
        ccPostboxCity=(EditText) findViewById(R.id.cp_customers_bank_city_edit_text);
        ccPostboxZip=(EditText) findViewById(R.id.cp_customers_bank_zip_edit_text);
        ccBankName=(EditText) findViewById(R.id.cp_customers_bank_name_edit_text);
        ccBankCode=(EditText) findViewById(R.id.cp_customers_bank_code_edit_text);
        ccBankAccount=(EditText) findViewById(R.id.cp_customers_bank_account_edit_text);
        ccBankUser=(EditText) findViewById(R.id.cp_customers_bank_user_edit_text);
        ccBankIBAN=(EditText) findViewById(R.id.cp_customers_bank_iban_edit_text);
        ccBankBIC=(EditText) findViewById(R.id.cp_customers_bank_bic_edit_text);
        ccBankCurrency=(EditText) findViewById(R.id.cp_customers_bank_currency_edit_text);
        createCustomerBtn=(Button) findViewById(R.id.cp_customers_bank_nxt_btn);
        final RequestQueue createCustomerQueue = Volley.newRequestQueue(this);
        customerDetails=new Bundle();
        customerDetails=getIntent().getExtras();
        createCustomerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostboxName=ccPostboxName.getText().toString().trim();
                PostboxCity=ccPostboxCity.getText().toString().trim();
                PostboxZip=ccPostboxZip.getText().toString().trim();
                BankName=ccBankName.getText().toString().trim();
                BankCode=ccBankName.getText().toString().trim();
                BankAccount=ccBankName.getText().toString().trim();
                BankUser=ccBankName.getText().toString().trim();
                BankIBAN=ccBankName.getText().toString().trim();
                BankBIC=ccBankName.getText().toString().trim();
                BankCurrency=ccBankName.getText().toString().trim();
                Log.d("Debugging Purpose","hfkahkajdka");
                postRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.equalsIgnoreCase("created successfuly")) {
                                    Toast.makeText(CreateCustomerBankDetails.this, "Customer Creation successfull!", Toast.LENGTH_LONG).show();
                                    //startActivity(new Intent(CreateCustomerBankDetails.this, HoursReviewActivity.class));
                                } else{
                                    Toast.makeText(CreateCustomerBankDetails.this, "Customer Creation failed!", Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                Toast.makeText(CreateCustomerBankDetails.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("ckname", customerDetails.getString("ckname"));
                        params.put("cdebnum", customerDetails.getString("cdebnum"));
                        params.put("ccdate", customerDetails.getString("ccdate"));
                        params.put("ceditedon", customerDetails.getString("ceditedon"));
                        params.put("ceditedby", customerDetails.getString("ceditedby"));
                        params.put("csalutation", customerDetails.getString("csalutation"));
                        params.put("cname", customerDetails.getString("cname"));
                        params.put("cphone", customerDetails.getString("cphone"));
                        params.put("cfax", customerDetails.getString("cfax"));
                        params.put("cemail", customerDetails.getString("cemail"));
                        params.put("cacity", customerDetails.getString("cacity"));
                        params.put("caadd", customerDetails.getString("caadd"));
                        params.put("capostcode", customerDetails.getString("capostcode"));
                        params.put("caccode", customerDetails.getString("caccode"));
                        params.put("cpname", PostboxName);
                        params.put("cppostcode", PostboxZip);
                        params.put("cpcity", PostboxCity);
                        params.put("cpccode", PostboxZip);
                        params.put("cbbank", BankName);
                        params.put("cbbcode", BankCode);
                        params.put("cbbaccount", BankAccount);
                        params.put("cbbankuser", BankUser);
                        params.put("cbiban", BankIBAN);
                        params.put("cbbic", BankBIC);
                        params.put("cbcountrycode", PostboxZip);
                        params.put("cbcurrency", BankCurrency);
                        return params;
                    }
                };
                createCustomerQueue.add(postRequest);
            }
        });
    }
}


