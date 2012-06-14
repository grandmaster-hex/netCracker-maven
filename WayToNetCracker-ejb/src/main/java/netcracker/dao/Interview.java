/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author lastride
 */
public class Interview {
    private int id_student;
    private int id_employee;
    private String comment;
    public Interview(int id_student, int id_employee, String comment) {
        this.id_student = id_student;
        this.id_employee = id_employee;
        this.comment = comment;
    }
    
    public int getIdStudent() {
        return this.id_student;
    }
    
    public int getIdEmployee() {
        return this.id_employee;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public String toString() {
        StringBuffer sbResult = new StringBuffer();
	sbResult.append("id_student = ");
	sbResult.append(this.id_student);
	sbResult.append(", id_employee = ");
	sbResult.append(this.id_employee);
	sbResult.append(", comment = ");
	sbResult.append(this.comment);
        
	return sbResult.toString();
    }
}
