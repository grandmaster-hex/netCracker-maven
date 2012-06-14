/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import netcracker.dao.DAOConstants;
import netcracker.dao.DAOFactory;
import org.apache.poi.hssf.usermodel.*;

/**
 *
 * @author lastride
 */
public class ExcelExport {
    public static boolean exportToExcel() {
        PreparedStatement stmt = null;
        ResultSet result = null;
        Connection conn = DAOFactory.createConnection();
        FileOutputStream fos = null;
        HSSFWorkbook workbook = new HSSFWorkbook();
        try {
            StringBuffer sbEmployees = new StringBuffer();
            sbEmployees.append("SELECT * FROM ");
            sbEmployees.append(DAOConstants.EmpTableName);
            stmt = conn.prepareStatement(sbEmployees.toString());
            result = stmt.executeQuery();
            
            HSSFSheet advertsSheet = workbook.createSheet("adverts");
            HSSFSheet advertsForStudentsSheet = workbook.createSheet("advertsForStudents");
            HSSFSheet employeesSheet = workbook.createSheet("employees");
            HSSFSheet facultiesSheet = workbook.createSheet("faculties");
            HSSFSheet interestsSheet = workbook.createSheet("interests");
            HSSFSheet interestsForStudentsSheet = workbook.createSheet("interestsForStudents");
            HSSFSheet intervalsSheet = workbook.createSheet("intervals");
            HSSFSheet intervalsForStudentsSheet = workbook.createSheet("intervalsForStudents");
            HSSFSheet intervalStatusesSheet = workbook.createSheet("intervalStatuses");
            HSSFSheet messagesSheet = workbook.createSheet("messages");
            HSSFSheet resultsSheet = workbook.createSheet("results");
            HSSFSheet rolesSheet = workbook.createSheet("roles");
            HSSFSheet skillsSheet = workbook.createSheet("skills");
            HSSFSheet skillsForStudentsSheet = workbook.createSheet("skillsForStudents");
            HSSFSheet skillsTypesSheet = workbook.createSheet("skillsTypes");
            HSSFSheet studentsSheet = workbook.createSheet("students");
            HSSFSheet universitiesSheet = workbook.createSheet("universities");
           
            String[] advertsFields = {"id_advert", "advert_name"};
            String[] advertsForStudentsFields = {"id_student", "id_advert", "notes"};
            String[] employeesFields = {"id_employee", "login", "password",
                                        "first_name", "last_name", "email", "id_role"};
            String[] facultiesFields = {"id_faculty", "id_university", "faculty_name"};
            String[] interestsFields = {"id_interest", "interst_name"};
            String[] interestsForStudentsFields = {"id_interest", "id_student", "mark", "notes"};
            String[] intervalsFields = {"id_interval", "start_time", "end_time", "interviewers_count", "id_interval_status"};
            String[] intervalsForStudentsFields = {"id_interval", "id_student"};
            String[] intervalStatusesFields = {"id_interval_status", "interval_status_name"};
            String[] messagesFields = {"id_student", "edit_values", "status"};
            String[] resultsFields = {"id_student", "id_employee", "comment"};
            String[] rolesFields = {"id_role", "role_name"};
            String[] skillsFields = {"id_skill", "skill_name", "id_skill_type"};
            String[] skillsForStudentsFields = {"id_skill", "id_student", "mark", "notes"};
            String[] skillsTypesFields = {"id_skill_type", "skill_type_name"};
            String[] studentsFields = {"id_student", "first_name", "last_name", "middle_name", "course", "study_end_year",
                                    "id_faculty", "email1", "email2", "phone1", "extra_contacts", "why", "experience",
                                    "extra", "photo"};
            String[] universitiesFields = {"id_university", "university_name"};
            
            
            HSSFRow row = employeesSheet.createRow(0);
            for (int i = 0; i < employeesFields.length; i++) {
               System.out.print(i);
               HSSFCell cell = row.createCell(i);
               cell.setCellValue(employeesFields[i]);
            }

            
            
           /* while (result.next()) {
                System.out.println(result.getInt("id_employee") + result.getString("login") +
                                    result.getString("password") + result.getString("first_name") +
                                    result.getString("last_name") + result.getString("email") +
                                    result.getString("id_role"));
            }*/
            
        }
        catch (SQLException ex) {
            System.out.print("\nSQL exception in exportToExcel");
        }
        finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmt);
        }
        /**
         * Write xls file
         */
        try {
            fos = new FileOutputStream(new File("/home/phd/DataBase.xls"));
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return true;
    }
    public static void main(String[] args) {
        ExcelExport.exportToExcel();
    }
}
