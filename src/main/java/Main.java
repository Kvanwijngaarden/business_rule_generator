import database.dao.DaoService;

import java.sql.SQLException;


/**
 * Created by kvanwijngaarden on 03/07/2017.
 */
public class Main {

    public static void main(String args[]) throws SQLException{
        DaoService daoService = new DaoService();

        System.out.println(daoService.getBRDefinition(230));

    }
}