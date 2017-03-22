package com.example.ravivats.worknopsysmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.ravivats.worknopsysmobile.domain.Authorization;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class AboutActivity extends AppCompatActivity {
    Button authButton;
    Switch custCreate,custView,projCreate,projView,woCreate,woView;
    ExpandableRelativeLayout expandableLayout1;
    Authorization userAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        authButton=(Button) findViewById(R.id.about_auth_button);
        custCreate=(Switch) findViewById(R.id.about_adm_emp_cust_create);
        custView=(Switch) findViewById(R.id.about_adm_emp_cust_view);
        projCreate=(Switch) findViewById(R.id.about_adm_emp_proj_create);
        projView=(Switch) findViewById(R.id.about_adm_emp_proj_view);
        woCreate=(Switch) findViewById(R.id.about_adm_emp_wo_create);
        woView=(Switch) findViewById(R.id.about_adm_emp_wo_view);
        userAuth=Constants.getAUTH();

        if(userAuth.getAdmEmpCustCreate())  custCreate.setChecked(true);
        if(userAuth.getAdmEmpCustView())  custView.setChecked(true);
        if(userAuth.getAdmEmpProjCreate())  projCreate.setChecked(true);
        if(userAuth.getAdmEmpProjView())  projView.setChecked(true);
        if(userAuth.getAdmWoCreate()  )  woCreate.setChecked(true);
        if(userAuth.getAdmWoView()  )  woView.setChecked(true);


        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.about_auth_expandableLayout);
                expandableLayout1.toggle();
            }
        });

    }
}
