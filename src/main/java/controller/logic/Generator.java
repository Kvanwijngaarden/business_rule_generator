package controller.logic;

import database.dao.DaoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
public class Generator {
    private DaoService daoService = new DaoService();

    String generateTemplate(int brID) throws SQLException {
        Map<String, String> definition = daoService.getBRDefinition(brID);
        String template = daoService.getTemplate(definition);

        for (Map.Entry<String, String> entry : definition.entrySet()) {
            template = template.replaceAll("\\b"+"xx"+entry.getKey()+"xx"+"\\b", entry.getValue());
        }
        return template;
    }


//    public void executeTemplate(int brID, String Action) throws SQLException{
    // inser update delete
    public void InsertTemplate(List<Integer> exList) throws SQLException{


        for (int brID : exList){


            Map<String, String> BRDefinition = daoService.getBRDefinition(brID);


            Map<String, String> DBCredentials = daoService.getDBCredentials(BRDefinition);
            String generatedTemplate = generateTemplate(brID);

            // Activeerd te template
            daoService.sendBusinessRule(generatedTemplate, DBCredentials);
            // bewaard brule definitie
            daoService.InsertBRDtoTarget(DBCredentials, BRDefinition);

        }
    }

    public void UpdateTemplate(int brID) throws SQLException{

        Map<String, String> BRDefinition = daoService.getBRDefinition(brID);
        Map<String, String> DBCredentials = daoService.getDBCredentials(BRDefinition);

        daoService.UpdateBRDtoTarget(DBCredentials, BRDefinition);

    }

    public void deleteBusinessRule(int brID) throws SQLException{
        Map<String, String> BRDefinition = daoService.getBRDefinition(brID);
        Map<String, String> DBCredentials = daoService.getDBCredentials(BRDefinition);
        daoService.deleteBusinessRule(BRDefinition, DBCredentials);
        daoService.deleteBRDTarget(DBCredentials, BRDefinition);
    }

}
