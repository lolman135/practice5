package org.practice;

import java.sql.SQLException;

/**
 * {@code ConcreteMediator} implements DBMediator interface to coordinate operations
 * on the StudentTable.
 */
public class ConcreteMediator implements DBMediator{

    private StudentTable studentTable;
    private DepartmentTable departmentTable;

    /**
     * Notifies the mediator to delete records in StudentTable by department ID.
     * @param id the department ID whose records are to be deleted
     */
    @Override
    public void notifyDelete(int id) {
        try {
            studentTable.deleteByDepartmentId(id);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Notifies the mediator to update department ID in StudentTable.
     * @param oldDepartmentId the current department ID to be updated
     * @param newDepartmentId the new department ID
     */
    @Override
    public void notifyUpdate(int oldDepartmentId, int newDepartmentId) {
        try {
            studentTable.updateDepartmentID(oldDepartmentId, newDepartmentId);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Notifies the transfer of a foreign key from a target table.
     * @param foreignKey the foreign key value to send.
     * @param table the target table where the foreign key receives.
     */
    @Override
    public void notifySendKey(int foreignKey, Table table) {
        if (table instanceof StudentTable){
            try {
                departmentTable.checkPrimaryKey(foreignKey);
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Sets the StudentTable instance for this mediator.
     * @param studentTable the StudentTable instance
     */
    public void setStudentTable(StudentTable studentTable) {
        this.studentTable = studentTable;
    }

    public void setDepartmentTable(DepartmentTable departmentTable) {
        this.departmentTable = departmentTable;
    }
}