/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author lastride
 */
public class Shedule {
    private final int id_interval;
    private final Timestamp start_time;
    private final Timestamp end_time;
    private final int interviewers_count;
    private final int id_interval_status;
    
    public Shedule(int id_interval, Timestamp start_time, Timestamp end_time, int interviewers_count,
                    int id_interval_status) {
        this.id_interval = id_interval;
        this.start_time = start_time;
        this.end_time = end_time;
        this.interviewers_count = interviewers_count;
        this.id_interval_status = id_interval_status;
    }
    public int getIdInterval() {
        return this.id_interval;
    }
    public Timestamp getStartTime() {
        return this.start_time;
    }
    public Timestamp getEndTime() {
        return this.end_time;
    }
    public int getInterviewersCount() {
        return this.interviewers_count;
    }
    public int getIdIntervalStatus() {
        return this.id_interval_status;
    }
}
