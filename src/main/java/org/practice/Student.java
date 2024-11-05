package org.practice;

public class Student {

    private int id;
    private String name;
    private int departmentId;

    public Student(int id, String name, int departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

}
