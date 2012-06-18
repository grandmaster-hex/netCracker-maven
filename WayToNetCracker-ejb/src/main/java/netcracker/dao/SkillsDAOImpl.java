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
            sbSelect.append("select " + DAOConstants.SkillsForStudentsTableName + ".id_skill, "
                    + DAOConstants.SkillsTableName + ".skill_name, "
                    + DAOConstants.SkillsForStudentsTableName + ".mark, "
                    + DAOConstants.SkillsForStudentsTableName + ".notes, "
                    + DAOConstants.SkillsForStudentsTableName + ".id_student, "
                    + DAOConstants.SkillsTableName + ".id_skill_type "
                    + "from " + DAOConstants.SkillsTableName + ", " + DAOConstants.SkillsForStudentsTableName
                    + " where " + DAOConstants.SkillsForStudentsTableName + ".id_skill"
                    + " = " + DAOConstants.SkillsTableName + ".id_skill "
                    + "and " + DAOConstants.SkillsForStudentsTableName + ".id_student = ? "
                    + "and " + DAOConstants.SkillsTableName + ".id_skill_type = ?");

            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1, id_student);
            stmtSelect.setInt(2, id_skill_type);
            //System.out.print(stmtSelect.toString());
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
    public List<SkillsOfType> getAllSkillTypes(int id_student) {
        List<SkillsOfType> skillTypes = new ArrayList();
        Connection conn = DAOFactory.createConnection();
        PreparedStatement stmtSelect = null;
        ResultSet res = null;
        SkillsOfType sot = null;
        try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("SELECT ID_SKILL_TYPE, SKILL_TYPE_NAME FROM ");
            sbSelect.append(DAOConstants.SkillsTypesForStudentsTableName);
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            //System.out.print(sbSelect.toString());
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
    public boolean deleteAllSkillsForIdStudent(int id_student) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        try {
            StringBuffer sbInsert = new StringBuffer();
            sbInsert.append("DELETE FROM ");
            sbInsert.append(DAOConstants.SkillsForStudentsTableName);
            sbInsert.append(" WHERE id_student = ?");
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            stmtInsert.setInt(1, id_student);
        } catch (SQLException ex) {
            System.out.print("\nSQL exception in deleteAllSkillsForIdStudent");
        } finally {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtInsert);
        }
        return true;
    }

    @Override
    public boolean createSkillsForIdStudent(List<Skill> skills, int id_student) {
        PreparedStatement stmtInsert = null;
        Connection conn = DAOFactory.createConnection();
        Iterator<Skill> itr = skills.iterator();
        StringBuffer sbInsert = new StringBuffer();
        sbInsert.append("insert into ");
        sbInsert.append(DAOConstants.SkillsForStudentsTableName);
        sbInsert.append(" (id_skill, id_student, mark, notes)");
        sbInsert.append(" values(");
        sbInsert.append("?,?,?,?)");
        try {
            stmtInsert = conn.prepareStatement(sbInsert.toString());
            while (itr.hasNext()) {
                Skill element = itr.next();
                stmtInsert.setInt(1, element.getId_skill());
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
