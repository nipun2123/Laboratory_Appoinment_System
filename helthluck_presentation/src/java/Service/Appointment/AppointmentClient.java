/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Appointment;

import Servlet.Technician.TechnicianServlet;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AppointmentClient {

    private static AppointmentClient appointmentClient = null;

    public static synchronized AppointmentClient getInstance() {
        if (appointmentClient == null) {
            appointmentClient = new AppointmentClient();
        }

        return appointmentClient;
    }

    private AppointmentClient() {
    }

    public Response saveAppointmentDB(AppointmentModel appointmentModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/appointmentprocess/addappointment");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response cancelAppointmentDB(AppointmentModel appointmentModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/appointmentprocess/cancelappointment");

        Response response = target.request().put(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));

        return response;
    }

    public static ArrayList<AppointmentModel> getAllAppointment() {
        ClientConfig config = new ClientConfig();

        Client client = ClientBuilder.newClient(config);

        WebTarget target = client.target(getBaseURI());

        GenericType<ArrayList<AppointmentModel>> genericType = new GenericType<ArrayList<AppointmentModel>>() {
        };

        ArrayList<AppointmentModel> response = target.path("business").
                path("appointmentprocess/getappointment").
                request().
                accept(MediaType.APPLICATION_JSON).
                get(genericType);

        return response;
    }

    public Response isCountOver(AppointmentModel appointmentModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/appointmentprocess/iscountover");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    public Response uploadTestReportDB(AppointmentModel appointmentModel) {
        Client client = ClientBuilder.newClient(new ClientConfig());
        WebTarget target = client.target(getBaseURI()).path("business/appointmentprocess/uploadtestreport");

        Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(appointmentModel, MediaType.APPLICATION_JSON));
        return response;
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/helthluck_business").build();
    }

    public static void main(String[] args) {
       

//       Response r =e.getCurrentCount(a);
    }
}
