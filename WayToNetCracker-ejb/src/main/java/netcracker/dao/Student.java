/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Blob;
import java.util.List;

/**
 *
 * @author lastride
 */
public class Student {

    private final int id_student;
    private final String first_name;
    private final String last_name;
    private final String middle_name;
    private final int course;
    private final java.sql.Date study_end_year;
    private final int id_faculty;
    private final String email1;
    private final String email2;
    private final String phone1;
    private final List<SkillsOfType> skills;
    private final List<Interest> interests;
    private final String extra_contacts;
    private final String why;
    private final String experience;
    private final String extra;
    private final java.sql.Date reg_date;
    private final Blob photo;

    public Student(int id_student, String first_name, String last_name, String middle_name,
            int course, java.sql.Date study_end_year, int id_faculty, String email1,
            String email2, String phone1, String extra_contacts,
            String why, String experience, String extra, Blob photo) {
        this.id_student = id_student;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.course = course;
        this.study_end_year = study_end_year;
        this.id_faculty = id_faculty;
        this.interests = DAOFactory.getInterestDAO().getStudentInterestsById(id_student);
        this.skills = DAOFactory.getSkillsDAO().getAllSkillTypesByIdStudent(id_student);
        this.email1 = email1;
        this.email2 = email2;
        this.phone1 = phone1;
        this.extra_contacts = extra_contacts;
        this.why = why;
        this.experience = experience;
        this.extra = extra;
        this.reg_date = new java.sql.Date(System.currentTimeMillis());
        this.photo = photo;

    }
    public Student(String first_name, String last_name, String middle_name,
            int course, java.sql.Date study_end_year, int id_faculty, String email1,
            String email2, String phone1, String extra_contacts,
            String why, String experience, String extra, Blob photo) {
        this.id_student = 0;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.course = course;
        this.study_end_year = study_end_year;
        this.id_faculty = id_faculty;
        this.interests = null;
        this.skills = null;
        this.email1 = email1;
        this.email2 = email2;
        this.phone1 = phone1;
        this.extra_contacts = extra_contacts;
        this.why = why;
        this.experience = experience;
        this.extra = extra;
        this.reg_date = new java.sql.Date(System.currentTimeMillis());
        this.photo = photo;

    }

    public int getStudentId() {
        return this.id_student;
    }

    public List<Interest> getInterests() {
        return this.interests;
    }

    public List<SkillsOfType> getSkills() {
        return this.skills;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public String getMiddleName() {
        return this.middle_name;
    }

    public int getCourse() {
        return this.course;
    }

    public java.sql.Date getStudyEndYear() {
        return this.study_end_year;
    }

    public int getIdFaculty() {
        return this.id_faculty;
    }

    public String getEmail1() {
        return this.email1;
    }

    public String getEmail2() {
        return this.email2;
    }

    public String getPhone1() {
        return this.phone1;
    }

    public String getExtraContacts() {
        return this.extra_contacts;
    }

    public String getWhy() {
        return this.why;
    }

    public String getExperience() {
        return this.experience;
    }

    public String getExtra() {
        return this.extra;
    }
    
    public java.sql.Date getRegDate() {
        return this.reg_date;
    }

    public Blob getPhoto() {
        return this.photo;
    }

    public String toString() {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("first name = ");
        sbResult.append(this.first_name);
        sbResult.append(", last name = ");
        sbResult.append(this.last_name);
        sbResult.append(", middle name = ");
        sbResult.append(this.middle_name);
        sbResult.append(", course = ");
        sbResult.append(this.course);
        sbResult.append(", studyEndYear = ");
        sbResult.append(this.study_end_year);
        sbResult.append(", id_faculty = ");
        sbResult.append(this.id_faculty);
        sbResult.append(", email1 = ");
        sbResult.append(this.email1);
        sbResult.append(", email2 = ");
        sbResult.append(this.email2);
        sbResult.append(", phone1 = ");
        sbResult.append(this.phone1);
        sbResult.append(", extra contacts = ");
        sbResult.append(this.extra_contacts);
        sbResult.append(", why = ");
        sbResult.append(this.why);
        sbResult.append(", experience = ");
        sbResult.append(this.experience);
        sbResult.append(", extra = ");
        sbResult.append(this.extra);
        sbResult.append(", reg_date = ");
        sbResult.append(this.reg_date);
        sbResult.append(", photo = ");
        sbResult.append(this.photo);

        return sbResult.toString();
    }
}
