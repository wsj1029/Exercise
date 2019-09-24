package com.example.exercise;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD=108;
    private static final int UPDATE=109;
    private StudentAdapter studentAdapter;
    private StudentDaoImpl studentDaoImpl;
    private Student selectedStudent;
    private int studentPos;
    private ListView listView;
    private List<Student> students;
    private Button btnADD,btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lv_studentlist);
        btnADD = findViewById(R.id.btn_add);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        btnADD.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        initData();

        studentAdapter = new StudentAdapter(students);
        listView.setAdapter(studentAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                studentAdapter.setSelectItem(i);
                studentPos = i;
                selectedStudent = (Student) adapterView.getItemAtPosition(i);
                studentAdapter.notifyDataSetInvalidated();
            }
        });
    }

    private void initData() {

        studentDaoImpl = new StudentDaoImpl(this);
        students=studentDaoImpl.selectAllStudent();

        if (students == null){
            students = new ArrayList<>();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,ADD);
                break;
            case R.id.btn_update:
                if (selectedStudent != null){
                    Intent intent1 = new Intent(MainActivity.this,Main3Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("updata2",selectedStudent);
                    intent1.putExtras(bundle);
                    startActivityForResult(intent1,UPDATE);
                }
                break;
            case R.id.btn_delete:
                if (selectedStudent != null){
                    studentDaoImpl.delete(selectedStudent.getName());
                    students.remove(studentPos);
                    studentAdapter.notifyDataSetChanged();
                }
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
            selectedStudent = (Student) bundle.get("22");
            if (requestCode == UPDATE) {
                students.set(studentPos, selectedStudent);
            } else if (requestCode == ADD) {
                students.add(selectedStudent);
            }
            studentAdapter.notifyDataSetChanged();
        }

    }
}
