/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.mysql.jdbc.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author lastride
 */
public class FormDAOImpl implements FormDAO {
    public FormDAOImpl() {
        
    }

    @Override
    public boolean createForm(Form form) {
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
            stmtInsert.setString(2, form.getFirstName());
            stmtInsert.setString(3, form.getLastName());
            stmtInsert.setString(4, form.getMiddleName());
            stmtInsert.setInt(5,form.getCourse());
            stmtInsert.setDate(6, form.getStudyEndYear());
            stmtInsert.setInt(7, form.getIdFaculty());
            stmtInsert.setString(8, form.getEmail1());
            stmtInsert.setString(9, form.getEmail2());
            stmtInsert.setString(10, form.getPhone1());  
            stmtInsert.setString(11, form.getExtraContacts());  
            stmtInsert.setString(12, form.getWhy());  
            stmtInsert.setString(13, form.getExperience()); 
            stmtInsert.setString(14, form.getExperience());  
            stmtInsert.setBlob(15, form.getPhoto());  
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
    public Form getFormByStudentId(int id_student){
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Form form = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT ID_STUDENT,FIRST_NAME,LAST_NAME,MIDDLE_NAME,COURSE, "
                    + "STUDY_END_YEAR,ID_FACULTY,EMAIL1,EMAIL2,PHONE1,EXTRA_CONTACTS,WHY,"
                    + "EXPERIENCE, EXTRA,PHOTO FROM ");
            sbSelect.append(DAOConstants.StudentsTableName);
            sbSelect.append(" WHERE ID_STUDENT = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);            
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {                
               form = new Form(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),
                      res.getInt(5),res.getDate(6),res.getInt(7),res.getString(8),
                       res.getString(9),res.getString(10),res.getString(11),
                       res.getString(12),res.getString(13),res.getString(14),res.getBlob(15));               
               rowsCount++;
            }
            //System.out.print(stmtSelect.toString());
            if (rowsCount<=0){
                System.out.print("\n\nNo Form found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting Form!\n");
        }
            finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return form;
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
            finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
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
            finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return universities;
    }
}
