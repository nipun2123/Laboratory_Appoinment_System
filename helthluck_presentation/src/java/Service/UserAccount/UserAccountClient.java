/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UserAccount;

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
public class UserAccountClient {

    private static UserAccountClient userAccountClient = null;

    public static synchronized UserAccountClient getInstance() {
        if (userAccountClient == null) {
            userAccountClient = new UserAccountClient();
        }

        return userAccountClient;
    }

    private UserAccountClient() {
    }

    public Response saveUserAccountDB(UserAccountModel accountModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/userprocess/adduser");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(accountModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response editUserAccountDB(UserAccountModel accountModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/userprocess/edituser");

        Response response = target.request().put(Entity.entity(accountModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public static ArrayList<UserAccountModel> getAllUserAccounts() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<UserAccountModel>> genericType = new GenericType<ArrayList<UserAccountModel>>() {
        };

        ArrayList<UserAccountModel> response = target.path("business").
                path("userprocess/getuser").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    public boolean isUsernameExists(@PathParam("username") String username) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        boolean response = target.path("business").
                path("userprocess/userexists").
                path(username).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

    public static ArrayList<String> getAllEmpNoAcc() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<String>> genericType = new GenericType<ArrayList<String>>() {
        };

        ArrayList<String> response = target.path("business").
                path("userprocess/getempnoacc").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
        
         
    }

    public Response checkUsernamePassword(UserAccountModel accountModel) {
  Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/userprocess/usercheck");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(accountModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response editPasswordDB(UserAccountModel accountModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/userprocess/editpass");

        Response response = target.request().put(Entity.entity(accountModel, MediaType.APPLICATION_JSON));

        return response;
    }
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_business").build();
    }

    public static void main(String[] args) {
        UserAccountClient e = new UserAccountClient();

       UserAccountModel m = new UserAccountModel();
        m.setUsername("nipz2123");
        m.setPassword("nipz2123");
        System.out.println(e.editPasswordDB(m));
    }
}
