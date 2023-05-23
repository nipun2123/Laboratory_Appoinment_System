/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Appointment;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nipun
 */
public class AppointmentRepositoryTest {
    
    public AppointmentRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
//    @Test
//    public void testAddAppontment() {
//        System.out.println("addAppontment");
//        AppointmentModel model = new AppointmentModel();
//        model.setNettotal("5000");
//        model.setPayType("Cash");
//        model.setAppointmentDate("2020-12-15");
//        model.setAppointmentTime("08:30");
//        model.setCurrentCount(1);
//        model.setDoctor("Gihan Senarathna");
//        model.setStatus("Pending");
//        model.setNic("786523562V");
//        model.setTestId("1");
//        model.setUserId("2");
//        AppointmentRepository instance = AppointmentRepository.getInstance();
//        boolean expResult = true;
//        boolean result = instance.addAppontment(model);
//        assertEquals(expResult, result);
//    }

    
  
    
//    @Test
//    public void testCancelAppointment() {
//        System.out.println("cancelAppointment");
//        AppointmentModel appointmentModel = new AppointmentModel();
//        appointmentModel.setAppointmentId("18");
//        AppointmentRepository instance = AppointmentRepository.getInstance();
//        boolean expResult = true;
//        boolean result = instance.cancelAppointment(appointmentModel);
//        assertEquals(expResult, result);
//    }
//    
//    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void testGetAllAppointment() {
        System.out.println("getAllAppointment");
        AppointmentRepository instance = AppointmentRepository.getInstance();
        ArrayList<AppointmentModel> expResult = new ArrayList<AppointmentModel>();
         AppointmentModel model = new AppointmentModel();
        model.setNettotal("5000");
        model.setPayType("Cash");
        model.setAppointmentDate("2020-10-25");
        model.setAppointmentTime("08:30");
        model.setCurrentCount(1);
        model.setDoctor("Mr. Nipun");
        model.setStatus("Cancel");
        model.setNic("826523695V");
        model.setTestId("1");
        model.setUserId("2");
        model.setAppointmentId("1");
        expResult.add(model);
        ArrayList<AppointmentModel> result = instance.getAllAppointment();
        assertEquals(expResult.get(0).getAppointmentId(), result.get(0).getAppointmentId());
    }
}
