package controller.rest;

import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by JariPC on 3-7-2017.
 */
@Path("/brule")
public class BusinessRuleService {
    @GET
    @Produces("text/plain")
    public String getBrule() {

        String output = "The brule service is working! Hooray!";
        return output;
    }
}
