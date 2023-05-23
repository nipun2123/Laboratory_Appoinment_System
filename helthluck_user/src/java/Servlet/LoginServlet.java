/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Service.UserAccount.UserAccountClient;
import Service.UserAccount.UserAccountModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/**
 *
 * @author nipun
 */
public class LoginServlet extends HttpServlet {
    
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
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String userid = request.getParameter("userid").trim();
        String password = Encription.Encript.encript(request.getParameter("password").trim());

        UserAccountModel accountModel = new UserAccountModel();
        accountModel.setUsername(userid);
        accountModel.setPassword(password);

        Response r = userAccountClient.checkPatientUsernamePassword(accountModel);
        
        if (r.getStatus() == 200) {
            String name = r.readEntity(String.class);
            
             HttpSession session = request.getSession();
            session.setAttribute("userid", userid);
            session.setAttribute("name", name);
            session.setMaxInactiveInterval(60*60);
            
            Cookie user = new Cookie("userid", userid);
            user.setMaxAge(60*60);
            response.addCookie(user);

                response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
