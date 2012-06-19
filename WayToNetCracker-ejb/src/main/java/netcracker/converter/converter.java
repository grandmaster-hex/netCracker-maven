/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import netcracker.dao.Advert;

/**
 *
 * @author lasha.k;
 */
public class converter {
    public static List<String> AdvertNames(List<Advert> adv){
        List<String> adverts = new ArrayList<String>();
        Iterator<Advert> itr = adv.iterator();
        while(itr.hasNext()){
            Advert buff = itr.next();
            adverts.add(buff.getAdvertName());
        }        
        return adverts;
    }
}
