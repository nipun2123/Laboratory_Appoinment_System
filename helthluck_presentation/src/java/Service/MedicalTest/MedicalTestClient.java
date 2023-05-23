/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.MedicalTest;

import Service.Employee.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
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
public class MedicalTestClient {

    private static MedicalTestClient medicalTestClient = null;

    public static synchronized MedicalTestClient getInstance() {
        if (medicalTestClient == null) {
            medicalTestClient = new MedicalTestClient();
        }

        return medicalTestClient;
    }

    private MedicalTestClient() {
    }

//    Test Category
    public Response saveTestCategoryDB(MedicalTestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/testprocess/addtestcat");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response editTestCategoryDB(MedicalTestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/testprocess/edittestcat");

        Response response = target.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public static ArrayList<MedicalTestModel> getAllTestCategory() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<MedicalTestModel>> genericType = new GenericType<ArrayList<MedicalTestModel>>() {
        };

        ArrayList<MedicalTestModel> response = target.path("business").
                path("testprocess/getcat").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    public boolean isTestCategoryExists(@PathParam("category") String category) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("business").
                path("testprocess/testcatexists").
                path(category).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

//    Test
    public Response saveTestDB(MedicalTestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/testprocess/addtest");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(testModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response editTestDB(MedicalTestModel testModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/testprocess/edittest");

        Response response = target.request().put(Entity.entity(testModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public static ArrayList<MedicalTestModel> getAllTest() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<MedicalTestModel>> genericType = new GenericType<ArrayList<MedicalTestModel>>() {
        };

        ArrayList<MedicalTestModel> response = target.path("business").
                path("testprocess/gettest").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    public boolean isTestExists(@PathParam("test") String test) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("business").
                path("testprocess/testexists").
                path(test).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }
    
     public String getPriceFromTest(@PathParam("test") String test) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String response = target.path("business").
                path("testprocess/getprice").
                path(test).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(String.class);
        return response;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_business").build();
    }

    public static void main(String[] args) {
        MedicalTestClient e = new MedicalTestClient();

        MedicalTestModel m = new MedicalTestModel();
        m.setTestCategory("Scan");
        m.setLabNo("Lab BBB");
        m.setMaxCount("10");
        m.setEmpId(11);
        m.setPastTestCategory("Scan");
        System.out.println(e.getPriceFromTest("Head x-tray"));
    }
}
