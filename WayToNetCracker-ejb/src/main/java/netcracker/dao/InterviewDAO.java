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
public interface InterviewDAO {
    /**
     * Creates new interview
     * @param id_student
     * @param id_employee
     * @param comment
     * @return 
     */
    public boolean createInterview(int id_student, int id_employee, String comment);
    /**
     * Deletes interview by id_student
     * @param id_student
     * @return 
     */
    public boolean deleteInterviewByIdStudent(int id_student);
    
    public List <String> getInterviewsResultByStudentId(int id_student);
    
    public List <String> getInterviewsResultByEmployeeId(int id_employee);
}
