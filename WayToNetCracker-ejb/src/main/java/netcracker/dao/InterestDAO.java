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
    public List<Interest> getStudentInterestsById(int id_student);
    public boolean createInterest(Interest interest);
    public boolean createInterests(List<Interest> interests);
}
