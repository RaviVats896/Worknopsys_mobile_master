package com.example.ravivats.worknopsysmobile;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class ConfigurationActivity extends AppCompatActivity {
    Switch configProjectEnhance, configPosLocation;
    Button changePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        configProjectEnhance = (Switch) findViewById(R.id.config_project_enhance);
        configPosLocation = (Switch) findViewById(R.id.config_pos_location);
        changePassword =(Button) findViewById(R.id.config_change_password);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ConfigurationActivity.this);
                alertDialog.setTitle("Values");
                final EditText oldPass = new EditText(ConfigurationActivity.this);
                final EditText newPass = new EditText(ConfigurationActivity.this);
                final EditText confirmPass = new EditText(ConfigurationActivity.this);
                oldPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                newPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                confirmPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                oldPass.setHint("Old Password");
                newPass.setHint("New Password");
                confirmPass.setHint("Confirm Password");
                LinearLayout ll=new LinearLayout(ConfigurationActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.addView(oldPass);
                ll.addView(newPass);
                ll.addView(confirmPass);
                alertDialog.setView(ll);
                alertDialog.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Toast.makeText(ConfigurationActivity.this, "Yippee!", Toast.LENGTH_SHORT).show();
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
