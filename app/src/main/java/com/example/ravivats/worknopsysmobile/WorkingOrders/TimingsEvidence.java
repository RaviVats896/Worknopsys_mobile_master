package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.ravivats.worknopsysmobile.R;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimingsEvidence extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    TimePickerDialog.OnTimeSetListener evidenceGTime1Listener,evidenceGTime2Listener;
    EditText evidenceGTimePicker1, evidenceGTimePicker2;
    Calendar cal;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timings_evidence);
        nextBtn = (Button) findViewById(R.id.evidence_nextBtn);
        evidenceGTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_gTimePicker1);
        evidenceGTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_gTimePicker2);
        cal = Calendar.getInstance();

        evidenceGTimePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceGTime1Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceGTimePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceGTime2Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceGTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceGTimePicker1.setText(hourOfDay+":"+minute);
            }};
        evidenceGTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceGTimePicker2.setText(hourOfDay+":"+minute);
            }};


    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {}

}
