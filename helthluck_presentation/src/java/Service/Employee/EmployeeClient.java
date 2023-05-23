/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Employee;

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
public class EmployeeClient {

    private static EmployeeClient empClient = null;

    public static synchronized EmployeeClient getInstance() {
        if (empClient == null) {
            empClient = new EmployeeClient();
        }

        return empClient;
    }

    private EmployeeClient() {
    }

    public Response saveEmpDB(EmployeeModel empModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/empprocess/addemp");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(empModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response editEmpDB(EmployeeModel empModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/empprocess/editemp");

        Response response = target.request().put(Entity.entity(empModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public static ArrayList<EmployeeModel> getAllEmployee() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<EmployeeModel>> genericType = new GenericType<ArrayList<EmployeeModel>>() {
        };

        ArrayList<EmployeeModel> response = target.path("business").
                path("empprocess/getemp").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    public boolean isNicExits(@PathParam("nic") String nic) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("business").
                path("empprocess/nicexits").
                path(nic).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

      public static ArrayList<String> getAllTechnicians() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<String>> genericType = new GenericType<ArrayList<String>>() {
        };

        ArrayList<String> response = target.path("business").
                path("empprocess/gettechnicians").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }
    
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_business").build();
    }

    public static void main(String[] args) {
        EmployeeClient e = new EmployeeClient();

        EmployeeModel ee = new EmployeeModel();

        ee.setNic("975642123V");
        ee.setFname("Madhus");
        ee.setLname("Perera");
        ee.setGender("m");
        ee.setTp1("119");
        ee.setRole("T");
        ee.setStatus("1");
        ee.setNo("541/2");
        ee.setStreet1("Bata padura rd");
        ee.setStreet2("Pillawa");
        ee.setCity("Mahara");
        System.out.println(getAllTechnicians());
    }
}
