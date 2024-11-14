package org.practice;

import java.sql.SQLException;

/**
 * The {@code DBMediator} interface defines methods for handling notifications related to
 * department deletion and updates in a database.
 * @author Kusosvkyi Kyrylo
 */
public interface DBMediator {

    /**
     * Notifies a department deletion by its unique identifier.
     * @param id the ID of the department to delete.
     * @param table the target table provides id.
     */
    void notifyDelete(int id, Table table) throws SQLException;

    /**
     * Notifies an update to change a department's ID.
     * @param oldDepartmentId the current ID.
     * @param newDepartmentId the new ID.
     * @param table the target table provides id.
     */
    void notifyUpdate(int oldDepartmentId, int newDepartmentId, Table table) throws SQLException;

    /**
     * Notifies the transfer of a foreign key from a target table.
     * @param foreignKey the foreign key value to send.
     * @param table the target table provides the foreign key.
     */
    void notifySendKey(int foreignKey, Table table) throws SQLException;
}
