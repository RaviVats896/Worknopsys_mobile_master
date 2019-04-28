package com.example.ravivats.worknopsysmobile.Others;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ravivats.worknopsysmobile.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QRResourcesDisplay extends AppCompatActivity {

    private TextView qrResourcesDisplay;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrresources_display);

        qrResourcesDisplay = (TextView) findViewById(R.id.qr_resources_display_textView);
        imageView = (ImageView) findViewById(R.id.qr_resources_display_imageView);

        ArrayList<String> qrScanInfoTempList = getIntent().getExtras().getStringArrayList("qrScanInfoList");
        String tempQRString = "";
        if (qrScanInfoTempList.size() > 0) {
            for (String data : qrScanInfoTempList) {


                if(data.contains(QRScanActivity.QR_DESC_INFO_PREFIX)){
                    String imageDesc = data.replaceFirst(QRScanActivity.QR_DESC_INFO_PREFIX, "");
                    tempQRString += imageDesc + " ";
                }

                if (data.contains(QRScanActivity.QR_IMAGE_INFO_PREFIX)) {
                    String imageUrl = data.replaceFirst(QRScanActivity.QR_IMAGE_INFO_PREFIX, "");
                    try {
                        Picasso.with(getApplicationContext()).load(imageUrl).into(imageView);
                    } catch (Exception e) {
                        Log.d("QRResourcesDisplay", e.toString());
                    }
                }
            }


            qrResourcesDisplay.setText(tempQRString);
        } else {
            Toast.makeText(this, "No resources to display", Toast.LENGTH_LONG).show();
        }
    }
}
