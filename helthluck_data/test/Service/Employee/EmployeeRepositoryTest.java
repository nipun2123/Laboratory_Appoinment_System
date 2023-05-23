/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.Employee;

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
public class EmployeeRepositoryTest {
    
    public EmployeeRepositoryTest() {
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
     * Test of addEmployee method, of class EmployeeRepository.
     */
    @Test
    public void testAddEmployee() {
        System.out.println("addEmployee");
        EmployeeModel emp = new EmployeeModel();
        EmployeeRepository instance = EmployeeRepository.getInstance();
        boolean expResult = true;
        boolean result = instance.addEmployee(emp);
        assertEquals(expResult, result);
    }

    /**
     * Test of editEmployee method, of class EmployeeRepository.
     */
    @Test
    public void testEditEmployee() {
        System.out.println("editEmployee");
        EmployeeModel emp = null;
        EmployeeRepository instance = null;
        boolean expResult = false;
        boolean result = instance.editEmployee(emp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getAllEmployee method, of class EmployeeRepository.
     */
    @Test
    public void testGetAllEmployee() {
        System.out.println("getAllEmployee");
        EmployeeRepository instance = null;
        ArrayList<EmployeeModel> expResult = null;
        ArrayList<EmployeeModel> result = instance.getAllEmployee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    
}
