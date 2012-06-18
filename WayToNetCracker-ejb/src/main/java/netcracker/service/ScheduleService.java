/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import netcracker.dao.DAOFactory;
import netcracker.dao.Shedule;
/**
 *
 * @author AnnKats
 */
public class ScheduleService {
    
    public static List<Shedule> separate(Timestamp begin, Timestamp end, int duration,int interval_status,int interviewers_count) {
               
        List<Shedule> she = new ArrayList<Shedule>();
        long msBegin = begin.getTime();
        long msEnd = end.getTime();
        long msDuration = duration * 60 * 1000;
        
        for(long tm = msBegin; tm + msDuration <= msEnd; tm += msDuration) {
            she.add(new Shedule(new Timestamp(new Date(tm).getTime()), 
                    new Timestamp(new Date(tm+msDuration).getTime()),interviewers_count,interval_status));
        }
        return she;
    }
    
    public static boolean deleteSheduleBetweenDates(Date start, Date end){
        java.sql.Timestamp st = new java.sql.Timestamp(start.getTime());
        java.sql.Timestamp en = new java.sql.Timestamp(end.getTime());
        if(DAOFactory.getSheduleDAO().deleteShedule(st, en))
            return true;
        else return false;
    }
   
}
