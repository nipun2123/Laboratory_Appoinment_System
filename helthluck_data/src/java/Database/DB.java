/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nipun
 */
public class DB {
    
       
      static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/helthluck";

   static final String USER = "root";
   static final String PASS = "1234";
   
   private static Connection c = null;
   
   public static synchronized Connection getConnection() throws Exception{
       if(c == null){
       Class.forName(JDBC_DRIVER);
           
          c = DriverManager.getConnection(DB_URL, USER, PASS);
       }
       
       return c;
   }
   
   public static boolean iud(String sql){
       
          try {
            int i = getConnection().createStatement().executeUpdate(sql);
              if(i == 1){
              return true;
          }
              
          } catch (Exception ex) {
              ex.printStackTrace();
          }
          
          
          return false;
       
   }
   
   public static int iudReturnId(String sql) {
        try {
            PreparedStatement prepareStatement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatement.executeUpdate();
            ResultSet generatedKeys = prepareStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
   
   
      public static ResultSet search(String sql){
          
          try {
            return getConnection().createStatement().executeQuery(sql);
              
              
          } catch (Exception ex) {
             ex.printStackTrace();
          }
          
          return null;
       
   }
      
    
}
