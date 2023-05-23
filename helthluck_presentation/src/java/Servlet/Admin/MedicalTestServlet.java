package Servlet.Admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Service.Employee.EmployeeClient;
import Service.MedicalTest.MedicalTestClient;
import Service.MedicalTest.MedicalTestModel;
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
public class MedicalTestServlet extends HttpServlet {

             MedicalTestClient medicalTestClient;
    @Override
    public void init(ServletConfig config) throws ServletException {
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

        if (type.equals("category")) {
            String category = request.getParameter("testCat");
            String pcategory = request.getParameter("pTestCat");

   

            if (pcategory == null) {
                if (medicalTestClient.isTestCategoryExists(category)) {
                    out.println("Already exists");
                } else {
                    out.println("");

                }
            } else {
                if (medicalTestClient.isTestCategoryExists(category) && !category.equalsIgnoreCase(pcategory)) {
                    out.println("Already exists");
                } else {
                    out.println("");

                }
            }

        } else {

            String test = request.getParameter("test");
            String ptest = request.getParameter("pTest");


            if (ptest == null) {
                if (medicalTestClient.isTestExists(test)) {
                    out.println("Already exists");
                } else {
                    out.println("");

                }
            } else {
                if (medicalTestClient.isTestExists(test) && !test.equalsIgnoreCase(ptest)) {
                    out.println("Already exists");
                } else {
                    out.println("");

                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type");
        MedicalTestModel testModel = new MedicalTestModel();

        if (type.equals("category")) {

            String pcategory = request.getParameter("pastTestCategory");

            if (pcategory.equals("")) {

                testModel.setTestCategory(request.getParameter("testcategory").trim());
                testModel.setLabNo(request.getParameter("labno").trim());
                testModel.setMaxCount(request.getParameter("maxcount").trim());
                testModel.setEmpName(request.getParameter("techniciant"));

                Response r = medicalTestClient.saveTestCategoryDB(testModel);
                if (r.getStatus() == 201) {
                    response.sendRedirect("admin/admin_medical_test.jsp");
                }

            } else {
                doPut(request, response);
            }

        } else {
            String ptest = request.getParameter("pastTest");

            if (ptest.equals("")) {

                testModel.setTestName(request.getParameter("testname").trim());
                testModel.setTestCategory(request.getParameter("testcat"));
                testModel.setPrice(request.getParameter("price").trim());

                Response r = medicalTestClient.saveTestDB(testModel);
                if (r.getStatus() == 201) {
                    response.sendRedirect("admin/admin_medical_test.jsp");
                }

            } else {
                doPut(request, response);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");
        MedicalTestModel testModel = new MedicalTestModel();

        if (type.equals("category")) {

            testModel.setTestCategory(req.getParameter("testcategory").trim());
            testModel.setLabNo(req.getParameter("labno").trim());
            testModel.setMaxCount(req.getParameter("maxcount").trim());
            testModel.setEmpName(req.getParameter("techniciant"));
            testModel.setPastTestCategory(req.getParameter("pastTestCategory").trim());

            Response r = medicalTestClient.editTestCategoryDB(testModel);
            if (r.getStatus() == 201) {
                resp.sendRedirect("admin/admin_medical_test.jsp");
            }
        } else {

               testModel.setTestName(req.getParameter("testname").trim());
                testModel.setTestCategory(req.getParameter("testcat"));
                testModel.setPrice(req.getParameter("price").trim());
            testModel.setPastTestName(req.getParameter("pastTest"));

            Response r = medicalTestClient.editTestDB(testModel);
            if (r.getStatus() == 201) {
                resp.sendRedirect("admin/admin_medical_test.jsp");
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
