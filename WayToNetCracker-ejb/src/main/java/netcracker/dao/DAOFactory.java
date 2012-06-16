package netcracker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
*
* @author lasha.k
*/
public final class DAOFactory {
    private DAOFactory(){
        
    }
   
    public static EmployeeDAO getEmployeeDAO(){
        return new EmployeeDAOImpl();
    }
    
    public static FormDAO getFormDAO() {
        return new FormDAOImpl();
    }
    
    public static InterviewDAO getInterviewDAO() {
        return new InterviewDAOImpl();
    }
    //Create java.sql.Connection with params set in DAOConstants.java
    public static Connection createConnection(){
            Connection conn = null;
try
{
                     Class.forName(DAOConstants.driver).newInstance();
                     conn = DriverManager.getConnection(DAOConstants.url, DAOConstants.user, DAOConstants.password);
                     System.out.print("\nDB connection established! \n");
}
catch (Exception ex)
{
                        System.err.print("\nCannot connect to DB!\n");
}
                 return conn;
    }
    //Closes opened java.sql.Connection or throws SQLException
    public static void closeConnection(final Connection conn)
{
if (conn != null)
{
try
{
conn.close();
}
catch (SQLException ex)
{
System.out.print("Close connection error");
}
}
}
    // closes opened stmt or throws SQLException
    public static void closeStatement(final PreparedStatement stmt)
{
if (stmt != null)
{
try
{
stmt.close();
}
catch (SQLException ex)
{
System.out.print("Close statement error");
}
}
}
}