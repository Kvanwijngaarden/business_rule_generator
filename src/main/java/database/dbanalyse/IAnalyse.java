package database.dbanalyse;

import java.util.Map;

/**
 * Created by JariPC on 3-7-2017.
 */
public interface IAnalyse {
    Map<String, String> CollectCollumns(Map<String, String> DBCredentials);
}
