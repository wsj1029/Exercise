package com.example.exercise.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName ="t_student1")
public class StudentOrm implements Serializable {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(index = true,columnName = "student_name")
    private String name;

    @DatabaseField(index =true, columnName = "student_mate",dataType = DataType.STRING,canBeNull = true)
    private String classmage;

    @DatabaseField(index =true,columnName = "student_age",dataType = DataType.INTEGER,canBeNull = true)
    private int age;

    public StudentOrm(){
    }

    public StudentOrm(int id, String name, String classmage, int age) {
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
