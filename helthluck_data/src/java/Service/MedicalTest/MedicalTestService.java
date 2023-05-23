/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.MedicalTest;

import Service.Employee.*;
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
@Path("/test")
public class MedicalTestService {

    MedicalTestRepository medicalTestRepository = MedicalTestRepository.getInstance();

//    Test Category
    @POST
    @Path("addtestcatdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTestCategory(MedicalTestModel testModel) {

        return Response.status(201).entity(medicalTestRepository.addTestCategory(testModel)).build();

    }

    @PUT
    @Path("edittestcatdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editTestCategory(MedicalTestModel testModel) {

        return Response.status(201).entity(medicalTestRepository.editCategory(testModel)).build();

    }

    @GET
    @Path("gettestCatdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MedicalTestModel> getAllTestCategory() {

        return medicalTestRepository.getAllMedicalCategory();
    }

    @GET
    @Path("testcatexistsdb/{category}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestCatExists(@PathParam("category") String category) {

        return medicalTestRepository.isCategoryExists(category);
    }

//    Test
    @POST
    @Path("addtestdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTest(MedicalTestModel testModel) {

        return Response.status(201).entity(medicalTestRepository.addTest(testModel)).build();

    }

    @PUT
    @Path("edittestdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editTest(MedicalTestModel testModel) {

        return Response.status(201).entity(medicalTestRepository.editTest(testModel)).build();

    }

    @GET
    @Path("gettestdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<MedicalTestModel> getAllTest() {

        return medicalTestRepository.getAllMedicalTest();
    }

    @GET
    @Path("testexistsdb/{test}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isTestExists(@PathParam("test") String test) {

        return medicalTestRepository.isTestExists(test);
    }

    @GET
    @Path("getcatiddb/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getCategoryIdFromName(@PathParam("category") String category) {

        return medicalTestRepository.getCatIdFromName(category);
    }

    @GET
    @Path("gettestiddb/{test}")
    @Produces(MediaType.APPLICATION_JSON)
    public int getTestIdFromName(@PathParam("test") String test) {

        return medicalTestRepository.getTestIdFromName(test);
    }

    @GET
    @Path("getcatdb/{test}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCategoryFromTest(@PathParam("test") String test) {

        return medicalTestRepository.getCatFromTest(test);
    }

    @GET
    @Path("getpricedb/{test}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPriceFromTest(@PathParam("test") String test) {

        return medicalTestRepository.getPriceFromTest(test);
    }

}
