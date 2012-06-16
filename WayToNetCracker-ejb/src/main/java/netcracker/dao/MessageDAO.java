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
    public boolean createMessage(Message message);
    public boolean deleteMessageByIdStudent(int id_student);
    public Message getMessageByIdStudent(int id_student);
    public boolean setStatusByIdStudent(int id_student, boolean status);
    public List<Message> getVisitedMessage();
    public List<Message> getNotVisitedMessage();
}