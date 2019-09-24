package com.example.exercise.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.exercise.entity.StudentOrm;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DBOrmHepler extends OrmLiteSqliteOpenHelper {

    public DBOrmHepler(Context context) {
        super(context, "test.db", null, 1);
    }

    private static DBOrmHepler dbOrmHepler;
    public static synchronized DBOrmHepler newInstance(Context context){
        if (dbOrmHepler == null){
            dbOrmHepler = new DBOrmHepler(context);
        }
        return dbOrmHepler;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            //
            TableUtils.createTable(connectionSource, StudentOrm.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            //
            TableUtils.dropTable(connectionSource,StudentOrm.class,true);
            onCreate(sqLiteDatabase,connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
