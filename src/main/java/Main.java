import controller.logic.GeneratorService;
import database.dao.DaoService;
import database.jdbc.jdbcFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




/**
 * Created by kvanwijngaarden on 03/07/2017.
 */
public class Main {

    public static Connection connection;
    public static PreparedStatement statement;

    public static void main(String args[]) throws SQLException{
        GeneratorService gen = new GeneratorService();

        DaoService doa = new DaoService();


        Map<String, String> map = new HashMap<String, String>();
        map.put("USER", "tosad_2016_2b_team4_target");
        map.put("PASS", "tosad_2016_2b_team4_target");
        map.put("URL", "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl");

        Map<String, String> test = new HashMap<>();

        test = doa.getBRDefinition(242);



        System.out.println(test);

        System.out.println("1");
        connection = jdbcFactory.getDB("oracle").createConnection(map.get("URL"), map.get("USER"), map.get("PASS"));

        System.out.println("2");
        statement = connection.prepareStatement(jdbcFactory.getDB("oracle").enableConstraint(test));

        System.out.println("3");
        statement.executeQuery();




    }
}