/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;

import javax.inject.Named; 
   // or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped; 
   // or import javax.faces.bean.SessionScoped;

@Named("Schedule")
@SessionScoped
public class Schedule implements Serializable {
    
    private String mounth ="Октябре";
    
    String email;    
    String editingData; // поле для файла doEditing.xhtml
    boolean emailCheck; // проверка почты на присутствие в базе данных
    
    String date;
    String time;
    String[] times = {"xx","xx","xx"};

    public String getEditingData() {
        return editingData;
    }

    public void setEditingData(String editingData) {
        this.editingData = editingData;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }

    public String getMounth() {
        return mounth;
    }

    public void setMounth(String mounth) {
        this.mounth = mounth;
    }
    
    public int[] getListDate(){
        int[] a= {7,8,9};
        return a;
    }

    public boolean isEmailCheck() {
        return emailCheck;
    }

    public void setEmailCheck(boolean emailCheck) {
        this.emailCheck = emailCheck;
    }
    
    public void getListTime(){
        if(date !=null && !date.equals(""))  
        {
            if (date.equals("7"))
            {
                times[0]="17:00"; 
                times[1]="17:20";
                times[2]="17:40";
            }
            if (date.equals("8"))
            {
                times[0]="18:00"; 
                times[1]="18:20";
                times[2]="18:40";
            }
            if (date.equals("9"))
            {
                times[0]="19:00"; 
                times[1]="19:20";
                times[2]="19:40";
            }
        }
        else {
            times[0]="00:00"; 
            times[1]="00:00";
            times[2]="00:00";
        }
    }
    
    public String doEmailCheck(){
        String a = "почта существует в базе";
        return a;
    }
    
}
