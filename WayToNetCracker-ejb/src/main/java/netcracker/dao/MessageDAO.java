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

    /**
     * Creates new message
     *
     * @param message
     * @return
     */
    public boolean createMessage(Message message);

    /**
     * Deletes message for one student by id_student
     *
     * @param id_student
     * @return
     */
    public boolean deleteMessageByIdStudent(int id_student);

    /**
     * Gets message for one student by id_student
     *
     * @param id_student
     * @return
     */
    public Message getMessageByIdStudent(int id_student);

    /**
     * Sets status for one student by id_student
     *
     * @param id_student
     * @param status
     * @return
     */
    public boolean setStatusByIdStudent(int id_student, boolean status);

    /**
     * Gets all visited messages
     *
     * @return
     */
    public List<Message> getVisitedMessage();

    /**
     * Gets all not visited messages
     *
     * @return
     */
    public List<Message> getNotVisitedMessage();
}