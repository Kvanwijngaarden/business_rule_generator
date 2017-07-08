package database.dao;

import database.Constants;
import database.jdbc.jdbcFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

//This DAO fetches the users' target database credentials
class DBCredentialsDAO {
    private Connection connection;
    private Statement statement;

    Map getDBCredentials(Map<String, String> brdefinition) throws SQLException {
        String customerID = brdefinition.get("GCUSTOMER_CUS_ID");

        String query = "SELECT DATABASE_USERNAME, DATABASE_PASSWORD, CONNECTION_STRING, DATABASE_TYPE FROM GCUSTOMER where CUS_ID='" + customerID + "'";
        ResultSet rs = null;
        Map<String, String> DBCredentials = new HashMap();

        try{
            connection = jdbcFactory.getDB("oracle").createConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);

            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while (rs.next()) {
                DBCredentials.put("USER", rs.getString("DATABASE_USERNAME"));
                DBCredentials.put("PASS", rs.getString("DATABASE_PASSWORD"));
                DBCredentials.put("URL", rs.getString("CONNECTION_STRING"));
                DBCredentials.put("TYPE", rs.getString("DATABASE_TYPE"));
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to DataAccess.Database.");
            e.printStackTrace();
        }
        finally {
            if (rs != null){
                rs.close();}
            if (statement != null){
                statement.close();}
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }
        return DBCredentials;
    }
}