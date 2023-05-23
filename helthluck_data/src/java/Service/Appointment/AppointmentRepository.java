/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Appointment;

import Database.DB;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nipun
 */
public class AppointmentRepository {

    private static AppointmentRepository appointmentRepository = null;

    public static synchronized AppointmentRepository getInstance() {
        if (appointmentRepository == null) {
            appointmentRepository = new AppointmentRepository();
        }

        return appointmentRepository;
    }

    private AppointmentRepository() {
    }

    public boolean addAppontment(AppointmentModel model) {

        String query1 = "INSERT INTO invoice(nettotal,type) VALUES('" + model.getNettotal() + "', '" + model.getPayType() + "') ";
        int id = DB.iudReturnId(query1);

        if (id != -1) {
            String query2 = "INSERT INTO appointment(appointeddate,appointmentdate,appointmenttime, doctor,currentcount,status,patient_nic,medical_test_idmedical_test,user_account_iduser_account,invoice_idinvoice)"
                    + " VALUES(now(), '" + model.getAppointmentDate() + "','" + model.getAppointmentTime() + "', '" + model.getDoctor() + "', "
                    + " '" + model.getCurrentCount() + "', '" + model.getStatus() + "', '" + model.getNic() + "', '" + model.getTestId() + "', '" + model.getUserId() + "', '" + id + "' ) ";

            return DB.iud(query2);
        }

        return false;
    }

    public boolean cancelAppointment(AppointmentModel appointmentModel) {

        String query = "UPDATE appointment SET status = 'Cancel' WHERE idappointment = '" + appointmentModel.getAppointmentId() + "' ";

        return false;

    }

    public ArrayList<AppointmentModel> getAllAppointment() {

        ArrayList<AppointmentModel> appoitmentList = new ArrayList<AppointmentModel>();

        String query = "SELECT a.idappointment, a.appointeddate, a.appointmentdate,a.appointmenttime,t.testname, a.doctor,a.currentcount,a.status,"
                + " a.patient_nic, CONCAT(ee.fname, ' ', ee.lname) AS appointedby,CONCAT(e.fname, ' ', e.lname) AS techician, i.nettotal, i.type,p.id FROM"
                + " appointment a INNER JOIN patient p INNER JOIN medical_test t INNER JOIN medical_test_category c INNER JOIN employee e INNER JOIN"
                + " user_account u inner join employee ee inner join invoice i ON a.patient_nic=p.nic AND a.medical_test_idmedical_test=t.idmedical_test "
                + "AND t.idmedical_test_category=c.idmedical_test_category AND c.employee_idemployee=e.idemployee AND "
                + "a.user_account_iduser_account=u.iduser_account AND u.employee_idemployee=ee.idemployee AND a.invoice_idinvoice = i.idinvoice "
                + "ORDER BY a.idappointment";

        ResultSet result = DB.search(query);

        try {
            while (result.next()) {
                AppointmentModel model = new AppointmentModel();
                model.setAppointmentId(result.getString("a.idappointment"));
                model.setAppointedDate(result.getString("a.appointeddate"));
                model.setAppointmentDate(result.getString("a.appointmentdate"));
                model.setAppointmentTime(result.getString("a.appointmenttime"));
                model.setTestName(result.getString("t.testname"));
                model.setDoctor(result.getString("a.doctor"));
                model.setCurrentCount(result.getInt("a.currentcount"));
                model.setStatus(result.getString("a.status"));
                model.setNic(result.getString("a.patient_nic"));
                model.setAppointedBy(result.getString("appointedby"));
                model.setNettotal(result.getString("i.nettotal"));
                model.setPayType(result.getString("i.type"));
                model.setTechnician(result.getString("techician"));
                model.setPatientId(result.getString("p.id"));

                appoitmentList.add(model);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AppointmentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return appoitmentList;
    }

    public boolean isCountOver(AppointmentModel appointmentModel) {
        String query = "SELECT COUNT(appointment.idappointment) AS currentCount, medical_test_category.maxtestcount AS maxCount FROM appointment "
                + "INNER JOIN medical_test INNER JOIN medical_test_category ON appointment.medical_test_idmedical_test=medical_test.idmedical_test "
                + "AND medical_test.idmedical_test_category=medical_test_category.idmedical_test_category "
                + "WHERE appointment.appointmentdate='" + appointmentModel.getAppointmentDate() + "' AND "
                + "medical_test_category.category='" + appointmentModel.getTestCategory() + "' GROUP BY medical_test_category.category";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                int currentCount = result.getInt("currentCount");
                int maxCount = result.getInt("maxCount");
                if (currentCount >= maxCount) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public int getCurrentCount(AppointmentModel appointmentModel) {
        String query = "SELECT COUNT(appointment.idappointment) AS currentCount FROM appointment "
                + "INNER JOIN medical_test INNER JOIN medical_test_category ON appointment.medical_test_idmedical_test=medical_test.idmedical_test "
                + "AND medical_test.idmedical_test_category=medical_test_category.idmedical_test_category "
                + "WHERE appointment.appointmentdate='" + appointmentModel.getAppointmentDate() + "' AND "
                + "medical_test_category.category='" + appointmentModel.getTestCategory() + "' GROUP BY medical_test_category.category";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return result.getInt("currentCount");

            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean uploadTestReport(AppointmentModel appointmentModel) {

        try {
            String query = "INSERt INTO report(report,appointment_idappointment,user_account_iduser_account) values(?,?,?) ";
            PreparedStatement prs = DB.getConnection().prepareStatement(query);
            prs.setBinaryStream(1, new ByteArrayInputStream(appointmentModel.getTestReport()));
            prs.setString(2, appointmentModel.getAppointmentId());
            prs.setString(3, appointmentModel.getUserId());

            int i = prs.executeUpdate();
            if (i > 0) {
                return true;
            }

        } catch (Exception ex) {
            Logger.getLogger(AppointmentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public AppointmentModel getReportFromId(AppointmentModel appointmentModel){
        
        String query = "SELECT report FROM report WHERE appointment_idappointment = '"+appointmentModel.getAppointmentId()+"' ";
        
        ResultSet result = DB.search(query);
        AppointmentModel model = new AppointmentModel();
        
        try {
            if(result.next()){
                model.setTestReport(result.getBytes("report"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AppointmentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return model;
    }

}
