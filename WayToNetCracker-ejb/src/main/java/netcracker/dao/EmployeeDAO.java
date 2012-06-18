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
    public boolean createEmployee(Employee emp);
    public boolean deleteEmployee(int id);
    public Employee getEmployeeById(int id_employee);
    public List<String> getAllRoles();
    public List<Employee> getEmployeeListByRole(String role_name);
    public List<Employee> getInterviewerList();
    public List<Employee> getHRList();
    public boolean checkPassword(int id_employee, String password);
    public boolean changePassword(int id_employee, String oldPassword, String password);
    public boolean resetPassword(int id_employee, String oldPassword);
}