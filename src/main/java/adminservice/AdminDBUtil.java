package adminservice;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; // ✅ fixed import
import java.util.ArrayList;
import java.util.List;

import admincontrroller.DBConnect;
import adminmodel.Admin;
import model.User;

public class AdminDBUtil {
	private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static Admin validateAdmin(String gmail, String password) {
        Admin admin = null;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM admin WHERE agmail = ? AND BINARY apassword = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, gmail);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                admin = new Admin(
                    rs.getInt("aid"),
                    rs.getString("agmail"),
                    rs.getString("apassword"),
                    rs.getString("afullname"),
                    rs.getString("agender"),
                    rs.getString("adob"),
                    rs.getString("amobile"),
                    rs.getString("aaddress"),
                    rs.getString("anic"),
                    rs.getString("admintype")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM user";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("gmail"),
                    rs.getString("password"),
                    rs.getString("country"),
                    rs.getString("gender"),
                    rs.getString("dob"),
                    rs.getString("mobile"),
                    rs.getString("profileimage"),
                    rs.getString("nic"),
                    rs.getString("address")
                );
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public static boolean insertAdmin(Admin admin) {
        boolean isSuccess = false;

        try {
            con = DBConnect.getConnection();
            String sql = "INSERT INTO admin (agmail, apassword, afullname, agender, adob, amobile, aaddress, anic, admintype) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, admin.getGmail());         // from Person
            pst.setString(2, admin.getApassword());
            pst.setString(3, admin.getAfullname());
            pst.setString(4, admin.getGender());        // from Person
            pst.setString(5, admin.getDob());           // from Person
            pst.setString(6, admin.getMobile());        // from Person
            pst.setString(7, admin.getAddress());       // from Person
            pst.setString(8, admin.getNic());           // from Person
            pst.setString(9, admin.getAdmintype());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    
    public static boolean isAdminEmailExists(String gmail) {
        boolean exists = false;
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM admin WHERE agmail = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, gmail);
            rs = pst.executeQuery();

            if (rs.next()) {
                exists = true; // Email already exists
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
    
    public static List<Admin> getAllAdmins() {
        List<Admin> admins = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM admin";
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                Admin admin = new Admin(
                    rs.getInt("aid"),
                    rs.getString("agmail"),
                    rs.getString("apassword"),
                    rs.getString("afullname"),
                    rs.getString("agender"),
                    rs.getString("adob"),
                    rs.getString("amobile"),
                    rs.getString("aaddress"),
                    rs.getString("anic"),
                    rs.getString("admintype")
                );
                admins.add(admin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }
    
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
    
    public boolean updateCustomer(String id, String fname, String lname, String gmail, String password, String country, String gender, String dob, String mobile, String profileimage, String nic, String address) {
        boolean isSuccess = false;
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
    
    public boolean deleteUser(String id) {
        boolean isSuccess = false;

        try (Connection con = DBConnect.getConnection()) {
            String sql = "DELETE FROM user WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    
 // Method to update admin details
    public static boolean updateAdmin(Admin admin) {
        try (Connection conn = DBConnect.getConnection()) {
            String query = "UPDATE admin SET afullname=?, agmail=?, apassword=?, agender=?, adob=?, amobile=?, aaddress=?, anic=?, admintype=? WHERE aid=?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, admin.getAfullname());
                ps.setString(2, admin.getGmail());
                ps.setString(3, admin.getApassword());
                ps.setString(4, admin.getGender());
                ps.setString(5, admin.getDob());
                ps.setString(6, admin.getMobile());
                ps.setString(7, admin.getAddress());
                ps.setString(8, admin.getNic());
                ps.setString(9, admin.getAdmintype());
                ps.setInt(10, admin.getAid()); // Set aid here for update
                
                int result = ps.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to delete admin
    public static boolean deleteAdmin(int adminId) {
        try (Connection conn = DBConnect.getConnection()) {
            String query = "DELETE FROM admin WHERE aid=?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, adminId);
                int result = ps.executeUpdate();
                return result > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
