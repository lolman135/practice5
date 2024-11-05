package org.practice;

public interface DBMediator {
    void notifyDelete(int id);
    void notifyUpdate(int oldDepartmentId, int newDepartmentId);
}
