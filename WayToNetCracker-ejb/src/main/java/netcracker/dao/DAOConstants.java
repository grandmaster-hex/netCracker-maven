/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package netcracker.dao;

/**
 *
 * @author lasha.k;
 */
final public class DAOConstants {
        /**
         * temlate for url jdbc:mysql://[host][,failoverhost...][:port]/[database]
         */
        static public final String url="jdbc:mysql://localhost/waytonetcracker?useUnicode=true&characterEncoding=Cp1251";
        /**
         * class name of mysql driver 
         */
        static public final String driver="com.mysql.jdbc.Driver";
        /**
         * user name for mysql db connection
         */
        static public final String user ="root";
        /**
         * password for mysql db user
         */
        static public final String password="13adsf";
        /**
         * table name "Employees"
         */
        static public final String EmpTableName="employees";
        /**
         * table name "Students"
         */
        static public final String StudentsTableName="students";
        /**
         * table name "Students"
         */
        static public final String UniversitiesTableName="universities";
         /**
         * table name "Faculties"
         */
        static public final String FacultiesTableName="faculties";
        /**
         * table name "results"
         */
        static public final String ResultsTableName = "results";
        /**
         * table name "adverts"
         */
        static public final String AdvertsTableName = "adverts";
        /**
         * table name "advertsForStudents"
         */
        static public final String AdvertsForStudentsTableName = "advertsForStudents";
        /**
         * table name "interests"
         */
        static public final String InterestsTableName = "interests";
        /**
         * table name "interestsForStudents"
         */
        static public final String InterestsForStudentsTableName = "interestsForStudents";
        /**
         * table name "intervals"
         */
        static public final String IntervalsTableName = "intervals";
         /**
         * table name "intervalsForStudents"
         */
        static public final String IntervalsForStudentsTableName = "intervalsForStudents";
         /**
         * table name "intervalStatuses"
         */
        static public final String IntervalStatusesTableName = "intervalStatuses";
         /**
         * table name "messages"
         */
        static public final String MessagesTableName = "messages";
        /**
         * table name "roles"
         */
        static public final String RolesTableName = "roles";
        /**
         * table name "skills"
         */
        static public final String SkillsTableName = "skills";
        /**
         * table name "skillsForStudents"
         */
        static public final String SkillsForStudentsTableName = "skillsForStudents";
        /**
         * table name "skillsTypes"
         */
        static public final String SkillsTypesForStudentsTableName = "skillsTypes";
        
	private DAOConstants()
	{
		// this constructor is intentionally private 
	}
}
