package org.practice;

/**
 * The {@code Department} class represents a department entity with an ID
 * and a name.
 */
public class Department {
    private int id;
    private String departmentName;

    /**
     * Constructs a new {@code Department} with the specified name and ID.
     * @param departmentName the name of the department.
     * @param id the ID of the department.
     */
    public Department(String departmentName, int id) {
        this.departmentName = departmentName;
        this.id = id;
    }

    /**
     * Returns the department ID.
     * @return the ID of the department.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the department ID.
     * @param id the new ID of the department.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the department name.
     * @return the name of the department.
     */
    public String getDepartmentName() {
        return departmentName;
    }

}
