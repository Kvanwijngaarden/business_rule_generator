package database.dao;

import database.jdbc.jdbcFactory;

import java.sql.*;
import java.util.Map;


//This DAO inserts, updates or deletes triggers / constraints to / from target DataAccess.Database
class BRImplementDAO {
    private Connection connection;
    private Statement generatedTemplateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement disableStatement;
    private PreparedStatement enableStatement;

    //apply trigger or constraint to target database
    void sendBusinessRule(String businessRule, Map<String, String> DBCredentials) throws SQLException {

        try{
//            connection = jdbcFactory.getDB("oracle").getTargetConnection(DBCredentials);

            System.out.println("BRIMPL SEND");
            System.out.println("url " + DBCredentials.get("URL"));
            System.out.println("user " + DBCredentials.get("USER"));
            System.out.println("pass " + DBCredentials.get("PASS"));

            connection = jdbcFactory.getDB("oracle").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));



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

    //delete trigger or constraint from target database
    void deleteBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException {

        try{
            connection = jdbcFactory.getDB("oracle").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            if (BRDefinition.get("TRIGGER_STATEMENT").equals("TRIGGER")){
//                disableStatement = connection.prepareStatement("DROP TRIGGER " + BRDefinition.get("NAME") + " DISABLE");
//                deleteStatement = connection.prepareStatement("DROP TRIGGER " + BRDefinition.get("NAME"));
                disableStatement = connection.prepareStatement(jdbcFactory.getDB("oracle").disableTrigger(BRDefinition));
                deleteStatement  = connection.prepareStatement(jdbcFactory.getDB("oracle").deleteTrigger(BRDefinition));
            }
            else if (BRDefinition.get("TRIGGER_STATEMENT").equals("CONSTRAINT")){
//                disableStatement = connection.prepareStatement("ALTER TABLE" + BRDefinition.get("TARGET_TABLE") +" DISABLE CONSTRAINT " + BRDefinition.get("NAME"));
//                deleteStatement = connection.prepareStatement("ALTER TABLE" + BRDefinition.get("TARGET_TABLE") +" DROP CONSTRAINT " + BRDefinition.get("NAME"));
                disableStatement = connection.prepareStatement(jdbcFactory.getDB("oracle").disableConstraint(BRDefinition));
                deleteStatement  = connection.prepareStatement(jdbcFactory.getDB("oracle").deleteConstraint(BRDefinition));
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

    //enable trigger or constraint from target database
    void enableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException {
        try {

            connection = jdbcFactory.getDB("oracle").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            if (BRDefinition.get("TRIGGER_STATEMENT").equals("TRIGGER")) {
                enableStatement = connection.prepareStatement(jdbcFactory.getDB("oracle").enableTrigger(BRDefinition));
            } else if (BRDefinition.get("TRIGGER_STATEMENT").equals("CONSTRAINT")) {
                enableStatement = connection.prepareStatement(jdbcFactory.getDB("oracle").enableConstraint(BRDefinition));
            }

            if (enableStatement != null) {
                enableStatement.executeQuery();
            }

        } catch (Exception e){
            System.out.println(e);
        }

//        } catch (SQLException e){
//            System.out.println(e);
//        }
        finally{
            if (enableStatement != null){
                enableStatement.close();
            }
            if (connection != null){
                connection.close();
                System.out.println("Connection to database closed.");
            }
        }
    }

    //disable trigger or constraint from target database
    void disableBusinessRule(Map<String, String> BRDefinition, Map<String, String> DBCredentials) throws SQLException {
        try {

            connection = jdbcFactory.getDB("oracle").createConnection(DBCredentials.get("URL"), DBCredentials.get("USER"), DBCredentials.get("PASS"));

            if (BRDefinition.get("TRIGGER_STATEMENT").equals("TRIGGER")){
                disableStatement = connection.prepareStatement(jdbcFactory.getDB("oracle").disableTrigger(BRDefinition));
            }
            else if (BRDefinition.get("TRIGGER_STATEMENT").equals("CONSTRAINT")){
                disableStatement = connection.prepareStatement(jdbcFactory.getDB("oracle").disableConstraint(BRDefinition));
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
