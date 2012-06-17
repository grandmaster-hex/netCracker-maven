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
    
}
