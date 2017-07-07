package controller.logic;

import java.sql.SQLException;
import java.util.List;

public class GeneratorService {
    private Generator generator = new Generator();

    public String generateTemplate(int brID) throws SQLException{
        return generator.generateTemplate(brID);
    }

    public int InsertTemplate(List<Integer> brID) throws SQLException{
        return generator.InsertTemplate(brID);
    }

    public void UpdateTemplate(int brID) throws SQLException{
        generator.UpdateTemplate(brID);
    }

    public void deleteBusinessRule(int brID) throws SQLException{
        generator.deleteBusinessRule(brID);
    }

}
