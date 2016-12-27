package com.example.ravivats.worknopsysmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Switch locationSwitch;
    EditText loginPersonalNoEditText;
    EditText loginPasswordEditText;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        locationSwitch=(Switch)findViewById(R.id.loginLocationSwitch);
        loginPersonalNoEditText=(EditText) findViewById(R.id.loginPersonalNoEditText);
        loginPasswordEditText=(EditText) findViewById(R.id.loginPasswordEditText);
        loginBtn =(Button) findViewById(R.id.loginLoginBtn);
        locationSwitch.setChecked(false);
        locationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Geo-location services enabled ", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Geo-location services disabled ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginPersonalNoEditText.getText().toString().equals("12345") && loginPasswordEditText.getText().toString().equals("admin") ) {
                    Intent i = new Intent(LoginActivity.this, Main2Activity.class);
                    startActivity(i);
                    String number = loginPersonalNoEditText.getText().toString();
                    Constants.setPhoneNumber(number);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect login credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
