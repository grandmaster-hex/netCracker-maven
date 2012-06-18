/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.util.List;

/**
 *
 * @author lasha.k;
 */
public interface SkillsDAO {
    
    public List<Skill> getAllSkillsByTypeIdForStudent(int id_skill_type,int id_student);
    public List<SkillsOfType> getAllSkillTypes(int id_student);
    public boolean deleteAllSkillsForIdStudent(int id_student);
    public boolean createSkillsForIdStudent(List<Skill> skills, int id_student);
}
