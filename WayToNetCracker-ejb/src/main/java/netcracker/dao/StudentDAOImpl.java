/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class StudentDAOImpl implements StudentDAO {

@Override
    public boolean createStudent(Student st) {
        
        
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try{
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.StudentsTableName);
            sbInsert.append(" (id_student, first_name, last_name, middle_name, course, study_end_year, id_faculty, "
                             + "email1, email2, phone1, extra_contacts, why, experience, extra, photo)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setString(1, null);
            stmtInsert.setString(2,st.getFirstName());
            stmtInsert.setString(3, st.getLastName());
            stmtInsert.setString(4,st.getMiddleName());
            stmtInsert.setInt(5,st.getCourse());
            stmtInsert.setDate(6, st.getStudyEndYear());
            stmtInsert.setInt(7, st.getIdFaculty());
            stmtInsert.setString(8, st.getEmail1());
            stmtInsert.setString(9, st.getEmail2());
            stmtInsert.setString(10,st.getPhone1());
            stmtInsert.setString(11,st.getExtraContacts());
            stmtInsert.setString(12, st.getExtra());
            stmtInsert.setString(13, st.getExperience());
            stmtInsert.setString(14, st.getExtra());
            stmtInsert.setBlob(15, st.getPhoto());
            
            
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
    public boolean deleteStudentById(int id_student) {
        if (id_student <= 0) {
            throw new IllegalArgumentException("id_student param");
        }
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
        if (name == null)
            throw new IllegalArgumentException();
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
