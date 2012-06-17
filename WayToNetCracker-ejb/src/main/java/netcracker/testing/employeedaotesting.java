/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.testing;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import netcracker.dao.DAOFactory;
import netcracker.dao.*; 
import netcracker.service.ScheduleService;
import org.apache.poi.util.IOUtils;
/**
 *
 * @author lasha.k
 */
public class employeedaotesting {
    public static void main(String[] args){
      
            //---------------Testing getingAllUniversities();
                    
            //   FormDAO st = DAOFactory.getFormDAO();
            //   List a =st.getAllUniversities();
            //   System.out.print(a.toString());
            //------------------Testing separate date method
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.HOUR_OF_DAY,17);
                    cal.set(Calendar.MINUTE,00);
                    cal.set(Calendar.SECOND,00);
                    Date d = cal.getTime();
                    cal.set(Calendar.HOUR_OF_DAY,18);
                    cal.set(Calendar.MINUTE,00);
                    cal.set(Calendar.SECOND,00);
                    Date end = cal.getTime();
                    netcracker.service.ScheduleService.deleteSheduleBetweenDates(d, end);                    
                    DAOFactory.getSheduleDAO().createShedule(netcracker.service.ScheduleService.separate(d, end, 20, 1, 7));

        
            //---------------Testing Skills
            //        List<SkillsOfType> a = DAOFactory.getSkillsDAO().getAllSkillTypes(1);
            //        SkillsOfType b = a.get(1);//2 - Английский, 1-Технологии, 0 - Языки программирования
            //        List<Skill> c = b.getSkills();
            //        List<Interest> f = DAOFactory.getInterestDAO().getStudentInterestsById(1);
            //        System.out.print(f.toString());
            //     
            //-------------------Testing Form
//        // try{
//      Calendar cal = Calendar.getInstance();
//      cal.set(Calendar.YEAR,2007);
//      java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
//      Student b = DAOFactory.getStudentDAO().getStudentByIdStudent(1);
//      System.out.print(b.getLastName());
//      
      
                 // System.out.print(b.toString());
//                  
//      File file = new File("/home/phd/Pictures/5.jpg");  
//      FileInputStream fis = new FileInputStream(file);  
//      byte b1[] = new byte[(int)file.length()];  
//      fis.read(b1);  
//      System.out.println(b1.length);  
//            try {
//                java.sql.Blob b2 = new SerialBlob(b1);  
//                Student s1 = new Student(2, "asd", "asd", "asd", 4, sqlDate, 1, "asd", "fgh", "gfh", "hjg", "kj", "hgj", "kj", b2);        
//                StudentDAO stud = DAOFactory.getStudentDAO();
//                 stud.createStudent(s1);
                      //---------------Testing createEmployees();
                      //   EmployeeDAO d = null;
                      //   d = DAOFactory.getEmployeeDAO();  
                      //   boolean result = d.createEmployee("phd", "13adsf", "Иван", "Дебилов", "phdhospital@gmail.com", 1);
                      //   if(result)
                      //       System.out.print("\nCreate Successful");
                      //   System.out.print("\n\n\n"+d.getEmployeeById(1).getFirstName());
                      //---------------Testing deleteEmployeeById()
                      //   d.deleteEmployeeById(2);
                      //---------------Testing sending mail with attachment   
                      //       MailBean mail = new MailBean();
                      //        try{
                      //        mail.sendForm("kvata.l@gmail.com, kalininasofiya@gmail.com,kac.anna.vl@gmail.com,onlylastride@gmail.com,lebentrop@gmail.com","/home/phd/ssh.txt");
                      //        }
                      //        catch (Exception ex){
                      //        }
//                      //        }
//            } catch (SerialException ex) {
//                Logger.getLogger(employeedaotesting.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (SQLException ex) {
//                Logger.getLogger(employeedaotesting.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(employeedaotesting.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   
   
    }
}
