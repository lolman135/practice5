package org.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code StudentTable} class provides database operations for the {@code Student} table,
 * allowing insertion, deletion by department ID, and updating department IDs.
 */
public class StudentTable {

    Connection connection;
    DBMediator mediator;

    /**
     * Constructs a {@code StudentTable} with the specified database connection and mediator.
     * @param connection the database connection.
     * @param mediator the mediator to handle notifications.
     */
    public StudentTable(Connection connection, DBMediator mediator) {
        this.connection = connection;
        this.mediator = mediator;
    }

    /**
     * Inserts a new student record into the database.
     * @param record the {@code Student} record to insert.
     * @throws SQLException if a database access error occurs.
     */
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

    /**
     * Deletes all student records with the specified department ID.
     * @param departmentId the ID of the department whose students should be deleted.
     * @throws SQLException if a database access error occurs.
     */
    public void deleteByDepartmentId(int departmentId) throws SQLException {
        String request = "DELETE FROM student WHERE department_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, departmentId);
            statement.execute();
            System.out.println("Deleted all students with department id: " + departmentId);
        }
    }

    /**
     * Updates the department ID for all students with the specified old department ID.
     * @param oldDepartmentId the current department ID.
     * @param newDepartmentId the new department ID.
     * @throws SQLException if a database access error occurs.
     */
    public void updateDepartmentID(int oldDepartmentId, int newDepartmentId) throws SQLException {
        String request = "UPDATE student SET department_id = ? WHERE department_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, newDepartmentId);
            statement.setInt(2, oldDepartmentId);
            statement.execute();
            System.out.println("Updated department id for all students: " + oldDepartmentId + " changed to: " + newDepartmentId);
        }
    }
}