package database.dao;

import database.Constants;
import database.jdbc.jdbcFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

/**
 * Created by kvanwijngaarden on 04/07/2017.
 */
public class TargetRulesDAO {

    private Connection connection;
    private Statement statement;



    public Map<String, Map<String,String>> getTargetRules(Map<String, String> DBCredentials) throws SQLException{
//    public ResultSet getTargetRules(Map<String, String> DBCredentials) throws SQLException{


        String query = "SELECT * FROM GRULE";
        ResultSet rs = null;

        Map<String, Map<String, String>> targetRules = new HashMap<>();
        connection = jdbcFactory.getDB("oracle").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));


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
