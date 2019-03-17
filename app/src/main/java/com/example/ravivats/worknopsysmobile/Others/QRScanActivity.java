package com.example.ravivats.worknopsysmobile.Others;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class QRScanActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    private List<String> qrScannedStringList = new ArrayList<>();
    private ListView qrCodeInfoList;
    private List<List<QRScanInfo>> qrScannedInfoList = new ArrayList<>();
    private final static String QR_CODE_DESCRIPTION = "desc";
    private final static String QR_CODE_IMAGE_URL = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        qrCodeInfoList = (ListView) findViewById(R.id.qrCodeInfoList);
        updateListView();
        qrScan = new IntentIntegrator(QRScanActivity.this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.scan_new_qr_code);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });
    }

    private void updateListView(){
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, qrScannedStringList);
        qrCodeInfoList.setAdapter(adapter);
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
                    // converting the data to a jsonArray
                    final JSONArray resultJsonArray = new JSONArray(result.getContents());
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(QRScanActivity.this);
                    alertDialog.setTitle("Save Scanned QR Code");
                    final TextView scannedContents = new EditText(QRScanActivity.this);
                    scannedContents.setText(String.format("%s %s", getString(R.string.scannedContents), result.getContents()));
                    scannedContents.setEnabled(false);
                    scannedContents.setCursorVisible(false);
                    LinearLayout ll = new LinearLayout(QRScanActivity.this);
                    ll.setOrientation(LinearLayout.VERTICAL);
                    ll.addView(scannedContents);
                    alertDialog.setView(ll);
                    alertDialog.setPositiveButton("Save",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    List<QRScanInfo> qrScannedSingleInfo = new ArrayList<>();
                                    try {
                                        for (int i = 0; i < resultJsonArray.length(); i++) {
                                            qrScannedSingleInfo.add(new QRScanInfo(resultJsonArray.getJSONObject(i).getString(QR_CODE_IMAGE_URL), resultJsonArray.getJSONObject(i).getString(QR_CODE_DESCRIPTION)));
                                        }
                                        qrScannedInfoList.add(qrScannedSingleInfo);
                                        if (qrScannedSingleInfo.size() == 1)
                                            qrScannedStringList.add("Scanned QR Code " + (qrScannedStringList.size() + 1) + ": " + qrScannedSingleInfo.get(0).getDescription());
                                        else if (qrScannedSingleInfo.size() >= 2)
                                            qrScannedStringList.add("Scanned QR Code " + (qrScannedStringList.size() + 1) + ": " + qrScannedSingleInfo.get(0).getDescription() + ", " + qrScannedSingleInfo.get(1).getDescription());
                                        updateListView();
                                    } catch (JSONException e) {
                                        Log.e("QRScanActivity", "Scanned data cannot be converted to JSON format");
                                        Toast.makeText(QRScanActivity.this, "Scanned data cannot be converted to JSON format", Toast.LENGTH_LONG).show();
                                        e.printStackTrace();
                                        dialog.cancel();
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
                } catch (JSONException e) {
                    // If control comes here, that means the encoded format not matches
                    // In this case you can display qrCode data using a toast
                    Toast.makeText(this, "Scanned data cannot be converted to JSON format", Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "Scanned contents: " + result.getContents(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (Exception e) {
                    Toast.makeText(this, "Some exception has occurred. Scanning could not be completed.", Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "Scanned contents: " + result.getContents(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
