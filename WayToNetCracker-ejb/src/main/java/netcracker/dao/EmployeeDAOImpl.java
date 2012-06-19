/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lasha.k
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    public EmployeeDAOImpl() {
    }

    public boolean createEmployee(Employee emp) {
        PreparedStatement stmtInsert = null;
        PreparedStatement stmtCheckLogin = null;
        ResultSet resultCheckLogin = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbCheckLogin = new StringBuffer();
            sbCheckLogin.append("SELECT count(id_employee) FROM ");
            sbCheckLogin.append(DAOConstants.EmpTableName);
            sbCheckLogin.append(" WHERE login like ?");

            stmtCheckLogin = conn.prepareStatement(sbCheckLogin.toString());
            stmtCheckLogin.setString(1, emp.getLogin());
            resultCheckLogin = stmtCheckLogin.executeQuery();
            int rowsCount = 0;
            while (resultCheckLogin.next()) {
                rowsCount = resultCheckLogin.getInt(1);
            }
            if (rowsCount > 0) {
                return false;
            } else {
                StringBuffer sbInsert = new StringBuffer();
                sbInsert.append("INSERT INTO ");
                sbInsert.append(DAOConstants.EmpTableName);
                sbInsert.append(" (login, password, first_name, last_name, email, id_role)");
                sbInsert.append(" VALUES(");
                sbInsert.append("?,?,?,?,?,?)");
                stmtInsert = conn.prepareStatement(sbInsert.toString());
                stmtInsert.setString(1, emp.getLogin());
                stmtInsert.setString(2, this.getPasswordHash(emp.getPassword()));
                stmtInsert.setString(3, emp.getFirstName());
                stmtInsert.setString(4, emp.getLastName());
                stmtInsert.setString(5, emp.getLogin());
                stmtInsert.setInt(6, emp.getIdRole());

                int rows = stmtInsert.executeUpdate();
                if (rows != 1) {
                    throw new SQLException(
                            "executeUpdate return value: "
                            + rows);
                }
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in createEmployee");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }

    @Override
    public boolean deleteEmployeeById(int id_employee) {

        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtDelete = null;
        try {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("delete from ");
            sbDelete.append(DAOConstants.EmpTableName);
            sbDelete.append(" where id_employee = ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setInt(1, id_employee);
            stmtDelete.executeUpdate();
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

    public List<Employee> getEmployeeListByRole(String role_name) {
        List<Employee> empList = new ArrayList<Employee>();

        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;

        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT login, password, first_name, last_name, email, id_role FROM ");
            sbSelect.append(DAOConstants.EmpTableName);
            sbSelect.append(" WHERE id_role = (SELECT id_role FROM roles WHERE role_name = ? )");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setString(1, role_name);
            result = stmtSelect.executeQuery();

            while (result.next()) {
                empList.add(new Employee(result.getString(1), result.getString(2),
                        result.getString(3), result.getString(4), result.getString(5),
                        result.getInt(6)));
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in getInterviewerList");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }

        return empList;
    }

    public List<Employee> getInterviewerList() {
        return getEmployeeListByRole("Interviewer");
    }

    public List<Employee> getHRList() {
        return getEmployeeListByRole("HR");
    }

    public boolean checkPassword(String login, String password) {
        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet result = null;
        boolean found = false;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT login FROM ");
            sbSelect.append(DAOConstants.EmpTableName);
            sbSelect.append(" WHERE login like ? AND password = ?");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setString(1, login);
            stmtSelect.setString(2, this.getPasswordHash(password));
            result = stmtSelect.executeQuery();

            int cnt = 0;
            while (result.next()) {
                cnt++;
            }
            if (cnt > 0) {
                found = true;
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in checkPassword");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }

        return found;
    }

    public boolean changePassword(int id_employee, String oldPassword, String password) {

        PreparedStatement stmtSelect = null;
        Connection conn = DAOFactory.createConnection();
        ResultSet resultLogin = null;
        String login = null;
        StringBuffer sbSelect = new StringBuffer();
        sbSelect.append("SELECT login FROM ");
        sbSelect.append(DAOConstants.EmpTableName);
        sbSelect.append(" WHERE id_employee = ?");
        try {
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_employee);
            resultLogin = stmtSelect.executeQuery();
            while (resultLogin.next()) {
                login = resultLogin.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (checkPassword(login, oldPassword) == false) {
            return false;
        }

        PreparedStatement stmtUpdate = null;
        ResultSet result = null;

        try {
            StringBuffer sbUpdate = new StringBuffer();
            sbUpdate.append("UPDATE ");
            sbUpdate.append(DAOConstants.EmpTableName);
            sbUpdate.append(" SET password = ? WHERE id_employee = ?");

            stmtUpdate = conn.prepareStatement(sbUpdate.toString());
            stmtUpdate.setString(1, this.getPasswordHash(password));
            stmtUpdate.setInt(2, id_employee);

            int rows = stmtUpdate.executeUpdate();
            if (rows != 1) {
                throw new SQLException("\nexecuteUpdate in changePassword() return value: " + rows);
            }

        } catch (SQLException ex) {
            System.out.print("\nSQL exception in changePassword");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtUpdate);
        }

        return true;
    }

    public boolean resetPassword(int id_employee, String oldPassword) {
        return changePassword(id_employee, oldPassword, "");
    }

    /**
     * Password hashing (md5)
     */
    public String getPasswordHash(String password) {
        MessageDigest md5;
        StringBuffer hexString = new StringBuffer();
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(password.getBytes());
            byte messageDigest[] = md5.digest();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
        return hexString.toString();
    }
}