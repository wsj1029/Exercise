package com.example.exercise;

import java.io.Serializable;

public class Student implements Serializable {
    public static final String TBL_STUDENT="create table if not exists t_student_info(" +
            "id integer primary key autoincrement," +
            "student_name varchar(100)," +
            "student_classmate varchar(100)," +
            "student_age integer)";


    private int id;
    private String name;
    private String classmage;
    private int age;

    public Student(){
    }

    public Student(int id, String name, String classmage, int age) {
        this.id = id;
        this.name = name;
        this.classmage = classmage;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassmage() {
        return classmage;
    }

    public void setClassmage(String classmage) {
        this.classmage = classmage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classmage='" + classmage + '\'' +
                ", age=" + age +
                '}';
    }
}
