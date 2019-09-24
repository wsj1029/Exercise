package com.example.exercise.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exercise.R;

import java.util.ArrayList;
import java.util.List;

public class PhoneActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView1;
    private List<String> contacts;
    private Button read1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        listView1 = findViewById(R.id.studentlist1);
        read1 = findViewById(R.id.read);

        read1.setOnClickListener(this);
    }


    private void readContacts() {
        Cursor cursor = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);

        //1.获取联系人，将他放入list中
        contacts = new ArrayList<>();
        if (cursor != null && cursor.moveToNext()){
            do{
                String name = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phone = cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add("姓名:"+name +"    "+"手机号码:" + phone);
            }while (cursor.moveToNext());
            cursor.close();
        }

        //2.设置adapter
        if(contacts.isEmpty()){
            Toast.makeText(PhoneActivity.this,"没有联系人",Toast.LENGTH_SHORT);
            return;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,contacts);
        listView1.setAdapter(arrayAdapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readContacts();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.read:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_CONTACTS},1);
                }
                else{
                    readContacts();
                }

                break;
        }
    }
}
