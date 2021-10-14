package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.taller2.ui.CameraActivity;
import com.example.taller2.ui.ContactsActiviy;
import com.example.taller2.ui.MapsActivity;

public class MainActivity extends AppCompatActivity {
    ImageButton btnCamera;
    ImageButton btnContacts;
    ImageButton btnMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCamera = findViewById(R.id.cameraBtn);
        btnContacts = findViewById(R.id.contactsBtn);
        btnMaps = findViewById(R.id.mapsBtn);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CameraActivity.class);
                startActivity(intent);
            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ContactsActiviy.class);
                startActivity(intent);
            }
        });
        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}