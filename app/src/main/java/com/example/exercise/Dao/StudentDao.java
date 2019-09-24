package com.example.exercise.Dao;

import android.database.Cursor;

import com.example.exercise.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> selectAllStudent();
    void insert(Student student);
    void updata(Student student);
    void delete(int studentID);
    Cursor select();
}
