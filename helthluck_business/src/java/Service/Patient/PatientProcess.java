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
//import org.glassfish.jersey.filter.LoggingFilter;

/**
 *
 * @author nipun
 */
@Path("/patientprocess")
public class PatientProcess {

    @POST
    @Path("addpatient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePatientDB(PatientModel patientModel) {
        
        String randomId = String.valueOf(RandomGenerator.RandomGenerator.generateRandomId());
        String randompassword = RandomGenerator.RandomGenerator.generateRandomPassword();
        patientModel.setId(randomId);
        patientModel.setPassword(Encription.Encript.encript(randompassword));
        
          String patientEmail = patientModel.getEmail();

        if (patientEmail != null) {

            String emailSubject = "Registered to HealthLuck";
            String emailContent = "<p>This email contains userid and password to your account</p>"
                    + "<p>Userid:" + randomId + "</p>"
                    + "<p>Password:" + randompassword+ "</p>";
            
            MailApi.Mailer.sendEmail(patientEmail, emailSubject, emailContent);
        }
        
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/patient/addpatientdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(patientModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("editpatient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editPatientDB(PatientModel patientModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/patient/editpatientdb");

        Response response = target.request().put(Entity.entity(patientModel, MediaType.APPLICATION_JSON));

        return response;
    }

    @GET
    @Path("getpatient")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<PatientModel> getPatientFromDB() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<PatientModel>> genericType = new GenericType<ArrayList<PatientModel>>() {
        };

        ArrayList<PatientModel> response = target.path("data").
                path("patient/getpatientdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

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
                path("patient/nicexitsdb").
                path(nic).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(Boolean.class);
        return response;
    }

     @GET
    @Path("getemail/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmailFromNicFromDB(@PathParam("nic") String nic) {

        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        String response = target.path("data").
                path("patient/getemaildb").
                path(nic).
                request().
                accept(MediaType.TEXT_PLAIN).
                get(String.class);
        return response;
    }
   

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_data").build();
    }

    public static void main(String[] args) {
        PatientProcess e = new PatientProcess();

        System.out.println(e.getPatientFromDB());
    }

}
