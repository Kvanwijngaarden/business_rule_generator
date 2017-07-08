package database.dbanalyse;

import database.jdbc.jdbcFactory;

import java.sql.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JariPC on 3-7-2017.
 */
public class OracleAnalyse implements IAnalyse {

    @Override
    public Map<String, String> CollectCollumns(Map<String, String> DBCredentials) {
        try {
            Map allcolums = new HashMap();
            Statement stmt = null;
            ResultSet rs = null;
            Connection conn = null;

            conn = jdbcFactory.getDB("oracle").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COLUMN_NAME, TABLE_NAME\n" +
                    "FROM all_tab_cols\n" +
                    "WHERE owner = 'TOSAD_2016_2B_TEAM4_TARGET'\n" +
                    "AND TABLE_NAME != 'GRULE'");

            while (rs.next()) {
                allcolums.put(rs.getString(1).toLowerCase(), rs.getString(2).toLowerCase());
            }

            return allcolums;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
