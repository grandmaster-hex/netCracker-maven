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
public interface InterestDAO {
    /**
     * Gets all interests of one student by id_student
     * @param id_student
     * @return 
     */
    public List<Interest> getStudentInterestsById(int id_student);
    /**
     * Creates interest
     * @param interest
     * @return 
     */
    public boolean createInterest(Interest interest);
    /**
     * Creates many interests
     * @param interests
     * @return 
     */
    public boolean createInterests(List<Interest> interests);
    /**
     * Deletes all interests for one student by id_student
     * @param id_student
     * @return 
     */
    public boolean deleteInterestsForIdStudent(int id_student);
}
