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
public interface AdvertDAO {
    /**
     * Creates new advert
     * @param advert
     * @return 
     */
    public boolean createAdvert(Advert advert);
    /**
     * Gets all adverts by id_student
     * @param id_student
     * @return 
     */
    public List<Advert> getAdvertsByIdStudent(int id_student);
    /**
     * Gets all adverts
     * @return 
     */
    public List<Advert> getAllAdverts();
    /**
     * Sets advert for student by id_student
     * @param id_advert
     * @param id_student
     * @param notes
     * @return 
     */
    public boolean setAdvertForIdStudent(int id_advert, int id_student, String notes);
    /**
     * Deletes all adverts for student by id_student
     * @param id_student
     * @return 
     */
    public boolean deleteAdvertsForIdStudent(int id_student);
}
