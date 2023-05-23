/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Patient;

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
public class PatientClient {

    private static PatientClient patientClient = null;

    public static synchronized PatientClient getInstance() {
        if (patientClient == null) {
            patientClient = new PatientClient();
        }

        return patientClient;
    }

    private PatientClient() {
    }

    public Response savePatientDB(PatientModel patientModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/patientprocess/addpatient");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(patientModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response editPatientDB(PatientModel patientModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/patientprocess/editpatient");

        Response response = target.request().put(Entity.entity(patientModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public static ArrayList<PatientModel> getAllPatient() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<PatientModel>> genericType = new GenericType<ArrayList<PatientModel>>() {
        };

        ArrayList<PatientModel> response = target.path("business").
                path("patientprocess/getpatient").
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
                path("patientprocess/nicexits").
                path(nic).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

     
    
    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_business").build();
    }

    public static void main(String[] args) {
        PatientClient e = new PatientClient();
        
        System.out.println(getAllPatient());

    }
}
