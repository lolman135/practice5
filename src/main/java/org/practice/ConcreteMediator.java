package org.practice;

import java.sql.SQLException;

public class ConcreteMediator implements DBMediator{

    private DepartmentTable departmentTable;
    private StudentTable studentTable;

    @Override
    public void notifyDelete(int id) {
        try {
            studentTable.deleteByDepartmentId(id);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());;
        }
    }

    @Override
    public void notifyUpdate(int oldDepartmentId, int newDepartmentId) {
        try{
            studentTable.updateDepartmentID(oldDepartmentId, newDepartmentId);
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setDepartmentTable(DepartmentTable departmentTable) {
        this.departmentTable = departmentTable;
    }

    public void setStudentTable(StudentTable studentTable) {
        this.studentTable = studentTable;
    }
}
