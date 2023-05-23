/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Patient;

import Service.Employee.*;
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
public class PatientRepository {

    private static PatientRepository patientRepository = null;

    public static synchronized PatientRepository getInstance() {
        if (patientRepository == null) {
            patientRepository = new PatientRepository();
        }

        return patientRepository;
    }

    private PatientRepository() {
    }

    public boolean addPatient(PatientModel patient) {

        String query = "INSERT INTO patient(nic,fname,lname,tp,gender,email,id,password) VALUES('" + patient.getNic() + "', '" + patient.getFname() + "',"
                + " '" + patient.getLname() + "', '" + patient.getTp() + "', '" + patient.getGender() + "', '" + patient.getEmail() + "', '" + patient.getId() + "',"
                + " '" + patient.getPassword() + "' ) ";

        return DB.iud(query);
    }

    public boolean editPatient(PatientModel patient) {

        String query = "UPDATE patient SET nic = '" + patient.getNic() + "', fname = '" + patient.getFname() + "', lname = '" + patient.getLname() + "', "
                + "tp = '" + patient.getTp() + "', gender = '" + patient.getGender() + "',email = '" + patient.getEmail() + "' WHERE nic = '" + patient.getPastNic() + "' ";

        return DB.iud(query);
    }

//scasc
    public ArrayList<PatientModel> getAllPatient() {

        ArrayList<PatientModel> patientList = new ArrayList<PatientModel>();

//        String query = "SELECT nic, CONCAT(fname, ' ', lname) AS name, tp, IF(gender= 'm', 'Male', 'Female') as gender, email, id FROM patient";
        String query = "{CALL getAllPatient()}";
        try {
            CallableStatement stm = getConnection().prepareCall(query);
            ResultSet result = stm.executeQuery();
            while (result.next()) {
                PatientModel model = new PatientModel();
                model.setNic(result.getString("nic"));
                model.setName(result.getString("name"));
                model.setTp(result.getString("tp"));
                model.setGender(result.getString("gender"));
                model.setEmail(result.getString("email"));
                model.setId(result.getString("id"));
                patientList.add(model);
            }

        } catch (Exception ex) {
            Logger.getLogger(PatientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return patientList;
    }

    public boolean isNicExits(String nic) {
        String query = "SELECT nic FROM patient WHERE nic = '" + nic + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getEmailFromNic(String nic) {
        String query = "SELECT email FROM patient WHERE nic = '" + nic + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return result.getString("email");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        PatientRepository er = new PatientRepository();

        System.out.println(er.getAllPatient());

    }

}
