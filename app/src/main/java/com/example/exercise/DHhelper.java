package com.example.exercise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DHhelper extends SQLiteOpenHelper {
    public DHhelper(Context context){
        super(context,"student.db",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Student.TBL_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists t_student_info");
        onCreate(sqLiteDatabase);
    }
}
