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
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class AdvertDAOImpl implements AdvertDAO{

    @Override
    public boolean createAdvert(Advert advert) {
         PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("insert into ");
            sbInsert.append(DAOConstants.AdvertsTableName);
            sbInsert.append(" (advert_name)");
            sbInsert.append(" values(");
            sbInsert.append("?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setString(1, advert.getAdvert_name());
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
            System.out.print("\nSQL exception in create adverts");
        }
        finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
       return true;
    }

    @Override
    public List<Advert> getAdvertsByIdStudent(int id_student) {
        List<Advert> ad = new ArrayList<Advert>();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;        
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT "+DAOConstants.AdvertsForStudentsTableName+".id_advert, "
                    + DAOConstants.AdvertsTableName + ".advert_name FROM ");
            sbSelect.append(DAOConstants.AdvertsTableName +", "+DAOConstants.AdvertsForStudentsTableName);
            sbSelect.append(" WHERE "+DAOConstants.AdvertsForStudentsTableName+
                    ".ID_ADVERT = "+DAOConstants.AdvertsTableName+".ID_ADVERT ");
            sbSelect.append("AND "+DAOConstants.AdvertsForStudentsTableName+".ID_STUDENT = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
//            System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
               ad.add(new Advert(res.getInt(1),res.getString(2)));           
               rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo adverts dound");
            }
        }
        catch (SQLException e){
            System.out.print("\n Error while getting all adverts checked by student!\n");
        }
            finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return ad;    
         
    }
    
}
