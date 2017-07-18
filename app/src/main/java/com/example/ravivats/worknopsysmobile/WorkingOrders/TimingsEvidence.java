package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimingsEvidence extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    TimePickerDialog.OnTimeSetListener evidenceGTime1Listener,evidenceGTime2Listener,evidenceWTime1Listener,
            evidenceWTime2Listener, evidenceBTime1Listener,evidenceBTime2Listener,evidenceRTime1Listener,evidenceRTime2Listener;
    DatePickerDialog.OnDateSetListener evidenceWorkDateListener;
    EditText evidenceGTimePicker1, evidenceGTimePicker2, evidenceWTimePicker1, evidenceWTimePicker2,
    evidenceBTimePicker1, evidenceBTimePicker2, evidenceRTimePicker1, evidenceRTimePicker2, evidenceWorkDate;
    Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timings_evidence);
        evidenceWorkDate = (EditText) findViewById(R.id.create_wo_evidence_sDate);
        evidenceGTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_gTimePicker1);
        evidenceGTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_gTimePicker2);
        evidenceWTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_wTimePicker1);
        evidenceWTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_wTimePicker2);
        evidenceBTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_bTimePicker1);
        evidenceBTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_bTimePicker2);
        evidenceRTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_rTimePicker1);
        evidenceRTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_rTimePicker2);

        cal = Calendar.getInstance();

        evidenceWorkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp1=  new DatePickerDialog(TimingsEvidence.this,evidenceWorkDateListener , cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));
                dp1.show();
            }
        });

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

        evidenceWTimePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceWTime1Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceWTimePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceWTime2Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceBTimePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceBTime1Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceBTimePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceBTime2Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceRTimePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceRTime1Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceRTimePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tp1 = new TimePickerDialog(TimingsEvidence.this, evidenceRTime2Listener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                tp1.show();
            }
        });

        evidenceGTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceGTimePicker1.setText(hourOfDay+":"+minute);
                Constants.setEvidenceGTime1(hourOfDay+":"+minute);
            }};
        evidenceGTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceGTimePicker2.setText(hourOfDay+":"+minute);
                Constants.setEvidenceGTime2(hourOfDay+":"+minute);
            }};

        evidenceBTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceBTimePicker1.setText(hourOfDay+":"+minute);
                Constants.setEvidenceBTime1(hourOfDay+":"+minute);
            }};
        evidenceBTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceBTimePicker2.setText(hourOfDay+":"+minute);
                Constants.setEvidenceBTime2(hourOfDay+":"+minute);
            }};

        evidenceWTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceWTimePicker1.setText(hourOfDay+":"+minute);
                Constants.setEvidenceWTime1(hourOfDay+":"+minute);
            }};
        evidenceWTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceWTimePicker2.setText(hourOfDay+":"+minute);
                Constants.setEvidenceWTime2(hourOfDay+":"+minute);
            }};

        evidenceRTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceRTimePicker1.setText(hourOfDay+":"+minute);
                Constants.setEvidenceRTime1(hourOfDay+":"+minute);
            }};
        evidenceRTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceRTimePicker2.setText(hourOfDay+":"+minute);
                Constants.setEvidenceRTime2(hourOfDay+":"+minute);
            }};
        evidenceWorkDateListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                evidenceWorkDate.setText(dayOfMonth+"/"+month+"/"+year);
                Constants.setEvidenceWorkDate(dayOfMonth+"/"+month+"/"+year);
            }
        };


    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {}

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_working_order_evidence, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.create_wo_evidence_send) {
            startActivity(new Intent(TimingsEvidence.this, CreateWorkingOrder.class));
        } else if(id == R.id.create_wo_evidence_discard){
            Constants.setEvidenceWTime1("--:--"); Constants.setEvidenceWTime2("--:--");
            Constants.setEvidenceBTime1("--:--"); Constants.setEvidenceBTime2("--:--");
            Constants.setEvidenceGTime1("--:--"); Constants.setEvidenceGTime2("--:--");
            Constants.setEvidenceRTime1("--:--"); Constants.setEvidenceRTime2("--:--");
            Constants.setEvidenceWorkDate("--/--/----");
            startActivity(new Intent(TimingsEvidence.this, CreateWorkingOrder.class));
        } else if(id == R.id.create_wo_evidence_logout){

        }
        return super.onOptionsItemSelected(item);
    }

}
