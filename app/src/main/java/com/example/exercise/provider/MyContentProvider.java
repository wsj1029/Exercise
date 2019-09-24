package com.example.exercise.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.exercise.utils.DBOrmHepler;
import com.example.exercise.utils.DHhelper;

public class MyContentProvider extends ContentProvider {

    private SQLiteDatabase db;
    public static final String TBL_STUDENT = "t_student";
    public static final  String _ID="_id";

    private static final String CONTENT = "content://";
    private static final String AUTHORIY = "com.example.exercise";
    private static  final String URI = CONTENT + AUTHORIY + "/" + TBL_STUDENT;
    public static final Uri STUDENT_URI = Uri.parse(URI);

    private static final String STUDENT_TYPE_ITEM = "vnd.android.cursor.item/vnd."+AUTHORIY;
    private static final String STUDENT_TYPE = "vnd.android.cursor.dir/vnd."+AUTHORIY;

    static final int STUDENT = 1;
    static final int STUDENT_ITEM = 2;

    static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI(AUTHORIY,TBL_STUDENT,STUDENT);
        matcher.addURI(AUTHORIY,TBL_STUDENT+"/#",STUDENT_ITEM);
    }

    public MyContentProvider() {
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int newID = db.delete(TBL_STUDENT,selection,selectionArgs);
        if (newID > 0){
            return newID;
        }
        throw new IllegalArgumentException("删除失败"+uri);
    }


    //作用，在进行增删改查时进行配对根据type选择对应的数据表
    @Override
    public String getType(Uri uri) {
        switch (matcher.match(uri)){
            case STUDENT:
                return STUDENT_TYPE;
            case STUDENT_ITEM:
                return STUDENT_TYPE_ITEM;
                default:
                    throw new RuntimeException("错误的uri");
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long newID =0;
        newID = db.insert(TBL_STUDENT,null,values);
        Uri newUri = Uri.parse(CONTENT + AUTHORIY + "/" + TBL_STUDENT + "/" + newID);
        if (newID > 0){
            return newUri;
        }
        throw new IllegalArgumentException("插入失败"+uri);
    }



    @Override
    public boolean onCreate() {
        DHhelper hhelper = new DHhelper(getContext());
        db = hhelper.getWritableDatabase();
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        switch (matcher.match(uri)){
            case STUDENT:
                cursor = db.query(TBL_STUDENT,projection,
                        selection,selectionArgs,null,null,sortOrder);
                break;
            case STUDENT_ITEM:
                String id = uri.getPathSegments().get(1);
                cursor =db.query(TBL_STUDENT,projection,
                        "_id=?",new String[]{id},
                        null,null,sortOrder);
                break;
        }
      return cursor;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int newId = db.update(TBL_STUDENT,values,selection,selectionArgs);
        if (newId>0){
            return newId;
        }
        throw new IllegalArgumentException("更新失败"+uri);
    }
}
