package database.dbanalyse;

/**
 * Created by JariPC on 4-7-2017.
 */
public class AnalyseFactory {
    public static IAnalyse getAnalyse(String db){
        if(db.equals("mysql"))
        {
            return new MysqlAnalyse();
        }
        else if(db.equals("oracle"))
        {
            return new OracleAnalyse();
        }

        return null;
    }
}
