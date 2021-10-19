package com.example.taller2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class SimplelocationActivity2 extends AppCompatActivity {
    TextView longitude, latitude, elevation;
    //Permissions attributes
    String permission = Manifest.permission.ACCESS_FINE_LOCATION;
    int permissionId = 0;

    //Simple location attributes
    private FusedLocationProviderClient fusedLocationProviderClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplelocation2);
        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        longitude= findViewById(R.id.longitude);
        latitude= findViewById(R.id.latitude);
        elevation= findViewById(R.id.elevation);

        requestPermission(this, permission, "Access to GPS", permissionId);
        initView();
    }

    private void initView(){
        Log.i("TAG", "Enters initview");
        if (ContextCompat.checkSelfPermission(this, permission)
                == PackageManager.PERMISSION_GRANTED) {
            Log.i("TAG", "permission granted");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener( this, new OnSuccessListener<Location>(){
                @Override
                public void onSuccess(Location location) {
                    Log.i("TAG", "location null");
                    if(location != null){
                        Log.i("TAG", "location: "+location.toString());
                        longitude.setText(String.valueOf(location.getLongitude()));
                        latitude.setText(String.valueOf(location.getLatitude()));
                        elevation.setText(String.valueOf(location.getAltitude()));

                    }

                }

            });

        }
    }

    private void requestPermission(Activity contex, String permission, String justification, int id){
        if (ContextCompat.checkSelfPermission(contex, this.permission)
                != PackageManager.PERMISSION_GRANTED){
            //should we show an explanation?
            if(ActivityCompat.shouldShowRequestPermissionRationale(contex,
                    Manifest.permission.READ_CONTACTS)){
                Toast.makeText(contex, justification, Toast.LENGTH_SHORT).show();
            }
            //request de permissiom
            ActivityCompat.requestPermissions(contex, new String[]{this.permission}, id);
        }
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode== permissionId){
            initView();

        }
    }

}
