package com.example.exercise.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.exercise.R;
import com.example.exercise.entity.Student;
import com.example.exercise.entity.StudentOrm;

import java.util.List;

public class StudentOrmAdapter extends BaseAdapter {

    private int selectItem = -1;
    private List<StudentOrm> students;

    public StudentOrmAdapter(List<StudentOrm> students) {
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student1,viewGroup,false);
        StudentOrm studentOrm = students.get(i);

        TextView name = view.findViewById(R.id.tv_name);
        TextView classmage = view.findViewById(R.id.tv_classmate);
        TextView age = view.findViewById(R.id.tv_age);

        name.setText(studentOrm.getName());
        classmage.setText(studentOrm.getClassmage());
        age.setText(String.valueOf(studentOrm.getAge()));

        if (selectItem == i){
            view.setBackgroundColor(Color.YELLOW);
        }
        else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return view;
    }
    public void setSelectItem(int selectItem){
        this.selectItem = selectItem;
    }
}
