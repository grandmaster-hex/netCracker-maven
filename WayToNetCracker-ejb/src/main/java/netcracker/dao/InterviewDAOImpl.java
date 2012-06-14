/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lastride
 */
public class InterviewDAOImpl implements InterviewDAO {

    @Override
    public boolean createInterview(int id_student, int id_employee, String comment) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.ResultsTableName);
            sbInsert.append(" (id_student, id_employee, comment)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1, id_student);
            stmtInsert.setInt(2, id_employee);
            stmtInsert.setString(3, comment);
            
            int rows = stmtInsert.executeUpdate();
            if (rows != 1) {
		throw new SQLException("executeUpdate return value: "+ rows);
            }
          
        }
        catch (SQLException ex) {
            System.out.print("\nSQL exception in createEmployee");
        }
        finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
       return true;
    }

    @Override
    public boolean deleteInterviewByIdStudent(int id_student) {
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("DELETE FROM ");
            sbDelete.append(DAOConstants.ResultsTableName);
            sbDelete.append(" WHERE id_student = ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setInt(1, id_student);
            int rows = stmtDelete.executeUpdate();
            if (rows !=1) {
                throw new SQLException("\n executeUpdate in deleteEmployee() return value: "+rows);
            }
        }
        catch(SQLException ex) {
            System.out.print("\nSQLException while removing Employee");
        }
        finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;
    }
    
}
