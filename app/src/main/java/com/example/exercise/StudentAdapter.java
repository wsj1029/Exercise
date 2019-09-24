package com.example.exercise;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private int selectItem = -1;
    private List<Student> students;

    public StudentAdapter(List<Student> students) {
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
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_student,viewGroup,false);
        Student student = students.get(i);

        TextView name = view.findViewById(R.id.tv_name);
        TextView classmage = view.findViewById(R.id.tv_classmate);
        TextView age = view.findViewById(R.id.tv_age);

        name.setText(student.getName());
        classmage.setText(student.getClassmage());
        age.setText(String.valueOf(student.getAge()));

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
