/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author lasha.k;
 */
public class Interest {

    private final int id_interest;
    private final int id_student;
    private final String interest_name;
    private final String mark;
    private final String notes;
    public Interest(int id_interest,int id_student,String interest_name, String mark, String notes){
        this.id_interest = id_interest;
        this.id_student=id_student;
        this.interest_name = interest_name;
        this.mark = mark;
        this.notes = notes;
    }
    
    public int getId_interest() {
        return id_interest;
    }

    public int getId_student() {
        return id_student;
    }

    public String getMark() {
        return mark;
    }

    public String getNotes() {
        return notes;
    }
    public String toString(){
        StringBuffer sbResult = new StringBuffer();
	sbResult.append("id_interest = ");
	sbResult.append(this.id_interest);
	sbResult.append(", id_student = ");
	sbResult.append(this.id_student);
        sbResult.append(", interest_name = ");
	sbResult.append(this.interest_name);
	sbResult.append(", mark = ");
	sbResult.append(this.mark);
	sbResult.append(", notes = ");
	sbResult.append(this.notes);
        
        return sbResult.toString();
    }
            
}
