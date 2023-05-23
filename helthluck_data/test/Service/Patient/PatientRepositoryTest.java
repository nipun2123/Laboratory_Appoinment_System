/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Patient;

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
public class PatientRepositoryTest {

    public PatientRepositoryTest() {
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

    @Test
    public void testAddPatient() {
        System.out.println("addPatient");
        PatientModel patient = new PatientModel();
        patient.setNic("986523562V");
        patient.setFname("Dilshan");
        patient.setLname("Charuka");
        patient.setTp("0714206500");
        patient.setGender("m");
        patient.setId("guagdj4544");
        patient.setPassword("12254s");
        PatientRepository instance = PatientRepository.getInstance();
        boolean expResult = true;
        boolean result = instance.addPatient(patient);
        assertEquals(expResult, result);
    }
    
  
    @Test
    public void testEditPatient() {
        System.out.println("editPatient");
        PatientModel patient = new PatientModel();
        patient.setNic("986564415V");
        patient.setFname("Dilshan");
        patient.setLname("Charuka");
        patient.setTp("0714206500");
        patient.setGender("m");
        patient.setId("guagdj4544");
        patient.setPassword("12254s");
        
         patient.setPastNic("986523562V");
         
        PatientRepository instance = PatientRepository.getInstance();
        boolean expResult = true;
        boolean result = instance.editPatient(patient);
        assertEquals(expResult, result);
    }
  
    @Test
    public void testGetAllPatient() {
        System.out.println("getAllPatient");
        PatientRepository instance = PatientRepository.getInstance();
        ArrayList<PatientModel> expResult = new ArrayList<PatientModel>();
        PatientModel patient = new PatientModel();
        patient.setNic("200011253695");
        patient.setFname("Ruwan");
        patient.setLname("Chamara");
        patient.setTp("0775263566");
        patient.setGender("m");
        patient.setId("csd455dv");
        patient.setPassword("d45dcs");
        expResult.add(patient);
        
        ArrayList<PatientModel> result = instance.getAllPatient();
        assertEquals(expResult.get(0).getNic(), result.get(0).getNic());
    }

}
