package com.example.ravivats.worknopsysmobile.Customer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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

public class ViewCustomers extends AppCompatActivity {
    ListView customersList;
    String url = "http://worknopsys.ml/api/customers";
    ProgressDialog dialog;
    JSONArray allCustomers;
    String selectedCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers);
        customersList = (ListView) findViewById(R.id.customersList);
        customersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    selectedCustomer = allCustomers.getJSONObject(position).toString();
                    Intent intent1 = new Intent(ViewCustomers.this, CustomerDetails.class);
                    intent1.putExtra("selectedCustomer", selectedCustomer);
                    startActivity(intent1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading....");
        dialog.show();
        StringRequest viewCustomersRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String string) {
                try {
                    allCustomers = new JSONArray(string);
                    Constants.setCUSTOMERS(allCustomers);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(ViewCustomers.this);
        rQueue.add(viewCustomersRequest);
    }

    void parseJsonData(String jsonString) {
        try {
            JSONArray customersArray = new JSONArray(jsonString);
            ArrayList<String> al = new ArrayList();

            for (int i = 0; i < customersArray.length(); ++i) {
                if (customersArray.getJSONObject(i).getString("CName") != null) {
                    al.add(customersArray.getJSONObject(i).getString("CName"));
                }
            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, al);
            customersList.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        dialog.dismiss();
    }
}
