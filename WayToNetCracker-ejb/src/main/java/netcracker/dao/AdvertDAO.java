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
    public boolean createAdvert(Advert advert);
    public List<Advert> getAdvertsByIdStudent(int id_student);
    public List<Advert> getAllAdverts();
    public boolean setAdvertForIdStudent(int id_advert, int id_student, String notes);
    public boolean deleteAdvertsForIdStudent(int id_student);
}
