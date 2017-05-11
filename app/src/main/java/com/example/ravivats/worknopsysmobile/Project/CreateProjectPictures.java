package com.example.ravivats.worknopsysmobile.Project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.ravivats.worknopsysmobile.AboutActivity;
import com.example.ravivats.worknopsysmobile.ConfigurationActivity;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Customer.CreateCustomer;
import com.example.ravivats.worknopsysmobile.HoursReviewActivity;
import com.example.ravivats.worknopsysmobile.LoginActivity;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateProjectPictures extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button cpPicturesNxtBtn;
    Bundle detailsBundle;
    ImageButton cpPicturesCameraBtn, cpPicturesUploadBtn, cpPicturesDeleteBtn;
    String userChosenTask;
    ImageView cpPicturesImageView;
    String picLocation,picId;
    private int REQUEST_CAMERA = 2;
    private int SELECT_FILE = 1;
    private int flag;
    private static final String TAG = "AddBook";
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project_pictures);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        cpPicturesImageView = (ImageView) findViewById(R.id.cp_pictures_ImageView);
        cpPicturesNxtBtn = (Button) findViewById(R.id.cp_pictures_nextBtn);
        cpPicturesCameraBtn = (ImageButton) findViewById(R.id.cp_pictures_camera);
        cpPicturesUploadBtn = (ImageButton) findViewById(R.id.cp_pictures_upload);
        cpPicturesDeleteBtn = (ImageButton) findViewById(R.id.cp_pictures_delete);
        detailsBundle=getIntent().getExtras();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Map config = new HashMap();
        config.put("cloud_name", "worknopsys");
        config.put("api_key", "675743786426914");
        config.put("api_secret", "8aoJtViSin-WWv4NF5XLIwf9tnI");
        final Cloudinary cloudinary = new Cloudinary(config);

        cpPicturesImageView.setImageResource(R.drawable.ic_satellite_black_24dp);
        cpPicturesUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                     picId=detailsBundle.getString("CustomerName")+"_"+detailsBundle.getString("CustomerName")
                            +"_"+ Constants.getDate()+"_"+Constants.getTime();
                    Log.e("picID",picId);
                    Map m= cloudinary.uploader().upload(picLocation,ObjectUtils.asMap("public_id", picId));
                    Toast.makeText(CreateProjectPictures.this, "Upload Successful.", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(CreateProjectPictures.this, "Upload failed. Check your internet connection.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        });
        cpPicturesCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        cpPicturesDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpPicturesImageView.setImageResource(R.drawable.ic_satellite_black_24dp);
            }
        });
        cpPicturesNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsBundle.putString("PictureId",picId);
                Intent in = new Intent(CreateProjectPictures.this, CreateProjectOrders.class);
                in.putExtras(detailsBundle);
                startActivity(in);
            }
        });

    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library",
                "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateProjectPictures.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(CreateProjectPictures.this);
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
                cpPicturesImageView.setImageBitmap(bm);
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
        cpPicturesImageView.setImageBitmap(thumbnail);
        flag = 1;
        getUri(thumbnail);
    }

    private void getUri(Bitmap bitmap) {
        cpPicturesImageView.setDrawingCacheEnabled(true);
        cpPicturesImageView.buildDrawingCache();

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
        picLocation=fileUri.toString().substring(7);
        Log.e("Destination",picLocation);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_daily_overview) {
            // Handle the camera action
        } else if (id == R.id.nav_working_orders) {

        } else if(id==R.id.nav_facebook){

        } else if(id==R.id.nav_whatsapp){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_whatsapp));
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else if (id == R.id.nav_create_customer) {
            startActivity(new Intent(CreateProjectPictures.this, CreateCustomer.class));
        } else if (id == R.id.nav_create_project) {
            startActivity(new Intent(CreateProjectPictures.this, CreateProjectDetails.class));
        } else if (id == R.id.nav_mgmt_working_orders) {

        } else if (id == R.id.nav_hours_review) {
            startActivity(new Intent(CreateProjectPictures.this, HoursReviewActivity.class));
        } else if (id == R.id.nav_config) {
            startActivity(new Intent(CreateProjectPictures.this, ConfigurationActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(CreateProjectPictures.this, LoginActivity.class));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(CreateProjectPictures.this, AboutActivity.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
