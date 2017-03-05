package com.example.ravivats.worknopsysmobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

public class AboutActivity extends AppCompatActivity {
    Button authButton;
    ExpandableRelativeLayout expandableLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        authButton=(Button) findViewById(R.id.about_auth_button);
        authButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.about_auth_expandableLayout);
                expandableLayout1.toggle();
            }
        });
    }
}
