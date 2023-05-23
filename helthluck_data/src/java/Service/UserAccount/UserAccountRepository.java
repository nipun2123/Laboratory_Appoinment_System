/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.UserAccount;

import Database.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nipun
 */
public class UserAccountRepository {

    private static UserAccountRepository accountRepository = null;

    public static synchronized UserAccountRepository getInstance() {
        if (accountRepository == null) {
            accountRepository = new UserAccountRepository();
        }

        return accountRepository;
    }

    private UserAccountRepository() {
    }

    public boolean addUserAccount(UserAccountModel userModel) {
        String query = "Insert into user_account(username, password,email, status, employee_idemployee) VALUES('" + userModel.getUsername() + "',"
                + " '" + userModel.getPassword() + "','" + userModel.getEmail() + "', '" + userModel.getStatus() + "', '" + userModel.getEmpId() + "' ) ";

        return DB.iud(query);
    }

    public boolean editUserAccount(UserAccountModel userModel) {

        String query = "UPDATE user_account SET username = '" + userModel.getUsername() + "', password = '" + userModel.getPassword() + "', "
                + " email = '" + userModel.getEmail() + "', status = '" + userModel.getStatus() + "', employee_idemployee = '" + userModel.getEmpId() + "' "
                + "WHERE username = '" + userModel.getPastUsername() + "' ";

        return DB.iud(query);

    }

    public ArrayList<UserAccountModel> getAllUserAccounts() {

        ArrayList<UserAccountModel> userAccountList = new ArrayList<UserAccountModel>();

        String query = "SELECT username,password,u.`status`, CONCAT(fname,' ', mname,' ',lname) AS empname, u.email FROM user_account u INNER JOIN employee e ON u.employee_idemployee = e.idemployee";

        ResultSet result = DB.search(query);

        try {
            while (result.next()) {
                UserAccountModel model = new UserAccountModel();
                model.setUsername(result.getString("username"));
                model.setPassword(result.getString("password"));
                model.setStatus(result.getString("u.status"));
                model.setEmpName(result.getString("empname"));
                model.setEmail(result.getString("u.email"));

                userAccountList.add(model);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userAccountList;
    }

    public boolean isUsernameExists(String username) {
        String query = "SELECT username FROM user_account WHERE username = '" + username + "' ";

        ResultSet result = DB.search(query);

        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<String> getAllEmpNoAcc() {

        ArrayList<String> empList = new ArrayList<String>();

        String query = "SELECT CONCAT(fname,' ', mname,' ',lname) AS empname FROM employee WHERE status ='1' ";

        ResultSet result = DB.search(query);

        try {
            while (result.next()) {

                empList.add(result.getString("empname"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return empList;
    }

    public UserAccountModel checkUsernamePassword(String username, String password) {
        String query = "SELECT iduser_account, username, role, CONCAT(fname,' ',lname) AS empname FROM employee e INNER JOIN user_account u ON "
                + "u.employee_idemployee = e.idemployee WHERE e.`status` = '1' AND u.`status` = '1' AND u.username = '" + username + "' "
                + "AND u.password = '" + password + "' ";

        ResultSet result = DB.search(query);
        UserAccountModel userModel = new UserAccountModel();
        try {
            if (result.next()) {
                userModel.setUserId(result.getString("iduser_account"));
                userModel.setUsername(result.getString("username"));
                userModel.setRole(result.getString("role"));
                userModel.setEmpName(result.getString("empname"));
                return userModel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        userModel = null;
        return userModel;
    }

    public boolean editPassword(UserAccountModel userModel) {

        String query = "UPDATE user_account SET password = '" + userModel.getPassword() + "' WHERE username = '" + userModel.getUsername() + "' ";

        return DB.iud(query);

    }

    public String checkPatientUseridPassword(String userId, String password) {
        String query = "SELECT CONCAT(fname, ' ', lname) AS name FROM patient WHERE id = '" + userId + "' AND password = '" + password + "' ";

        ResultSet result = DB.search(query);
        try {
            if (result.next()) {
                return result.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void main(String[] args) {

        UserAccountModel m = new UserAccountModel();
        m.setUsername("nipz2123");
        m.setPassword("nipz102030");
//        System.out.println(new UserAccountRepository().isPasswordCorrect(m));
    }
}
