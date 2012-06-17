/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author lastride
 */
public class SheduleDAOImpl implements SheduleDAO {

    @Override
    public boolean createShedule(List<Shedule> shedule) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        Iterator<Shedule> itr = shedule.iterator();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.IntervalsTableName);
            sbInsert.append(" (id_interval, start_time, end_time, interviewers_count, "
                            + "id_interval_status)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
         while(itr.hasNext()){
            Shedule element = itr.next();
            stmtInsert.setString(1, null);
            stmtInsert.setTimestamp(2, element.getStartTime());
            stmtInsert.setTimestamp(3, element.getEndTime());
            stmtInsert.setInt(4, element.getInterviewersCount());
            stmtInsert.setInt(5, element.getIdIntervalStatus());
            int rows = stmtInsert.executeUpdate();
            stmtInsert.clearParameters();
            if (rows != 1) {
		throw new SQLException("executeUpdate return value: "+ rows);
            }
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
    public boolean deleteShedule(Timestamp start, Timestamp end) {
        Connection conn=DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try{
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("delete from ");
            sbDelete.append(DAOConstants.IntervalsTableName);
            sbDelete.append(" where start_time >= ? and end_time <= ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setTimestamp(1, start);
            stmtDelete.setTimestamp(2, end);
            int rows = stmtDelete.executeUpdate();
            System.out.print(stmtDelete.toString());
            if (rows ==0)
            {
                System.out.print("\n\n Nothing to delete! ");
               
            }
            
        }
        catch(SQLException ex){
            System.out.print("\nSQLException while removing shedule");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;
        
    }
    
}
