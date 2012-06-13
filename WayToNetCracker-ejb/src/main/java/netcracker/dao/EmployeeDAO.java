/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author lasha.k
 */
public interface EmployeeDAO {
    public boolean createEmployee(String login, String password, String firstName, String lastName, String email, int roleId);
    public boolean deleteEmployee(int id);
    public Employee getEmployeeById(int id_employee);
}