package org.practice;

import java.sql.SQLException;

/**
 * {@code ConcreteMediator} implements DBMediator interface to coordinate operations
 * on the StudentTable.
 * @author Kusosvkyi Kyrylo
 */
public class ConcreteMediator implements DBMediator{

    private StudentTable studentTable;
    private DepartmentTable departmentTable;

    /**
     * Notifies the mediator to delete records in StudentTable by department ID.
     * @param id the department ID whose records are to be deleted
     * @param table the target table provides id.
     */
    @Override
    public void notifyDelete(int id, Table table) throws SQLException {
        if (table instanceof DepartmentTable){
            studentTable.deleteByDepartmentId(id);
        }
    }

    /**
     * Notifies the mediator to update department ID in StudentTable.
     * @param oldDepartmentId the current department ID to be updated
     * @param newDepartmentId the new department ID
     * @param table the target table provides id.
     */
    @Override
    public void notifyUpdate(int oldDepartmentId, int newDepartmentId, Table table) throws SQLException {
        if (table instanceof DepartmentTable){
            studentTable.updateDepartmentID(oldDepartmentId, newDepartmentId);
        }

    }

    /**
     * Notifies the transfer of a foreign key from a target table.
     * @param foreignKey the foreign key value to send.
     * @param table the target table where the foreign key receives.
     */
    @Override
    public void notifySendKey(int foreignKey, Table table) throws SQLException {
        if (table instanceof StudentTable){
            departmentTable.checkPrimaryKey(foreignKey);
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