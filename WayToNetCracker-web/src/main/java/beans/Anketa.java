/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor. Здесь по идее должны быть объкты а не все поля
 */
package beans;

import java.io.Serializable;

import javax.inject.Named; 
   // or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped; 
   // or import javax.faces.bean.SessionScoped;
import netcracker.dao.*;
import netcracker.converter.*;
import java.util.*;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.primefaces.event.FileUploadEvent;  
import org.primefaces.model.UploadedFile;

@Named("Anketa")
@SessionScoped
public class Anketa implements Serializable {
    
    // Персональная информация
    private String last_name;
    private String first_name;
    private String middle_name;
    private String vuz=null;
    private int course;
    private String faculty;
    private int studt_end_year;
    private UploadedFile foto;
    
    //Контакты
    private String email1;
    private String email2;
    private String phone1;
    private String extra_contacts;
    
    //Интересы
    private String lernCenter;
    private String workNC;
    private String developer;
    private String chekExtraInterest;//Если выбран + показываем поле для extraInterest
    private String extraInterest;
    private String deepSpec;
    private String diffWork;
    private String managSpec;
    private String selling;
    private String chekExtraTypesInterest;//Если выбран + показываем поле для extraTypesInterest
    private String extraTypesInterest;
    
    //Достоинства
    private int cPlusPlusLevel;
    private int javaLevel;
    private String lang1Name;
    private int lang1Level;
    private String lang2Name;
    private int lang2Level;
    private String lang3Name;
    private int lang3Level;

    private int tecIntenetLevel;
    private int tecOopLevel;
    private int tecEffAlgLevel;
    private int tecBdLevel;
    private int tecWebLevel;
    private int tecWebProgramLevel;
    private int tecGrafLevel;
    private int tecProjLevel;
    
    private String extraItLern;    
    private String experience;
    private int englReadLevel;
    private int engWriteLevel;
    private int engSpeackLevel;
    private String advertising;
    private String whyMe;
    private String extraData;    
    //Соглашение        
    private boolean confirm;
    
    //поля для аякса
    private List faculties=new LinkedList();


    public String getAdvertising() {
        return advertising;
    }

    public void setAdvertising(String advertising) {
        this.advertising = advertising;
    }

    public int getcPlusPlusLevel() {
        return cPlusPlusLevel;
    }

    public void setcPlusPlusLevel(int cPlusPlusLevel) {
        this.cPlusPlusLevel = cPlusPlusLevel;
    }

    public String getChekExtraInterest() {
        return chekExtraInterest;
    }

    public void setChekExtraInterest(String chekExtraInterest) {
        this.chekExtraInterest = chekExtraInterest;
    }

    public String getChekExtraTypesInterest() {
        return chekExtraTypesInterest;
    }

