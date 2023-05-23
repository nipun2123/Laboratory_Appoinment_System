/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UserAccount;

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


    public Response checkPatientUsernamePassword(UserAccountModel accountModel) {
  Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/userprocess/patientcheck");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(accountModel, MediaType.APPLICATION_JSON));
        return response;
    }

    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_business").build();
    }

  
}
