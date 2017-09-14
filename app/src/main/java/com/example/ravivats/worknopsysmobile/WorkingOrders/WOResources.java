package com.example.ravivats.worknopsysmobile.WorkingOrders;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.Utility;
import com.example.ravivats.worknopsysmobile.domain.WorkingOrder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class WOResources extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static final String RESOURCES_URL = "http://worknopsys.ml:5000/api/resourcesapp/create";
    private int REQUEST_CAMERA = 2, SELECT_FILE = 1, flag, workingOrderIndex, imageViewChoice = 0;
    private static final String TAG = "WOResources";
    private Uri fileUri;
    Calendar cal;
    StringRequest resourcesRequest;
    DatePickerDialog.OnDateSetListener woResourcesWorkDateListener;
    ImageButton woResourcesCamera1Btn, woResourcesUpload1Btn, woResourcesDelete1Btn, woResourcesCamera2Btn,
            woResourcesUpload2Btn, woResourcesDelete2Btn, woResourcesCamera3Btn, woResourcesUpload3Btn, woResourcesDelete3Btn;
    ImageView woResourcesImageView1, woResourcesImageView2, woResourcesImageView3;
    EditText woResourcesImageDesc1, woResourcesImageDesc2, woResourcesImageDesc3, woResourcesWorkDate, woResourcesPName;
    Button woResourcesNxtBtn;
    String userChosenTask;
    String picLocation1 = "", picLocation2 = "", picLocation3 = "", picId;
    ArrayList<WorkingOrder> workingOrders;
    WorkingOrder workingOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_woresources);
        woResourcesImageView1 = (ImageView) findViewById(R.id.wo_resources_ImageView1);
        woResourcesImageView2 = (ImageView) findViewById(R.id.wo_resources_ImageView2);
        woResourcesImageView3 = (ImageView) findViewById(R.id.wo_resources_ImageView3);
        woResourcesCamera1Btn = (ImageButton) findViewById(R.id.wo_resources_camera1);
        woResourcesCamera2Btn = (ImageButton) findViewById(R.id.wo_resources_camera2);
        woResourcesCamera3Btn = (ImageButton) findViewById(R.id.wo_resources_camera3);
        woResourcesUpload1Btn = (ImageButton) findViewById(R.id.wo_resources_upload1);
        woResourcesUpload2Btn = (ImageButton) findViewById(R.id.wo_resources_upload2);
        woResourcesUpload3Btn = (ImageButton) findViewById(R.id.wo_resources_upload3);
        woResourcesDelete1Btn = (ImageButton) findViewById(R.id.wo_resources_delete1);
        woResourcesDelete2Btn = (ImageButton) findViewById(R.id.wo_resources_delete2);
        woResourcesDelete3Btn = (ImageButton) findViewById(R.id.wo_resources_delete3);
        woResourcesImageDesc1 = (EditText) findViewById(R.id.wo_resources_description1);
        woResourcesImageDesc2 = (EditText) findViewById(R.id.wo_resources_description2);
        woResourcesImageDesc3 = (EditText) findViewById(R.id.wo_resources_description3);
        woResourcesWorkDate = (EditText) findViewById(R.id.wo_resources_wDate);
        woResourcesPName = (EditText) findViewById(R.id.wo_resources_pName);
        woResourcesNxtBtn = (Button) findViewById(R.id.wo_resources_nextBtn);

        workingOrderIndex = getIntent().getIntExtra("woPosition", 0);
        workingOrders = new ArrayList<WorkingOrder>();
        workingOrders = Constants.getWorkingOrders();
        workingOrder = workingOrders.get(workingOrderIndex);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        cal = Calendar.getInstance();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Map config = new HashMap();
        config.put("cloud_name", "worknopsys");
        config.put("api_key", "675743786426914");
        config.put("api_secret", "8aoJtViSin-WWv4NF5XLIwf9tnI");
        final Cloudinary cloudinary = new Cloudinary(config);

        woResourcesImageView1.setImageResource(R.drawable.ic_menu_gallery_black);
        woResourcesImageView2.setImageResource(R.drawable.ic_menu_gallery_black);
        woResourcesImageView3.setImageResource(R.drawable.ic_menu_gallery_black);

        woResourcesWorkDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dp1 = new DatePickerDialog(WOResources.this, woResourcesWorkDateListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                dp1.show();
            }
        });

        woResourcesWorkDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                woResourcesWorkDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };

        woResourcesCamera1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewChoice = 1;
                selectImage();
            }
        });
        woResourcesCamera2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewChoice = 2;
                selectImage();
            }
        });
        woResourcesCamera3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewChoice = 3;
                selectImage();
            }
        });
        woResourcesUpload1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    picId = workingOrder.getId() + Constants.getDate() + "_" + Constants.getTime();
                    Log.e("picID", picId);
                    if (!picLocation1.equals("")) {
                        Map m = cloudinary.uploader().upload(picLocation1, ObjectUtils.asMap("public_id", picId));
                        Toast.makeText(WOResources.this, "Upload Successful.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(WOResources.this, "Upload failed. Check your internet connection.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        woResourcesUpload2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    picId = workingOrder.getId() + Constants.getDate() + "_" + Constants.getTime();
                    Log.e("picID", picId);
                    if (!picLocation2.equals("")) {
                        Map m = cloudinary.uploader().upload(picLocation2, ObjectUtils.asMap("public_id", picId));
                        Toast.makeText(WOResources.this, "Upload Successful.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(WOResources.this, "Upload failed. Check your internet connection.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        woResourcesUpload3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    picId = workingOrder.getId() + Constants.getDate() + "_" + Constants.getTime();
                    Log.e("picID", picId);
                    if (!picLocation3.equals("")) {
                        Map m = cloudinary.uploader().upload(picLocation3, ObjectUtils.asMap("public_id", picId));
                        Toast.makeText(WOResources.this, "Upload Successful.", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(WOResources.this, "Upload failed. Check your internet connection.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        woResourcesDelete1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woResourcesImageView1.setImageResource(R.drawable.ic_menu_gallery_black);
            }
        });
        woResourcesDelete2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woResourcesImageView2.setImageResource(R.drawable.ic_menu_gallery_black);
            }
        });
        woResourcesDelete3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                woResourcesImageView3.setImageResource(R.drawable.ic_menu_gallery_black);
            }
        });

        woResourcesNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] rList= {picLocation1, picLocation2,picLocation3};
                String[] rDescriptionList= {woResourcesImageDesc1.getText().toString(),woResourcesImageDesc2.getText().toString(),
                        woResourcesImageDesc3.getText().toString()};
                resourcesRequest = new StringRequest(Request.Method.POST, RESOURCES_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(WOResources.this, "Response is:" + response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(WOResources.this, MyWorkingOrderDetails.class));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(WOResources.this, "Request failed! Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                                error.printStackTrace();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("employee", Constants.getEMPLOYEE().getId());
                        params.put("wo", workingOrder.getId());
                        params.put("startdate", workingOrder.getStartDate());
                        params.put("workdate", woResourcesWorkDate.getText().toString());
                        params.put("pname", woResourcesPName.getText().toString());
                        params.put("rlist1", picLocation1);
                        params.put("rlist2", picLocation2);
                        params.put("rlist3", picLocation3);
                        params.put("rdesc1", woResourcesImageDesc1.getText().toString());
                        params.put("rdesc2", woResourcesImageDesc2.getText().toString());
                        params.put("rdesc3", woResourcesImageDesc3.getText().toString());
                        return params;
                    }

                };
                requestQueue.add(resourcesRequest);
            }
        });


    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(WOResources.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(WOResources.this);
                if (items[item].equals("Take Photo")) {
                    userChosenTask = "Take Photo";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                Bitmap bm = onSelectFromGalleryResult(data);
                Log.d(TAG, "gallery");
                switch (imageViewChoice) {
                    case 1:
                        woResourcesImageView1.setImageBitmap(bm);
                        break;
                    case 2:
                        woResourcesImageView2.setImageBitmap(bm);
                        break;
                    case 3:
                        woResourcesImageView3.setImageBitmap(bm);
                        break;
                    default:
                        break;
                }
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
                Log.d(TAG, "image");
            }
        }
    }

    @SuppressWarnings("deprecation")
    private Bitmap onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            Log.d(TAG, "data not null");
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                getUri(bm);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        flag = 1;
        return bm;
    }

    private void onCaptureImageResult(Intent data) {
        // Bitmap bitmap = cpPicturesImageView.getDrawingCache();
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        //Bitmap b = Bitmap.createScaledBitmap(thumbnail, 150, 150, false);
        switch (imageViewChoice) {
            case 1:
                woResourcesImageView1.setImageBitmap(thumbnail);
                break;
            case 2:
                woResourcesImageView2.setImageBitmap(thumbnail);
                break;
            case 3:
                woResourcesImageView3.setImageBitmap(thumbnail);
                break;
            default:
                break;
        }
        flag = 1;
        getUri(thumbnail);
    }

    private void getUri(Bitmap bitmap) {
        switch (imageViewChoice) {
            case 1:
                woResourcesImageView1.setDrawingCacheEnabled(true);
                woResourcesImageView1.buildDrawingCache();
                break;
            case 2:
                woResourcesImageView2.setDrawingCacheEnabled(true);
                woResourcesImageView2.buildDrawingCache();
                break;
            case 3:
                woResourcesImageView3.setDrawingCacheEnabled(true);
                woResourcesImageView3.buildDrawingCache();
                break;
            default:
                break;
        }
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        Log.d(TAG, "dest" + destination);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileUri = Uri.fromFile(destination);
        switch (imageViewChoice) {
            case 1:
                picLocation1 = fileUri.toString().substring(7);
                Log.i("Destination: pic1 ", picLocation1);
                break;
            case 2:
                picLocation2 = fileUri.toString().substring(7);
                Log.i("Destination: pic2 ", picLocation2);
                break;
            case 3:
                picLocation3 = fileUri.toString().substring(7);
                Log.i("Destination: pic3 ", picLocation3);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    }
}
