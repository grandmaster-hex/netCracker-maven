/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Sonya
 */
public class Order {
    public Order(){}
    
    /*public List<Conversation> getAllConversations(){
       List conversations = new LinkedList();
       Connection conn = DAOFactory.createConnection();
       PreparedStatement stmtSelect1 = null;
       PreparedStatement stmtSelect2 = null;
       ResultSet res1 = null;
       ResultSet res2 = null;
       Conversation conversation = null;
       try {
            StringBuffer sbSelect1 = new StringBuffer();
            StringBuffer sbSelect2 = new StringBuffer();
            
            sbSelect1.append("select start_time, interviewers_count ");
            sbSelect1.append(DAOConstants.IntervalsTableName);
            stmtSelect1 = conn.prepareStatement(sbSelect1.toString());
            System.out.print(stmtSelect1.toString());
            
            sbSelect2.append("select start_time, interviewers_count ");
            
            res1 = stmtSelect1.executeQuery();
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
          finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
       return conversations;
    }*/
    
    public List<String> getAllStudentThisInterval(int id_interval){
        if (id_interval <=0)
            {
                throw new IllegalArgumentException("\nError with interval param");
               
            }
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        List<String> students = new ArrayList();
        String student = null;
     
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("select "+DAOConstants.StudentsTableName+".last_name from "+
                    DAOConstants.StudentsTableName+", "+DAOConstants.IntervalsForStudentsTableName+
                    " where "+DAOConstants.StudentsTableName+".id_student = "+
                    DAOConstants.IntervalsForStudentsTableName+".id_student and "+
                    DAOConstants.IntervalsForStudentsTableName+".id_interval = ?");
            
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            System.out.print(stmtSelect.toString());
            stmtSelect.setInt(1, id_interval);
            
            result = stmtSelect.executeQuery();
            
            int rowsCount = 0;
            while (result.next()) {
               student = new String(result.getString(1));
               students.add(student);
               rowsCount++;
            }
            
            if (rowsCount < 1) {
                System.out.println("\n No students found");
            }
        }
        catch(SQLException ex){
            System.out.print("\nSQLException while getting students");
        }          

        finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return students;
    }
    
}
