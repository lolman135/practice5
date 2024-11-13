package org.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentTable {

    Connection connection;
    DBMediator mediator;

    /**
     * Constructs a {@code DepartmentTable} with the specified database connection and mediator.
     * @param connection the database connection.
     * @param mediator the mediator for handling notifications.
     */
    public DepartmentTable(Connection connection, DBMediator mediator) {
        this.connection = connection;
        this.mediator = mediator;
    }

    /**
     * Inserts a new department record into the database.
     * @param record the {@code Department} record to insert.
     * @throws SQLException if a database access error occurs.
     */
    public void insert(Object record) throws SQLException {
        Department department = (Department) record;
        String request = "INSERT INTO department (id, department_name) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, department.getId());
            statement.setString(2, department.getDepartmentName());
            statement.executeUpdate();
            System.out.println("Inserted department: " + department.getDepartmentName());
        }
    }

    /**
     * Deletes a department record by its ID and notifies the mediator.
     * @param id the ID of the department to delete.
     * @throws SQLException if a database access error occurs.
     */
    public void delete(int id) throws SQLException {
        mediator.notifyDelete(id);
        String request = "DELETE FROM department WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Deleted department with id: " + id);
        }
    }

    /**
     * Updates the department ID, disabling and re-enabling foreign key checks as needed,
     * and notifies the mediator of the update.
     * @param record the {@code Department} record with the new ID.
     * @param oldDepartmentId the old ID of the department.
     * @throws SQLException if a database access error occurs.
     */
    public void update(Object record, int oldDepartmentId) throws SQLException {
        Department department = (Department) record;
        String disableForeignKeyChecks = "ALTER TABLE student DROP CONSTRAINT student_department_id_fkey;";
        String enableForeignKeyChecks = "ALTER TABLE student ADD CONSTRAINT student_department_id_fkey " +
                "FOREIGN KEY (department_id) REFERENCES department(id);";

        try {
            try (PreparedStatement disableStatement = connection.prepareStatement(disableForeignKeyChecks)) {
                disableStatement.execute();
            }

            String request = "UPDATE department SET id = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(request)) {
                statement.setInt(1, department.getId());
                statement.setInt(2, oldDepartmentId);
                statement.executeUpdate();
                System.out.println("Updated department id from " + oldDepartmentId + " to " + department.getId());
            }

            mediator.notifyUpdate(oldDepartmentId, department.getId());

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try (PreparedStatement enableStatement = connection.prepareStatement(enableForeignKeyChecks)) {
                enableStatement.execute();
            }
        }
    }
}
