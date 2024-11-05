package org.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentTable{
    Connection connection;
    DBMediator mediator;

    public DepartmentTable(Connection connection, DBMediator mediator) {
        this.connection = connection;
        this.mediator = mediator;
    }

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

    public void delete(int id) throws SQLException {
        mediator.notifyDelete(id);
        String request = "DELETE FROM department WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(request)) {
            statement.setInt(1, id);
            statement.execute();
            System.out.println("Deleted department with id:" + id);

        }
    }

    public void update(Object record, int oldDepartmentId) throws SQLException {
        Department department = (Department) record;

        String disableForeignKeyChecks = "ALTER TABLE student DROP CONSTRAINT student_department_id_fkey;";
        String enableForeignKeyChecks = "ALTER TABLE student ADD CONSTRAINT student_department_id_fkey FOREIGN KEY (department_id) REFERENCES department(id);";

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
