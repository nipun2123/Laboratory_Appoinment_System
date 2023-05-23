/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Technician;

import Service.Appointment.AppointmentClient;
import Service.Appointment.AppointmentModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author nipun
 */
@MultipartConfig() 
public class TechnicianServlet extends HttpServlet {

    AppointmentClient appointmentClient;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentClient = AppointmentClient.getInstance();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AppointmentModel appointmentModel = new AppointmentModel();

        String appointmentId = request.getParameter("appointmentId");
        String patientNic = request.getParameter("nic");
        Part uploadReport = request.getPart("uploadReport");

        InputStream inputStream = uploadReport.getInputStream();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        try {
            for (int len = inputStream.read(buffer); len != -1; len = inputStream.read(buffer)) {

                outputStream.write(buffer, 0, len);
            }

        } catch (IOException ex) {
            Logger.getLogger(TechnicianServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        HttpSession session = request.getSession(false);

        appointmentModel.setUserId((String) session.getAttribute("userid"));
        
        PrintWriter out = response.getWriter();
        out.println(appointmentId);
        out.println(patientNic);
        out.println(uploadReport);

        appointmentModel.setAppointmentId(appointmentId);
        appointmentModel.setNic(patientNic);
        appointmentModel.setTestReport(outputStream.toByteArray());

        boolean b = appointmentClient.uploadTestReportDB(appointmentModel).readEntity(Boolean.class);

        if (b) {
            response.sendRedirect("technician/technician_page.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
