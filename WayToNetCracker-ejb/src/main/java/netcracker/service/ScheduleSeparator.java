/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author AnnKats
 */
public class ScheduleSeparator {
    
    public List<Date> separate(Date begin, Date end, int duration) {
        
        List<Date> list = new ArrayList<Date>();
        
        long msBegin = begin.getTime();
        long msEnd = end.getTime();
        long msDuration = duration * 60 * 1000;
        
        for(long tm = msBegin; tm + msDuration <= msEnd; tm += msDuration) {
            list.add(new Date(tm));
        }
        
        return list;
    }
}
