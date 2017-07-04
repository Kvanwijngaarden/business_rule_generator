package controller.rest;



import controller.logic.GeneratorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
}