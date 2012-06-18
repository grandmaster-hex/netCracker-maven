/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.*;
import java.util.ArrayList;
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
        try {
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.IntervalsTableName);
            sbInsert.append(" (id_interval, start_time, end_time, interviewers_count, "
                    + "id_interval_status)");
            sbInsert.append(" VALUES (");
            sbInsert.append("?, ?, ?, ?, ?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            while (itr.hasNext()) {
                Shedule element = itr.next();
                stmtInsert.setString(1, null);
                stmtInsert.setTimestamp(2, element.getStartTime());
                stmtInsert.setTimestamp(3, element.getEndTime());
                stmtInsert.setInt(4, element.getInterviewersCount());
                stmtInsert.setInt(5, element.getIdIntervalStatus());
                int rows = stmtInsert.executeUpdate();
                stmtInsert.clearParameters();
                if (rows != 1) {
                    throw new SQLException("executeUpdate return value: " + rows);
                }
            }
        } catch (SQLException ex) {
            System.out.print("\nSQL exception in createEmployee");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }

    @Override
    public boolean deleteShedule(Timestamp start, Timestamp end) {
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("delete from ");
            sbDelete.append(DAOConstants.IntervalsTableName);
            sbDelete.append(" where start_time >= ? and end_time <= ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setTimestamp(1, start);
            stmtDelete.setTimestamp(2, end);
            int rows = stmtDelete.executeUpdate();
            System.out.print(stmtDelete.toString());
            if (rows == 0) {
                System.out.print("\n\n Nothing to delete! ");

            }

        } catch (SQLException ex) {
            System.out.print("\nSQLException while removing shedule");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;

    }

    @Override
    public List<Shedule> getAllIntervals() {
        List<Shedule> sheduleList = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Shedule shedule = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_interval, start_time, end_time,"
                    + " interviewers_count, id_interval_status FROM ");
            sbSelect.append(DAOConstants.IntervalsTableName);
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                shedule = new Shedule(res.getInt(1), null,
                        null, res.getInt(4),
                        res.getInt(5));
                sheduleList.add(shedule);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo intervals found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getting all intervals !\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return sheduleList;
    }

    @Override
    public boolean addStudentToInterval(int id_interval, int id_student) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("INSERT INTO ");
            sbInsert.append(DAOConstants.IntervalsForStudentsTableName);
            sbInsert.append(" (id_interval, id_student)");
            sbInsert.append(" values(");
            sbInsert.append("?,?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1, id_interval);
            stmtInsert.setInt(2, id_student);

            int rows = stmtInsert.executeUpdate();
            if (rows != 1) {
                throw new SQLException(
                        "executeUpdate return value: "
                        + rows);
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in addStudentToInterval()");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }

    @Override
    public boolean removeStudentFromInterval(int id_interval, int id_student) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("DELETE FROM ");
            sbInsert.append(DAOConstants.IntervalsForStudentsTableName);
            sbInsert.append(" WHERE id_interval = ? AND id_student = ?");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1, id_interval);
            stmtInsert.setInt(2, id_student);

            int rows = stmtInsert.executeUpdate();
            if (rows != 1) {
                throw new SQLException(
                        "executeUpdate return value: "
                        + rows);
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in addStudentToInterval()");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }
}
