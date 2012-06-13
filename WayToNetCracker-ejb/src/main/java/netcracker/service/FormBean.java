/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author phd
 */
@Stateless
@WebService
public class FormBean implements FormBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public int returnNum(){
        return testingData.getX();
    }
}
