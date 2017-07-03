package database.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public class Oracle implements Ijdbc{

    private static Oracle instance = new Oracle();
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";

    public Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException{
        Connection conn = null;

        try {Class.forName(JDBC_DRIVER);}
        catch(ClassNotFoundException e) {e.printStackTrace();}

        try {conn = DriverManager.getConnection(DB_URL, USER, PASS);}
        catch (SQLException e) {e.printStackTrace();}

        return conn;
    }

}
