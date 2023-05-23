/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Appointment;

import Service.MedicalTest.MedicalTestProcess;
import Service.Patient.PatientProcess;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
@Path("/appointmentprocess")
public class AppointmentProcess {

    @POST
    @Path("addappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveAppointmentDB(AppointmentModel appointmentModel) {

        MedicalTestProcess medical = new MedicalTestProcess();

        String testName = appointmentModel.getTestName();

        appointmentModel.setTestId(String.valueOf(medical.getTestIdFromNameDB(testName)));

        Response r = getCurrentCountDB(appointmentModel);
        int count = r.readEntity(Integer.class);

        appointmentModel.setCurrentCount(++count);

        PatientProcess patientProcess = new PatientProcess();
        
        String patientEmail = patientProcess.getEmailFromNicFromDB(appointmentModel.getNic());

        if (patientEmail != null) {

            String emailSubject = "New Appointment by HealthLuck";
            String emailContent = "<p>Test:" + appointmentModel.getTestName() + "</p>"
                    + "<p>Date: " + appointmentModel.getAppointmentDate()+ "</p>"
                    + "<p>Time: " + appointmentModel.getAppointmentTime() + "</p>"
                    + "<p>Number: " + appointmentModel.getCurrentCount() + "</p>"
                    + "<p>Nettotal: " + appointmentModel.getNettotal()+ "</p>";
            
            MailApi.Mailer.sendEmail(patientEmail, emailSubject, emailContent);
        }

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/appointment/addappointmentdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @PUT
    @Path("cancelappointment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cancelAppointmentDB(AppointmentModel appointmentModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/appointment/cancelappointmentdb");

        Response response = target.request().put(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));

        return response;
    }

    @GET
    @Path("getappointment")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AppointmentModel> getAppointmentFromDB() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<AppointmentModel>> genericType = new GenericType<ArrayList<AppointmentModel>>() {
        };

        ArrayList<AppointmentModel> response = target.path("data").
                path("appointment/getappointmentdb").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    @POST
    @Path("iscountover")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response isCountOverDB(AppointmentModel appointmentModel) {

        MedicalTestProcess medical = new MedicalTestProcess();

        String testName = appointmentModel.getTestName();

        appointmentModel.setTestCategory(String.valueOf(medical.getCategoryFromTestDB(testName)));

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/appointment/iscountoverdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @POST
    @Path("getcurrentcount")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getCurrentCountDB(AppointmentModel appointmentModel) {

        MedicalTestProcess medical = new MedicalTestProcess();

        String testName = appointmentModel.getTestName();

        appointmentModel.setTestCategory(String.valueOf(medical.getCategoryFromTestDB(testName)));

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/appointment/getcurrentcountdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    @POST
    @Path("uploadtestreport")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response uploadTestReportDB(AppointmentModel appointmentModel) {
        
           PatientProcess patientProcess = new PatientProcess();
        
        String patientEmail = patientProcess.getEmailFromNicFromDB(appointmentModel.getNic());

        if (patientEmail != null) {

            String emailSubject = "Test report has uploaded!";
            String emailContent = "<p>Your test report of " + appointmentModel.getAppointmentId()+ " has been uploaded!</p>";
            
            MailApi.Mailer.sendEmail(patientEmail, emailSubject, emailContent);
        }

        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/appointment/uploadtestreportdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }
    
    
     @POST
    @Path("getreport")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getReportFromIdDB(AppointmentModel appointmentModel) {
      
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("data/appointment/getreportdb");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_data").build();
    }

    public static void main(String[] args) {
        AppointmentProcess e = new AppointmentProcess();

        System.out.println(e.getAppointmentFromDB());

//        System.out.println(e.getEmployeFromDB());
    }

}
