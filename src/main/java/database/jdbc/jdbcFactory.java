package database.jdbc;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public class jdbcFactory {
    public static Ijdbc getDB(String db){
        if(db.equals("mysql"))
        {
            return new Mysql();
        }
        else if(db.equals("oracle"))
        {
            return new Oracle();
        }

        return null;
    }

}
