import controller.logic.GeneratorService;
import database.dao.DaoService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Created by kvanwijngaarden on 03/07/2017.
 */
public class Main {

    public static void main(String args[]) throws SQLException{
        GeneratorService gen = new GeneratorService();

        DaoService doa = new DaoService();



        Map<String, String> map = new HashMap<String, String>();
        map.put("USER", "tosad_2016_2b_team4_target");
        map.put("PASS", "tosad_2016_2b_team4_target");
        map.put("URL", "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl");

        Map<String, Map<String,String>> result = new HashMap<String, Map<String,String>>();

        result = doa.getTargetRules(map);

        for (Map.Entry<String, Map<String, String>> entry : result.entrySet())
        {
            System.out.println("ID = " + entry.getKey() + ", Map = " + entry.getValue());
        }




//
//
//        List<Integer> rules = new ArrayList<>();
//        rules.add(235);
//        rules.add(236);
//        rules.add(237);
//
//        gen.InsertTemplate(rules);

    }
}