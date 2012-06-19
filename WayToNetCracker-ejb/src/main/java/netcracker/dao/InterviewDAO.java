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
     *
     * @param interview
     * @return
     */
    public boolean createInterview(Interview interview);

    /**
     * Deletes interview by id_student
     *
     * @param id_student
     * @return
     */
    public boolean deleteInterviewByIdStudent(int id_student);

    /**
     * Gets interviews result for one student by id_student
     *
     * @param id_student
     * @return
     */
    public List<String> getInterviewsResultByStudentId(int id_student);

    /**
     * Gets interviews result for one student by id_employee
     *
     * @param id_employee
     * @return
     */
    public List<String> getInterviewsResultByEmployeeId(int id_employee);
}
