/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author lasha.k;
 */
public class Advert {

    private final int id_advert;
    private final String advert_name;

    public Advert(int id_advert, String advert_name) {
        this.id_advert = id_advert;
        this.advert_name = advert_name;
    }

    public Advert(String advert_name) {
        this.id_advert = 0;
        this.advert_name = advert_name;
    }

    public String getAdvertName() {
        return advert_name;
    }

    public int getIdAdvert() {
        return id_advert;
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        res.append("id_advert = " + id_advert);
        res.append(" advert_name = " + advert_name + "\n\n");
        return res.toString();
    }
}
