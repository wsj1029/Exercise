package com.example.exercise.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exercise.entity.Student;
import com.example.exercise.entity.StudentOrm;
import com.example.exercise.utils.DBOrmHepler;
import com.example.exercise.utils.DHhelper;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentOrmDaoImpl implements StudentOrmDao {

    private DBOrmHepler dbHelper;
    private Dao<StudentOrm,Integer> dao;

    public StudentOrmDaoImpl(Context context) {
        dbHelper = new DBOrmHepler(context);

        try {
            dao = dbHelper.getDao(StudentOrm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<StudentOrm> selectAllStudent() {
     List<StudentOrm> studentOrms = null;
        try {
            studentOrms = dao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentOrms;
    }


    @Override
    public void insert(StudentOrm student) {
        try {
            dao.create(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updata(StudentOrm student) {
        try {
            dao.update(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(StudentOrm student) {
        try {
            dao.delete(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Cursor select() {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            return db.query("t_student_info",null,null,null,null,
                    null,null);
        }
}
