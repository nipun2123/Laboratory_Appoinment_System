/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Employee;

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
@Path("/emp")
public class EmployeeService {

    EmployeeRepository empRepository = EmployeeRepository.getInstance();

    @POST
    @Path("addempdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(EmployeeModel emp) {

        return Response.status(201).entity(empRepository.addEmployee(emp)).build();

    }

    @PUT
    @Path("editempdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editEmployee(EmployeeModel emp) {

        return Response.status(201).entity(empRepository.editEmployee(emp)).build();

    }

    @GET
    @Path("getempdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<EmployeeModel> getAllemployee() {

        return empRepository.getAllEmployee();
    }

    @GET
    @Path("nicexitsdb/{nic}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isNicExits(@PathParam("nic") String nic) {

        return empRepository.isNicExits(nic);
    }
    
      @GET
    @Path("gettechniciansdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> getAllTecnicians() {

        return empRepository.getAllTechnicians();
    }
    
      @GET
    @Path("getiddb/{name}")
     @Produces(MediaType.APPLICATION_JSON)
    public int getEmpIdFromName(@PathParam("name") String name) {

        return empRepository.getEmpIdFromName(name);
    }

}
