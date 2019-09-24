package com.example.exercise.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.exercise.R;

public class MyCursorAdapter extends CursorAdapter {


    public MyCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student1,viewGroup,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name =view.findViewById(R.id.tv_name);
        TextView classmate =view.findViewById(R.id.tv_classmate);
        TextView age =view.findViewById(R.id.tv_age);

        name.setText(cursor.getString(cursor.getColumnIndex("student_name")));
        classmate.setText(cursor.getString(cursor.getColumnIndex("student_classmate")));
        age.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex("student_age"))));
    }
}
