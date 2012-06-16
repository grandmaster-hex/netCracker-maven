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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class InterestDAOImpl implements InterestDAO {
    
    @Override
    public boolean createInterest(Interest interest){
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("insert into ");
            sbInsert.append(DAOConstants.InterestsForStudentsTableName);
            sbInsert.append(" (id_interest, id_student, mark, notes)");
            sbInsert.append(" values(");
            sbInsert.append("?,?,?,?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1,interest.getId_interest());
            stmtInsert.setInt(2,interest.getId_student());
            stmtInsert.setString(3,interest.getMark());
            stmtInsert.setString(4,interest.getNotes());
            System.out.print(stmtInsert.toString());          
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
            System.out.print("\nSQL exception in adding interests for student");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
       return true;  
    }
    @Override
    public boolean createInterests(List<Interest> interests){
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        Iterator<Interest> itr = interests.iterator();
        StringBuffer sbInsert = new StringBuffer();
       
            
            sbInsert.append("insert into ");
            sbInsert.append(DAOConstants.InterestsForStudentsTableName);
            sbInsert.append(" (id_interest, id_student, mark, notes)");
            sbInsert.append(" values(");
            sbInsert.append("?,?,?,?)");
      try{ 
            stmtInsert = conn.prepareStatement(sbInsert.toString());
      while(itr.hasNext()){
            Interest element = itr.next();            
            stmtInsert.setInt(1,element.getId_interest());
            stmtInsert.setInt(2,element.getId_student());
            stmtInsert.setString(3,element.getMark());
            stmtInsert.setString(4,element.getNotes());
            //System.out.print(stmtInsert.toString());    
            int rows = stmtInsert.executeUpdate();
            stmtInsert.clearParameters();
            sbInsert.delete(0, sbInsert.length());
            if (rows != 1)
			{
				throw new SQLException(
					"executeUpdate return value: "
					+ rows);
			}
          
                          }
          }
        catch (SQLException ex)
        {
            System.out.print("\nSQL exception in adding interests for student");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        
       return true;  
    }
    @Override
    public List<Interest> getStudentInterestsById(int id_student) {
        List<Interest> interests = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Interest i = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT "+DAOConstants.InterestsForStudentsTableName + ".ID_INTEREST, MARK, NOTES FROM ");
            sbSelect.append(DAOConstants.InterestsForStudentsTableName +", "+DAOConstants.InterestsTableName);
            sbSelect.append(" WHERE "+DAOConstants.InterestsForStudentsTableName+".ID_STUDENT = ? AND "+DAOConstants.InterestsForStudentsTableName+
                    ".ID_INTEREST = "+DAOConstants.InterestsTableName+".ID_INTEREST");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
           // System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
               i = new Interest(res.getInt(1),id_student,res.getString(2), res.getString(3));
               interests.add(i);              
               rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo interests found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting all interests for student!\n");
        }
            finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return interests;    
    }
    
    
}
