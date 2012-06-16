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
import netcracker.service.ScheduleSeparator;
import org.apache.poi.util.IOUtils;
/**
 *
 * @author lasha.k
 */
public class employeedaotesting {
    public static void main(String[] args) throws FileNotFoundException{
        try {
            //---------------Testing getingAllUniversities();
                    
            //   FormDAO st = DAOFactory.getFormDAO();
            //   List a =st.getAllUniversities();
            //   System.out.print(a.toString());
            //------------------Testing separate date method
            //        Calendar cal = Calendar.getInstance();
            //        cal.set(Calendar.HOUR_OF_DAY,17);
            //        cal.set(Calendar.MINUTE,00);
            //        Date d = cal.getTime();
            //        cal.set(Calendar.HOUR_OF_DAY,18);
            //        cal.set(Calendar.MINUTE,00);
            //        Date end = cal.getTime();
            //        
            //        List<Date> res = ScheduleSeparator.separate(d, end, 20);
            //        System.out.print(res.toString());
            //---------------Testing Skills
            //        List<SkillsOfType> a = DAOFactory.getSkillsDAO().getAllSkillTypes(1);
            //        SkillsOfType b = a.get(1);//2 - Английский, 1-Технологии, 0 - Языки программирования
            //        List<Skill> c = b.getSkills();
            //        List<Interest> f = DAOFactory.getInterestDAO().getStudentInterestsById(1);
            //        System.out.print(f.toString());
            //     
            //-------------------Testing Form
                  Calendar cal = Calendar.getInstance();
                  cal.set(Calendar.YEAR,2007);
                  Date b = cal.getTime();
                  System.out.print(b.toString());
            //      Student a = new Student("Анна", "Кудра", "Алексеевна",5,b,1,"violatormode@rambler.ru","phdhospital@mail.ru","050-886-16-27",
                     //     null, "beacause I'm the best", "no expirience",null,0);
                    
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
            //            System.out.print("Something wrong");
            //        }
        } catch (IOException ex) {
            Logger.getLogger(employeedaotesting.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
    }
}
