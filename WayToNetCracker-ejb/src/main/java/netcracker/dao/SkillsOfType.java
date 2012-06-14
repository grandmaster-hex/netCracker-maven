/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class SkillsOfType {    
    
    private final int id_skill_type;
    private final String skill_type_name;
    private List<Skill> skills;
    
    public SkillsOfType(int id_skill_type, String skill_type_name,int id_student){
        if (id_skill_type<=0){
            throw new IllegalArgumentException("id skill type parameter");
        }
        if (skill_type_name == null){
            throw new IllegalArgumentException("skill type name parameter");
        }
        this.id_skill_type= id_skill_type;
        this.skill_type_name = skill_type_name;
        this.skills =DAOFactory.getSkillsDAO().getAllSkillsByTypeIdForStudent(id_student);
    }
    
    public int getId_skill_type() {
        return id_skill_type;
    }

    public String getSkill_type_name() {
        return skill_type_name;
    }
    
   
    
    @Override
    public String toString(){
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("id_skill_type = ");
        sbResult.append(id_skill_type);
        sbResult.append(" ,skill_type_name = ");
        sbResult.append(skill_type_name);
        
        return sbResult.toString();
    }
}
