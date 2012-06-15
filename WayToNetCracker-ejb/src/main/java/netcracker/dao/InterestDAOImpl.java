/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class InterestDAOImpl implements InterestDAO {

    @Override
    public List<Interest> getStudentInterestsById(int id_student) {
        List<Interest> interests = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Interest i = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT "+DAOConstants.InterestsForStudentsTableName + ".ID_INTEREST,INTEREST_NAME, MARK, NOTES FROM ");
            sbSelect.append(DAOConstants.InterestsForStudentsTableName +", "+DAOConstants.InterestsTableName);
            sbSelect.append(" WHERE "+DAOConstants.InterestsForStudentsTableName+".ID_STUDENT = ? AND "+DAOConstants.InterestsForStudentsTableName+
                    ".ID_INTEREST = "+DAOConstants.InterestsTableName+".ID_INTEREST");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
           // System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
               i = new Interest(res.getInt(1),id_student,res.getString(2), res.getString(3),res.getString(4));
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
