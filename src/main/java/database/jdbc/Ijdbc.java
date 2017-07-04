package database.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public interface Ijdbc {
    Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException;

    String deleteTrigger(Map<String, String> BRDefinition);
    String deleteConstraint(Map<String, String> BRDefinition);

    String enableTrigger(Map<String, String> BRDefinition);
    String enableConstraint(Map<String, String> BRDefinition);

    String disableTrigger(Map<String, String> BRDefinition);
    String disableConstraint(Map<String, String> BRDefinition);

}
