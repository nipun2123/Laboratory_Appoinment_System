/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Appointment;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nipun
 */
@Path("/appointment")
public class AppointmentService {

    AppointmentRepository appointmentRepository = AppointmentRepository.getInstance();

    @POST
    @Path("addappointmentdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(AppointmentModel appointmentModel) {

        return Response.status(201).entity(appointmentRepository.addAppontment(appointmentModel)).build();

    }

    @PUT
    @Path("cancelappointmentdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelAppointment(AppointmentModel appointmentModel) {

        return Response.status(201).entity(appointmentRepository.cancelAppointment(appointmentModel)).build();

    }

    @GET
    @Path("getappointmentdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<AppointmentModel> getAllAppointment() {

        return appointmentRepository.getAllAppointment();
    }

    @POST
    @Path("iscountoverdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response isCountOver(AppointmentModel appointmentModel) {

        return Response.status(201).entity(appointmentRepository.isCountOver(appointmentModel)).build();

    }

    @POST
    @Path("getcurrentcountdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrentCount(AppointmentModel appointmentModel) {

        return Response.status(201).entity(appointmentRepository.getCurrentCount(appointmentModel)).build();

    }
    
     @POST
    @Path("uploadtestreportdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTestReport(AppointmentModel appointmentModel) {

        return Response.status(201).entity(appointmentRepository.uploadTestReport(appointmentModel)).build();

    }
    
      @POST
    @Path("getreportdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReportFromId(AppointmentModel appointmentModel) {

        return Response.status(201).entity(appointmentRepository.getReportFromId(appointmentModel)).build();

    }


}