    public void setChekExtraTypesInterest(String chekExtraTypesInterest) {
        this.chekExtraTypesInterest = chekExtraTypesInterest;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getDeepSpec() {
        return deepSpec;
    }

    public void setDeepSpec(String deepSpec) {
        this.deepSpec = deepSpec;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDiffWork() {
        return diffWork;
    }

    public void setDiffWork(String diffWork) {
        this.diffWork = diffWork;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public int getEngSpeackLevel() {
        return engSpeackLevel;
    }

    public void setEngSpeackLevel(int engSpeackLevel) {
        this.engSpeackLevel = engSpeackLevel;
    }

    public int getEngWriteLevel() {
        return engWriteLevel;
    }

    public void setEngWriteLevel(int engWriteLevel) {
        this.engWriteLevel = engWriteLevel;
    }

    public int getEnglReadLevel() {
        return englReadLevel;
    }

    public void setEnglReadLevel(int englReadLevel) {
        this.englReadLevel = englReadLevel;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getExtraInterest() {
        return extraInterest;
    }

    public void setExtraInterest(String extraInterest) {
        this.extraInterest = extraInterest;
    }

    public String getExtraItLern() {
        return extraItLern;
    }

    public void setExtraItLern(String extraItLern) {
        this.extraItLern = extraItLern;
    }

    public String getExtraTypesInterest() {
        return extraTypesInterest;
    }

    public void setExtraTypesInterest(String extraTypesInterest) {
        this.extraTypesInterest = extraTypesInterest;
    }

    public String getExtra_contacts() {
        return extra_contacts;
    }

    public void setExtra_contacts(String extra_contacts) {
        this.extra_contacts = extra_contacts;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public int getJavaLevel() {
        return javaLevel;
    }

    public void setJavaLevel(int javaLevel) {
        this.javaLevel = javaLevel;
    }

    public int getLang1Level() {
        return lang1Level;
    }

    public void setLang1Level(int lang1Level) {
        this.lang1Level = lang1Level;
    }

    public String getLang1Name() {
        return lang1Name;
    }

    public void setLang1Name(String lang1Name) {
        this.lang1Name = lang1Name;
    }

    public int getLang2Level() {
        return lang2Level;
    }

    public void setLang2Level(int lang2Level) {
        this.lang2Level = lang2Level;
    }

    public String getLang2Name() {
        return lang2Name;
    }

    public void setLang2Name(String lang2Name) {
        this.lang2Name = lang2Name;
    }

    public int getLang3Level() {
        return lang3Level;
    }

    public void setLang3Level(int lang3Level) {
        this.lang3Level = lang3Level;
    }

    public String getLang3Name() {
        return lang3Name;
    }

    public void setLang3Name(String lang3Name) {
        this.lang3Name = lang3Name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getLernCenter() {
        return lernCenter;
    }

    public void setLernCenter(String lernCenter) {
        this.lernCenter = lernCenter;
    }

    public String getManagSpec() {
        return managSpec;
    }

    public void setManagSpec(String managSpec) {
        this.managSpec = managSpec;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getSelling() {
        return selling;
    }

    public void setSelling(String selling) {
        this.selling = selling;
    }

    public int getStudt_end_year() {
        return studt_end_year;
    }

    public void setStudt_end_year(int studt_end_year) {
        this.studt_end_year = studt_end_year;
    }

    public int getTecBdLevel() {
        return tecBdLevel;
    }

    public void setTecBdLevel(int tecBdLevel) {
        this.tecBdLevel = tecBdLevel;
    }

    public int getTecEffAlgLevel() {
        return tecEffAlgLevel;
    }

    public void setTecEffAlgLevel(int tecEffAlgLevel) {
        this.tecEffAlgLevel = tecEffAlgLevel;
    }

    public int getTecGrafLevel() {
        return tecGrafLevel;
    }

    public void setTecGrafLevel(int tecGrafLevel) {
        this.tecGrafLevel = tecGrafLevel;
    }

    public int getTecIntenetLevel() {
        return tecIntenetLevel;
    }

    public void setTecIntenetLevel(int tecIntenetLevel) {
        this.tecIntenetLevel = tecIntenetLevel;
    }

    public int getTecOopLevel() {
        return tecOopLevel;
    }

    public void setTecOopLevel(int tecOopLevel) {
        this.tecOopLevel = tecOopLevel;
    }

    public int getTecProjLevel() {
        return tecProjLevel;
    }

    public void setTecProjLevel(int tecProjLevel) {
        this.tecProjLevel = tecProjLevel;
    }

    public int getTecWebLevel() {
        return tecWebLevel;
    }

    public void setTecWebLevel(int tecWebLevel) {
        this.tecWebLevel = tecWebLevel;
    }

    public int getTecWebProgramLevel() {
        return tecWebProgramLevel;
    }

    public void setTecWebProgramLevel(int tecWebProgramLevel) {
        this.tecWebProgramLevel = tecWebProgramLevel;
    }

    public String getVuz() {
        return vuz;
    }

    public void setVuz(String vuz) {
        this.vuz = vuz;
    }

    public String getWhyMe() {
        return whyMe;
    }

    public void setWhyMe(String whyMe) {
        this.whyMe = whyMe;
    }

    public String getWorkNC() {
        return workNC;
    }

    public void setWorkNC(String workNC) {
        this.workNC = workNC;
    }

    public List getFaculties() {
        return faculties;
    }

    public void setFaculties(List faculties) {
        this.faculties = faculties;
    }

    public UploadedFile getFoto() {
        return foto;
    }

    public void setFoto(UploadedFile foto) {
        this.foto = foto;
    }
    
    //метод берущий список вузов
    public List getListVuz(){
        
        StudentDAO a = DAOFactory.getStudentDAO();
        List res = a.getAllUniversities();
        
        return res;
    }        
    
    //метод берущий список факультетов по выбраному вузу
    public void getListFaculty(){
        if(vuz !=null && !vuz.equals(""))  
        {
            StudentDAO a = DAOFactory.getStudentDAO();
            List res = a.getFacultiesByUniverName(vuz);
            faculties=res;
        }
        else  
            faculties.removeAll(faculties);
    }
    
    //берем список реклам      
    public List getListAdvertising(){
        
        List<Advert> a = DAOFactory.getAdvertDAO().getAllAdverts();
        
        List<String> res=netcracker.converter.converter.AdvertNames(a);  
        
        return res;
    } 
    
}
