package database.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public class Oracle implements Ijdbc{

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException{
        Connection conn = null;

        try {Class.forName(JDBC_DRIVER);}
        catch(ClassNotFoundException e) {e.printStackTrace();}

        try {conn = DriverManager.getConnection(DB_URL, USER, PASS);}
        catch (SQLException e) {e.printStackTrace();}

        return conn;
    }

    @Override
    public String deleteTrigger(Map<String, String> BRDefinition) {
        return "DROP TRIGGER " + BRDefinition.get("NAME");
    }

    @Override
    public String deleteConstraint(Map<String, String> BRDefinition) {
        return "ALTER TABLE  " + BRDefinition.get("TARGET_TABLE") +" DROP CONSTRAINT " + BRDefinition.get("NAME");
    }

    @Override
    public String enableTrigger(Map<String, String> BRDefinition) {
        return "ALTER TRIGGER " + BRDefinition.get("NAME") + " ENABLE";
    }

    @Override
    public String enableConstraint(Map<String, String> BRDefinition) {
        return "ALTER TABLE " + BRDefinition.get("TARGET_TABLE") +" ENABLE CONSTRAINT " + BRDefinition.get("NAME");
    }

    @Override
    public String disableTrigger(Map<String, String> BRDefinition) {
        return "ALTER TRIGGER " + BRDefinition.get("NAME") + " DISABLE";
    }

    @Override
    public String disableConstraint(Map<String, String> BRDefinition) {
        return "ALTER TABLE " + BRDefinition.get("TARGET_TABLE") + " DISABLE CONSTRAINT " + BRDefinition.get("NAME");
    }


}
