package org.practice;

/**
 * The {@code Student} class represents a student entity with an ID, name,
 * and associated department ID.
 */
public class Student {

    private int id;
    private String name;
    private int departmentId;

    /**
     * Constructs a new {@code Student} with the specified ID, name, and department ID.
     * @param id the unique ID of the student.
     * @param name the name of the student.
     * @param departmentId the ID of the department associated with the student.
     */
    public Student(int id, String name, int departmentId) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
    }

    /**
     * Returns the student ID.
     * @return the ID of the student.
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the student's name.
     * @return the name of the student.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ID of the student's department.
     * @return the department ID associated with the student.
     */
    public int getDepartmentId() {
        return departmentId;
    }
}
