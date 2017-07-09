package database.dao;

import java.sql.SQLException;
import java.util.Map;

public class DaoService {

    private BRDToolDAO brdToolDAO = new BRDToolDAO();
    private TemplateDAO templateDAO = new TemplateDAO();
    private DBCredentialsDAO credentialsDAO = new DBCredentialsDAO();
    private BRTargetDAO brTargetDAO = new BRTargetDAO();
    private BRDTargetDAO brdTargetDAO = new BRDTargetDAO();

    public Map getBRDefinition(int brID) throws SQLException{
        return brdToolDAO.getBusinessRuleDefinition(brID);
    }

    public String getTemplate(Map<String, String> BRDefinition) throws SQLException{
        return templateDAO.getTemplate(BRDefinition);
    }

    public Map getDBCredentials(Map<String, String> BRDefinition) throws SQLException{
        return credentialsDAO.getDBCredentials(BRDefinition);
    }

    public void sendBusinessRule(String businessRule, Map<String, String> DBCredentials) throws SQLException{
        brTargetDAO.sendBusinessRule(businessRule, DBCredentials);
    }

    public void deleteBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException{
        brTargetDAO.deleteBusinessRule(BRDefinition, DBCredentials);
    }

    public void disableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException{
        brTargetDAO.disableBusinessRule(BRDefinition, DBCredentials);
    }

    public boolean enableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException{
        return brTargetDAO.enableBusinessRule(BRDefinition, DBCredentials);
    }

    public void InsertBRDtoTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        brdTargetDAO.InsertBRDtoTarget(DBCredentials, BRDefinition);
    }

    public void UpdateBRDtoTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        brdTargetDAO.UpdateBRDtoTarget(DBCredentials, BRDefinition);
    }

    public void deleteBRDTarget(Map<String, String> DBCredentials, Map<String, String> BRDefinition) throws SQLException {
        brdTargetDAO.deleteBRDTarget(DBCredentials, BRDefinition);
    }

    public Map<String, Map<String, String>> getTargetRules(Map<String, String> DBCredentials) throws SQLException {
        return brdTargetDAO.getTargetRules(DBCredentials);
    }

    public void deleteBRD(Map<String, String> BRDefinition) throws SQLException{
        brdToolDAO.deleteBRD(BRDefinition);
    }

    public void setBRDToGenerated(Map<String, String> BRDefinition) throws SQLException{
        brdToolDAO.setBRDToGenerated(BRDefinition);
    }

}
