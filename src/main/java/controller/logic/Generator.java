package controller.logic;

import controller.validation.ValidationHandler;
import database.dao.DaoService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by kvanwijngaarden on 27/06/2017.
 */
class Generator {
    private DaoService daoService = new DaoService();
    private ValidationHandler validationHandler = new ValidationHandler();

    /*
     * Generates the template. It searches for keywords and replaces these with values
     * Keywords look like xx{COLUMN_NAME}xx
    */
    String generateTemplate(int brID) throws SQLException {
        Map<String, String> definition = daoService.getBRDefinition(brID);
        String template = daoService.getTemplate(definition);

        for (Map.Entry<String, String> entry : definition.entrySet()) {
            template = template.replaceAll("\\b"+"xx"+entry.getKey()+"xx"+"\\b", entry.getValue());
        }
        return template;
    }

    int InsertTemplate(List<Integer> exList) throws SQLException{
        int error = 0;

        for (int brID : exList){
            Map<String, String> BRDefinition = daoService.getBRDefinition(brID);
            Map<String, String> DBCredentials = daoService.getDBCredentials(BRDefinition);

            if(!validationHandler.Choice(BRDefinition)){
                error = 1;
            }

            if(error == 0) {
                String generatedTemplate = generateTemplate(brID);

                // Activates the template
                daoService.sendBusinessRule(generatedTemplate, DBCredentials);
                // saves business rule definition
                daoService.InsertBRDtoTarget(DBCredentials, BRDefinition);
            }else{
                return 1;
            }


        }

        return 0;
    }

    void UpdateTemplate(int brID) throws SQLException{

        Map<String, String> BRDefinition = daoService.getBRDefinition(brID);
        Map<String, String> DBCredentials = daoService.getDBCredentials(BRDefinition);

        daoService.UpdateBRDtoTarget(DBCredentials, BRDefinition);

    }

    void deleteBusinessRule(int brID) throws SQLException{
        Map<String, String> BRDefinition = daoService.getBRDefinition(brID);
        Map<String, String> DBCredentials = daoService.getDBCredentials(BRDefinition);

        daoService.deleteBusinessRule(BRDefinition, DBCredentials);
        daoService.deleteBRDTarget(DBCredentials, BRDefinition);
        daoService.deleteBRD(BRDefinition);
    }

}
