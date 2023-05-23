/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.Receptionist;

import Service.Appointment.AppointmentClient;
import Service.Appointment.AppointmentModel;
import Service.MedicalTest.MedicalTestClient;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

/**
 *
 * @author nipun
 */
public class AppointmentServlet extends HttpServlet {

    AppointmentClient appointmentClient;
    MedicalTestClient medicalTestClient;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentClient = AppointmentClient.getInstance();
        medicalTestClient = MedicalTestClient.getInstance();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String type = request.getParameter("type");

        if (type.equalsIgnoreCase("count")) {

            String date = request.getParameter("date");
            String test = request.getParameter("test");

            AppointmentModel model = new AppointmentModel();

            model.setAppointmentDate(date);
            model.setTestName(test);

            Response r = appointmentClient.isCountOver(model);

            boolean b = r.readEntity(Boolean.class);

            if (b) {
                out.println("");
            } else {
                out.println("No more appointments can be taken!");
            }

        } else {

            String test = request.getParameter("test");

            AppointmentModel model = new AppointmentModel();

            model.setTestName(test);

            out.println(medicalTestClient.getPriceFromTest(test));

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String type = request.getParameter("type");

        if (type.equalsIgnoreCase("add")) {

            AppointmentModel model = new AppointmentModel();
            
            HttpSession session = request.getSession(false);
            
            String userId = (String) session.getAttribute("userid");
            
            if(userId != null){

            model.setNettotal(request.getParameter("amount"));
            model.setPayType(request.getParameter("payment"));
            model.setAppointmentDate(request.getParameter("appointmentDate"));
            model.setAppointmentTime(request.getParameter("appointmentTime"));
            model.setDoctor(request.getParameter("doctor"));
            model.setStatus("Pending");
            model.setNic(request.getParameter("patient"));
            model.setTestName(request.getParameter("test"));
            model.setUserId(userId);

            Response r = appointmentClient.saveAppointmentDB(model);
            if (r.getStatus() == 201) {
                response.sendRedirect("receptionist/receptionist_appointment.jsp");
            }
            }
        } else if (type.equalsIgnoreCase("cancel")) {

            doPut(request, response);

        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String appointmentId = req.getParameter("appointmentId");
        String status = req.getParameter("clickStatus");

        if (appointmentId != "") {

            if (!status.equalsIgnoreCase("Completed")) {
                AppointmentModel model = new AppointmentModel();

                model.setAppointmentId(appointmentId);

                Response r = appointmentClient.cancelAppointmentDB(model);
                if (r.getStatus() == 201) {
                    resp.sendRedirect("receptionist/receptionist_appointment.jsp");
                }
            } else {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Can not cancel a completed appointment!');");
                out.println("location='receptionist/receptionist_appointment.jsp';");
                out.println("</script>");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
