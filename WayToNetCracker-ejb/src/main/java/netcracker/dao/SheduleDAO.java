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
    public boolean createShedule(List<Shedule> shedule);
    public boolean deleteShedule(Timestamp start, Timestamp end);
    public List<Shedule> getAllIntervals();
    public boolean addStudentToInterval(int id_interval, int id_student);
    public boolean removeStudentFromInterval(int id_interval, int id_student);
    public List<Shedule> getAllAvailableIntervals();
    public List<Shedule> getAllAdditionalIntervals();
    public List<Shedule> getAllNotAvailableIntervals();
    public List<Date> getUniqueDates();
}
