/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.util.List;

/**
 *
 * @author lasha.k
 */
public interface EmployeeDAO {

    /**
     * Creates new employee
     *
     * @param emp
     * @return if login already existed returns false
     */
    public boolean createEmployee(Employee emp);

    /**
     * Deletes employee by id_employee
     *
     * @param id
     * @return
     */
    public boolean deleteEmployeeById(int id_employee);

    /**
     * Gets employee by id_employee
     *
     * @param id_employee
     * @return
     */
    public Employee getEmployeeById(int id_employee);

    /**
     * Get all existing roles
     *
     * @return
     */
    public List<String> getAllRoles();

    /**
     * Get list of employees by role_name
     *
     * @param role_name
     * @return
     */
    public List<Employee> getEmployeeListByRole(String role_name);

    /**
     * Gets all interviewers
     *
     * @return
     */
    public List<Employee> getInterviewerList();

    /**
     * Gets all HR
     *
     * @return
     */
    public List<Employee> getHRList();

    /**
     * Checks password by employee login
     *
     * @param login
     * @param password
     * @return
     */
    public boolean checkPassword(String login, String password);

    /**
     * Changes password by id_employee
     *
     * @param id_employee
     * @param oldPassword
     * @param password
     * @return
     */
    public boolean changePassword(int id_employee, String oldPassword, String password);

    /**
     * Resets password to '' by id_employee
     *
     * @param id_employee
     * @param oldPassword
     * @return
     */
    public boolean resetPassword(int id_employee, String oldPassword);
}