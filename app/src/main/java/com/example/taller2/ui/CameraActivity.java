package com.example.taller2.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taller2.R;

public class CameraActivity extends AppCompatActivity {
    Button btnCamera;
    Button btnImages;
    ImageView image;
    String cameraPermission = Manifest.permission.CAMERA;
    String imagePermission = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static final int CAMERA_ID = 1;
    public static final int IMAGE_ID = 5;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        image = findViewById(R.id.imageCamera);
        btnCamera = (Button) findViewById(R.id.camera);
        btnImages = (Button) findViewById(R.id.selectImg);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission((Activity) view.getContext(), cameraPermission, "Necesito el permiso para acceder a su cámara", CAMERA_ID);
                updateUICamera();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
            }
        });
        btnImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission((Activity) view.getContext(), imagePermission, "Necesito el permiso para acceder a sus imágenes", CAMERA_ID);
                updateUIImages();
                openGallery();

            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (data.getData() != null) {
                imageUri = data.getData();
                image.setImageURI(imageUri);
            } else {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            }

        }

    }

    private void updateUICamera() {
        if (ContextCompat.checkSelfPermission(this, cameraPermission) == PackageManager.PERMISSION_GRANTED) {
            //Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,projection,null,null,null);
            // adapter.changeCursor(cursor);
        }
    }

    private void updateUIImages() {
        if (ContextCompat.checkSelfPermission(this, imagePermission) == PackageManager.PERMISSION_GRANTED) {
            //Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,projection,null,null,null);
            // adapter.changeCursor(cursor);
        }
    }

    private void requestPermission(Activity context, String permission, String justification, int id) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context, permission)) {
                Toast.makeText(context, justification, Toast.LENGTH_SHORT).show();

            }
            ActivityCompat.requestPermissions(context, new String[]{permission}, id);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_ID) {
            updateUICamera();
        }
        if (requestCode == IMAGE_ID) {
            updateUICamera();
        }
    }

}