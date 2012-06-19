/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class SkillsDAOImpl implements SkillsDAO {

    @Override
    public List<Skill> getAllSkillsByTypeIdForStudent(int id_skill_type, int id_student) {
        List<Skill> skills = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        Skill skill = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT " + DAOConstants.SkillsForStudentsTableName + ".id_skill, "
                    + DAOConstants.SkillsTableName + ".skill_name, "
                    + DAOConstants.SkillsForStudentsTableName + ".mark, "
                    + DAOConstants.SkillsForStudentsTableName + ".notes, "
                    + DAOConstants.SkillsForStudentsTableName + ".id_student, "
                    + DAOConstants.SkillsTableName + ".id_skill_type "
                    + "FROM " + DAOConstants.SkillsTableName + ", " + DAOConstants.SkillsForStudentsTableName
                    + " WHERE " + DAOConstants.SkillsForStudentsTableName + ".id_skill"
                    + " = " + DAOConstants.SkillsTableName + ".id_skill "
                    + "AND " + DAOConstants.SkillsForStudentsTableName + ".id_student = ? "
                    + "AND " + DAOConstants.SkillsTableName + ".id_skill_type = ?");
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
            stmtSelect.setInt(2, id_skill_type);
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                skill = new Skill(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getInt(5), res.getInt(6));
                skills.add(skill);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo skill types found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getting all skill types!\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return skills;
    }

    @Override
    public List<SkillsOfType> getAllSkillTypesByIdStudent(int id_student) {
        List<SkillsOfType> skillTypes = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        SkillsOfType sot = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT id_skill_type, skill_type_name FROM ");
            sbSelect.append(DAOConstants.SkillsTypesForStudentsTableName);
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
                sot = new SkillsOfType(res.getInt(1), res.getString(2), id_student);
                skillTypes.add(sot);
                rowsCount++;
            }
            if (rowsCount <= 0) {
                System.out.print("\n\nNo skill types found");
            }
        } catch (Exception e) {
            System.out.print("\n Error while getting all skill types!\n");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return skillTypes;

    }

    @Override
    public boolean deleteAllSkillsByIdStudent(int id_student) {
        PreparedStatement stmtDelete = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbDelete = new StringBuffer();
            sbDelete.append("DELETE FROM ");
            sbDelete.append(DAOConstants.SkillsForStudentsTableName);
            sbDelete.append(" WHERE id_student = ?");
            stmtDelete = conn.prepareStatement(sbDelete.toString());
            stmtDelete.setInt(1, id_student);
            stmtDelete.executeUpdate();
        } catch (SQLException ex) {
            System.out.print("\nSQL exception in deleteAllSkillsForIdStudent");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtDelete);
        }
        return true;
    }

    @Override
    public boolean createSkillsByIdStudent(List<Skill> skills, int id_student) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        Iterator<Skill> itr = skills.iterator();
        StringBuffer sbInsert = new StringBuffer();
        sbInsert.append("INSERT INTO ");
        sbInsert.append(DAOConstants.SkillsForStudentsTableName);
        sbInsert.append(" (id_skill, id_student, mark, notes)");
        sbInsert.append(" VALUES(");
        sbInsert.append("?,?,?,?)");
        try {
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            while (itr.hasNext()) {
                Skill element = itr.next();
                stmtInsert.setInt(1, element.getIdSkill());
                stmtInsert.setInt(2, id_student);
                stmtInsert.setInt(3, element.getMark());
                stmtInsert.setString(4, element.getNotes());
                int rows = stmtInsert.executeUpdate();
                stmtInsert.clearParameters();
                sbInsert.delete(0, sbInsert.length());
                if (rows != 1) {
                    throw new SQLException(
                            "executeUpdate return value: "
                            + rows);
                }

            }
        } catch (SQLException ex) {
            System.out.print("\nSQL exception in createSkillsForIdStudent");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }

        return true;
    }
}
