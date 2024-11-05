package org.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentTable{

    Connection connection;
    DBMediator mediator;

    public StudentTable(Connection connection, DBMediator mediator) {
        this.connection = connection;
        this.mediator = mediator;
    }

    public void insert(Object record) throws SQLException {
        Student student = (Student) record;
        String request = "INSERT INTO student (id, name, department_id) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getDepartmentId());
            statement.executeUpdate();
            System.out.println("Inserted student: " + student.getName());
        }
    }

    public void deleteByDepartmentId(int departmentId) throws SQLException {
        String request = "DELETE FROM student WHERE department_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, departmentId);
            statement.execute();
                System.out.println("Deleted all students with department id:" + departmentId);
        }
    }

    public void updateDepartmentID(int oldDepartmentId, int newDepartmentId) throws SQLException {
        String request = "UPDATE student SET department_id = ? WHERE department_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, newDepartmentId);
            statement.setInt(2, oldDepartmentId);
            statement.execute();
            System.out.println("Updated department id for all students: " + oldDepartmentId + " changed for: " + newDepartmentId);
        }
    }
}
