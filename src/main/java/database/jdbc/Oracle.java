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

//    public Oracle() {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException{
        Connection conn = null;

        try {Class.forName(JDBC_DRIVER);}
        catch(ClassNotFoundException e) {e.printStackTrace();}

        try {conn = DriverManager.getConnection(DB_URL, USER, PASS);}
        catch (SQLException e) {e.printStackTrace();}

        return conn;
    }



    public Connection getToolConnection() throws SQLException{
        String DB_URL = "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl";
        String USER = "tosad_2016_2b_team4";
        String PASS = "tosad_2016_2b_team4";

        return instance.createConnection(DB_URL, USER, PASS);
    }

    public Connection getTargetConnection(Map<String, String> DBCredentials) throws SQLException{
        String DB_URL = DBCredentials.get("DB_URL");
        String USER = DBCredentials.get("USER");
        String PASS = DBCredentials.get("PASS");
        return instance.createConnection(DB_URL, USER, PASS);
    }
}
