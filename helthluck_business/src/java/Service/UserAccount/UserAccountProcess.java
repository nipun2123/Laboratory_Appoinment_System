/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UserAccount;

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
@Path("/userprocess")
public class UserAccountProcess {

//    Test Category
    @POST
    @Path("adduser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUserAccountDB(UserAccountModel accountModel) {

         EmployeeProcess empProcess = new EmployeeProcess();
        accountModel.setEmpId(empProcess.getIdFromNameDB(accountModel.getEmpName()));
        
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/user/adduserdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(accountModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("edituser")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editUserAccountDB(UserAccountModel accountModel) {
        
           EmployeeProcess empProcess = new EmployeeProcess();
        accountModel.setEmpId(empProcess.getIdFromNameDB(accountModel.getEmpName()));
        
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/user/edituserdb");

        Response response = target.request().put(Entity.entity(accountModel, MediaType.APPLICATION_JSON));

        return response;
    }

    @GET
    @Path("getuser")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserAccountModel> getUserAccountsFromDB() {
        
     
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<UserAccountModel>> genericType = new GenericType<ArrayList<UserAccountModel>>() {
        };

        ArrayList<UserAccountModel> response = target.path("data").
                path("user/getuserdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);
        
        for(UserAccountModel model:response){
            if(model.getStatus().equals("1")){
                model.setStatus("Active");
            }else{
                 model.setStatus("Dective");
            }
        }

        return response;
    }

    @GET
    @Path("userexists/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isUsernameExistsFromDB(@PathParam("username") String category) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("data").
                path("user/userexistsdb").
                path(category).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }
    
    
     @GET
    @Path("getempnoacc")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> getEmpNoAccFromDB() {
        
     
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<String>> genericType = new GenericType<ArrayList<String>>() {
        };

        ArrayList<String> response = target.path("data").
                path("user/getempnoaccdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);
        

        return response;
    }
    
    @POST
    @Path("usercheck")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkUsernamePasswordFromDb(UserAccountModel accountModel) {

        
           Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/user/usercheckdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(accountModel, MediaType.APPLICATION_JSON));
        return response;
        
    }
    
     @PUT
    @Path("editpass")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPasswordDB(UserAccountModel accountModel) {
        
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/user/editpassdb");

        Response response = target.request().put(Entity.entity(accountModel, MediaType.APPLICATION_JSON));

        return response;
    }
    
     @POST
    @Path("patientcheck")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkPatientFromDb(UserAccountModel accountModel) {

        
           Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/user/patientcheckdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(this, MediaType.WILDCARD_TYPE).entity(accountModel, MediaType.APPLICATION_JSON));
        return response;
        
    }
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_data").build();
    }

    public static void main(String[] args) {

     UserAccountModel m = new UserAccountModel();
        m.setUsername("nipz2123");
        m.setPassword("nipz2123");
        System.out.println(new UserAccountProcess().editPasswordDB(m));
    }
}
