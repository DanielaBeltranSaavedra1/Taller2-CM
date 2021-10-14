package com.example.taller2.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taller2.R;
import com.example.taller2.adapters.ListContactsAdapter;

public class ContactsActiviy extends AppCompatActivity {
    private TextView statusPermission;
    ListView listContacts;
    ListContactsAdapter adapter;
    String contactsPermission = Manifest.permission.READ_CONTACTS;
    public static final int CONTACTS_ID =5;
    String [] projection = new String[]{ContactsContract.Profile._ID, ContactsContract.Profile.DISPLAY_NAME_PRIMARY};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_activiy);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        listContacts = findViewById(R.id.listContacts);
        adapter = new ListContactsAdapter(this, null,0);
        listContacts.setAdapter(adapter);
        //statusPermission = findViewById(R.id.statusPermission);
        requestPermission( this,contactsPermission, "Necesito el permiso para mostrar los contactos", CONTACTS_ID);
        updateUI();

    }


    private void updateUI() {
        if(ContextCompat.checkSelfPermission(this,contactsPermission) == PackageManager.PERMISSION_GRANTED){
            Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,projection,null,null,null);
            adapter.changeCursor(cursor);
        }
    }

    private void requestPermission(Activity context, String permission, String justification, int id) {
        if(ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(context,permission)){
                Toast.makeText(context, justification, Toast.LENGTH_SHORT).show();

            }
            ActivityCompat.requestPermissions(context, new  String[]{permission},id);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CONTACTS_ID){
            updateUI();
        }
    }
}