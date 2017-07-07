package database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DaoService {

    private BRDefinitionDAO definitionDAO = new BRDefinitionDAO();
    private TemplateDAO templateDAO = new TemplateDAO();
    private DBCredentialsDAO credentialsDAO = new DBCredentialsDAO();
    private BRImplementDAO brImplementDAO = new BRImplementDAO();
    private BRDImplementDAO brdImplementDAO = new BRDImplementDAO();
    private TargetRulesDAO targetRulesDAO = new TargetRulesDAO();

    public Map getBRDefinition(int brID) throws SQLException{
        return definitionDAO.getBusinessRuleDefinition(brID);
    }

    public String getTemplate(Map<String, String> BRDefinition) throws SQLException{
        return templateDAO.getTemplate(BRDefinition);
    }

    public Map getDBCredentials(Map<String, String> BRDefinition) throws SQLException{
        return credentialsDAO.getDBCredentials(BRDefinition);
    }

    public void sendBusinessRule(String businessRule, Map<String, String> DBCredentials) throws SQLException{
        brImplementDAO.sendBusinessRule(businessRule, DBCredentials);
    }

    public void deleteBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException{
        brImplementDAO.deleteBusinessRule(BRDefinition, DBCredentials);
    }

    public void disableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException{
        brImplementDAO.disableBusinessRule(BRDefinition, DBCredentials);
    }

    public void enableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException{
        brImplementDAO.enableBusinessRule(BRDefinition, DBCredentials);
    }

    public void InsertBRDtoTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        brdImplementDAO.InsertBRDtoTarget(DBCredentials, BRDefinition);
    }

    public void UpdateBRDtoTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        brdImplementDAO.UpdateBRDtoTarget(DBCredentials, BRDefinition);
    }

    public void deleteBRDTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        brdImplementDAO.deleteBRDTarget(DBCredentials, BRDefinition);
    }

    public Map<String, Map<String, String>> getTargetRules(Map<String, String> DBCredentials) throws SQLException {
        return targetRulesDAO.getTargetRules(DBCredentials);
    }

    public void deleteBRD(Map<String, String> BRDefinition) throws SQLException{
        definitionDAO.deleteBRD(BRDefinition);
    }

//    public ResultSet getTargetRules(Map<String, String> DBCredentials) throws SQLException {
//        return targetRulesDAO.getTargetRules(DBCredentials);
//    }

}
