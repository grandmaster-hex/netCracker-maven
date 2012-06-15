/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import javax.ejb.Stateless;

/**
 *
 * @author lasha.k;
 */
@Stateless
public class FormBean implements FormBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public int myBean (int a){
        return a;
    }
}
