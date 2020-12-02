package persistence;

import Model.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class StaffDAO {


    public static boolean validateUser(String name, String password ) {

        boolean status = false;
        try {
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            String select = "select * from Staff where UserName= '" + name + "' and UserPass='" + password + "'";
            Statement selectStatement = con.createStatement();
            ResultSet rs = selectStatement.executeQuery(select);
            rs.next();
            rs.getString("Locked");
            if(rs.getString("locked").equals("UNLOCKED")){
                status=true;
            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static boolean validatePin(int pin) {

        boolean status = false;
        try {
            System.out.println(pin);
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            String select = "select * from Staff where Pin = " + pin + "";
            System.out.println(select);
            PreparedStatement selectStatement = con.prepareStatement(select);
            ResultSet rs = selectStatement.executeQuery();
            if(rs.next() == true) {
                status = true;
            } else {
                status = false;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }

    public static int lockAttempts(String userName){
        int attempts =0;
        try {
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            String select = "select * from Staff where UserName= ";
            PreparedStatement selectStatement = con.prepareStatement(select);
            selectStatement.setString(1,userName);
            ResultSet rs =selectStatement.executeQuery();
            attempts= rs.getInt("LockAttempt"); // denies access to the user after all attempts are gone
            con.close();
            System.out.println(attempts);
        }catch (Exception e) {
            System.out.println(e);
        }
        return attempts;
    }// get the number attempts

    public static boolean lockAccount(String username){
        String lockedState;
        boolean accountStatus=true;

        try{
            if(lockAttempts(username)<0){
                lockedState="LOCKED";
                Connection con = persistence.DBMySQL.getInstance().getConnection();
                Statement st = con.createStatement();
                String sql1 = "UPDATE Staff set Locked='"+lockedState+"' where UserName='"+username+"'";
                st.executeUpdate(sql1);
                accountStatus=false;
            }
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Updating Data");
        }
        return accountStatus;
    }// Locked the account

    public static String getRole(String name, String password) {
        String role = "dave";
        System.out.println("Name "+name);
        try {
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            String select = "select * from Staff where UserName='"+name+"' and UserPass='"+password+"'";
            PreparedStatement selectStatement = con.prepareStatement(select);
            ResultSet rs = selectStatement.executeQuery(select);
            while (rs.next()) {
                System.out.println(rs.getString("UserName"));
                System.out.println(rs.getString("Role"));
                role = rs.getString("Role");
                System.out.println(role);

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            role = "Exception";
        }
        return role;

    }

    public static boolean checkIfExists(String UserName) {
        boolean status = false;
        try {
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            String select = "select * from Staff where UserName= '" + UserName + "'";
            PreparedStatement selectStatement = con.prepareStatement(select);
            ResultSet rs = selectStatement.executeQuery(select);
            status = rs.next();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;

    }// This checksif the users exist in the database

    public static int addUser(String userName, String userPass, String userPin, String userEmail) {
        int status = 0;
        try {
            String role = "User";
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("insert into Staff(UserName, UserPass,Role, Pin, Email) values(?,?,?,?,?)");
            ps.setString(1, userName);
            ps.setString(2, userPass);
            ps.setString(3, role);
            ps.setString(4, userPin);
            ps.setString(5, userEmail);
            status = ps.executeUpdate(); // return either 1 or 2
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
    public static int deleteUser(String userName) {
        int status = 0;
        String msg = "";
        try {
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM Staff WHERE UserName=?");
            ps.setString(1, userName);
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;

    }// Registering new users

    public static ObservableList<Staff> getAllStaff(){
        ObservableList<Staff> data = FXCollections.observableArrayList();
        try{
            String SQL = "Select * from staff";
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            ResultSet rs = con.createStatement().executeQuery(SQL);
            while(rs.next()){

                String UserName = rs.getString("UserName");
                String Role = rs.getString("Role");
                String Locked = rs.getString("Locked");
                String LockAttempt = String.valueOf(rs.getInt("LockAttempt"));
                Staff cm = new Staff(UserName,Role,Locked,LockAttempt);
                data.add(cm);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }finally {
            return data;
        }
    }// locates all the staff in the database in an array

    public static String updateAttempts(String username) {
        String msg;
        try{
            Connection con = persistence.DBMySQL.getInstance().getConnection();
            Statement st = con.createStatement();
            String sql1 = "UPDATE Staff set LockAttempt=LockAttempt-1 where UserName='"+username+"'";
            st.executeUpdate(sql1);
            msg="Lock Attempt value changed";
        }
        catch(Exception e){
            e.printStackTrace();
            msg="Error on Updating Date";
        }
        return msg;
    }// This updates the amount of attempts 10>0

    public static String updateLockedData(String userName, String lockedState, String role){
        String msg;

        try{

            Connection con = persistence.DBMySQL.getInstance().getConnection();
            Statement st = con.createStatement();
            String sql1 = "UPDATE Staff set Locked='"+lockedState+"' where UserName='"+userName+"'";
            st.executeUpdate(sql1);
            if(lockedState.equals("UNLOCKED")){
                int LockAttempt = role.equals("admin")? 10:10;
                String sql2 = "UPDATE Staff set LockAttempt='"+LockAttempt+"' where UserName='"+userName+"'";
                st.executeUpdate(sql2);
            }
            msg = "Database updated";
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Updating Data");
            msg = "Error on Updating Database";
        }
        return msg;
    }

}
