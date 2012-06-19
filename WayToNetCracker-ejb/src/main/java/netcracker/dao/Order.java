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

    public Order() {
    }

    /**
     * Gets all students by id_interval
     *
     * @param id_interval
     * @return
     */
    public List<String> getAllStudentsByIdInterval(int id_interval) {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        List<String> students = new ArrayList();
        String student = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT " + DAOConstants.StudentsTableName + ".last_name FROM "
                    + DAOConstants.StudentsTableName + ", " + DAOConstants.IntervalsForStudentsTableName
                    + " WHERE " + DAOConstants.StudentsTableName + ".id_student = "
                    + DAOConstants.IntervalsForStudentsTableName + ".id_student AND "
                    + DAOConstants.IntervalsForStudentsTableName + ".id_interval = ?");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_interval);
            System.out.print(stmtSelect.toString());
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
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getAllStudentsByIdInterval");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return students;
    }

    /**
     * Gets all students by id_university
     *
     * @param id_university
     * @return
     */
    public List<String> getAllStudentsByIdUniversity(int id_university) {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        List<String> students = new ArrayList();
        String student = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT " + DAOConstants.StudentsTableName + ".last_name FROM "
                    + DAOConstants.StudentsTableName + ", " + DAOConstants.FacultiesTableName
                    + " WHERE " + DAOConstants.StudentsTableName + ".id_faculty = "
                    + DAOConstants.FacultiesTableName + ".id_id_faculty and "
                    + DAOConstants.IntervalsForStudentsTableName + ".id_unversity = ?");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_university);
            System.out.print(stmtSelect.toString());
            result = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (result.next()) {
                student = result.getString(1);
                students.add(student);
                rowsCount++;
            }

            if (rowsCount < 1) {
                System.out.println("\n No students found");
            }
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getting students");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return students;
    }
    /**
     * Gets all students by id_faculty
     * @param id_faculty
     * @return 
     */
    public List<String> getAllStudentsByIdFaculty(int id_faculty) {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        List<String> students = new ArrayList();
        String student = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT last_name FROM "
                    + DAOConstants.StudentsTableName
                    + " WHERE id_faculty = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_faculty);
            System.out.print(stmtSelect.toString());
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
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getting students");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return students;
    }
    /**
     * Gets all students by course number
     * @param course
     * @return 
     */
    public List<String> getAllStudentsByCourse(int course) {
        if ((course > 6)) {
            throw new IllegalArgumentException("\nError course param");

        }
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        List<String> students = new ArrayList();
        String student = null;

        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT last_name FROM "
                    + DAOConstants.StudentsTableName
                    + " WHERE course = ?");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, course);
            System.out.print(stmtSelect.toString());

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
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getAllStudentThisCourse");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return students;
    }
    /**
     * Gets count of all students in table
     * @return 
     */
    public int getAllStudentsCount() {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet res = null;
        int result = 0;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT COUNT(id_student) FROM ");
            sbSelect.append(DAOConstants.StudentsTableName);
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                result = res.getInt(1);
                rowsCount++;
            }
            if (rowsCount < 1) {
                System.out.println("\n No students found");
            }
            if (rowsCount > 1) {
                System.out.println("\n Some error!");
            }
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getting number of students with forms");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return result;
    }
    /**
     * Gets count of all students with results
     * @return 
     */
    public int getAllStudentsWithResultsCount() {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet res = null;
        int result = 0;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT COUNT(last_name) FROM "
                    + DAOConstants.StudentsTableName + ", " + DAOConstants.ResultsTableName
                    + " WHERE " + DAOConstants.StudentsTableName + ".id_student = "
                    + DAOConstants.ResultsTableName + ".id_student");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();

            int rowsCount = 0;
            while (res.next()) {
                result = res.getInt(1);
                rowsCount++;
            }

            if (rowsCount < 1) {
                System.out.println("\n No students found");
            }
            if (rowsCount > 1) {
                System.out.println("\n Some error!");
            }
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getting number of students with results");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return result;
    }
    /**
     * Gets students count by id_advert
     * @param id_advert
     * @return 
     */
    public int getStudentsCountByIdAdvert(int id_advert) {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        int number = 0;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT COUNT(id_student) FROM "
                    + DAOConstants.AdvertsForStudentsTableName
                    + " WHERE id_advert = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_advert);
            System.out.print(stmtSelect.toString());
            result = stmtSelect.executeQuery();

            int rowsCount = 0;
            while (result.next()) {
                number = result.getInt(1);
                rowsCount++;
            }

            if (rowsCount < 1) {
                System.out.println("\n No students found");
            }
            if (rowsCount > 1) {
                System.out.println("\n Some error!");
            }
        } catch (SQLException ex) {
            System.out.print("\nSQLException while getting number of students with results");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }
        return number;
    }
}