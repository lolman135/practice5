package org.practice;

public class Department {
    private int id;
    private String departmentName;

    public Department(String departmentName, int id) {
        this.departmentName = departmentName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

}
