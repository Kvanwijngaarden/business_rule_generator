package database.jdbc;

import java.sql.Connection;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public interface Ijdbc {
    Connection createConnexction();
    Connection getToolDatabase();
    Connection getTargetDatabase();
}
