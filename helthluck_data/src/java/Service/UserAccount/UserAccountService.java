/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UserAccount;

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
@Path("/user")
public class UserAccountService {

    UserAccountRepository accountRepository = UserAccountRepository.getInstance();

    @POST
    @Path("adduserdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserAccount(UserAccountModel accountModel) {

        return Response.status(201).entity(accountRepository.addUserAccount(accountModel)).build();

    }

    @PUT
    @Path("edituserdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUserAccount(UserAccountModel accountModel) {

        return Response.status(201).entity(accountRepository.editUserAccount(accountModel)).build();

    }

    @GET
    @Path("getuserdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<UserAccountModel> getUserAccounts() {

        return accountRepository.getAllUserAccounts();
    }

    @GET
    @Path("userexistsdb/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isUsernameExists(@PathParam("username") String username) {

        return accountRepository.isUsernameExists(username);
    }

    @GET
    @Path("getempnoaccdb")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<String> getEmpNoAcc() {

        return accountRepository.getAllEmpNoAcc();
    }

    @POST
    @Path("usercheckdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUsernamePassword(UserAccountModel userAccountModel) {
        UserAccountModel model = accountRepository.checkUsernamePassword(userAccountModel.getUsername(), userAccountModel.getPassword());
        if (model != null) {
            return Response.status(200).entity(model).build();
        } else {
            return Response.status(401).build();
        }
    }


    @PUT
    @Path("editpassdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPassword(UserAccountModel accountModel) {

        return Response.status(201).entity(accountRepository.editPassword(accountModel)).build();

    }
    
        
     @POST
    @Path("patientcheckdb")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkPatientUseridPassword(UserAccountModel userAccountModel) {
        String s = accountRepository.checkPatientUseridPassword(userAccountModel.getUsername(), userAccountModel.getPassword());
        if (s != null) {
            return Response.status(200).entity(s).build();
        } else {
            return Response.status(401).build();
        }
    }

}
