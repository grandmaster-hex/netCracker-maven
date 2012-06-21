/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import java.io.File;
import java.io.IOException;
import javax.ejb.Local;
import javax.mail.MessagingException;
import javax.naming.NamingException;

/**
 *
 * @author lasha.k;
 */
@Local
public interface MailBeanLocal {
    public void sendForm(String email, File file) 
            throws NamingException, MessagingException, IOException;
}
