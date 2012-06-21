/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import netcracker.dao.*;

/**
 *
 * @author AnnKats
 */
public class FormToPDF {
    
    private Document document;
    
    private BaseFont times;
        
    private Student student;
    
    private StudentDAOImpl studImpl;
    
    public FormToPDF() {
        
        studImpl = new StudentDAOImpl();        
        
        setFonts();
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
        cb.showText(Integer.toString(student.getStudentId()));  
        cb.endText();
    }
    
    private void writePersonalInfoAndContacts(PdfContentByte cb) {
                
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(98, 748); 
        cb.showText(student.getLastName());
        cb.moveText(153, 0); 
        cb.showText(student.getFirstName());
        cb.moveText(156, 0); 
        cb.showText(student.getMiddleName());
        cb.moveText(-333, -20); 
        cb.showText(studImpl.getUniversityNameByIdStudent(student.getStudentId())); ///!!!!!1
        cb.moveText(182, 0); 
        cb.showText(Integer.toString(student.getCourse()));
        cb.moveText(118, 0); 
        cb.showText(studImpl.getFacultyNameByIdStudent(student.getStudentId()));            
        cb.moveText(-248, -21);
        cb.showText(df.format(student.getStudyEndYear()));           

        cb.moveText(80, -65); 
        cb.showText(student.getEmail1());
        cb.moveText(0, -20); 
        cb.showText(student.getEmail2());
        cb.moveText(0, -21); 
        cb.showText(student.getPhone1());
        cb.moveText(42, -21); 
        cb.showText(student.getPhone1());
        cb.endText();
    }
    
    private Map<String, String> getInterestMarksMap() {
        
        Map<String, String> map = new HashMap<String, String>();        
        List<Interest> interestList = student.getInterests();
        
        for (int i = 0; i < interestList.size(); i++) {
             Interest interest = interestList.get(i);
             String key = DAOFactory.getInterestDAO().getInterestNameById(interest.getIdInterest());
             String value = interest.getMark();
             if (key.equals(DAOConstants.InterestOther) ||
                     key.equals(DAOConstants.InterestOtherJob))
                 value += " " + interest.getNotes();
             map.put(key, value);
        }
        
        return map;
    }
    
    private void writeInterests(PdfContentByte cb) {
        
        Map<String, String> map = getInterestMarksMap();
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(182, 478);         
        cb.showText(map.get(DAOConstants.InterestStudying));  
        cb.moveText(0, -28); 
        cb.showText(map.get(DAOConstants.InterestWorking));  
        cb.endText();

        String other = map.get(DAOConstants.InterestOther);
        
        cb.beginText();
        cb.moveText(182, 408); 
        cb.showText(map.get(DAOConstants.InterestSoftwareDeveloping));
        cb.moveText(0, -25); 
        cb.showText(Character.toString(other.charAt(0)));
        cb.endText();
        
        cb.beginText();
        cb.moveText(340, 379); 
        cb.showText(other.substring(2));
        cb.endText();
    }
    
    private void writeInterestsAboutJobType(PdfContentByte cb) {
        
        Map<String, String> map = getInterestMarksMap();
        
        cb.beginText();
        cb.moveText(90, 335);
        cb.showText(map.get(DAOConstants.InterestDeepSpecialization));
        cb.moveText(0, -22); 
        cb.showText(map.get(DAOConstants.InterestDifferentJob));
        cb.moveText(0, -22); 
        cb.showText(map.get(DAOConstants.InterestExpertsManagement));
        cb.endText();

        String other = map.get(DAOConstants.InterestOtherJob);
        
        cb.beginText();
        cb.moveText(345, 335);
        cb.showText(map.get(DAOConstants.InterestSale));
        cb.moveText(0, -22); 
        cb.showText(Character.toString(other.charAt(0)));
        cb.endText();

        cb.beginText();
        cb.moveText(452, 310); 
        cb.showText(other.substring(2));
        cb.endText();
    }
    
    private Map<String, Integer> getSkillMarksMapBySkillType(String skillTypeName) {
        
        Map<String, Integer> map = new HashMap<String, Integer>();        
        List<SkillsOfType> skillsByTypesList = student.getSkills();
        
        for (int i = 0; i < skillsByTypesList.size(); i++) {
             
            SkillsOfType skillType = skillsByTypesList.get(i);
            
            if(skillTypeName.equals(skillType.getSkillTypeNamr())) {
                List<Skill> skillList = skillType.getSkills();
                
                for(int j = 0; j < skillList.size(); j++) {
                    Skill skill = skillList.get(j);
                    
                    String key = skill.getSkillName();
                    Integer value = skill.getMark();
                    
                    map.put(key, value);
                }
             }
        }
        
        return map;
    }
    
    private void writeLanguageSkills(PdfContentByte cb) { 
        
        Map<String, Integer> mapLanguage = getSkillMarksMapBySkillType(DAOConstants.SkillTypeLanguage);
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        
        cb.moveText(144, 162); 
        cb.showText(mapLanguage.get(DAOConstants.SkillCPP).toString());              
        cb.moveText(107, 0); 
        cb.showText(mapLanguage.get(DAOConstants.SkillJava).toString());
        
        mapLanguage.remove(DAOConstants.SkillCPP);  
        mapLanguage.remove(DAOConstants.SkillJava);
        
        Iterator it = mapLanguage.entrySet().iterator();
        for(int i = 0; i < 3 && it.hasNext(); i++)
        {
            Map.Entry skill =(Map.Entry)it.next();

            cb.moveText(100 - 8*i, 0); 
            cb.showText(skill.getValue().toString());
            if(i == 0)
                cb.moveText(-30, 16);
            else
                cb.moveText(0, 16);
            cb.showText(skill.getKey().toString());
            cb.moveText(0, -16);
        }          
        cb.endText();
    }
    
