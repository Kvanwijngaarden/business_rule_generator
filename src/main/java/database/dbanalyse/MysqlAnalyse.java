package database.dbanalyse;

import database.jdbc.jdbcFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JariPC on 3-7-2017.
 */
public class MysqlAnalyse implements IAnalyse {

    @Override
    public Map<String, String> CollectCollumns(Map<String, String> DBCredentials) {
        try {
            Map allcolls = new HashMap();
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = null;

            conn = jdbcFactory.getDB("mysql").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select COLUMN_NAME, TABLE_NAME from information_schema.columns\n" +
                    "where table_schema = 'mysqltarget'\n" +
                    "order by table_name,ordinal_position");

            while (rs.next()) {
                allcolls.put(rs.getString(1).toLowerCase(), rs.getString(2).toLowerCase());
            }

            return allcolls;

        } catch (SQLException e) {
            // Do something with it!
        }
        return null;
    }

}
