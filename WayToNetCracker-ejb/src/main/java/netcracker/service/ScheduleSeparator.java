/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

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
public class ScheduleSeparator {
    
    public static void separate(Date begin, Date end, int duration,int interval_status,int interviewers_count) {
               
        List<Shedule> she = new ArrayList<Shedule>();
        long msBegin = begin.getTime();
        long msEnd = end.getTime();
        long msDuration = duration * 60 * 1000;
        
        for(long tm = msBegin; tm + msDuration <= msEnd; tm += msDuration) {
            she.add(new Shedule(new java.sql.Date(new Date(tm).getTime()), 
                    new java.sql.Date(new Date(tm+msDuration).getTime()),interviewers_count,interval_status));
        }
        DAOFactory.getSheduleDAO().createShedule(she);
    }
   
}
