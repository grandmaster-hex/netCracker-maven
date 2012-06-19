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
 * @author lastride
 */
public class InterviewDAOImpl implements InterviewDAO {

    @Override
    public boolean createInterview(Interview interview) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.ResultsTableName);
            sbInsert.append(" (id_student, id_employee, comment)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1, interview.getIdStudent());
            stmtInsert.setInt(2, interview.getIdEmployee());
            stmtInsert.setString(3, interview.getComment());

            int rows = stmtInsert.executeUpdate();
            if (rows != 1) {
                throw new SQLException("executeUpdate return value: " + rows);
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in createInterview");
        } finally {
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
            stmtDelete.executeUpdate();
        } catch (SQLException ex) {
            System.out.print("\nSQLException while deleteInterviewByIdStudent");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;
    }

    @Override
    public List<String> getInterviewsResultByStudentId(int id_student) {
        List<String> comments = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        String comment = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT " + DAOConstants.ResultsTableName + ".id_comment "
                    + "FROM " + DAOConstants.ResultsTableName
                    + " WHERE " + DAOConstants.ResultsTableName + ".id_student = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
            System.out.print(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                comment = new String(res.getString(1));
                comments.add(comment);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo comments found!");
            }
            if (rowsCount > 2) {
                System.out.print("\n\nSome error: student have more than 2 comments!");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getting all comments about this student!\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return comments;
    }

    @Override
    public List<String> getInterviewsResultByEmployeeId(int id_employee) {
        List<String> comments = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        String comment = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT " + DAOConstants.ResultsTableName + ".id_comment "
                    + "FROM " + DAOConstants.ResultsTableName
                    + " WHERE " + DAOConstants.ResultsTableName + ".id_employee = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_employee);
            System.out.print(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                comment = res.getString(1);
                comments.add(comment);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nThis employee did no comment!");
            }

        } catch (Exception e) {
            System.out.print("\n Error while getting all comments this employee!\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return comments;

    }
}
