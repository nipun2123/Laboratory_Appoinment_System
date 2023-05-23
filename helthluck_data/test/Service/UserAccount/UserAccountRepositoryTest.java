/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UserAccount;

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
public class UserAccountRepositoryTest {
    
    public UserAccountRepositoryTest() {
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
    public void testAddUserAccount() {
        System.out.println("addUserAccount");
        UserAccountModel userModel = new UserAccountModel();
        userModel.setUsername("ruwan1122");
        userModel.setPassword("Ruwan10203050");
        userModel.setEmail("ruwan@gmail.com");
        userModel.setStatus("1");
        userModel.setEmpId(5);
        UserAccountRepository instance = UserAccountRepository.getInstance();
        boolean expResult = true;
        boolean result = instance.addUserAccount(userModel);
        assertEquals(expResult, result);
    }
    
 
    @Test
    public void testEditUserAccount() {
        System.out.println("editUserAccount");
        UserAccountModel userModel = new UserAccountModel();
        userModel.setUsername("ruwan11");
        userModel.setPassword("Ruwan104050");
        userModel.setEmail("ruwan10@gmail.com");
        userModel.setStatus("1");
        userModel.setEmpId(5);
        userModel.setPastUsername("ruwan1122");
        UserAccountRepository instance = UserAccountRepository.getInstance();
        boolean expResult = true;
        boolean result = instance.editUserAccount(userModel);
        assertEquals(expResult, result);
    }

    
   
    @Test
    public void testGetAllUserAccounts() {
        System.out.println("getAllUserAccounts");
        UserAccountRepository instance = UserAccountRepository.getInstance();
        ArrayList<UserAccountModel> expResult = new ArrayList<UserAccountModel>();
        UserAccountModel model = new UserAccountModel();
        model.setUsername("nipz2123");
        model.setPassword("IMYrQpsb0i4UVniK0cb+MQ==");
        model.setEmail("nipun.jayasanka10@gmail.com");
        model.setStatus("1");
        model.setEmpId(1);
        expResult.add(model);
        
        ArrayList<UserAccountModel> result = instance.getAllUserAccounts();
        assertEquals(expResult.get(0).getUsername(), result.get(0).getUsername());
    }
  
}
