/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.util.List;

/**
 *
 * @author lasha.k;
 */
public interface StudentDAO {
    public boolean createStudent(Student st);
    /**
* Delete student by id_student
* @param id_student students id
* @return boolean
*/
    public boolean deleteStudentById(int id_student);
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
