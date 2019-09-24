package com.example.exercise.Dao;

import android.database.Cursor;

import com.example.exercise.entity.Student;
import com.example.exercise.entity.StudentOrm;

import java.util.List;

public interface StudentOrmDao {
    List<StudentOrm> selectAllStudent();
    void insert(StudentOrm student);
    void updata(StudentOrm student);
    void delete(StudentOrm student);
    Cursor select();
}
