package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Others.HoursReviewActivity;
import com.example.ravivats.worknopsysmobile.Others.LoginActivity;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.domain.WorkingOrder;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TimingsEvidence extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    static final String TIMINGS_URL = "http://207.154.200.101:5000/api/timings/create";
    Calendar cal;
    StringRequest timingsRequest;
    Button timingsSubmitButton;
    ArrayList<WorkingOrder> workingOrders;
    WorkingOrder workingOrder;
    TimePickerDialog.OnTimeSetListener evidenceGTime1Listener, evidenceGTime2Listener, evidenceWTime1Listener,
            evidenceWTime2Listener, evidenceBTime1Listener, evidenceBTime2Listener, evidenceRTime1Listener, evidenceRTime2Listener;
    DatePickerDialog.OnDateSetListener evidenceWorkDateListener;
    EditText evidenceGTimePicker1, evidenceGTimePicker2, evidenceWTimePicker1, evidenceWTimePicker2,
            evidenceBTimePicker1, evidenceBTimePicker2, evidenceRTimePicker1, evidenceRTimePicker2, evidenceWorkDate, evidencePersonName;
    int workingOrderIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timings_evidence);

        timingsSubmitButton = (Button) findViewById(R.id.create_wo_evidence_timingsApiSubmitButton);
        evidenceWorkDate = (EditText) findViewById(R.id.create_wo_evidence_sDate);
        evidencePersonName = (EditText) findViewById(R.id.create_wo_evidence_name);
        evidenceGTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_gTimePicker1);
        evidenceGTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_gTimePicker2);
        evidenceWTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_wTimePicker1);
        evidenceWTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_wTimePicker2);
        evidenceBTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_bTimePicker1);
        evidenceBTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_bTimePicker2);
        evidenceRTimePicker1 = (EditText) findViewById(R.id.create_wo_evidence_rTimePicker1);
        evidenceRTimePicker2 = (EditText) findViewById(R.id.create_wo_evidence_rTimePicker2);

        evidencePersonName.setText(Constants.getEMPLOYEE().getFirstName() + " " + Constants.getEMPLOYEE().getLastName());
        evidenceGTimePicker1.setText(R.string.default_timing);
        evidenceGTimePicker2.setText(R.string.default_timing);
        evidenceWTimePicker1.setText(R.string.default_timing);
        evidenceWTimePicker2.setText(R.string.default_timing);
        evidenceRTimePicker1.setText(R.string.default_timing);
        evidenceRTimePicker2.setText(R.string.default_timing);
        evidenceBTimePicker1.setText(R.string.default_timing);
        evidenceBTimePicker2.setText(R.string.default_timing);


        workingOrderIndex = getIntent().getIntExtra("woPosition", 0);
        workingOrders = new ArrayList<WorkingOrder>();
        workingOrders = Constants.getWorkingOrders();
        workingOrder = workingOrders.get(workingOrderIndex);


        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        cal = Calendar.getInstance();

        evidenceWorkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp1 = new DatePickerDialog(TimingsEvidence.this, evidenceWorkDateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
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
                evidenceGTimePicker1.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceGTime1(hourOfDay + ":" + minute);
            }
        };
        evidenceGTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceGTimePicker2.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceGTime2(hourOfDay + ":" + minute);
            }
        };

        evidenceBTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceBTimePicker1.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceBTime1(hourOfDay + ":" + minute);
            }
        };
        evidenceBTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceBTimePicker2.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceBTime2(hourOfDay + ":" + minute);
            }
        };

        evidenceWTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceWTimePicker1.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceWTime1(hourOfDay + ":" + minute);
            }
        };
        evidenceWTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceWTimePicker2.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceWTime2(hourOfDay + ":" + minute);
            }
        };

        evidenceRTime1Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceRTimePicker1.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceRTime1(hourOfDay + ":" + minute);
            }
        };
        evidenceRTime2Listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE, minute);
                evidenceRTimePicker2.setText(hourOfDay + ":" + minute);
                Constants.setEvidenceRTime2(hourOfDay + ":" + minute);
            }
        };
        evidenceWorkDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                evidenceWorkDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                Constants.setEvidenceWorkDate(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };

        timingsSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEntryMade()) {
                    timingsRequest = new StringRequest(Request.Method.POST, TIMINGS_URL,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(TimingsEvidence.this, "Response is:" + response, Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(TimingsEvidence.this, MyWorkingOrderDetails.class));
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(TimingsEvidence.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                                    error.printStackTrace();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("employee", Constants.getEMPLOYEE().getId());
                            params.put("wo", workingOrder.getId());
                            params.put("resource", workingOrder.getResources());
                            params.put("startdate", workingOrder.getStartDate());
                            params.put("workdate", evidenceWorkDate.getText().toString());
                            params.put("pname", evidencePersonName.getText().toString());
                            params.put("gfrom", evidenceGTimePicker1.getText().toString());
                            params.put("gto", evidenceGTimePicker2.getText().toString());
                            params.put("wfrom", evidenceWTimePicker1.getText().toString());
                            params.put("wto", evidenceWTimePicker2.getText().toString());
                            params.put("bfrom", evidenceBTimePicker1.getText().toString());
                            params.put("bto", evidenceBTimePicker2.getText().toString());
                            params.put("rfrom", evidenceRTimePicker1.getText().toString());
                            params.put("rto", evidenceRTimePicker2.getText().toString());
                            return params;
                        }

                    };
                    requestQueue.add(timingsRequest);
                } else {
                    Toast.makeText(TimingsEvidence.this, "Please enter details in all fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isEntryMade() {
        if (evidenceWorkDate.getText() != null && !evidenceWorkDate.getText().toString().equals("")) {
            if (evidencePersonName.getText() != null && !evidencePersonName.getText().toString().equals("")) {
                return true;
            }
        }
        return false;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    }

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
            Toast.makeText(this, "Tap submit to save timings evidence.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.create_wo_evidence_discard) {
            Constants.setEvidenceWTime1(getString(R.string.default_timing));
            Constants.setEvidenceWTime2(getString(R.string.default_timing));
            Constants.setEvidenceBTime1(getString(R.string.default_timing));
            Constants.setEvidenceBTime2(getString(R.string.default_timing));
            Constants.setEvidenceGTime1(getString(R.string.default_timing));
            Constants.setEvidenceGTime2(getString(R.string.default_timing));
            Constants.setEvidenceRTime1(getString(R.string.default_timing));
            Constants.setEvidenceRTime2(getString(R.string.default_timing));
            Constants.setEvidenceWorkDate("12/12/2017");
            startActivity(new Intent(TimingsEvidence.this, MyWorkingOrderDetails.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
