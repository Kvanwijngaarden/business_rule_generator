package controller.rest;



import controller.logic.GeneratorService;
import database.dao.DaoService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by JariPC on 3-7-2017.
 */
@Path("/brg")
public class BusinessRuleService {

    GeneratorService gs = new GeneratorService();

    @GET
    @Path("/insertrules")
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertRules(@QueryParam("IDs") List<String> IDs){

        try{
            List<Integer> intList = new ArrayList();

            for(String s : IDs) {
                intList.add(Integer.valueOf(s));
            }
            gs.InsertTemplate(intList);
            return Response.status(200).build();

        }catch (SQLException e){
            return Response.status(400).build();
        }
    }



    @GET
    @Produces("text/plain")
    public String getBrule() {

        String output = "The brule service is working! Hooray!";
        return output;
    }


    @Path("/targetrules")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response test() throws SQLException{
        DaoService doa = new DaoService();

        /*De map met credentials van de user moet nog meekomen vanuit Apex*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("USER", "tosad_2016_2b_team4_target");
        map.put("PASS", "tosad_2016_2b_team4_target");
        map.put("URL", "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl");

        Map<String, Map<String,String>> result = new HashMap<String, Map<String,String>>();

        result = doa.getTargetRules(map);

        return Response.ok(result).header("Access-Control-Allow-Origin","*").build();

    }
}
