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
    /**
     * Gets skills for students by id_skill_type and id_student
     * @param id_skill_type
     * @param id_student
     * @return 
     */
    public List<Skill> getAllSkillsByTypeIdForStudent(int id_skill_type,int id_student);
    /**
     * Gets all skill types by id_student
     * @param id_student
     * @return 
     */
    public List<SkillsOfType> getAllSkillTypesByIdStudent(int id_student);
    /**
     * Deletes all skills for one student by id_student
     * @param id_student
     * @return 
     */
    public boolean deleteAllSkillsByIdStudent(int id_student);
    /**
     * Creates skills for one student by id_student
     * @param skills
     * @param id_student
     * @return 
     */
    public boolean createSkillsByIdStudent(List<Skill> skills, int id_student);
}
