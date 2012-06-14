/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AnnKats
 */
public class FormToPDF {
    
    private Document document;
    
    private BaseFont times;
    
    private String id;
    private String first_name;
    private String last_name;
    private String middle_name;
    private String course;
    private String univer;
    private String study_end_year;
    private String id_faculty;
    private String email1;
    private String email2;
    private String phone1;
    private String phone2;
    
    public FormToPDF() {
        
        setFonts();   
        
        ////
        id = "1"; 
        first_name = "Анна"; 
        last_name = "Кац"; 
        middle_name = "Владимировна"; 
        course = "3"; 
        univer = "ОНУ им. Мечникова"; 
        study_end_year = "2013";                        
        id_faculty = "Прикладная математика"; 
        email1 = "kac.anna.vl@gmail.com"; 
        email2 = "kac.anna.vl@gmail.com"; 
        phone1 = "7843751"; 
        phone2 = "7843752";
        ///
    }
    
    private void setFonts() {
        try {
            times  = BaseFont.createFont("Font/times.ttf",  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            
        } catch (DocumentException ex) {
            Logger.getLogger(FormToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    private void writeNumber(PdfContentByte cb) {
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(60, 815); 
        cb.showText(id);  
        cb.endText();
    }
    
    private void writePersonalInfoAndContacts(PdfContentByte cb) {
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(98, 748); 
        cb.showText(last_name);
        cb.moveText(153, 0); 
        cb.showText(first_name);
        cb.moveText(156, 0); 
        cb.showText(middle_name);
        cb.moveText(-333, -20); 
        cb.showText(univer);
        cb.moveText(182, 0); 
        cb.showText(course);
        cb.moveText(118, 0); 
        cb.showText(id_faculty);            
        cb.moveText(-248, -21); 
        cb.showText(study_end_year);           

        cb.moveText(80, -65); 
        cb.showText(email1);
        cb.moveText(0, -20); 
        cb.showText(email2);
        cb.moveText(0, -21); 
        cb.showText(phone1);
        cb.moveText(42, -21); 
        cb.showText(phone2);
        cb.endText();
    }
    
    private void writeInterests(PdfContentByte cb) {
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(182, 478); 
        cb.showText("±");  
        cb.moveText(0, -28); 
        cb.showText("+");  
        cb.endText();

        cb.beginText();
        cb.moveText(182, 408); 
        cb.showText("±");
        cb.moveText(0, -25); 
        cb.showText("-");
        cb.endText();

        cb.beginText();
        cb.moveText(340, 379); 
        cb.showText("Что-то интересует.");
        cb.endText();
    }
    
    private void writeInterestsAboutJobType(PdfContentByte cb) {
        cb.beginText();
        cb.moveText(90, 335);
        cb.showText("+");
        cb.moveText(0, -22); 
        cb.showText("±");
        cb.moveText(0, -22); 
        cb.showText("+");
        cb.endText();

        cb.beginText();
        cb.moveText(345, 335);
        cb.showText("±");
        cb.moveText(0, -22); 
        cb.showText("?");
        cb.endText();

        cb.beginText();
        cb.moveText(452, 310); 
        cb.showText("Какая-то.");
        cb.endText();
    }
    
    private void writeSkills(PdfContentByte cb) {        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(144, 162); 
        cb.showText("5");
        cb.moveText(107, 0); 
        cb.showText("5");
        for(int i = 0; i < 3; i++)
        {
            cb.moveText(100 - 8*i, 0); 
            cb.showText("5");
            if(i == 0)
                cb.moveText(-30, 16);
            else
                cb.moveText(0, 16);
            cb.showText("C#");
            cb.moveText(0, -16);
        }          
        cb.endText();

        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(48, 102); 
        cb.showText("5");  
        cb.moveText(0, -22); 
        cb.showText("5"); 
        cb.moveText(275.5f, 22); 
        cb.showText("5"); 
        cb.moveText(0, -22); 
        cb.showText("5");             
        cb.endText();
    }
    
    private void writeSkillsOnSecondPage(PdfContentByte cb) {        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(48, 789); 
        cb.showText("5");  
        cb.moveText(0, -22); 
        cb.showText("5"); 
        cb.moveText(275.5f, 22); 
        cb.showText("5"); 
        cb.moveText(0, -22); 
        cb.showText("5");             
        cb.endText();

        String other1 = "Что-то еще 1", other2 = "Что-то еще 2", other3 = "Что-то еще 3";
        int mark1 = 5, mark2 = 5, mark3 = 5;
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(275, 748); 
        cb.showText(other1 + " (" + mark1 + ")");  
        cb.moveText(-230, -20);             
        cb.showText(other2 + " (" + mark2 + "), " + other3 + " (" + mark3 + ")");         
        cb.endText();
    }
    
    private void writeText(PdfContentByte cb, String s, float x, float y) {
        
        float downX = -20.5f;
        int cntSymbolsInString = 75;
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(x, y);
        
        int first = 0, last = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ' || i == s.length()-1)
                last = i;
            
            if((last-first) > cntSymbolsInString || i == s.length()-1) {                
                cb.showText(s.substring(first, last+1));
                cb.moveText(0, downX);
                first = last + 1;
            }
        }
        cb.endText();
    }
    
    private void writeEnglishLevel(PdfContentByte cb) {
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(183, 558);
        cb.showText("5");
        cb.moveText(70, 0);
        cb.showText("5");
        cb.moveText(66, 0);
        cb.showText("5");
        cb.endText();
        
        String commertials = "От друзей.";
        
        cb.beginText();
        cb.moveText(45, 505);
        cb.showText(commertials);
        cb.endText();
    }
    
    private void writeNameForSign(PdfContentByte cb) {
        float x = (float) (45 + (75 - last_name.length() - first_name.length() - middle_name.length())*3.3f); 
        cb.beginText();          
        cb.moveText(x, 248);
        cb.showText(last_name + " " + first_name + " " + middle_name);
        cb.endText();
    }
    
    
    public void createPDF() {

        try {
            //Путь к файлу
            String fileName = "Form/" + id + "_" + last_name + "_" + first_name + ".pdf";
            String templateName = "Form/Анкета для студентов september 2012.pdf";

            //Поток (стрим) для записи файла
            FileOutputStream fos = new FileOutputStream(fileName);
            
           
            document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, fos);
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            // Load existing PDF
            PdfReader reader = new PdfReader(templateName);
            PdfImportedPage page = writer.getImportedPage(reader, 1); 

            // Copy first page of existing PDF into output PDF
            document.newPage();
           
            cb.addTemplate(page, 0, 0);
            
            writeNumber(cb);
             
            writePersonalInfoAndContacts(cb);
            
            writeInterests(cb);
            writeInterestsAboutJobType(cb);
            
            writeSkills(cb);
            
            
            document.newPage();
            PdfImportedPage page2 = writer.getImportedPage(reader, 2); 
            cb.addTemplate(page2, 0, 0);
            
            writeNumber(cb);
            
            writeSkillsOnSecondPage(cb);
            
            
            String experienсe = "Была интерном в фейсбуке, гугле и яндексе. Писала на С++ и на Java. "
                                + "Стажировалась в разных компаниях, таких как Luxoft, Lohica, ProductEngine. "
                                + "Учавствовала во многих конференциях в Украине, Англии, Франции и Японии. "
                                + "Имею черный пояс по карате. Хорошо рисую.";
            
            writeText(cb, experienсe, 45, 680);
            writeEnglishLevel(cb);           
            
            
            String commertials = "От друзей.";
            
            String why = "Потому что я хочу выучить Java намного лучше, чем знаю сейчас. "
                        + "Хочу познакомиться с новыми людьми и чему-то у них научиться. "
                        + "Хочу занять свое время полезными вещами. Обещаю ходить регулярно на курсы и что-то делать.";
           
            writeText(cb, why, 45, 456);
            
            
            String moreAboutMe = "Участвую в олимпиадах по программированию типа ACM. "
                                + "Имею много сертификатов и дипломов за большие достижения в математике и программировании.";
            
            writeText(cb, moreAboutMe, 45, 372.5f);
            
            writeNameForSign(cb);           
            
            document.close();


        } catch (IOException ex) {
            Logger.getLogger(FormToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException e) {
            //Blah Blah Blah
        }
    }
  
}
