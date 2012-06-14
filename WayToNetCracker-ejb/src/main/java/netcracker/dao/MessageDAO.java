/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.util.List;
/**
 *
 * @author Администратор
 */
public interface MessageDAO {
    public boolean createMessage(int id_student, String message_text);
    public boolean deleteMessageById(int id_student);
    public Message getMessageByID(int id_student);
    public boolean setStatusById(int id_student, boolean status);
    public List<Message> getVisitedMessage();
    public List<Message> getNotVisitedMessage();
}