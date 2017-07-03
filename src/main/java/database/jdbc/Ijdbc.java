package database.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public interface Ijdbc {
    Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException;
    Connection getToolConnection() throws SQLException;
    Connection getTargetConnection(Map<String, String> DBCredentials) throws SQLException;
}
