/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.util.List;

/**
 *
 * @author lastride
 */
public interface StudentDAO {

    /**
     * Creates new student
     *
     * @param student
     * @return
     */
    public boolean createStudent(Student student);

    /**
     * Delete student by id_student
     *
     * @param id_student students id
     * @return boolean
     */
    public boolean deleteStudentByIdStudent(int id_student);

    /**
     * Get all universities from DB
     *
     * @return List<String>
     */
    public List<String> getAllUniversities();

    /**
     * Get all faculties by id from DB
     *
     * @return List<String>
     */
    public List<String> getFacultiesByUniverName(String name);

    /**
     * Gets student by id_student
     *
     * @param id_student
     * @return
     */
    public Student getStudentByIdStudent(int id_student);

    /**
     * Sets interval to student by id_interval and id_student
     *
     * @param id_student
     * @param id_interval
     * @return
     */
    public boolean setIntervalToStudent(int id_student, int id_interval);

    /**
     * Checks if email exists
     *
     * @param email
     * @return
     */
    public boolean emailExists(String email);
    
    // by Ann for Form
    public String getUniversityNameByIdStudent(int id_student);
    
    public String getFacultyNameByIdStudent(int id_student);
}
