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
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim();
        String password = Encription.Encript.encript(request.getParameter("password").trim());

        UserAccountModel accountModel = new UserAccountModel();
        accountModel.setUsername(username);
        accountModel.setPassword(password);

        Response r = userAccountClient.checkUsernamePassword(accountModel);
        PrintWriter out = response.getWriter();
        if (r.getStatus() == 200) {
            UserAccountModel resultModel = r.readEntity(UserAccountModel.class);
            
             HttpSession session = request.getSession();
            session.setAttribute("userid", resultModel.getUserId());
            session.setAttribute("username", username);
            session.setAttribute("emp", resultModel.getEmpName());
            session.setAttribute("role", resultModel.getRole());
            session.setAttribute("password", password);
            session.setMaxInactiveInterval(60*60);
            
            Cookie userName = new Cookie("username", username);
            userName.setMaxAge(60*60);
            response.addCookie(userName);

            if (resultModel.getRole().equals("a")) {
                response.sendRedirect("admin/admin_overview.jsp");
            } else if (resultModel.getRole().equals("r")) {
               response.sendRedirect("receptionist/receptionist_overview.jsp");
            } else if (resultModel.getRole().equals("t")) {
                response.sendRedirect("technician/technician_page.jsp");
            }
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
