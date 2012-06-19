/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
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
                shedule = new Shedule(res.getInt(1), res.getTimestamp(2),
                        res.getTimestamp(3), res.getInt(4),
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
        } catch (SQLException ex) {
            System.out.print("\nSQL exception in addStudentToInterval()");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }

    @Override
    public List<Shedule> getAllAvailableIntervals() {
        List<Shedule> availableIntervalsList = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Shedule shedule = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_interval, start_time, end_time,"
                    + " interviewers_count,"
                    + DAOConstants.IntervalsTableName + ".id_interval_status FROM ");
            sbSelect.append(DAOConstants.IntervalsTableName + "," + DAOConstants.IntervalStatusesTableName);
            sbSelect.append(" WHERE " + DAOConstants.IntervalsTableName + ".id_interval_status = "
                    + DAOConstants.IntervalStatusesTableName + ".id_interval_status AND interval_status_name like '%available%'");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                shedule = new Shedule(res.getInt(1), res.getTimestamp(2),
                        res.getTimestamp(3), res.getInt(4),
                        res.getInt(5));
                availableIntervalsList.add(shedule);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo available intervals found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return availableIntervalsList;
    }

    @Override
    public List<Shedule> getAllAdditionalIntervals() {
        List<Shedule> additionalIntervalsList = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Shedule shedule = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_interval, start_time, end_time,"
                    + " interviewers_count,"
                    + DAOConstants.IntervalsTableName + ".id_interval_status FROM ");
            sbSelect.append(DAOConstants.IntervalsTableName + "," + DAOConstants.IntervalStatusesTableName);
            sbSelect.append(" WHERE " + DAOConstants.IntervalsTableName + ".id_interval_status = "
                    + DAOConstants.IntervalStatusesTableName + ".id_interval_status AND interval_status_name like '%additional%'");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                shedule = new Shedule(res.getInt(1), res.getTimestamp(2),
                        res.getTimestamp(3), res.getInt(4),
                        res.getInt(5));
                additionalIntervalsList.add(shedule);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo additional intervals found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getAllAdditionalIntervals\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return additionalIntervalsList;
    }

    @Override
    public List<Shedule> getAllNotAvailableIntervals() {
        List<Shedule> notAvailableIntervalsList = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Shedule shedule = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_interval, start_time, end_time,"
                    + " interviewers_count,"
                    + DAOConstants.IntervalsTableName + ".id_interval_status FROM ");
            sbSelect.append(DAOConstants.IntervalsTableName + "," + DAOConstants.IntervalStatusesTableName);
            sbSelect.append(" WHERE " + DAOConstants.IntervalsTableName + ".id_interval_status = "
                    + DAOConstants.IntervalStatusesTableName + ".id_interval_status AND interval_status_name like '%not available %'");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                shedule = new Shedule(res.getInt(1), res.getTimestamp(2),
                        res.getTimestamp(3), res.getInt(4),
                        res.getInt(5));
                notAvailableIntervalsList.add(shedule);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo additional intervals found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getAllNotAvailableIntervals\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return notAvailableIntervalsList;
    }

    @Override
    public List<java.util.Date> getUniqueDates() {
        List<java.util.Date> datesList = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT DISTINCT YEAR(start_time), MONTH(start_time),  DAY(start_time) FROM ");
            sbSelect.append(DAOConstants.IntervalsTableName + ", " + DAOConstants.IntervalStatusesTableName);
            sbSelect.append(" WHERE " + DAOConstants.IntervalsTableName + ".id_interval_status = "
                    + DAOConstants.IntervalStatusesTableName + ".id_interval_status AND interval_status_name like '%available%'");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                java.util.Date d = new java.util.Date(res.getInt(1) - 1900, res.getInt(2), res.getInt(3));
                datesList.add(d);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo UniqueDates found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getUniqueDates\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return datesList;
    }

    @Override
    public List<String> getStartTimeByDate(java.util.Date d) {
        List<String> timestampList = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT TIME(start_time) FROM ");
            sbSelect.append(DAOConstants.IntervalsTableName);
            sbSelect.append(" WHERE YEAR(start_time) = ? AND MONTH(start_time) = ? "
                    + "AND DAYOFMONTH(start_time) = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfMonth = new SimpleDateFormat("M");
            SimpleDateFormat sdfDay = new SimpleDateFormat("d");
            stmtSelect.setString(1, sdfYear.format(d));
            stmtSelect.setString(2, sdfMonth.format(d));
            stmtSelect.setString(3, sdfDay.format(d));
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
                timestampList.add(sdfTime.format(res.getTimestamp(1)));
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo getStartTimeByDate found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return timestampList;
    }
}
