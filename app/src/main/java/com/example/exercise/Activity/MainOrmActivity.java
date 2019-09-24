package com.example.exercise.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exercise.Adapter.MyCursorAdapter;
import com.example.exercise.Adapter.StudentOrmAdapter;
import com.example.exercise.Dao.StudentDaoImpl;
import com.example.exercise.Dao.StudentOrmDaoImpl;
import com.example.exercise.R;
import com.example.exercise.entity.Student;
import com.example.exercise.entity.StudentOrm;

import java.util.ArrayList;
import java.util.List;

public class MainOrmActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD=108;
    private static final int UPDATE=109;
    private StudentOrmAdapter studentOrmAdapter;
    private StudentOrmDaoImpl studentDaoImpl;
    private StudentOrm selectedStudent;
    private int studentPos;
    private ListView listView;
    private List<StudentOrm> students;
    private Button btnADD,btnUpdate,btnDelete,btnRead;
    private List<String> contacts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_studentlist);
        btnADD = findViewById(R.id.btn_add);
        btnRead = findViewById(R.id.btn_read);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        btnADD.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        initData();

        studentOrmAdapter = new StudentOrmAdapter(students);
        listView.setAdapter(studentOrmAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                studentOrmAdapter.setSelectItem(i);
                studentPos = i;
                selectedStudent = (StudentOrm) adapterView.getItemAtPosition(i);
                studentOrmAdapter.notifyDataSetInvalidated();
            }
        });
    }

    private void initData() {
        studentDaoImpl = new StudentOrmDaoImpl(this);
        students=studentDaoImpl.selectAllStudent();

        if (students == null){
            students = new ArrayList<>();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Intent intent = new Intent(MainOrmActivity.this,Main2Activity.class);
                startActivityForResult(intent,ADD);
                break;
            case R.id.btn_update:
                if (selectedStudent != null){
                    Intent intent1 = new Intent(MainOrmActivity.this,Main3Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updata2",selectedStudent);
                    intent1.putExtras(bundle);
                    startActivityForResult(intent1,UPDATE);
                }
                break;
            case R.id.btn_delete:
                if (selectedStudent != null){
                    studentDaoImpl.delete(selectedStudent);
                    students.remove(studentPos);
                    studentOrmAdapter.notifyDataSetChanged();
                }
                break;
            case R.id.btn_read:
                Intent intent1 = new Intent(MainOrmActivity.this,PhoneActivity.class);
                startActivity(intent1);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK){
            return;
        }
                if (data != null) {
            Bundle bundle = data.getExtras();
            if (bundle == null) {
                return;
            }
            selectedStudent = (StudentOrm) bundle.get("22");
            if (requestCode == UPDATE) {
                students.set(studentPos, selectedStudent);
            } else if (requestCode == ADD) {
                students.add(selectedStudent);
            }
                    studentOrmAdapter.notifyDataSetChanged();
                }
    }
}
