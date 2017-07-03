import controller.logic.GeneratorService;
import database.dao.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by kvanwijngaarden on 03/07/2017.
 */
public class Main {

    public static void main(String args[]) throws SQLException{
        GeneratorService gen = new GeneratorService();


        List<Integer> rules = new ArrayList<>();
        rules.add(235);
        rules.add(236);
        rules.add(237);

        gen.InsertTemplate(rules);

    }
}