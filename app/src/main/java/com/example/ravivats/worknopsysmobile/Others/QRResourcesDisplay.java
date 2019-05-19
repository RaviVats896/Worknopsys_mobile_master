package com.example.ravivats.worknopsysmobile.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QRResourcesDisplay extends AppCompatActivity {

    private ImageView imageView;
    private ListView qrScanDescInfoList;
    ArrayList<String> qrScanDescInfo;
    int currentImage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrresources_display);

        imageView = (ImageView) findViewById(R.id.qr_resources_display_imageView);
        qrScanDescInfoList = (ListView) findViewById(R.id.qrScanDescListView);

        final ArrayList<String> qrScanImageInfoTempList = getIntent().getExtras().getStringArrayList("qrScanImageInfoList");
        ArrayList<String> qrScanDescInfoTempList = getIntent().getExtras().getStringArrayList("qrScanDescInfoList");
        qrScanDescInfo = new ArrayList<>();

        if (qrScanDescInfoTempList != null && qrScanDescInfoTempList.size() > 0) {
            for (String descData : qrScanDescInfoTempList) {
                qrScanDescInfo.add("Image " + (qrScanDescInfo.size() + 1) + ": " + descData);
            }
        } else {
            Toast.makeText(this, "No resources to display", Toast.LENGTH_LONG).show();
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, qrScanDescInfo);
        qrScanDescInfoList.setAdapter(adapter);

        if (qrScanImageInfoTempList != null && qrScanImageInfoTempList.size() > 0) {
            String imageUrl = qrScanImageInfoTempList.get(currentImage).replaceFirst(QRScanActivity.QR_IMAGE_INFO_PREFIX, "");
            try {
                Picasso.with(getApplicationContext()).load(imageUrl).into(imageView);
            } catch (Exception e) {
                Log.e("QRResourcesDisplay", e.toString());
            }
        }

        qrScanDescInfoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (qrScanImageInfoTempList != null && qrScanImageInfoTempList.size() > 0 && i != currentImage) {
                    String imageUrl = qrScanImageInfoTempList.get(i).replaceFirst(QRScanActivity.QR_IMAGE_INFO_PREFIX, "");
                    try {
                        Picasso.with(getApplicationContext()).load(imageUrl).into(imageView);
                        currentImage = i;
                    } catch (Exception e) {
                        Log.e("QRResourcesDisplay", e.toString());
                    }
                }
            }
        });
    }
}


