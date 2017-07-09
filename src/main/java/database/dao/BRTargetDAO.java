package database.dao;

import database.jdbc.jdbcFactory;

import java.sql.*;
import java.util.Map;


//This DAO inserts, updates or deletes triggers / constraints to / from target database.
class BRTargetDAO {
    private Connection connection;
    private Statement generatedTemplateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement disableStatement;
    private PreparedStatement enableStatement;

    //apply disabled trigger or constraint to target database
    void sendBusinessRule(String businessRule, Map<String, String> DBCredentials) throws SQLException {

        try{
            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            generatedTemplateStatement = connection.createStatement();

            generatedTemplateStatement.executeQuery(businessRule);
        }
        catch (SQLException e){
            System.out.println(e);
        }

        finally{
            if (generatedTemplateStatement != null){
                generatedTemplateStatement.close();}
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");}
        }
    }

    //disables and deletes trigger or constraint from target database
    void deleteBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException {

        try{
            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            if (BRDefinition.get("TRIGGER_STATEMENT").equals("TRIGGER")){
                disableStatement = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).disableTrigger(BRDefinition));
                deleteStatement  = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).deleteTrigger(BRDefinition));
            }
            else if (BRDefinition.get("TRIGGER_STATEMENT").equals("CONSTRAINT")){
                disableStatement = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).disableConstraint(BRDefinition));
                deleteStatement  = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).deleteConstraint(BRDefinition));
            }

            if (disableStatement != null) {
                disableStatement.executeQuery();
            }
            deleteStatement.executeQuery();
        }
        catch (SQLException e){
            System.out.println(e);
        }
        finally{
            if (deleteStatement != null){
                deleteStatement.close();
            }
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }
    }

    //enable trigger or constraint in target database
    boolean enableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException {
        boolean success = false;
        try {

            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            if (BRDefinition.get("TRIGGER_STATEMENT").equals("TRIGGER")) {
                enableStatement = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).enableTrigger(BRDefinition));
            } else if (BRDefinition.get("TRIGGER_STATEMENT").equals("CONSTRAINT")) {
                enableStatement = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).enableConstraint(BRDefinition));
            }

            if (enableStatement != null) {
                enableStatement.executeQuery();
            }

            success = true;

        } catch (SQLException e){
            System.out.println(e);

            success = false;
        }
        finally{
            if (enableStatement != null){
                enableStatement.close();
            }
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }
        return success;
    }

    //disable trigger or constraint in target database
    void disableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException {
        try {

            connection = jdbcFactory.getDB(DBCredentials.get("TYPE")).createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            if (BRDefinition.get("TRIGGER_STATEMENT").equals("TRIGGER")){
                disableStatement = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).disableTrigger(BRDefinition));
            }
            else if (BRDefinition.get("TRIGGER_STATEMENT").equals("CONSTRAINT")){
                disableStatement = connection.prepareStatement(jdbcFactory.getDB(DBCredentials.get("TYPE")).disableConstraint(BRDefinition));
            }

            if (disableStatement != null) {
                disableStatement.executeQuery();
            }

        } catch (SQLException e){
            System.out.println(e);
        }
        finally{
            if (disableStatement != null){
                disableStatement.close();
            }
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }
    }

}
