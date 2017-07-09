package database.dao;

import database.jdbc.jdbcFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

//This DAO inserts, updates or deletes businessrule definition to / from target database
class BRDTargetDAO {
    private Connection connection;
    private PreparedStatement BRDStatement;
    private PreparedStatement BRDDeleteStatement;
    private Statement statement;

    /* Inserts business rule definition into target database.*/
    void InsertBRDtoTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {

        try {
            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            BRDStatement = connection.prepareStatement("INSERT INTO GRULE (RULE_ID, DESCRIPTION, NAME, TARGET_TABLE, TARGET_COLUMN, TRIGGER_EVENT, OPERATOR, VALUE, VALUE2, COMPARE_TABLE, COMPARE_COLUMN, TRIGGER_ON, TRIGGER_STATEMENT, ISACTIVE, GCUSTOMER_CUS_ID, GRULETYPE_RULETYPE_ID, GLANGUAGE_LANG_ID) VALUES ('" + BRDefinition.get("RULE_ID") + "',?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            BRDStatement.setString(1, BRDefinition.get("DESCRIPTION"));
            BRDStatement.setString(2, BRDefinition.get("NAME"));
            BRDStatement.setString(3, BRDefinition.get("TARGET_TABLE"));
            BRDStatement.setString(4, BRDefinition.get("TARGET_COLUMN"));
            BRDStatement.setString(5, BRDefinition.get("TRIGGER_EVENT"));
            BRDStatement.setString(6, BRDefinition.get("OPERATOR"));
            BRDStatement.setString(7, BRDefinition.get("VALUE"));
            BRDStatement.setString(8, BRDefinition.get("VALUE2"));
            BRDStatement.setString(9, BRDefinition.get("COMPARE_TABLE"));
            BRDStatement.setString(10, BRDefinition.get("COMPARE_COLUMN"));
            BRDStatement.setString(11, BRDefinition.get("TRIGGER_ON"));
            BRDStatement.setString(12, BRDefinition.get("TRIGGER_STATEMENT"));
            BRDStatement.setInt(13, 1);
            BRDStatement.setInt(14, Integer.parseInt(BRDefinition.get("GCUSTOMER_CUS_ID")));
            BRDStatement.setInt(15, Integer.parseInt(BRDefinition.get("GRULETYPE_RULETYPE_ID")));


            BRDStatement.executeQuery();
        } finally {
            if (BRDStatement != null) {
                BRDStatement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }

    }

    /*Updates business rule definition in target database.*/
    void UpdateBRDtoTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {

        try {
            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            BRDStatement = connection.prepareStatement("UPDATE GRULE SET DESCRIPTION = ?, NAME = ?, TARGET_TABLE = ?, TARGET_COLUMN = ?, TRIGGER_EVENT = ?, OPERATOR = ?, VALUE = ?, VALUE2 = ?, COMPARE_TABLE = ?, COMPARE_COLUMN = ?, TRIGGER_ON = ?, TRIGGER_STATEMENT = ?, ISACTIVE = ?, GCUSTOMER_CUS_ID = ?, GRULETYPE_RULETYPE_ID = ?, GLANGUAGE_LANG_ID = ? WHERE NAME= '" + BRDefinition.get("NAME") + "'");

            BRDStatement.setString(1, BRDefinition.get("DESCRIPTION"));
            BRDStatement.setString(2, BRDefinition.get("NAME"));
            BRDStatement.setString(3, BRDefinition.get("TARGET_TABLE"));
            BRDStatement.setString(4, BRDefinition.get("TARGET_COLUMN"));
            BRDStatement.setString(5, BRDefinition.get("TRIGGER_EVENT"));
            BRDStatement.setString(6, BRDefinition.get("OPERATOR"));
            BRDStatement.setString(7, BRDefinition.get("VALUE"));
            BRDStatement.setString(8, BRDefinition.get("VALUE2"));
            BRDStatement.setString(9, BRDefinition.get("COMPARE_TABLE"));
            BRDStatement.setString(10, BRDefinition.get("COMPARE_COLUMN"));
            BRDStatement.setString(11, BRDefinition.get("TRIGGER_ON"));
            BRDStatement.setString(12, BRDefinition.get("TRIGGER_STATEMENT"));
            BRDStatement.setInt(13, 1);
            BRDStatement.setInt(14, Integer.parseInt(BRDefinition.get("GCUSTOMER_CUS_ID")));
            BRDStatement.setInt(15, Integer.parseInt(BRDefinition.get("GRULETYPE_RULETYPE_ID")));


            BRDStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (BRDStatement != null) {
                BRDStatement.close();
            }
            if (connection != null) {
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }

    }

    /*Deletes business rule definition from target database.*/
    void deleteBRDTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        try {
            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

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

    /*Fetches all business rule definitions from the target database*/
    Map<String, Map<String,String>> getTargetRules(Map<String, String> DBCredentials) throws SQLException{

        String query = "SELECT * FROM GRULE";
        ResultSet rs = null;

        Map<String, Map<String, String>> targetRules = new HashMap<>();
        connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

        statement = connection.createStatement();
        rs = statement.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        while (rs.next()) {
            Map<String, String> BRDef = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                if (rs.getString(i) != null) {
                    BRDef.put(columnName, rs.getString(i));
                }
            }
            targetRules.put("ID" + rs.getString("RULE_ID"), BRDef);

        }

        return targetRules;
    }

}
