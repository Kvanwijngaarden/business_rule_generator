package controller.rest;



import controller.logic.GeneratorService;
import database.dao.DaoService;
import database.dbanalyse.AnalyseFactory;
import database.dbanalyse.IAnalyse;
import database.dbanalyse.OracleAnalyse;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Map;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

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

    @DELETE
    @Path("/deleterule/")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteRule(@FormParam("ruleid") String ruleid){
        try{
            gs.deleteBusinessRule(Integer.parseInt(ruleid));
            return Response.status(200).header("Access-Control-Allow-Origin","*").build();

        } catch (SQLException e) {
            return Response.status(500).header("Access-Control-Allow-Origin","*").build();
        } catch (NumberFormatException|NullPointerException e) {
            return Response.status(400).header("Access-Control-Allow-Origin","*").build();
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
    public Response targetrules() throws SQLException{
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



    @Path("/targetcolumns")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response targetcolumns() throws SQLException{
        DaoService doa = new DaoService();

        /*De map met credentials van de user moet nog meekomen vanuit Apex*/
        Map<String, String> map = new HashMap<String, String>();
        map.put("USER", "tosad_2016_2b_team4_target");
        map.put("PASS", "tosad_2016_2b_team4_target");
        map.put("URL", "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl");

        Map<String, String> result = new HashMap<String, String>();

        IAnalyse a;
        a = AnalyseFactory.getAnalyse("oracle");

        result = a.CollectCollumns(map);

        return Response.ok(result).header("Access-Control-Allow-Origin","*").build();
    }

    @Path("/test")
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Response test() throws SQLException{
        DaoService doa = new DaoService();


        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("company");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("Staff");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("id");
            attr.setValue("1");
            staff.setAttributeNode(attr);

            // firstname elements
            Element firstname = doc.createElement("firstname");
            firstname.appendChild(doc.createTextNode("yong"));
            staff.appendChild(firstname);

            // lastname elements
            Element lastname = doc.createElement("lastname");
            lastname.appendChild(doc.createTextNode("mook kim"));
            staff.appendChild(lastname);

            // nickname elements
            Element nickname = doc.createElement("nickname");
            nickname.appendChild(doc.createTextNode("mkyong"));
            staff.appendChild(nickname);

            // salary elements
            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode("100000"));
            staff.appendChild(salary);


            return Response.ok(doc).header("Access-Control-Allow-Origin","*").build();

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }


        return Response.ok("Error").header("Access-Control-Allow-Origin","*").build();
    }


    @POST
    @Path("/disabletarget/")
    public Response disableTarget(@FormParam("ruleid") String ruleid) {
        //BRDefinitions and BRCredentials
        DaoService doa = new DaoService();

        try {
            /*De map met credentials van de user moet nog meekomen vanuit Apex*/
            Map<String, String> DBCredentials = new HashMap<>();
            DBCredentials.put("USER", "tosad_2016_2b_team4_target");
            DBCredentials.put("PASS", "tosad_2016_2b_team4_target");
            DBCredentials.put("URL", "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl");

            Map BRDefinition = doa.getBRDefinition(Integer.parseInt(ruleid));

            doa.disableBusinessRule(BRDefinition, DBCredentials);

            return Response.status(200).build();

        } catch (SQLException e) {
            return Response.status(500).build();
        } catch (NumberFormatException|NullPointerException e) {
            return Response.status(400).build();
        }

    }

    @POST
    @Path("/enabletarget/")
    public Response enableTarget(@FormParam("ruleid") String ruleid) {
        //BRDefinitions and BRCredentials
        DaoService doa = new DaoService();

        try {
            /*De map met credentials van de user moet nog meekomen vanuit Apex*/
            Map<String, String> DBCredentials = new HashMap<>();
            DBCredentials.put("USER", "tosad_2016_2b_team4_target");
            DBCredentials.put("PASS", "tosad_2016_2b_team4_target");
            DBCredentials.put("URL", "jdbc:oracle:thin:@//ondora02.hu.nl:8521/cursus02.hu.nl");

            Map BRDefinition = doa.getBRDefinition(Integer.parseInt(ruleid));

            doa.enableBusinessRule(BRDefinition, DBCredentials);

            return Response.status(200).build();

        } catch (SQLException e) {
            return Response.status(500).build();
        } catch (NumberFormatException|NullPointerException e) {
            return Response.status(400).build();
        }

    }

    //@POST
    //@Path("/enabletarget")



}
