/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import javax.ejb.Local;

/**
 *
 * @author lasha.k;
 */
@Local
public interface FormBeanLocal {
    public int myBean(int a);
}
