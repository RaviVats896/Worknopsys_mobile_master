package com.example.ravivats.worknopsysmobile.Others;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScanActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    private TextView qrCodeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        qrCodeResult = (TextView) findViewById(R.id.qr_code_text);

        qrScan = new IntentIntegrator(QRScanActivity.this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.scan_new_qr_code);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });
    }

    // Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            // if qrCode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                // if qrCode contains data
                try {
                    // converting the data to json
                    // JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textView
                    qrCodeResult.setText(result.getContents());

                } catch (Exception e) {
                    e.printStackTrace();
                    // If control comes here, that means the encoded format not matches
                    // In this case you can display qrCode data using a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
