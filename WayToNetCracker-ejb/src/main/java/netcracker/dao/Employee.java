package netcracker.dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lasha.k
 */
public class Employee {
    //attributes describing data for Employee
    private final String login;
    private final String password;
    private final String last_name;
    private final String first_name;
    private final String email;
    private final int id_role;
    //constructor initializing new Employee with params
     public Employee(String login, String password, String last, String first, String email, int id_role){
        this.email=email;
        this.first_name=first;
        this.last_name=last;
        this.id_role=id_role;
        this.login=login;
        this.password = password;
    }
    //Getters
    
    public String getLastName() {
        return this.last_name;
    }

    
    public String getFirstName() {
        return this.first_name;
    }

    
    public String getLogin() {
        return this.login;
    }

    
    public String getEmail() {
        return this.email;
    }

    
    public int getIdRole() {
        return this.id_role;
    }
    //employee to string
    
    public String toString()
	{
		StringBuffer sbResult = new StringBuffer();
		sbResult.append("last name = ");
		sbResult.append(last_name);
		sbResult.append(", first name = ");
		sbResult.append(first_name);
		sbResult.append(", login = ");
		sbResult.append(login);
		sbResult.append(", email = ");
		sbResult.append(email);
                sbResult.append(", id_role = ");
		sbResult.append(id_role);
		return sbResult.toString();
	}

    
    public String getPassword() {
        return this.password;
    }
    
}