/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import netcracker.dao.DAOFactory;

/**
 *
 * @author lastride
 */
public class ExcelExport {
    public static boolean exportToExcel() {
        PreparedStatement stmt = null;
        Connection conn = DAOFactory.createConnection();
        return true;
    }
}
