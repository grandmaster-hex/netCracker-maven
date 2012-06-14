/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.testing;
import netcracker.dao.DAOFactory;
import netcracker.dao.EmployeeDAO;
import netcracker.dao.Employee;  
import netcracker.dao.*; 
import java.util.List;
import java.util.LinkedList;
/**
 *
 * @author lasha.k
 */
public class employeedaotesting {
    public static void main(String[] args){
  /*      EmployeeDAO d = null;
   d = DAOFactory.getEmployeeDAO();
   boolean result = d.createEmployee("phd", "13adsf", "Иван", "Дебилов", "phdhospital@gmail.com", 1);
   if(result)
       System.out.print("\nCreate Successful");
   System.out.print("\n\n\n"+d.getEmployeeById(1).getFirstName());
   */
   //---------------Testing getingAllUniversities();
        
//   FormDAO st = DAOFactory.getFormDAO();
//   List a =st.getAllUniversities();
//   System.out.print(a.toString());
     FormDAO st = DAOFactory.getFormDAO();
     st.createStudent("Кваташидзе", "Лаша", "Тамазовчи", 5, "2007", 1, "kvata.l@gmail.com", "phdhospital@mail.ru",
             "(095)778-40-33", "33-95-46", "because", "no", "ok", null);
     SkillsDAO s = DAOFactory.getSkillsDAO();
     
     List<SkillsOfType> a = s.getAllSkillTypes(1);
     SkillsOfType as = a.get(0);
     System.out.print(as.getId_skill_type());
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
   
   
    }
}
