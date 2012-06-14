/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import com.mysql.jdbc.Blob;
import java.util.List;

/**
 *
 * @author lastride
 */
public interface FormDAO {
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
    public boolean createForm(String first_name, String last_name, String middle_name,
                    int course, String study_end_year, int id_faculty, String email1,
                    String email2, String phone1, String extra_contacts,
                    String why, String experience, String extra, Blob photo);
    /**
     * Delete student by id_student
     * @param id_student students id
     * @return boolean
     */
    public boolean deleteFormByStudentId(int id_student);
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
}
