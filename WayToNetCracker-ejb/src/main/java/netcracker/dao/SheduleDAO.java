/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lastride
 */
public interface SheduleDAO {
    /**
     * Creates new shedule
     * @param shedule
     * @return 
     */
    public boolean createShedule(List<Shedule> shedule);
    /**
     * Deletes shedule by start_time and end_time
     * @param start
     * @param end
     * @return 
     */
    public boolean deleteShedule(Timestamp start, Timestamp end);
    /**
     * Gets all intervals
     * @return 
     */
    public List<Shedule> getAllIntervals();
    /**
     * Register one student to interval by id_interval and id_student
     * @param id_interval
     * @param id_student
     * @return 
     */
    public boolean addStudentToInterval(int id_interval, int id_student);
    /**
     * Unregister student from interval by id_student and id_interval
     * @param id_interval
     * @param id_student
     * @return 
     */
    public boolean removeStudentFromInterval(int id_interval, int id_student);
    /**
     * Gets all intervals where interval_status_name = available
     * @return 
     */
    public List<Shedule> getAllAvailableIntervals();
    /**
     * Gets all intervals where interval_status_name = additional
     * @return 
     */
    public List<Shedule> getAllAdditionalIntervals();
    /**
     * Gets all intervals where interval_status_name = not available
     * @return 
     */
    public List<Shedule> getAllNotAvailableIntervals();
    /**
     * Gets all unique dates (YEAR-MONTH-DATE)
     * @return 
     */
    public List<Date> getUniqueDates();
    /**
     * Gets all start_time by date
     * @param d
     * @return 
     */
    public List<String> getStartTimeByDate(Date d);
}
