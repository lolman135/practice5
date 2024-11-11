package org.practice;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DBConnection dbConnection = new DBConnection();
            ConcreteMediator mediator = new ConcreteMediator();
            DepartmentTable departmentTable = new DepartmentTable(dbConnection.getConnection(), mediator);
            StudentTable studentTable = new StudentTable(dbConnection.getConnection(), mediator);
            mediator.setStudentTable(studentTable);

            Department department1 = new Department("Computer Science", 1);
            Department department2 = new Department("Mathematics", 2);
            departmentTable.insert(department1);
            departmentTable.insert(department2);

            Student student1 = new Student(101, "Alice", 1);
            Student student2 = new Student(102, "Bob", 1);
            Student student3 = new Student(103, "Charlie", 2);
            studentTable.insert(student1);
            studentTable.insert(student2);
            studentTable.insert(student3);

            System.out.println("\nDeleting department with id 1:");
            departmentTable.delete(1);

            System.out.println("\nUpdating department id from 2 to 3:");
            department2.setId(3);
            departmentTable.update(department2, 2);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}