    private void writeKnowledgeSkills(PdfContentByte cb) {
        
        Map<String, Integer> mapKnowledge = getSkillMarksMapBySkillType(DAOConstants.SkillTypeKnowledge);
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(48, 102); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillNetworkTechnologies).toString());  
        cb.moveText(0, -22); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillAlgorithms).toString()); 
        cb.moveText(275.5f, 22); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillOOP).toString()); 
        cb.moveText(0, -22); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillDB).toString());             
        cb.endText();
    }
    
    private void writeSkillsOnSecondPage(PdfContentByte cb) {      
        
        Map<String, Integer> mapKnowledge = getSkillMarksMapBySkillType(DAOConstants.SkillTypeKnowledge);
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(48, 789); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillWeb).toString());  
        cb.moveText(0, -22); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillGUI).toString()); 
        cb.moveText(275.5f, 22); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillNetworkProgramming).toString()); 
        cb.moveText(0, -22); 
        cb.showText(mapKnowledge.get(DAOConstants.SkillProgramDesign).toString());             
        cb.endText();
        
        mapKnowledge.remove(DAOConstants.SkillNetworkTechnologies);
        mapKnowledge.remove(DAOConstants.SkillAlgorithms);
        mapKnowledge.remove(DAOConstants.SkillOOP);
        mapKnowledge.remove(DAOConstants.SkillDB);
        mapKnowledge.remove(DAOConstants.SkillWeb);
        mapKnowledge.remove(DAOConstants.SkillGUI);
        mapKnowledge.remove(DAOConstants.SkillNetworkProgramming);
        mapKnowledge.remove(DAOConstants.SkillProgramDesign);
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(275, 748); 
        
        Iterator it = mapKnowledge.entrySet().iterator();
        for(int i = 0; i < 3 && it.hasNext(); i++)
        {
            Map.Entry skill =(Map.Entry)it.next();

            if(i == 1)
                cb.moveText(-230, -20);
            
            String knowledge = skill.getKey().toString();
            String mark = skill.getValue().toString();
            String text = knowledge + " (" + mark + ")";
            if(i < 2 && it.hasNext())
                text += ", ";
            cb.showText(text);
        }
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
    
    private void writeExperience(PdfContentByte cb) {
         writeText(cb, student.getExperience(), 45, 680);
    }
    
    private void writeWhy(PdfContentByte cb) {
         writeText(cb, student.getWhy(), 45, 456);
    }
    
    private void writeExtra(PdfContentByte cb) {
         writeText(cb, student.getExtra(), 45, 372.5f);
    }
    
    private void writeEnglishSkills(PdfContentByte cb) {
        
        Map<String, Integer> mapEnglish = getSkillMarksMapBySkillType(DAOConstants.SkillTypeKnowledge);
        
        cb.beginText();
        cb.setFontAndSize(times, 12);
        cb.moveText(183, 558);
        cb.showText(mapEnglish.get(DAOConstants.SkillReading).toString());
        cb.moveText(70, 0);
        cb.showText(mapEnglish.get(DAOConstants.SkillWriting).toString());
        cb.moveText(66, 0);
        cb.showText(mapEnglish.get(DAOConstants.SkillSpeaking).toString());
        cb.endText();
    }
    
    private void writeAdvert(PdfContentByte cb) {
        
        AdvertDAOImpl advertImpl = new AdvertDAOImpl();
        List<Advert> listAdvert = advertImpl.getAdvertsByIdStudent(student.getStudentId());
        
        String advert = "";
        for(int i = 0; i < listAdvert.size(); i++) {
            if(i > 0)
                advert += ", ";
            advert += listAdvert.get(i).getAdvertName();
        }
        writeText(cb, advert, 45, 505);
    }
    
    private void writeNameForSign(PdfContentByte cb) {
        
        float x = (float) (45 + (75 - student.getLastName().length() -
                student.getFirstName().length() -
                student.getMiddleName().length())*3.3f); 
        
        cb.beginText();          
        cb.moveText(x, 248);
        cb.showText(student.getLastName() + " " + student.getFirstName() +
                " " + student.getMiddleName());
        cb.endText();
    }
    
    
    public File createPDF(Student stud) {

        student = stud;
        String fileName = "";
        
        try {
            //Путь к файлу
            fileName = "Form/" + student.getLastName() + 
                    "_" + student.getFirstName() + 
                    "_" + student.getStudentId() + ".pdf";
            
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
            
            writeLanguageSkills(cb);
            writeKnowledgeSkills(cb);
            
            document.newPage();
            PdfImportedPage page2 = writer.getImportedPage(reader, 2); 
            cb.addTemplate(page2, 0, 0);
            
            writeNumber(cb);
            
            writeSkillsOnSecondPage(cb);
            writeEnglishSkills(cb);
            
            writeExperience(cb);
            writeAdvert(cb);
            writeWhy(cb);
            writeExtra(cb);
                         
            writeNameForSign(cb);           
            
            document.close();

        } catch (DocumentException ex) {
            Logger.getLogger(FormToPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FormToPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new File(fileName);
    }
  
}
