/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author lasha.k;
 */
public class Skill {

    private final int id_skill;
    private final String skill_name;
    private final int mark;
    private final String notes;
    private final int id_student;
    private final int id_skill_type;

    public Skill(int id_skill, String skill_name, int mark, String notes,
            int id_student, int id_skill_type) {
        this.id_skill = id_skill;
        this.id_skill_type = id_skill_type;
        this.id_student = id_student;
        this.mark = mark;
        this.notes = notes;
        this.skill_name = skill_name;
    }

    public int getIdSkill() {
        return id_skill;
    }

    public int getIdSkillType() {
        return id_skill_type;
    }

    public int getIdStudent() {
        return id_student;
    }

    public int getMark() {
        return mark;
    }

    public String getNotes() {
        return notes;
    }

    public String getSkillName() {
        return skill_name;
    }

    @Override
    public String toString() {
        StringBuffer sbResult = new StringBuffer();
        sbResult.append("id skill = ");
        sbResult.append(this.id_skill);
        sbResult.append(", mark = ");
        sbResult.append(this.mark);
        sbResult.append(", notes = ");
        sbResult.append(this.notes);
        sbResult.append(", skill_name = ");
        sbResult.append(this.skill_name);

        return sbResult.toString();
    }
}
