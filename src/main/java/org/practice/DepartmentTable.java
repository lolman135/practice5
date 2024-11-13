package org.practice;

import java.sql.*;

public class DepartmentTable extends Table {
    /**
     * Constructs a {@code DepartmentTable} with the specified database connection and mediator.
     * @param connection the database connection.
     * @param mediator the mediator for handling notifications.
     */
    public DepartmentTable(Connection connection, DBMediator mediator) {
        super(connection, mediator);
    }

    /**
     * Checks if a given foreign key exists as a primary key in the department table.
     * @param foreignKey the department ID to verify.
     * @throws SQLException if a database access error occurs or the query fails.
     */
    public void checkPrimaryKey(int foreignKey) throws SQLException{
        Integer primaryKey = null;

        String request =  "SELECT id FROM department WHERE id = " + foreignKey + ";";

        Statement statement = connection.createStatement();
        try (ResultSet resultSet = statement.executeQuery(request)){
            if (resultSet.next()){
                primaryKey = resultSet.getInt("id");
                System.out.println(String.format("Right connection: student's department " +
                        "id: %d, department's id: %d", foreignKey, primaryKey));
            }
        } catch (SQLException e){
            System.out.println("Error: department id of student cannot be " +
                    "different with real department id");
        }
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
