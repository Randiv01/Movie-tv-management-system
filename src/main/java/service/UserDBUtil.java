package service;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import controller.DBConnect;
import model.User;

public class UserDBUtil implements UserDAO {
    private static boolean isSuccess;
    private static Connection con = null;
    @SuppressWarnings("unused")
	private static Statement stmt = null;
    private static ResultSet rs = null;

    // Hash password using SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // check user gmail and password are correct(user login)
    //polymorphism
    @Override
    public boolean validate(String gmail, String password) {
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM user WHERE gmail = ? AND BINARY password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, gmail);
            pst.setString(2, hashPassword(password));
            rs = pst.executeQuery();
            isSuccess = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // check admin gmail and password are correct(admin login)
    //polymorphism
    @Override
    public boolean validateAdmin(String gmail, String password) {
        boolean isAdminValid = false;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM admin WHERE agmail = ? AND BINARY apassword = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, gmail);
            pst.setString(2, hashPassword(password));
            rs = pst.executeQuery();
            isAdminValid = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isAdminValid;
    }

    //check user is already register or not
    //polymorphism
    @Override
    public boolean isEmailRegistered(String email) {
        boolean isRegistered = false;
        try {
            con = DBConnect.getConnection();
            String query = "SELECT * FROM user WHERE gmail = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            rs = pst.executeQuery();
            isRegistered = rs.next();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    //insert user register details to the table
    @Override
    public boolean insertUser(String fname, String lname, String gmail, String password, String country, String gender, String dob, String mobile, String profileimage, String nic, String address) {
        boolean isSuccess = false;
        try {
            if (isEmailRegistered(gmail)) return false;

            con = DBConnect.getConnection();
            String hashedPassword = hashPassword(password);
            String sql = "INSERT INTO user VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, gmail);
            pst.setString(4, hashedPassword);
            pst.setString(5, country);
            pst.setString(6, gender);
            pst.setString(7, dob);
            pst.setString(8, mobile);
            pst.setString(9, profileimage);
            pst.setString(10, nic);
            pst.setString(11, address);
            int result = pst.executeUpdate();
            isSuccess = result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    //update user details
    @Override
    public boolean updateCustomer(String id, String fname, String lname, String gmail, String password, String country, String gender, String dob, String mobile, String profileimage, String nic, String address) {
        try {
            con = DBConnect.getConnection();
            String hashedPassword = hashPassword(password);
            String sql = "UPDATE user SET fname=?, lname=?, gmail=?, password=?, country=?, gender=?, dob=?, mobile=?, profileimage=?, nic=?, address=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, fname);
            pst.setString(2, lname);
            pst.setString(3, gmail);
            pst.setString(4, hashedPassword);
            pst.setString(5, country);
            pst.setString(6, gender);
            pst.setString(7, dob);
            pst.setString(8, mobile);
            pst.setString(9, profileimage);
            pst.setString(10, nic);
            pst.setString(11, address);
            pst.setInt(12, Integer.parseInt(id));
            int rs = pst.executeUpdate();
            isSuccess = rs > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    //select specific user details using his id
    @Override
    public List<User> getCustomer(String gmail) {
        ArrayList<User> users = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM user WHERE gmail = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, gmail);
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User(
                    rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12)
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    //select specific user details using his id
    @Override
    public List<User> getCustomerDetails(String id) {
        ArrayList<User> customers = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User(
                    rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9),
                    rs.getString(10), rs.getString(11), rs.getString(12)
                );
                customers.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }
    
    //delete user profile image from folder
    public String getProfileImageNameByUserId(String id) {
        String profileImage = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT profileimage FROM user WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                profileImage = rs.getString("profileimage");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profileImage;
    }


    //delete the user using his id
    @Override
    public boolean deleteCustomer(String id) {
        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            int result = pst.executeUpdate();
            isSuccess = result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    //check enterd details are correct for change password
    @Override
    public boolean isUserValidForPasswordReset(String email, String mobile, String nic) {
        boolean isValid = false;
        try {
            con = DBConnect.getConnection();
            String query = "SELECT * FROM user WHERE gmail = ? AND mobile = ? AND nic = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, email);
            pst.setString(2, mobile);
            pst.setString(3, nic);
            rs = pst.executeQuery();
            isValid = rs.next();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isValid;
    }

    //update the user password
    @Override
    public boolean updatePassword(String email, String newPassword) {
        boolean isSuccess = false;
        try {
            con = DBConnect.getConnection();
            String hashedPassword = hashPassword(newPassword);
            String sql = "UPDATE user SET password = ? WHERE gmail = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, hashedPassword);
            pst.setString(2, email);
            int result = pst.executeUpdate();
            isSuccess = result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    //if not any users in table ,show all null
	@Override
	public List<User> getAllUsers() {
		return null;
	}
}
