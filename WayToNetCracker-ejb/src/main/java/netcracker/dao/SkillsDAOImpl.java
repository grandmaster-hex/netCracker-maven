/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author lasha.k;
 */
public class SkillsDAOImpl implements SkillsDAO {

    @Override
    public List<Skill> getAllSkillsByTypeIdForStudent(int id_skill_type,int id_student) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        return skillTypes;
    }
    
}
