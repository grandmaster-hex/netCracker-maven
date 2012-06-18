/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lasha.k
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    public EmployeeDAOImpl() {
    }

    /**
     * Creates new employee
     *
     * @param login employees login
     * @param password employees password
     * @param first_name employees first_name
     * @param last_name employees last_name
     * @param email employees email
     * @param id_role employees id_role
     * @return
     */
    public boolean createEmployee(Employee emp) {

        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("insert into ");
            sbInsert.append(DAOConstants.EmpTableName);
            sbInsert.append(" (login, password, first_name, last_name, email, id_role)");
            sbInsert.append(" values(");
            sbInsert.append("?,?,?,?,?,?)");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setString(1, emp.getLogin());
            stmtInsert.setString(2, emp.getPassword());
            stmtInsert.setString(3, emp.getFirstName());
            stmtInsert.setString(4, emp.getLastName());
            stmtInsert.setString(5, emp.getLogin());
            stmtInsert.setInt(6, 1);

            int rows = stmtInsert.executeUpdate();
            if (rows != 1) {
                throw new SQLException(
                        "executeUpdate return value: "
                        + rows);
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in createEmployee");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }

    /**
     * Delete Employee
     *
     * @param id id of deleted employee
     * @return
     */
    @Override
    public boolean deleteEmployee(int id_employee) {

        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("delete from ");
            sbDelete.append(DAOConstants.EmpTableName);
            sbDelete.append(" where id_employee = ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setInt(1, id_employee);
            int rows = stmtDelete.executeUpdate();
            if (rows != 1) {
                throw new SQLException("\nexecuteUpdate in deleteEmployee() return value: " + rows);

            }

        } catch (SQLException ex) {
            System.out.print("\nSQLException while removing Employee");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;
    }

    @Override
    public Employee getEmployeeById(int id_employee) {

        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        Employee emp = null;

        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT login, password, first_name, last_name, email, id_role FROM ");
            sbSelect.append(DAOConstants.EmpTableName);
            sbSelect.append(" WHERE id_employee = ");
            sbSelect.append("?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_employee);
            result = stmtSelect.executeQuery();

            int rowsCount = 0;
            while (result.next()) {
                emp = new Employee(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getString(5),
                        result.getInt(6));
                rowsCount++;
            }

            if (rowsCount < 1) {
                System.out.println("\n No employees found");
            }
        } catch (SQLException ex) {
            System.out.print("\nSQLException while removing Employee");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);

        }

        return emp;
    }

    @Override
    public List<String> getAllRoles() {
        List<String> rolesList = new ArrayList<String>();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_role, role_name FROM ");
            sbSelect.append(DAOConstants.RolesTableName);
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                rolesList.add(res.getString(1));
                rolesList.add(res.getString(2));
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo roles found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getAllRoles\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return rolesList;
    }
}