/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import com.mysql.jdbc.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lastride
 */
public class FormDAOImpl implements FormDAO {
    public FormDAOImpl() {
        
    }

    @Override
    public boolean createForm(String first_name, String last_name, String middle_name, 
                                    int course, String study_end_year, int id_faculty,
                                    String email1, String email2, String phone1, 
                                    String extra_contacts, String why, 
                                    String experience, String extra, Blob photo) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.StudentsTableName);
            sbInsert.append(" (id_student, first_name, last_name, middle_name, course, study_end_year, id_faculty, "
                             + "email1, email2, phone1, extra_contacts, why, experience, extra, photo)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setString(1, null);
            stmtInsert.setString(2, first_name);
            stmtInsert.setString(3, last_name);
            stmtInsert.setString(4, middle_name);
            stmtInsert.setInt(5,course);
            stmtInsert.setString(6, study_end_year);
            stmtInsert.setInt(7, id_faculty);
            stmtInsert.setString(8, email1);
            stmtInsert.setString(9, email2);
            stmtInsert.setString(10, phone1);  
            stmtInsert.setString(11, extra_contacts);  
            stmtInsert.setString(12, why);  
            stmtInsert.setString(13, experience); 
            stmtInsert.setString(14, extra);  
            stmtInsert.setBlob(15, photo);  
            //uncomment for checking query syntax
            System.out.print(stmtInsert.toString());
            
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
    public boolean deleteFormByStudentId(int id_student) {
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("DELETE FROM ");
            sbDelete.append(DAOConstants.StudentsTableName);
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
    //return List of universities_name or empty List with console text output
    public List<String> getAllUniversities() {
        List universities = new LinkedList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT UNIVERSITY_NAME FROM ");
            sbSelect.append(DAOConstants.UniversitiesTableName);
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            //System.out.print(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
               universities.add(res.getString(1));
               rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo Universities found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting all Universities!\n");
        }
        return universities;
    }
    //get faculties by id returns list of faculty names or empty list
      //return List of universities_name or empty List with console text output
    public List<String> getFacultiesByUniverName(String name){
        List universities = new LinkedList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        String[] a = new String[2];
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT FACULTY_NAME FROM ");
            sbSelect.append(DAOConstants.UniversitiesTableName+", "+DAOConstants.FacultiesTableName);
            sbSelect.append(" WHERE ");
            sbSelect.append(DAOConstants.UniversitiesTableName+".university_name = ? and ");
            sbSelect.append(DAOConstants.UniversitiesTableName+".id_university = "+DAOConstants.FacultiesTableName+".id_university");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setString(1, name);
           // System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
               universities.add(res.getString(1));
               rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo Faculties found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting all Faculties!\n");
        }
        return universities;
    }
}
