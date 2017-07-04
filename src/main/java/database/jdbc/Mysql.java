package database.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */

/*
    Host: 83.87.212.114
    User: root
    Pass: mynewpassword
    Port: 3306
    Database: mysqltarget

 */

public class Mysql implements Ijdbc {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException {
        Connection conn = null;

        try {Class.forName(JDBC_DRIVER);}
        catch(ClassNotFoundException e) {e.printStackTrace();}

        try {conn = DriverManager.getConnection(DB_URL, USER, PASS);}
        catch (SQLException e) {e.printStackTrace();}

        return conn;
    }

    @Override
    public String deleteTrigger(Map<String, String> BRDefinition) {
        return null;
    }

    @Override
    public String deleteConstraint(Map<String, String> BRDefinition) {
        return null;
    }

    @Override
    public String enableTrigger(Map<String, String> BRDefinition) {
        return null;
    }

    @Override
    public String enableConstraint(Map<String, String> BRDefinition) {
        return null;
    }

    @Override
    public String disableTrigger(Map<String, String> BRDefinition) {
        return null;
    }

    @Override
    public String disableConstraint(Map<String, String> BRDefinition) {
        return null;
    }


}
