/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Patient;

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
@Path("/patient")
public class PatientService {

    PatientRepository patientRepository = PatientRepository.getInstance();

    @POST
    @Path("addpatientdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPatient(PatientModel patientModel) {

        return Response.status(201).entity(patientRepository.addPatient(patientModel)).build();

    }

    @PUT
    @Path("editpatientdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPatient(PatientModel patientModel) {

        return Response.status(201).entity(patientRepository.editPatient(patientModel)).build();

    }

    @GET
    @Path("getpatientdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<PatientModel> getAllPatient() {

        return patientRepository.getAllPatient();
    }

    @GET
    @Path("nicexitsdb/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isNicExits(@PathParam("nic") String nic) {

        return patientRepository.isNicExits(nic);
    }
    
     @GET
    @Path("getemaildb/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getEmailFromNic(@PathParam("nic") String nic) {

        return patientRepository.getEmailFromNic(nic);
    }
    
}
