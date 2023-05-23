
package Servlet.Receptionist;

import Service.Patient.PatientClient;
import Service.Patient.PatientModel;
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
public class PatientServlet extends HttpServlet {

    
        PatientClient patientClient;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
       patientClient = PatientClient.getInstance();
    }
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nic = request.getParameter("nic");
        String pnic = request.getParameter("pnic");


         

        if (pnic == null) {
            if (patientClient.isNicExits(nic)) {
                out.println("Already exists");
            } else {
                out.println("");

            }
        } else {
            if (patientClient.isNicExits(nic) && !nic.equalsIgnoreCase(pnic)) {
                out.println("Already exists");
            } else {
                out.println("");

            }
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String pastNic = request.getParameter("pastNic").trim();
        
        if(pastNic.equals("")){
        
        PatientModel patientModel = new PatientModel();
        patientModel.setNic(request.getParameter("nic").trim());
        patientModel.setFname(request.getParameter("fname").trim());
        patientModel.setLname(request.getParameter("lname").trim());
        patientModel.setTp(request.getParameter("tp").trim());
        patientModel.setGender(request.getParameter("gender").trim());
        
        String email = request.getParameter("email");
        
        if(email != null){
            
        patientModel.setEmail(email.trim());
        }
           
         Response r = patientClient.savePatientDB(patientModel);
            if (r.getStatus() == 201) {
                response.sendRedirect("receptionist/receptionist_patient.jsp");
            }
            
        }else{
            doPut(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          
        PatientModel patientModel = new PatientModel();
        patientModel.setNic(req.getParameter("nic").trim());
        patientModel.setPastNic(req.getParameter("pastNic").trim());
        patientModel.setFname(req.getParameter("fname").trim());
        patientModel.setLname(req.getParameter("lname").trim());
        patientModel.setTp(req.getParameter("tp").trim());
        patientModel.setGender(req.getParameter("gender").trim());
        
        String email = req.getParameter("email");
        
        if(email != null){
            
        patientModel.setEmail(email.trim());
        }
        
         Response r = patientClient.editPatientDB(patientModel);
            if (r.getStatus() == 201) {
                resp.sendRedirect("receptionist/receptionist_patient.jsp");
            }
    }
    
    

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
