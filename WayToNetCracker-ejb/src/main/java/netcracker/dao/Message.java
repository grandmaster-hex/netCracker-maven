/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author Администратор
 */
public class Message {
    private final int id_student;
    private String edit_values;
    private final boolean status;
    
    public Message(int id_student, String edit_values, boolean status){
        this.id_student = id_student;
        this.edit_values = edit_values;
        this.status = status;
    }
    
    public int getIdStudent(){
        return this.id_student;
    }
    public String getEditValues(){
        return this.edit_values;
    }
    public boolean getStatus(){
        return this.status;
    }
    public String toString() {
        StringBuffer sbResult = new StringBuffer();
	sbResult.append("id_student = ");
	sbResult.append(this.id_student);
	sbResult.append(", edit_values = ");
	sbResult.append(this.edit_values);
	sbResult.append(", status = ");
	sbResult.append(this.status);
	return sbResult.toString();
    }
}
