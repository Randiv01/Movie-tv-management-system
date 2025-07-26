package adminservice;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import admincontrroller.DBConnect;
import adminmodel.Feedback;

public class FeedbackDBUtil {

    // Inserts a new feedback record into the database.
    public static boolean insertFeedback(int messageId, int userId, int adminId,
                                         String adminName, String adminEmail, String adminMobile,
                                         String category, String language, String feedbackMessage,
                                         String attachmentFile) {

        boolean isSuccess = false;

        // SQL query to insert a feedback record
        String sql = "INSERT INTO feedback (message_id, user_id, admin_id, admin_name, admin_email, admin_mobile, " +
                     "category, language, feedback_message, feedback_datetime, attachment_file) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Set values in the SQL query
            pst.setInt(1, messageId);
            pst.setInt(2, userId);
            pst.setInt(3, adminId);
            pst.setString(4, adminName);
            pst.setString(5, adminEmail);
            pst.setString(6, adminMobile);
            pst.setString(7, category);
            pst.setString(8, language);
            pst.setString(9, feedbackMessage);
            pst.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
            pst.setString(11, attachmentFile);

            // Execute the query and check if insertion was successful
            isSuccess = pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    // Retrieves all feedback records from the database
    public static List<Feedback> getAllFeedback() {
        List<Feedback> feedbackList = new ArrayList<>();

        String sql = "SELECT * FROM feedback";

        try (Connection con = DBConnect.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Loop through each row in the result set
            while (rs.next()) {
                // Get values from the current row
                int feedbackId = rs.getInt("feedback_id");
                int messageId = rs.getInt("message_id");
                int userId = rs.getInt("user_id");
                int adminId = rs.getInt("admin_id");
                String adminName = rs.getString("admin_name");
                String adminEmail = rs.getString("admin_email");
                String adminMobile = rs.getString("admin_mobile");
                String category = rs.getString("category");
                String language = rs.getString("language");
                String feedbackMessage = rs.getString("feedback_message");
                LocalDateTime feedbackDatetime = rs.getTimestamp("feedback_datetime").toLocalDateTime();
                String attachmentFile = rs.getString("attachment_file");

                // Create a Feedback object with the values
                Feedback feedback = new Feedback(feedbackId, messageId, userId, adminId, feedbackMessage,
                        feedbackDatetime, category, adminMobile, adminName, adminEmail, language, attachmentFile);

                // Add to the list
                feedbackList.add(feedback);
            }

        } catch (Exception e) {
            e.printStackTrace();  // Print any error to console
        }

        return feedbackList;
    }

    // Updates an existing feedback record in the database.
    public static boolean updateFeedback(int feedbackId, int messageId, int userId, int adminId,
                                         String adminName, String adminEmail, String adminMobile,
                                         String category, String language, String feedbackMessage,
                                         String attachmentFile) {

        boolean isSuccess = false;

        // SQL query to update feedback
        String sql = "UPDATE feedback SET message_id=?, user_id=?, admin_id=?, admin_name=?, admin_email=?, " +
                     "admin_mobile=?, category=?, language=?, feedback_message=?, attachment_file=? " +
                     "WHERE feedback_id=?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Set values in the query
            pst.setInt(1, messageId);
            pst.setInt(2, userId);
            pst.setInt(3, adminId);
            pst.setString(4, adminName);
            pst.setString(5, adminEmail);
            pst.setString(6, adminMobile);
            pst.setString(7, category);
            pst.setString(8, language);
            pst.setString(9, feedbackMessage);
            pst.setString(10, attachmentFile);
            pst.setInt(11, feedbackId);

            // Execute the update and check if successful
            isSuccess = pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    // Deletes a feedback record from the database using the feedback ID.
    public static boolean deleteFeedback(int feedbackId) {
        boolean isSuccess = false;

        // SQL query to delete feedback
        String sql = "DELETE FROM feedback WHERE feedback_id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Set the ID of the feedback to delete
            pst.setInt(1, feedbackId);

            // Execute deletion and check result
            isSuccess = pst.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
