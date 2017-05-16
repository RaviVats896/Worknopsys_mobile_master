package com.example.ravivats.worknopsysmobile.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.domain.Authorization;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class AboutActivity extends AppCompatActivity {
    Button authButton;
    Switch custCreate,custView,projCreate,projView,woCreate,woView,woEdit,woDelete,authPass,authPin;
    Switch hoursAdd, hoursEdit,hoursQRScan,woShowall,woSortDistance;
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
        woEdit=(Switch) findViewById(R.id.about_adm_emp_wo_edit);
        woDelete=(Switch) findViewById(R.id.about_adm_emp_wo_delete);
        authPass=(Switch) findViewById(R.id.about_adm_auth_pass);
        authPin=(Switch) findViewById(R.id.about_adm_auth_pin);
        hoursAdd=(Switch) findViewById(R.id.about_adm_hours_add);
        hoursEdit=(Switch) findViewById(R.id.about_adm_hours_edit);
        hoursQRScan=(Switch) findViewById(R.id.about_adm_hours_member_QRScan);
        woShowall=(Switch) findViewById(R.id.about_adm_wo_show_all);
        woSortDistance=(Switch) findViewById(R.id.about_adm_wo_sort_distance);

        userAuth= Constants.getAUTH();

        if(userAuth.getAdmEmpCustCreate())  custCreate.setChecked(true);
        if(userAuth.getAdmEmpCustView())  custView.setChecked(true);
        if(userAuth.getAdmEmpProjCreate())  projCreate.setChecked(true);
        if(userAuth.getAdmEmpProjView())  projView.setChecked(true);
        if(userAuth.getAdmWoCreate())  woCreate.setChecked(true);
        if(userAuth.getAdmWoView())  woView.setChecked(true);
        if(userAuth.getAdmWoEdit())  woEdit.setChecked(true);
        if(userAuth.getAdmWoDelete())  woDelete.setChecked(true);
        if(userAuth.getAuthPass())  authPass.setChecked(true);
        if(userAuth.getAuthPin())  authPin.setChecked(true);
        if(userAuth.getHoursAdd())  hoursAdd.setChecked(true);
        if(userAuth.getHoursEdit())  hoursEdit.setChecked(true);
        if(userAuth.getHoursMemberQRscan())  hoursQRScan.setChecked(true);
        if(userAuth.getWoShowAll())  woShowall.setChecked(true);
        if(userAuth.getWoSortDistance())  woSortDistance.setChecked(true);

        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.about_auth_expandableLayout);
                expandableLayout1.toggle();
            }
        });

    }
}
