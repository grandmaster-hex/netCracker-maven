/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;

/**
 *
 * @author lasha.k;
 */
@Stateless
public class MailBean implements MailBeanLocal {

    //Sending mail with generated pdf file. 
    @Override
     public void sendForm(String email, String file) throws NamingException, MessagingException, IOException {
            String body = new String();
            body ="\tДобрый день, student.first_name,student.last_name!\n\n"
                    + "Благодарим за регистрацию для участия в собеседовании."
                    + "Высылаем на указанный Вами адрес анкету. Пожалуйста, распечатайте ее "
                    + "и приходите с ней к нам на собеседование.\n\n"
                    + "\tС уважением, hr_name";
            Properties props = new Properties();
            props.load(getClass().getResourceAsStream("mail.properties"));
            Session session = Session.getDefaultInstance(props);
            session.setDebug(true);
            Transport transport = session.getTransport();
            transport.connect(props.getProperty("host"),
                              Integer.parseInt(props.getProperty("port")),
                              props.getProperty("user"),
                              props.getProperty("password")
                    );            
            MimeMessage message = new MimeMessage(session);
            message.setSubject("Анкета для собеседования NetCracker");
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName("NetCracker III recruitment");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart,"text/html; charset=UTF-8");
            message.addRecipients(Message.RecipientType.TO, email);
            message.setSentDate(new Date());
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
    }
    
}

  
    

