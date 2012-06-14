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
    private String message_text;
    private final boolean status;
    
    public Message(int id_student, String message_text, boolean status){
        this.id_student = id_student;
        this.message_text = message_text;
        this.status = status;
    }
    
    public int getStudentId(){
        return this.id_student;
    }
    public String getMessageText(){
        return this.message_text;
    }
    public boolean getStatus(){
        return this.status;
    }
    public String toString(){
        StringBuffer sbResult = new StringBuffer();
	sbResult.append("id_student = ");
	sbResult.append(this.id_student);
	sbResult.append(", edit_values = ");
	sbResult.append(this.message_text);
	sbResult.append(", status = ");
	sbResult.append(this.status);
	return sbResult.toString();
    }
}
