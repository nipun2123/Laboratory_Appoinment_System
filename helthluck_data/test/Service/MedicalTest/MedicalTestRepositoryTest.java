/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.MedicalTest;

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
public class MedicalTestRepositoryTest {
    
    public MedicalTestRepositoryTest() {
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


    /**
     * Test of addTestCategory method, of class MedicalTestRepository.
     */
    @Test
    public void testAddTestCategory() {
        System.out.println("addTestCategory");
        MedicalTestModel testModel = null;
        MedicalTestRepository instance = null;
        boolean expResult = false;
        boolean result = instance.addTestCategory(testModel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editCategory method, of class MedicalTestRepository.
     */
    @Test
    public void testEditCategory() {
        System.out.println("editCategory");
        MedicalTestModel testModel = null;
        MedicalTestRepository instance = null;
        boolean expResult = false;
        boolean result = instance.editCategory(testModel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMedicalCategory method, of class MedicalTestRepository.
     */
    @Test
    public void testGetAllMedicalCategory() {
        System.out.println("getAllMedicalCategory");
        MedicalTestRepository instance = null;
        ArrayList<MedicalTestModel> expResult = null;
        ArrayList<MedicalTestModel> result = instance.getAllMedicalCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    /**
     * Test of addTest method, of class MedicalTestRepository.
     */
    @Test
    public void testAddTest() {
        System.out.println("addTest");
        MedicalTestModel testModel = null;
        MedicalTestRepository instance = null;
        boolean expResult = false;
        boolean result = instance.addTest(testModel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editTest method, of class MedicalTestRepository.
     */
    @Test
    public void testEditTest() {
        System.out.println("editTest");
        MedicalTestModel testModel = null;
        MedicalTestRepository instance = null;
        boolean expResult = false;
        boolean result = instance.editTest(testModel);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllMedicalTest method, of class MedicalTestRepository.
     */
    @Test
    public void testGetAllMedicalTest() {
        System.out.println("getAllMedicalTest");
        MedicalTestRepository instance = null;
        ArrayList<MedicalTestModel> expResult = null;
        ArrayList<MedicalTestModel> result = instance.getAllMedicalTest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    
}
