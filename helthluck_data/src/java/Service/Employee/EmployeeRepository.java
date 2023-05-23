/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Employee;

import Database.DB;
import static Database.DB.getConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nipun
 */
public class EmployeeRepository {

    private static EmployeeRepository empRepository = null;

    public static synchronized EmployeeRepository getInstance() {
        if (empRepository == null) {
            empRepository = new EmployeeRepository();
        }

        return empRepository;
    }

    private EmployeeRepository() {
    }

    public boolean addEmployee(EmployeeModel emp) {

        String query1 = "INSERT INTO address(no,street1,street2,city) VALUES('" + emp.getNo() + "', '" + emp.getStreet1() + "', '" + emp.getStreet2() + "', '" + emp.getCity() + "') ";
        int id = DB.iudReturnId(query1);
        if (id != -1) {
            String query2 = "INSERT INTO employee(nic,fname,mname,lname,gender,tp1,tp2,role,status,address_idaddress) VALUES('" + emp.getNic() + "', "
                    + "'" + emp.getFname() + "', '" + emp.getMname() + "', '" + emp.getLname() + "', '" + emp.getGender() + "', '" + emp.getTp1() + "', '" + emp.getTp2() + "',"
                    + " '" + emp.getRole() + "', '" + emp.getStatus() + "', '" + id + "' ) ";
            return DB.iud(query2);
        }

        return false;
    }

    public boolean editEmployee(EmployeeModel emp) {

        String query1 = "UPDATE address SET no = '" + emp.getNo() + "', street1 = '" + emp.getStreet1() + "', street2 = '" + emp.getStreet2() + "', "
                + "city = '" + emp.getCity() + "' WHERE idaddress = '" + getAddressIdFromEmpNic(emp.getPastNic()) + "' ";
        System.out.println("q1" + query1);
        boolean b = DB.iud(query1);
        System.out.println(b);
        if (b) {
            String query2 = "UPDATE employee SET nic = '" + emp.getNic() + "', fname = '" + emp.getFname() + "', mname = '" + emp.getMname() + "', "
                    + "lname = '" + emp.getLname() + "', gender = '" + emp.getGender() + "', tp1 = '" + emp.getTp1() + "', tp2 = '" + emp.getTp2() + "', "
                    + "role = '" + emp.getRole() + "', status = '" + emp.getStatus() + "' WHERE nic = '" + emp.getPastNic() + "' ";

            System.out.println("q2" + query2);
            return DB.iud(query2);
        }
        return false;
    }

    public int getAddressIdFromEmpNic(String nic) {
        String query = "SELECT address_idaddress FROM employee WHERE nic = '" + nic + "' ";
        ResultSet result = DB.search(query);

        try {
            while (result.next()) {
                return result.getInt("address_idaddress");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
//csacsa

    public ArrayList<EmployeeModel> getAllEmployee() {

        ArrayList<EmployeeModel> empList = new ArrayList<EmployeeModel>();

//        String query = "SELECT * FROM employee e INNER JOIN address a on e.address_idaddress = a.idaddress";
        String query = "{CALL getAllEmployee()}";
        try {
            CallableStatement stm = getConnection().prepareCall(query);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                EmployeeModel model = new EmployeeModel();
                model.setNic(result.getString("nic"));
                model.setFname(result.getString("fname"));
                model.setMname(result.getString("mname"));
                model.setLname(result.getString("lname"));
                model.setGender(result.getString("gender"));
                model.setTp1(result.getString("tp1"));
                model.setTp2(result.getString("tp2"));
                model.setRole(result.getString("role"));
                model.setStatus(result.getString("status"));
                model.setNo(result.getString("no"));
                model.setStreet1(result.getString("street1"));
                model.setStreet2(result.getString("street2"));
                model.setCity(result.getString("city"));

                empList.add(model);
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empList;
    }

    public boolean isNicExits(String nic) {
        String query = "SELECT nic FROM employee WHERE nic = '" + nic + "' ";

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
//    scsa

    public ArrayList<String> getAllTechnicians() {

        ArrayList<String> empList = new ArrayList<String>();

//        String query = "SELECT CONCAT(fname,' ', mname,' ',lname) AS empname FROM employee WHERE role = 't' ";
        String query = "{CALL getAllTechnicians()}";
        try {

            CallableStatement stm = getConnection().prepareCall(query);
            ResultSet result = stm.executeQuery();

            while (result.next()) {
                empList.add(result.getString("empname"));
            }

        } catch (Exception ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empList;
    }

    public int getEmpIdFromName(String empName) {

        String query = "SELECT idemployee FROM employee WHERE CONCAT(fname,' ', mname,' ',lname) = '" + empName + "' ";

        ResultSet result = DB.search(query);

        try {
            while (result.next()) {
                return result.getInt("idemployee");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public static void main(String[] args) {
        EmployeeRepository er = new EmployeeRepository();
//        
//        EmployeeModel e = new EmployeeModel();
//        
//        e.setNic("97777777V");
//        e.setFname("Makadure");
//        e.setLname("Madhush");
//        e.setGender("f");
//        e.setTp1("118");
//        e.setTp2("119");
//        e.setRole("a");
//        e.setNo("541/222");
//        e.setStatus("1");
//        e.setStreet1("Bata padura rdddddd");
//        e.setStreet2("Pillawaaaa");
//        e.setCity("Maharaaaa");
//        e.setPastNic("975642123V");
//        
        System.out.println(er.getEmpIdFromName("Asoka  Amarakoon"));
    }

}
