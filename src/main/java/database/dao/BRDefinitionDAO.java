package database.dao;

import database.Constants;
import database.jdbc.jdbcFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


//This DAO fetches the businessrule definitions for the rule to be generated
class BRDefinitionDAO {
    private Connection connection;
    private Statement statement;
    private PreparedStatement BRDDeleteStatement;

    Map getBusinessRuleDefinition(int brID) throws SQLException {
        String query = "SELECT * FROM GRULE WHERE RULE_ID = " + brID;
        ResultSet rs = null;
        Map<String, String> BRDef = new HashMap();

        try{
//            connection = jdbcFactory.getDB("oracle").getToolConnection();

            System.out.println("Definition");
            System.out.println("url " + Constants.DB_URL);
            System.out.println("user " + Constants.DB_USER);
            System.out.println("pass " + Constants.DB_PASS);
            connection = jdbcFactory.getDB("oracle").createConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);

            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    if (rs.getString(i) != null) {
                        BRDef.put(columnName, rs.getString(i));
                    }
                }
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
        return BRDef;
    }

    void deleteBRD(Map<String, String> BRDefinition) throws SQLException {
        try {

            connection = jdbcFactory.getDB("oracle").createConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);

            BRDDeleteStatement = connection.prepareStatement("DELETE FROM GRULE WHERE NAME = '" + BRDefinition.get("NAME") + "'");
            BRDDeleteStatement.executeQuery();
        } finally {
            if (BRDDeleteStatement != null) {
                BRDDeleteStatement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }

    }
}
