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
     * @param first_name
     * @param last_name
     * @param middle_name
     * @param course
     * @param study_end_year
     * @param id_faculty
     * @param email1
     * @param email2
     * @param phone1
     * @param extra_contacts
     * @param why
     * @param experience
     * @param extra
     * @param photo
     * @return 
     */
    public boolean createStudent(Student student);
    /**
     * Delete student by id_student
     * @param id_student students id
     * @return boolean
     */
    public boolean deleteStudentByIdStudent(int id_student);
    /**
     * Get all universities from DB
     * @return List<String>
     */
    public List<String> getAllUniversities();
     /**
     * Get all faculties by id from DB
     * @return List<String>
     */
    public List<String> getFacultiesByUniverName(String name);
    
    public Student getStudentByIdStudent(int id_student);
    
    public boolean setIntervalToStudent(int id_student, int id_interval);
}
