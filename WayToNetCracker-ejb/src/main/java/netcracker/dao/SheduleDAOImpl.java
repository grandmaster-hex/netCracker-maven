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
public class SheduleDAOImpl implements SheduleDAO {

    @Override
    public boolean createShedule(Shedule shedule) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.IntervalsTableName);
            sbInsert.append(" (id_interval, start_time, end_time, interviewers_count, "
                            + "id_interval_status)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setString(1, null);
            stmtInsert.setTimestamp(2, shedule.getStartTime());
            stmtInsert.setTimestamp(3, shedule.getEndTime());
            stmtInsert.setInt(4, shedule.getInterviewersCount());
            stmtInsert.setInt(5, shedule.getIdIntervalStatus());
            
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
    
}
