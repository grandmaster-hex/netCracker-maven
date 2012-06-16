/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class SkillsDAOImpl implements SkillsDAO {

   @Override
    public List<Skill> getAllSkillsByTypeIdForStudent(int id_skill_type,int id_student) {
       List<Skill> skills = new ArrayList();
       Connection conn = DAOFactory.createConnection();
       PreparedStatement stmtSelect = null;
       ResultSet res = null;
       Skill skill = null;
       try {
            StringBuffer sbSelect = new StringBuffer();
            sbSelect.append("select "+DAOConstants.SkillsForStudentsTableName+".id_skill, "+
                    DAOConstants.SkillsTableName+".skill_name, "+
                    DAOConstants.SkillsForStudentsTableName+".mark, "+
                    DAOConstants.SkillsForStudentsTableName+".notes, "+
                    DAOConstants.SkillsForStudentsTableName+".id_student, "+
                    DAOConstants.SkillsTableName+".id_skill_type "+
                    "from "+DAOConstants.SkillsTableName+", "+DAOConstants.SkillsForStudentsTableName+
                    " where "+DAOConstants.SkillsForStudentsTableName+".id_skill"+
                    " = "+DAOConstants.SkillsTableName+".id_skill "+
                    "and "+DAOConstants.SkillsForStudentsTableName+".id_student = ? "+
                    "and "+DAOConstants.SkillsTableName+".id_skill_type = ?");
            
            stmtSelect = conn.prepareStatement(sbSelect.toString());
            stmtSelect.setInt(1,id_student);
            stmtSelect.setInt(2,id_skill_type);
            //System.out.print(stmtSelect.toString());
            res = stmtSelect.executeQuery();
            int rowsCount = 0;
            while (res.next()) {
               skill = new Skill(res.getInt(1), res.getString(2), res.getInt(3), res.getString(4), res.getInt(5), res.getInt(6));
               skills.add(skill);
               rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo skill types found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting all skill types!\n");
        }
         finally
        {
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
               sot = new SkillsOfType(res.getInt(1),res.getString(2),id_student);
               skillTypes.add(sot);
               rowsCount++;
            }
            if (rowsCount<=0){
                System.out.print("\n\nNo skill types found");
            }
        }
        catch (Exception e){
            System.out.print("\n Error while getting all skill types!\n");
        }
          finally
        {
            DAOFactory.closeConnection(conn);
            DAOFactory.closeStatement(stmtSelect);
        }
        return skillTypes;
        
    }
    
}
