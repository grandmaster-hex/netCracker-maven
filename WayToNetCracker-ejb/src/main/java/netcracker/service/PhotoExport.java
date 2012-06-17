/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import netcracker.dao.DAOConstants;
import netcracker.dao.DAOFactory;

/**
 *
 * @author lastride
 */
public class PhotoExport {

    public static boolean exportPhoto() {
        ZipOutputStream zip = null;
        try {
            PreparedStatement stmt = null;
            ResultSet result = null;
            Connection conn = DAOFactory.createConnection();
            StringBuffer sbPhoto = null;
            sbPhoto = new StringBuffer();
            sbPhoto.append("SELECT photo, last_name, first_name FROM ");
            sbPhoto.append(DAOConstants.StudentsTableName);
            zip = new ZipOutputStream(new FileOutputStream("/home/lastride/photos.zip"));
            try {
                stmt = conn.prepareStatement(sbPhoto.toString());
                result = stmt.executeQuery();
                while (result.next()) {
                    ZipEntry entry = new ZipEntry(result.getString("last_name")
                            + "_" + result.getString("first_name") + ".jpeg");
                    zip.putNextEntry(entry);
                    zip.write((byte[]) result.getObject("photo"));
                }
            } catch (IOException ex) {
                Logger.getLogger(PhotoExport.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(PhotoExport.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DAOFactory.closeConnection(conn);
                DAOFactory.closeStatement(stmt);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PhotoExport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                zip.close();
            } catch (IOException ex) {
                Logger.getLogger(PhotoExport.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;

    }

    public static void main(String[] args) {
        PhotoExport.exportPhoto();
    }
}
