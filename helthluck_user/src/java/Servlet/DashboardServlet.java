/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Service.Appointment.AppointmentClient;
import Service.Appointment.AppointmentModel;
import java.io.IOException;
import java.io.OutputStream;
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
public class DashboardServlet extends HttpServlet {

     AppointmentClient appointmentClient;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentClient = AppointmentClient.getInstance();
    }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DashboardServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
       String id = request.getParameter("appointmentId");
       
        OutputStream outputStream = response.getOutputStream();
        
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setAppointmentId(id);
        
        Response r = appointmentClient.getReportFromIdDB(appointmentModel);
        AppointmentModel model = r.readEntity(AppointmentModel.class);
        
        outputStream.write(model.getTestReport());
        outputStream.flush();
        outputStream.close();
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
