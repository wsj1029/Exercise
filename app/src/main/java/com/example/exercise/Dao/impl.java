package com.example.exercise.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.exercise.entity.StudentOrm;
import com.example.exercise.utils.DBOrmHepler;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class impl implements StudentOrmDao {

    private DBOrmHepler dbOrmHepler;
    private Dao<StudentOrm,Integer> dao;

    public impl (Context context){
        dbOrmHepler = new DBOrmHepler(context);
        try {
            dao=dbOrmHepler.getDao(StudentOrm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public List<StudentOrm> selectAllStudent() {
        List<StudentOrm> studentOrms = null;
        try {
            studentOrms=dao.queryForAll();
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
        SQLiteDatabase db = dbOrmHepler.getReadableDatabase();
        return db.query("t_student",null,null,null,null,
                null,null);
    }
}
