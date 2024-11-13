package org.practice;

/**
 * The {@code DBMediator} interface defines methods for handling notifications related to
 * department deletion and updates in a database.
 */
public interface DBMediator {

    /**
     * Notifies a department deletion by its unique identifier.
     * @param id the ID of the department to delete.
     */
    void notifyDelete(int id);

    /**
     * Notifies an update to change a department's ID.
     * @param oldDepartmentId the current ID.
     * @param newDepartmentId the new ID.
     */
    void notifyUpdate(int oldDepartmentId, int newDepartmentId);

    void notifySendKey(int foreignKey, Table table);
}
