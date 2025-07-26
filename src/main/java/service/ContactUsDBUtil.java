package service;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import adminmodel.Feedback;
import controller.DBConnect;
import model.ContactUs;

public class ContactUsDBUtil {

    // Insert new contact message
    public static boolean insertMessage(String cid, String cfname, String cgmail, String cmobile, String cmessage, String submittedAt) {
        boolean isSuccess = false;
        String sql = "INSERT INTO contactus (cid, cfname, cgmail, cmobile, cmessage, datetime) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cid);
            ps.setString(2, cfname);
            ps.setString(3, cgmail);
            ps.setString(4, cmobile);
            ps.setString(5, cmessage);
            ps.setString(6, submittedAt);

            int rows = ps.executeUpdate();
            isSuccess = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    // Get contact messages by user ID
    public static List<ContactUs> getMessagesByUserId(int userId) {
        List<ContactUs> messages = new ArrayList<>();
        String sql = "SELECT * FROM contactus WHERE cid = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ContactUs c = new ContactUs(
                        rs.getInt("mid"),
                        rs.getInt("cid"),
                        rs.getString("cfname"),
                        rs.getString("cgmail"),
                        rs.getString("cmobile"),
                        rs.getString("cmessage"),
                        rs.getString("datetime")
                    );
                    messages.add(c);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }

    public static List<Feedback> getFeedbacksByUserId(int userId) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE user_id = ?";

        try (Connection con = DBConnect.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, userId);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Timestamp ts = rs.getTimestamp("feedback_datetime");
                    LocalDateTime feedbackDatetime = (ts != null) ? ts.toLocalDateTime() : null;

                    Feedback fb = new Feedback(
                        rs.getInt("feedback_id"),
                        rs.getInt("message_id"),
                        rs.getInt("user_id"),
                        rs.getInt("admin_id"),
                        rs.getString("feedback_message"),
                        feedbackDatetime,
                        rs.getString("category"),
                        rs.getString("admin_mobile"),
                        rs.getString("admin_name"),
                        rs.getString("admin_email"),
                        rs.getString("language"),
                        rs.getString("attachment_file")
                    );

                    feedbackList.add(fb);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return feedbackList;
    }

}
