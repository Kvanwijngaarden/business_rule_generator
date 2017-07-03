package database.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public class Mysql implements Ijdbc {

    public Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException {
        return null;
    }

    public Connection getToolConnection() throws SQLException {
        return null;
    }

    public Connection getTargetConnection(Map<String, String> DBCredentials) throws SQLException {
        return null;
    }
}
