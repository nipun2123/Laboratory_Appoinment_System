/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.MedicalTest;

import Service.Employee.EmployeeProcess;
import java.net.URI;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author nipun
 */
@Path("/testprocess")
public class MedicalTestProcess {

//    Test Category
    @POST
    @Path("addtestcat")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTestCategoryDB(MedicalTestModel testModel) {

        EmployeeProcess empProcess = new EmployeeProcess();
        testModel.setEmpId(empProcess.getIdFromNameDB(testModel.getEmpName()));

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/test/addtestcatdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("edittestcat")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editTestCategoryDB(MedicalTestModel testModel) {

        EmployeeProcess empProcess = new EmployeeProcess();
        testModel.setEmpId(empProcess.getIdFromNameDB(testModel.getEmpName()));

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/test/edittestcatdb");

        Response response = target.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));

        return response;
    }

    @GET
    @Path("getcat")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MedicalTestModel> getTestCategoryFromDB() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<MedicalTestModel>> genericType = new GenericType<ArrayList<MedicalTestModel>>() {
        };

        ArrayList<MedicalTestModel> response = target.path("data").
                path("test/gettestCatdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    @GET
    @Path("testcatexists/{category}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestCategoryExistsFromDB(@PathParam("category") String category) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("data").
                path("test/testcatexistsdb").
                path(category).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

//    Test
    @POST
    @Path("addtest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveTestDB(MedicalTestModel testModel) {

        testModel.setTestCategoryId(getCategoryIdFromNameDB(testModel.getTestCategory()));

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/test/addtestdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("edittest")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editTestDB(MedicalTestModel testModel) {

        testModel.setTestCategoryId(getCategoryIdFromNameDB(testModel.getTestCategory()));

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/test/edittestdb");

        Response response = target.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));

        return response;
    }

    @GET
    @Path("gettest")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MedicalTestModel> getTestFromDB() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<MedicalTestModel>> genericType = new GenericType<ArrayList<MedicalTestModel>>() {
        };

        ArrayList<MedicalTestModel> response = target.path("data").
                path("test/gettestdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    @GET
    @Path("testexists/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestExistsFromDB(@PathParam("test") String test) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("data").
                path("test/testexistsdb").
                path(test).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

    @GET
    @Path("getcatid/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getCategoryIdFromNameDB(@PathParam("category") String category) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        int response = target.path("data").
                path("test/getcatiddb").
                path(category).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(Integer.class);
        return response;
    }

    @GET
    @Path("gettestid/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getTestIdFromNameDB(@PathParam("test") String test) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        int response = target.path("data").
                path("test/gettestiddb").
                path(test).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(Integer.class);
        return response;
    }

    @GET
    @Path("getcat/{test}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategoryFromTestDB(@PathParam("test") String test) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String response = target.path("data").
                path("test/getcatdb").
                path(test).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);
        return response;
    }

    @GET
    @Path("getprice/{test}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPriceFromTestDB(@PathParam("test") String test) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String response = target.path("data").
                path("test/getpricedb").
                path(test).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);
        return response;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_data").build();
    }

    public static void main(String[] args) {

        MedicalTestModel m = new MedicalTestModel();
        m.setTestCategory("x-ray");
        m.setLabNo("Lab C");
        m.setMaxCount("30");
        m.setEmpName("Divya Nimsara Sitinamaluwa");

        System.out.println(new MedicalTestProcess().getPriceFromTestDB("Head x-tray"));
    }
}
