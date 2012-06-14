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
    private final int mark;
    private final String notes;
    public Interest(int id_interest,int id_student, int mark, String notes){
        this.id_interest = id_interest;
        this.id_student=id_student;
        this.mark = mark;
        this.notes = notes;
    }
    
    public int getId_interest() {
        return id_interest;
    }

    public int getId_student() {
        return id_student;
    }

    public int getMark() {
        return mark;
    }

    public String getNotes() {
        return notes;
    }
            
}
