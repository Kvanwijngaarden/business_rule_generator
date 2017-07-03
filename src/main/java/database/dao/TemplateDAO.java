package database.dao;

import database.Constants;
import database.jdbc.jdbcFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;


//Deze DAO klasse haalt de template uit de tooldatabase die overeenkomt met de taal en businessrule type van de te genereren businessrule
class TemplateDAO {
    private Connection connection;
    private Statement statement;

    String getTemplate(Map<String, String> brdefinition) throws SQLException{
        String languageID = brdefinition.get("GLANGUAGE_LANG_ID");
        String ruletypeID = brdefinition.get("GRULETYPE_RULETYPE_ID");
        String triggerOrConstraint = brdefinition.get("TRIGGER_STATEMENT");

        String query = "SELECT TEMPLATE_VALUE FROM GTEMPLATE where GLANGUAGE_LANG_ID=" + languageID + " AND GRULETYPE_RULETYPE_ID=" + ruletypeID + " AND TRIGGER_STATEMENT='" + triggerOrConstraint + "'";
        ResultSet rs = null;
        String templateValue = null;
        try {
//            connection = jdbcFactory.getDB("oracle").getToolConnection();


            System.out.println("Template");
            System.out.println("url " + Constants.DB_URL);
            System.out.println("user " + Constants.DB_USER);
            System.out.println("pass " + Constants.DB_PASS);

            connection = jdbcFactory.getDB("oracle").createConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);

            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()){
                    templateValue = rs.getString("TEMPLATE_VALUE");
                }


        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to DataAccess.Database.");
//            throw new SQLException(e);
        }
        finally {
            if (rs != null){
            rs.close();}
            if (statement != null){
                statement.close();}
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");}
    }
        return templateValue;

}
}
