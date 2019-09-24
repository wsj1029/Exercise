package com.example.exercise.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.exercise.entity.Student;
import com.example.exercise.provider.MyContentProvider;
import com.example.exercise.utils.DHhelper;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private DHhelper dbHelper;
    private SQLiteDatabase db;
    private Context context;

    public StudentDaoImpl(Context context) {
        dbHelper = new DHhelper(context);
        this.context = context;
    }


    @Override
    public List<Student> selectAllStudent() {
//        String sql = "select * from t_student_info";
        List<Student> students = null;

        // 1. 获取SQLiteDatabase对象
        db = dbHelper.getReadableDatabase();

        Cursor cursor = context.getContentResolver()
                .query(MyContentProvider.STUDENT_URI,null,null,null,null);


//        // 2. 执行SQL查询
//        // Cursor cursor = db.query(Student.TBL_NAME, null, null, null, null, null, null);
//        Cursor cursor = db.rawQuery(sql, null);
//
        // 3. 处理结果
        if (cursor != null && cursor.getCount() > 0) {
            students = new ArrayList<>();
            while (cursor.moveToNext()) {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                student.setName(cursor.getString(cursor.getColumnIndex("student_name")));
                student.setClassmage(cursor.getString(cursor.getColumnIndex("student_classmate")));
                student.setAge(cursor.getInt(cursor.getColumnIndex("student_age")));
                students.add(student);
            }
            // 4. 关闭cursor
            cursor.close();
        }
        db.close();
        // 5. 返回结果
        return students;
    }


    @Override
    public void insert(Student student) {
        db = dbHelper.getWritableDatabase();
//        String sql = "insert into t_student_info values(null,?,?,?)";
//        db.execSQL(sql, new Object[]{
//                student.getName(),
//                student.getClassmage(),
//                student.getAge()});

        ContentValues values = new ContentValues();
        values.put("student_name",student.getName());
        values.put("student_classmate",student.getClassmage());
        values.put("student_age",student.getAge());

        Uri uri = context.getContentResolver().insert(MyContentProvider.STUDENT_URI,values);
        Log.i("Exercise",uri != null ? uri.toString():"");
        db.close();
    }


    @Override
    public void updata(Student student) {
        // 1. 获取db对象
        db = dbHelper.getWritableDatabase();
        // 2. 执行sql
//        String sql = "update t_student_info set student_name=? ,student_classmate=?,student_age=? where _id=?";
//        db.execSQL(sql, new Object[]{
//                student.getName(),
//                student.getClassmage(),
//                student.getAge(),
//                student.getId()
//        });


        ContentValues values = new ContentValues();
        values.put("student_name",student.getName());
        values.put("student_classmate",student.getClassmage());
        values.put("student_age",student.getAge());

        context.getContentResolver().update(MyContentProvider.STUDENT_URI
                ,values
                ,"_id=?"
                ,new String[]{String.valueOf(student.getId())});
        db.close();
    }

    @Override
    public void delete(int studentID) {
        // 1. 获取db对象
        db = dbHelper.getWritableDatabase();
        context.getContentResolver().delete(MyContentProvider.STUDENT_URI,"_id=?",new String[]{String.valueOf(studentID)});
    }

    @Override
    public Cursor select() {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            return db.query("t_student",null,null,null,null,
                    null,null);
        }

//    public Cursor selectByCursor(String condition,String[] conditionArgs){
//        SQLiteDatabase db = helper.getReadableDatabase();
//        return db.query("insert1",null,condition,conditionArgs,null,null,
//                null);

}
