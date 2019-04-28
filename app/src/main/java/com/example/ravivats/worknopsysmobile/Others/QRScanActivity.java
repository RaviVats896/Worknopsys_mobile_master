package com.example.ravivats.worknopsysmobile.Others;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.TinyDB;
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
    private List<String> qrScannedInfoList = new ArrayList<>();
    private final static String QR_CODE_DESCRIPTION = "desc";
    private final static String QR_CODE_IMAGE_URL = "url";
    private final static String SMALL_SEPARATOR = "@~qsmlsprtr~@";
    private final static String BIG_SEPARATOR = "@~qbgsprtr~@";
    public final static String QR_IMAGE_INFO_PREFIX = "@~ImageURL~@:";
    public final static String QR_DESC_INFO_PREFIX = "@~ImageDesc~@:";
    private List<QRScanInfo> tempList = new ArrayList<>();
    TinyDB tinydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);
        qrCodeInfoList = (ListView) findViewById(R.id.qrCodeInfoList);
        Context context = getApplicationContext();
        tinydb = new TinyDB(context);

        qrCodeInfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> qrScanInfoTempList = new ArrayList<>();
                if (qrScannedInfoList.get(i) != null) {

                    String qrTempString = qrScannedInfoList.get(i);
                    // Log.d("QRScanActivity", "qrTempString" + qrTempString);
                    String[] qrScanInfoList = qrTempString.split(BIG_SEPARATOR);
                    for (String qrScanInfo : qrScanInfoList) {
                        // Log.d("QRScanActivity", "qrScanInfo" + qrScanInfo);
                        if (qrScanInfo.length() > 1) {
                            String[] allInfo = qrScanInfo.split(SMALL_SEPARATOR);
                            if (allInfo.length == 1)
                                qrScanInfoTempList.add(QR_IMAGE_INFO_PREFIX + allInfo[0]);
                            else if (allInfo.length == 2) {
                                qrScanInfoTempList.add(QR_IMAGE_INFO_PREFIX + allInfo[0]);
                                qrScanInfoTempList.add(QR_DESC_INFO_PREFIX + allInfo[1]);
                            }
                        }
                    }
                }
                startActivity(new Intent(QRScanActivity.this, QRResourcesDisplay.class).putStringArrayListExtra("qrScanInfoList", qrScanInfoTempList));
            }
        });

        ArrayList<String> tempScannedStringList = tinydb.getListString(getString(R.string.shared_pref_QR_code_string_list_key));
        if (tempScannedStringList != null) {
            if (tempScannedStringList.size() > 0) {
                qrScannedStringList.clear();
                qrScannedStringList.addAll(tempScannedStringList);
            }
        }

        ArrayList<String> tempScannedInfoList = tinydb.getListString(getString(R.string.shared_pref_QR_code_list_key));
        if (tempScannedInfoList != null) {
            if (tempScannedInfoList.size() > 0) {
                qrScannedInfoList.clear();
                qrScannedInfoList.addAll(tempScannedInfoList);
            }
        }

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

    private void updateListView() {
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
                                        String tempString = "";
                                        for (int i = 0; i < resultJsonArray.length(); i++) {
                                            qrScannedSingleInfo.add(new QRScanInfo(resultJsonArray.getJSONObject(i).getString(QR_CODE_IMAGE_URL), resultJsonArray.getJSONObject(i).getString(QR_CODE_DESCRIPTION)));
                                            tempString += resultJsonArray.getJSONObject(i).getString(QR_CODE_IMAGE_URL) + SMALL_SEPARATOR + resultJsonArray.getJSONObject(i).getString(QR_CODE_DESCRIPTION) + BIG_SEPARATOR;
                                        }
                                        // Log.d("QRScanActivity", "TempString" + tempString);
                                        qrScannedInfoList.add(tempString);
                                        tinydb.putListString(getString(R.string.shared_pref_QR_code_list_key), qrScannedInfoList);
                                        if (qrScannedSingleInfo.size() == 1)
                                            qrScannedStringList.add("Scanned QR Code " + (qrScannedStringList.size() + 1) + ": " + qrScannedSingleInfo.get(0).getDescription());
                                        else if (qrScannedSingleInfo.size() >= 2)
                                            qrScannedStringList.add("Scanned QR Code " + (qrScannedStringList.size() + 1) + ": " + qrScannedSingleInfo.get(0).getDescription() + ", " + qrScannedSingleInfo.get(1).getDescription());
                                        tinydb.putListString(getString(R.string.shared_pref_QR_code_string_list_key), qrScannedStringList);
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
