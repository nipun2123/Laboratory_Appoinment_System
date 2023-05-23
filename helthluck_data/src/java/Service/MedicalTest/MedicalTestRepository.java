/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.MedicalTest;

import Database.DB;
import Service.Employee.EmployeeModel;
import Service.Employee.EmployeeRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nipun
 */
public class MedicalTestRepository {

    private static MedicalTestRepository medicalTestRepository = null;

    public static synchronized MedicalTestRepository getInstance() {
        if (medicalTestRepository == null) {
            medicalTestRepository = new MedicalTestRepository();
        }

        return medicalTestRepository;
    }

    private MedicalTestRepository() {
    }

//    Test Category
    public boolean addTestCategory(MedicalTestModel testModel) {
        String query = "Insert into medical_test_category(category, labno, maxtestcount, employee_idemployee) VALUES('" + testModel.getTestCategory() + "',"
                + " '" + testModel.getLabNo() + "', '" + testModel.getMaxCount() + "', '" + testModel.getEmpId() + "' ) ";

        return DB.iud(query);
    }

    public boolean editCategory(MedicalTestModel testModel) {

        String query = "UPDATE medical_test_category SET category = '" + testModel.getTestCategory() + "', labno = '" + testModel.getLabNo() + "', "
                + " maxtestcount = '" + testModel.getMaxCount() + "', employee_idemployee = '" + testModel.getEmpId() + "' WHERE category = '" + testModel.getPastTestCategory() + "' ";
        return DB.iud(query);

    }

    public ArrayList<MedicalTestModel> getAllMedicalCategory() {

        ArrayList<MedicalTestModel> medicalCategoryList = new ArrayList<MedicalTestModel>();

        String query = "SELECT category,labno,maxtestcount, CONCAT(fname,' ', mname,' ',lname) AS empname FROM medical_test_category c INNER JOIN employee e ON c.employee_idemployee = e.idemployee";

        ResultSet result = DB.search(query);

        try {
            while (result.next()) {
                MedicalTestModel model = new MedicalTestModel();
                model.setTestCategory(result.getString("category"));
                model.setLabNo(result.getString("labno"));
                model.setMaxCount(result.getString("maxtestcount"));
                model.setEmpName(result.getString("empname"));

                medicalCategoryList.add(model);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicalCategoryList;
    }

    public boolean isCategoryExists(String category) {
        String query = "SELECT category FROM medical_test_category WHERE category = '" + category + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getCatIdFromName(String category) {

        String query = "SELECT idmedical_test_category from medical_test_category WHERE category = '" + category + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return result.getInt("idmedical_test_category");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

//    Test
    public boolean addTest(MedicalTestModel testModel) {
        String query = "Insert into medical_test(testname, price, idmedical_test_category) VALUES('" + testModel.getTestName() + "',"
                + " '" + testModel.getPrice() + "', '" + testModel.getTestCategoryId() + "' ) ";

        return DB.iud(query);
    }

    public boolean editTest(MedicalTestModel testModel) {

        String query = "UPDATE medical_test SET testname = '" + testModel.getTestName() + "', price = '" + testModel.getPrice() + "', "
                + " idmedical_test_category = '" + testModel.getTestCategoryId() + "' WHERE testname = '" + testModel.getPastTestName() + "' ";
        return DB.iud(query);

    }

    public ArrayList<MedicalTestModel> getAllMedicalTest() {

        ArrayList<MedicalTestModel> medicalTestList = new ArrayList<MedicalTestModel>();

        String query = "SELECT testname,category,price,maxtestcount,labno, CONCAT(fname,' ', mname,' ',lname) AS empname"
                + " FROM medical_test t INNER JOIN medical_test_category c INNER JOIN employee e ON t.idmedical_test_category = c.idmedical_test_category "
                + "AND c.employee_idemployee = e.idemployee";

        ResultSet result = DB.search(query);

        try {
            while (result.next()) {
                MedicalTestModel model = new MedicalTestModel();
                model.setTestName(result.getString("testname"));
                model.setTestCategory(result.getString("category"));
                model.setPrice(result.getString("price"));
                model.setMaxCount(result.getString("maxtestcount"));
                model.setLabNo(result.getString("labno"));
                model.setEmpName(result.getString("empname"));

                medicalTestList.add(model);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicalTestList;
    }

    public boolean isTestExists(String test) {
        String query = "SELECT testname FROM medical_test WHERE testname = '" + test + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getTestIdFromName(String test) {

        String query = "SELECT idmedical_test from medical_test WHERE testname = '" + test + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return result.getInt("idmedical_test");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
      public String getCatFromTest(String test) {

        String query = "SELECT category FROM medical_test_category c INNER JOIN medical_test t ON c.idmedical_test_category = t.idmedical_test_category"
                + " WHERE t.testname = '"+test+"' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return result.getString("category");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
        public String getPriceFromTest(String test) {

        String query = "SELECT price FROM medical_test WHERE testname = '"+test+"' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return result.getString("price");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicalTestRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {

        MedicalTestModel m = new MedicalTestModel();
        m.setTestName("Heart Scan");
        m.setPrice("5000");
        m.setTestCategoryId(2);
        m.setPastTestName("Heart Scann");
        System.out.println(new MedicalTestRepository().editTest(m));
    }
}
