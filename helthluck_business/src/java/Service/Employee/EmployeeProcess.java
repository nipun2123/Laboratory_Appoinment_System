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
//import org.glassfish.jersey.filter.LoggingFilter;

/**
 *
 * @author nipun
 */
@Path("/empprocess")
public class EmployeeProcess {

    @POST
    @Path("addemp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmpDB(EmployeeModel empModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/emp/addempdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(empModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("editemp")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editEmpDB(EmployeeModel empModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/emp/editempdb");

        Response response = target.request().put(Entity.entity(empModel, MediaType.APPLICATION_JSON));

        return response;
    }

    @GET
    @Path("getemp")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<EmployeeModel> getEmployeFromDB() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<EmployeeModel>> genericType = new GenericType<ArrayList<EmployeeModel>>() {
        };

        ArrayList<EmployeeModel> response = target.path("data").
                path("emp/getempdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        for (EmployeeModel e : response) {

            e.setNic(e.getNic().trim());
            if (e.getMname().equals("")) {
                e.setName(e.getFname().trim() + " " + e.getLname().trim());
            } else {
                e.setName(e.getFname().trim() + " " + e.getMname().trim() + " " + e.getLname().trim());
            }

            if (e.getGender().equals("m")) {
                e.setGender("Male");
            } else {
                e.setGender("Female");
            }

            if (e.getRole().equals("a")) {
                e.setRole("Admin");
            } else if (e.getRole().equals("r")) {
                e.setRole("Receptionist");
            } else if (e.getRole().equals("t")) {
                e.setRole("Technician");
            }

            if (e.getStatus().equals("1")) {
                e.setStatus("Active");
            } else {
                e.setStatus("Deactive");
            }

            if (e.getTp2().equals("")) {
                e.setTp(e.getTp1().trim());
            } else {
                e.setTp(e.getTp1().trim() + "/" + e.getTp2().trim());
            }

            if (e.getStreet2().equals("")) {
                e.setAddress(e.getNo().trim() + "," + e.getStreet1().trim() + "," + e.getCity().trim());
            } else {
                e.setAddress(e.getNo().trim() + "," + e.getStreet1().trim() + "," + e.getStreet2().trim() + "," + e.getCity().trim());
            }
            System.out.println(e.getAddress());
        }

        return response;
    }

    @GET
    @Path("nicexits/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isNicExitsFromDB(@PathParam("nic") String nic) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("data").
                path("emp/nicexitsdb").
                path(nic).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

    @GET
    @Path("gettechnicians")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> getTechniciantsFromDB() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<String>> genericType = new GenericType<ArrayList<String>>() {
        };

        ArrayList<String> response = target.path("data").
                path("emp/gettechniciansdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    @GET
    @Path("getid/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getIdFromNameDB(@PathParam("name") String name) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        int response = target.path("data").
                path("emp/getiddb").
                path(name).
                request().
                accept(MediaType.APPLICATION_JSON).
                get(Integer.class);
        return response;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_data").build();
    }

    public static void main(String[] args) {
        EmployeeProcess e = new EmployeeProcess();

        EmployeeModel ee = new EmployeeModel();

        ee.setNic("965423625V");
        ee.setFname("Podi");
        ee.setMname("Appuge");
        ee.setLname("Lasi");
        ee.setGender("m");
        ee.setTp1("998");
        ee.setTp1("889");
        ee.setRole("t");
        ee.setStatus("1");
        ee.setNo("89/1");
        ee.setStreet1("Kumara rd");
        ee.setStreet2("Jayela");
        ee.setCity("Kottawa");
        ee.setPastNic("22222222V");
        System.out.println(e.getIdFromNameDB("Divya Nimsara Sitinamaluwa"));

//        System.out.println(e.getEmployeFromDB());
    }

}
