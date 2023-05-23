package Servlet.Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Service.Employee.EmployeeClient;
import Service.Employee.EmployeeModel;
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
public class EmployeeServlet extends HttpServlet {

    EmployeeClient empClient;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
       empClient = EmployeeClient.getInstance();
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
            if (empClient.isNicExits(nic)) {
                out.println("Already exists");
            } else {
                out.println("");

            }
        } else {
            if (empClient.isNicExits(nic) && !nic.equalsIgnoreCase(pnic)) {
                out.println("Already exists");
            } else {
                out.println("");

            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("f").equals("up")) {
 
            doPut(request, response);

        } else {
            EmployeeModel empModel = new EmployeeModel();
            empModel.setNic(request.getParameter("nic").trim());
            empModel.setFname(request.getParameter("firstname").trim());

            if (request.getParameter("middelname") != null) {
                empModel.setMname(request.getParameter("middelname").trim());
            } else {
                empModel.setMname(request.getParameter("middelname"));
            }
            empModel.setLname(request.getParameter("lastname").trim());
            empModel.setGender(request.getParameter("gender"));

            empModel.setTp1(request.getParameter("tp1").trim());

            if (request.getParameter("tp2") != null) {
                empModel.setTp2(request.getParameter("tp2").trim());
            } else {
                empModel.setTp2(request.getParameter("tp2"));
            }

            empModel.setNo(request.getParameter("no").trim());
            empModel.setStreet1(request.getParameter("street1").trim());

            if (request.getParameter("street2") != null) {
                empModel.setStreet2(request.getParameter("street2").trim());
            } else {
                empModel.setStreet2(request.getParameter("street2"));
            }

            empModel.setCity(request.getParameter("city").trim());
            empModel.setRole(request.getParameter("role"));
            empModel.setStatus("1");

            Response r = empClient.saveEmpDB(empModel);
            if (r.getStatus() == 201) {
                response.sendRedirect("admin/admin_employee.jsp");
            }
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EmployeeModel empModel = new EmployeeModel();
        empModel.setNic(req.getParameter("nic").trim());
        empModel.setFname(req.getParameter("firstname").trim());

        
        if (req.getParameter("middelname") != null) {
            empModel.setMname(req.getParameter("middelname").trim());
        } else {
            empModel.setMname(req.getParameter("middelname"));
        }

        empModel.setLname(req.getParameter("lastname").trim());
        empModel.setGender(req.getParameter("gender"));
        empModel.setTp1(req.getParameter("tp1").trim());

        if (req.getParameter("tp2") != null) {
            empModel.setTp2(req.getParameter("tp2").trim());
        } else {
            empModel.setTp2(req.getParameter("tp2"));
        }

        empModel.setNo(req.getParameter("no").trim());
        empModel.setStreet1(req.getParameter("street1").trim());

        if (req.getParameter("street2") != null) {
            empModel.setStreet2(req.getParameter("street2").trim());
        } else {
            empModel.setStreet2(req.getParameter("street2"));
        }

        empModel.setCity(req.getParameter("city").trim());
        empModel.setRole(req.getParameter("role"));
        empModel.setStatus(req.getParameter("availibility"));
        empModel.setPastNic(req.getParameter("pastNic").trim());

        Response r = empClient.editEmpDB(empModel);

        System.out.println("Put");
        if (r.getStatus() == 201) {
            resp.sendRedirect("admin/admin_employee.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
