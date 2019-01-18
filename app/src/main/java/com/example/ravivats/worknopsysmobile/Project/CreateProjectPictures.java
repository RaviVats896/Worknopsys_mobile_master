package com.example.ravivats.worknopsysmobile.Project;

import android.app.Activity;
import android.content.ClipData;
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
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import android.support.v7.app.AlertDialog;
import com.example.ravivats.worknopsysmobile.Others.AboutActivity;
import com.example.ravivats.worknopsysmobile.Others.BrowserActivity;
import com.example.ravivats.worknopsysmobile.Others.ConfigurationActivity;
import com.example.ravivats.worknopsysmobile.Constants;
import com.example.ravivats.worknopsysmobile.Customer.CreateCustomer;
import com.example.ravivats.worknopsysmobile.Others.CreateComplaint;
import com.example.ravivats.worknopsysmobile.Others.HoursReviewActivity;
import com.example.ravivats.worknopsysmobile.Others.LoginActivity;
import com.example.ravivats.worknopsysmobile.WorkingOrders.MyWorkingOrders;
import com.example.ravivats.worknopsysmobile.R;
import com.example.ravivats.worknopsysmobile.Utility;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateProjectPictures extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Button cpPicturesNxtBtn;
    Bundle detailsBundle;
    ImageButton cpPicturesCameraBtn, cpPicturesUploadBtn, cpPicturesDeleteBtn;
    String userChosenTask;
    ImageView cpPicturesImageView;
    ListView picturesList;
    String picLocation, picId;
    ArrayList<String> picLocations = new ArrayList<>();
    ArrayList<String> picDescriptions = new ArrayList<>();
    ArrayList<String> picIds = new ArrayList<>();
    ArrayList<Uri> picUris = new ArrayList<>();

    private int REQUEST_CAMERA = 2, SELECT_FILE = 1, flag;
    private static final String TAG = "CPPictures";
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
        picturesList = (ListView) findViewById(R.id.cp_pictures_picturesList);

        picturesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = picUris.get(position);
                try {
                    Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                    cpPicturesImageView.setImageBitmap(thumbnail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        picturesList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int position = i;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateProjectPictures.this);
                alertDialog.setTitle("Set Picture Description");
                // final TextView oldDesc = new TextView(CreateProjectPictures.this);

                if(picDescriptions.get(i) != null)
                    alertDialog.setMessage("Current Description: " + picDescriptions.get(i)); // oldDesc.setText("Current Description: " + picDescriptions.get(i));

                else alertDialog.setMessage("Current Description: no description"); // oldDesc.setText("Current Description: no description");

                final EditText newDesc = new EditText(CreateProjectPictures.this);
                newDesc.setHint("New Description");
                LinearLayout ll = new LinearLayout(CreateProjectPictures.this);
                ll.setOrientation(LinearLayout.VERTICAL);
                // ll.addView(oldDesc);
                ll.addView(newDesc);

                alertDialog.setView(ll);

                alertDialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        picDescriptions.set(position, newDesc.getText().toString());
                    }
                });

                alertDialog.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = alertDialog.create();
                alert.show();
                return true;
            }
        });
        cpPicturesImageView = (ImageView) findViewById(R.id.cp_pictures_ImageView);
        cpPicturesNxtBtn = (Button) findViewById(R.id.cp_pictures_nextBtn);
        cpPicturesCameraBtn = (ImageButton) findViewById(R.id.cp_pictures_camera);
        cpPicturesUploadBtn = (ImageButton) findViewById(R.id.cp_pictures_upload);
        cpPicturesDeleteBtn = (ImageButton) findViewById(R.id.cp_pictures_delete);
        detailsBundle = getIntent().getExtras();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Map config = new HashMap();
        config.put("cloud_name", "worknopsys");
        config.put("api_key", "675743786426914");
        config.put("api_secret", "8aoJtViSin-WWv4NF5XLIwf9tnI");
        final Cloudinary cloudinary = new Cloudinary(config);

        cpPicturesImageView.setImageResource(R.drawable.ic_menu_gallery_black);
        cpPicturesUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    for (int count = 0; count < picLocations.size(); count++) {
                        String picLtn =  picLocations.get(count);
                        picId = detailsBundle.getString("CustomerName") + "_" + detailsBundle.getString("CustomerName")
                                + "_" + Constants.getDate() + "_" + Constants.getTime() + "_pic_" + count;
                        Log.e("picID", picId);
                        Map m = cloudinary.uploader().upload(picLtn, ObjectUtils.asMap("public_id", picId));
                        Toast.makeText(CreateProjectPictures.this, "Upload Successful.", Toast.LENGTH_SHORT).show();
                        picIds.add(picId);
                    }
                } catch (IOException e) {
                    Toast.makeText(CreateProjectPictures.this, "Upload failed. Check your internet connection.", Toast.LENGTH_SHORT).show();
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
                cpPicturesImageView.setImageResource(R.drawable.ic_menu_gallery_black);
            }
        });
        cpPicturesNxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsBundle.putString("PictureId", picId);
                detailsBundle.putStringArray("PictureIdArray", picIds.toArray(new String[0]));
                detailsBundle.putStringArray("PictureDescriptionArray", picDescriptions.toArray(new String[0]));
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
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
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
        picLocations = new ArrayList<>();
        Bitmap bm = null;
        if (data != null) {
            if (data.getClipData() != null) {
                ClipData mClipData = data.getClipData();
                ArrayList<Uri> mArrayUri = new ArrayList<>();
                for (int i = 0; i < mClipData.getItemCount(); i++) {
                    ClipData.Item item = mClipData.getItemAt(i);
                    Uri uri = item.getUri();
                    mArrayUri.add(uri);
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
                        getUri(bm);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                picUris = mArrayUri;

                picDescriptions.clear();
                for(String picLocation : picLocations){
                    picDescriptions.add("No description");
                }

                ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, picLocations);
                picturesList.setAdapter(adapter);
            } else if(data.getData() != null) {

                // Get the cursor
                Log.d(TAG, "data not null");
                try {
                    bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    getUri(bm);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                picDescriptions.clear();
                for(String picLocation : picLocations){
                    picDescriptions.add("No description");
                }

                ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, picLocations);
                picturesList.setAdapter(adapter);
            }
        }
        flag = 1;
        return bm;
    }


    private void onCaptureImageResult(Intent data) {
        picLocations = new ArrayList<>();
        // Bitmap bitmap = cpPicturesImageView.getDrawingCache();
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        //Bitmap b = Bitmap.createScaledBitmap(thumbnail, 150, 150, false);
        cpPicturesImageView.setImageBitmap(thumbnail);
        flag = 1;
        getUri(thumbnail);

        picDescriptions.clear();
        for(String picLocation : picLocations){
            picDescriptions.add("No description");
        }
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
        picLocation = fileUri.toString().substring(7);
        picLocations.add(picLocation);
        Log.e("Destination" + (picLocations.size() + 1), picLocation);
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
        int id = item.getItemId();

        if (id == R.id.nav_hours_review) {
            startActivity(new Intent(CreateProjectPictures.this, HoursReviewActivity.class));
        } else if (id == R.id.nav_working_orders) {
            startActivity(new Intent(CreateProjectPictures.this, MyWorkingOrders.class));
        } else if (id == R.id.nav_facebook) {
            startActivity(new Intent(CreateProjectPictures.this, BrowserActivity.class).putExtra("choice", 1));
        } else if (id == R.id.nav_youtube) {
            startActivity(new Intent(CreateProjectPictures.this, BrowserActivity.class).putExtra("choice", 2));
        } else if (id == R.id.nav_whatsapp) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_whatsapp));
            sendIntent.setType("text/plain");
            sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        } else if (id == R.id.nav_create_customer) {
            startActivity(new Intent(CreateProjectPictures.this, CreateCustomer.class));
        } else if (id == R.id.nav_create_complaint) {
            startActivity(new Intent(CreateProjectPictures.this, CreateComplaint.class));
        } else if (id == R.id.nav_create_project) {
            startActivity(new Intent(CreateProjectPictures.this, CreateProjectDetails.class));
        }
//        else if (id == R.id.nav_mgmt_working_orders) {
//            startActivity(new Intent(CreateProjectPictures.this, ManagementWorkingOrders.class));
//        }
        else if (id == R.id.nav_config) {
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
