package database.jdbc;

import java.sql.Connection;
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

    public Connection createConnection(String DB_URL, String USER, String PASS) throws SQLException {
        return null;
    }

}
