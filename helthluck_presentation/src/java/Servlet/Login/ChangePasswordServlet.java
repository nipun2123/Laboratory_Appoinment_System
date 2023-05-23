/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Login;

import Service.UserAccount.UserAccountClient;
import Service.UserAccount.UserAccountModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

public class ChangePasswordServlet extends HttpServlet {

    UserAccountClient userAccountClient;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userAccountClient = UserAccountClient.getInstance();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(false);

        String typePassword = request.getParameter("oldPassword");
        String password = (String) session.getAttribute("password");
                
        if (!Encription.Encript.encript(typePassword).equals(password)) {
            out.println("Wrong password");
        } else {
            out.println("");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        HttpSession session = request.getSession(false);

        UserAccountModel userModel = new UserAccountModel();
        userModel.setPassword(Encription.Encript.encript(request.getParameter("newpassword").trim()));
        userModel.setUsername((String) session.getAttribute("username"));

        Response r = userAccountClient.editPasswordDB(userModel);
        if (r.getStatus() == 201) {
            session.invalidate();
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
