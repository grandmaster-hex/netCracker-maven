/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;
//import java.sql.Date;
import java.util.Date;
/**
 *
 * @author Администратор
 */
public class Conversation {
    private Date start_time;
    private int max_num_stud;
    private int sign_up_num_stud;
    private int interviewed_stud_num;
    private int no_interviewed_stud_num;
    
    public Conversation(Date start_time, int max_num_stud, int sign_up_num_stud, int interviewed_stud_num, int no_interviewed_stud_num){
        this.start_time = start_time;
        this.max_num_stud = max_num_stud;
        this.sign_up_num_stud = sign_up_num_stud;
        this.interviewed_stud_num = interviewed_stud_num;
        this.no_interviewed_stud_num = no_interviewed_stud_num;
    }
    
    public Date getStartTime(){
        return this.start_time;
    }
    public int getMaxNumStudents(){
        return this.max_num_stud;
    }
    public int getSignUpStudentsNum(){
        return this.sign_up_num_stud;
    }
    public int getInterviewedStudentsNum(){
        return this.interviewed_stud_num;
    }
    public int getNoInterviewedStudentsNum(){
        return this.no_interviewed_stud_num;
    }
    public String toString() {
        StringBuffer sbResult = new StringBuffer();
	sbResult.append("\nstart time = ");
	sbResult.append(this.start_time);
	sbResult.append("\nmax number of students = ");
	sbResult.append(this.max_num_stud);
	sbResult.append("\nsigned up students number = ");
	sbResult.append(this.sign_up_num_stud);
        sbResult.append("\ninterviewed students number = ");
	sbResult.append(this.interviewed_stud_num);
        sbResult.append("\nno interviewed students number = ");
	sbResult.append(this.no_interviewed_stud_num);
        
	return sbResult.toString();
    }
}
