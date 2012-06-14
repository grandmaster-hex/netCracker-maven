/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import netcracker.dao.DAOConstants;
import netcracker.dao.DAOFactory;

/**
 *
 * @author Администратор
 */
public class MessageDAOImpl implements MessageDAO{
    
    public MessageDAOImpl(){}

    @Override
    public boolean createMessage(int id_student, String message_text) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("insert into ");
            sbInsert.append(DAOConstants.MessagesTableName);
            sbInsert.append(" (id_student, edit_values, status)");
            sbInsert.append(" values(");
            sbInsert.append("?,?,?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1,id_student);
            stmtInsert.setString(2,message_text);
            stmtInsert.setBoolean(3,false);
             
            //uncomment for checking query syntax
            //System.out.print(stmtInsert.toString());
            int rows = stmtInsert.executeUpdate();
            if (rows != 1)
			{
				throw new SQLException(
					"executeUpdate return value: "
					+ rows);
			}
          
        }
        catch (SQLException ex)
        {
            System.out.print("\nSQL exception in createMessage");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
       return true;
        
    }

    @Override
    public boolean deleteMessageById(int id_student) {
        Connection conn=DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try{
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("delete from ");
            sbDelete.append(DAOConstants.MessagesTableName);
            sbDelete.append(" where id_student = ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setInt(1, id_student);
            int rows = stmtDelete.executeUpdate();
            if (rows !=1)
            {
                throw new SQLException("\nexecuteUpdate in deleteMessageById() return value: "+rows);
               
            }
            
        }
        catch(SQLException ex){
            System.out.print("\nSQLException while removing Message");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;
    }

    @Override
    public Message getMessageByID(int id_student) {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        Message message = null;

        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_student, edit_values, status FROM ");
            sbSelect.append(DAOConstants.MessagesTableName);
            sbSelect.append(" WHERE id_student = ");
            sbSelect.append("?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
            result = stmtSelect.executeQuery();
            
            int rowsCount = 0;
            while (result.next()) {
               message = new Message(result.getInt(1), result.getString(2), result.getBoolean(3)); 
               rowsCount++;
            }
            
            if (rowsCount < 1) {
                System.out.println("\n No messages found");
            }
        }
        catch(SQLException ex){
            System.out.print("\nSQLException while getting Message");
        }          

        finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        
      return message;
    }

    @Override
    public boolean setStatusById(int id_student, boolean status) {
        Connection conn=DAOFactory.createConnection();
        PreparedStatement stmtUpdate = null;
        try{
            StringBuffer sbUpdate = new StringBuffer();
            sbUpdate.append("update ");
            sbUpdate.append(DAOConstants.MessagesTableName);
            sbUpdate.append(" set status = ");
            sbUpdate.append("?");
            sbUpdate.append(" where id_student = ?");
            System.out.println(sbUpdate);
            stmtUpdate = conn.prepareStatement(sbUpdate.toString());
            stmtUpdate.setBoolean(1, status);
            stmtUpdate.setInt(2, id_student);
            System.out.print(stmtUpdate.toString());
            int rows = stmtUpdate.executeUpdate();
            if (rows !=1)
            {
                throw new SQLException("\nexecuteUpdate in setStatusById() return value: "+rows);
               
            }
            
        }
        catch(SQLException ex){
            System.out.print("\nSQLException while updating Message");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtUpdate);
        }
        return true;
    }

    @Override
    public List<Message> getVisitedMessage() {
        List messages = new LinkedList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("select id_student, edit_values, status from ");
            sbSelect.append(DAOConstants.MessagesTableName);
            sbSelect.append(" where status = true");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            //System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                Message message = new Message(res.getInt(1), res.getString(2),res.getBoolean(3));
                messages.add(message);
                rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo visited messages found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting visited messages!\n");
        }
        return messages;
    }

    @Override
    public List<Message> getNotVisitedMessage() {
        List messages = new LinkedList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("select id_student, edit_values, status from ");
            sbSelect.append(DAOConstants.MessagesTableName);
            sbSelect.append(" where status = false");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                Message message = new Message(res.getInt(1), res.getString(2),res.getBoolean(3));
                messages.add(message);
                rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo not-visited messages found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting not-visited messages!\n");
        }
        return messages;
    }
    
}
