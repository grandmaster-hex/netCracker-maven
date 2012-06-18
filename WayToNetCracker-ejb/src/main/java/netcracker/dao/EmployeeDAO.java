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
}