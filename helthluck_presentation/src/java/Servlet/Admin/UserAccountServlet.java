package Servlet.Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Service.UserAccount.UserAccountClient;
import Service.UserAccount.UserAccountModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author nipun
 */
public class UserAccountServlet extends HttpServlet {

    UserAccountClient userAccountClient;
    @Override
    public void init(ServletConfig config) throws ServletException {
        userAccountClient = UserAccountClient.getInstance();
    }

    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("user");
        String pusername = request.getParameter("puser");
        

        if (pusername == "") {
            
            if (userAccountClient.isUsernameExists(username)) {
                out.println("Already exists");
            } else {
                out.println("");

            }
        } else {
            
            if (userAccountClient.isUsernameExists(username) && !username.equalsIgnoreCase(pusername)) {
                out.println("Already exists");
            } else {
                out.println("");

            }
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pusername = request.getParameter("pastusername");
        if (pusername.equals("")) {
            
            UserAccountModel userModel = new UserAccountModel();
                userModel.setUsername(request.getParameter("username").trim());
                userModel.setEmail(request.getParameter("email").trim());
                userModel.setPassword(Encription.Encript.encript(request.getParameter("newpassword").trim()));
                userModel.setEmpName(request.getParameter("employee").trim());
                userModel.setStatus("1");

                Response r = userAccountClient.saveUserAccountDB(userModel);
                if (r.getStatus() == 201) {
                    response.sendRedirect("admin/admin_useraccount.jsp");
                }

        } else {

                doPut(request, response);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         UserAccountModel userModel = new UserAccountModel();
                userModel.setUsername(req.getParameter("username").trim());
                userModel.setPassword(Encription.Encript.encript(req.getParameter("newpassword").trim()));
                userModel.setEmpName(req.getParameter("employee").trim());
                userModel.setEmail(req.getParameter("email").trim());
                userModel.setStatus(req.getParameter("availibility"));
                userModel.setPastUsername(req.getParameter("pastusername"));

                Response r = userAccountClient.editUserAccountDB(userModel);
                if (r.getStatus() == 201) {
                    resp.sendRedirect("admin/admin_useraccount.jsp");
                }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
