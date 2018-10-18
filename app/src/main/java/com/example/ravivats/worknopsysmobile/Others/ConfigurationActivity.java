package com.example.ravivats.worknopsysmobile.Others;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationActivity extends AppCompatActivity {
    static final String CHANGE_PASS_URL = "http://207.154.200.101:5000/api/changepass";
    Switch configProjectEnhance, configPosLocation;
    Button changePassword;
    StringRequest changePassRequest;
    static final String KEY_USERNAME = "employeephone";
    static final String KEY_PASSWORD = "employeepassword";
    static final String KEY_NEW = "newpass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        configProjectEnhance = (Switch) findViewById(R.id.config_project_enhance);
        configPosLocation = (Switch) findViewById(R.id.config_pos_location);
        changePassword = (Button) findViewById(R.id.config_change_password);
        configPosLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) Constants.setPosLocation(true);
                else Constants.setPosLocation(false);
            }
        });
        configProjectEnhance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) Constants.setProjEnhance(true);
                else Constants.setProjEnhance(false);
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ConfigurationActivity.this);
                alertDialog.setTitle("Change Password");
                final EditText oldPass = new EditText(ConfigurationActivity.this);
                final EditText newPass = new EditText(ConfigurationActivity.this);
                final EditText confirmPass = new EditText(ConfigurationActivity.this);
                oldPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                oldPass.setHint("Old Password");
                newPass.setHint("New Password");
                confirmPass.setHint("Confirm Password");
                LinearLayout ll = new LinearLayout(ConfigurationActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(oldPass);
                ll.addView(newPass);
                ll.addView(confirmPass);
                alertDialog.setView(ll);
                alertDialog.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                final RequestQueue passwordChangeQueue = Volley.newRequestQueue(ConfigurationActivity.this);
                                if (oldPass.getText().toString().equals(Constants.getEMPLOYEE().getPassword()) && newPass.getText().toString().equals(confirmPass.getText().toString())) {
                                    changePassRequest = new StringRequest(Request.Method.POST, CHANGE_PASS_URL,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    if (response.equalsIgnoreCase("successful")) {
                                                        Toast.makeText(ConfigurationActivity.this, "Password change successful!", Toast.LENGTH_SHORT).show();
                                                    } else if (response.equalsIgnoreCase("false")) {
                                                        Toast.makeText(ConfigurationActivity.this, "Password change failed!", Toast.LENGTH_SHORT).show();
                                                    }

                                                }
                                            },
                                            new Response.ErrorListener() {
                                                @Override
                                                public void onErrorResponse(VolleyError error) {
                                                    error.printStackTrace();
                                                    Toast.makeText(ConfigurationActivity.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                                                }
                                            }) {
                                        @Override
                                        protected Map<String, String> getParams() {
                                            Map<String, String> params = new HashMap<String, String>();
                                            params.put(KEY_USERNAME, Constants.getEMPLOYEE().getPhone());
                                            params.put(KEY_PASSWORD, oldPass.getText().toString());
                                            params.put(KEY_NEW, confirmPass.getText().toString());
                                            return params;
                                        }
                                    };
                                    passwordChangeQueue.add(changePassRequest);
                                } else {
                                    Toast.makeText(ConfigurationActivity.this, "Wrong Password credentials. ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert1 = alertDialog.create();
                alert1.show();

            }
        });

    }

}
