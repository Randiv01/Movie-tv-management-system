package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DBConnect;
import model.TVSReview;

/**
 * Utility class for handling database operations related to TV series reviews.
 * Provides CRUD (Create, Read, Update, Delete) functionality for TV series reviews.
 * All methods are static for easy access without instantiation.
 */
public abstract class TVSRBDUtil {

    /**
     * Inserts a new TV series review into the database.
     * 
     * @param review The TVSReview object containing all review details
     * @return true if the insertion was successful, false otherwise
     */
    public static boolean insertReview(TVSReview review) {
        boolean isSuccess = false;
        // SQL query with placeholders for all review fields
        String sql = "INSERT INTO tv_series_reviews (tv_series_id, user_id, tv_series_name, username, season, episode, rating, " +
                     "review_title, comment, pros, cons, recommend, review_date, screenshot, agree_terms) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            // Set all parameters for the prepared statement from the review object
            stmt.setInt(1, review.getTvSeriesId());
            stmt.setInt(2, review.getUserId());
            stmt.setString(3, review.getTvSeriesName());
            stmt.setString(4, review.getUsername());
            stmt.setInt(5, review.getSeason());
            stmt.setString(6, review.getEpisode());
            stmt.setInt(7, review.getRating());
            stmt.setString(8, review.getReviewTitle());
            stmt.setString(9, review.getComment());
            stmt.setString(10, review.getPros());
            stmt.setString(11, review.getCons());
            stmt.setString(12, review.getRecommend());
            stmt.setDate(13, review.getReviewDate());
            stmt.setString(14, review.getScreenshot());
            stmt.setBoolean(15, review.isAgreeTerms());

            // Execute the update and check if any rows were affected
            isSuccess = (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            // Log any database errors that occur during insertion
            e.printStackTrace();
        }
        return isSuccess;
    }

    /**
     * Retrieves all reviews for a specific TV series from the database.
     * 
     * @param tvSeriesId The ID of the TV series to get reviews for
     * @return List of TVSReview objects for the specified TV series
     */
    public static List<TVSReview> getReviewsByTVSeriesId(int tvSeriesId) {
        List<TVSReview> reviews = new ArrayList<>();
        // SQL query to select all reviews for a specific TV series
        String sql = "SELECT * FROM tv_series_reviews WHERE tv_series_id = ?";
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, tvSeriesId);
            ResultSet rs = stmt.executeQuery();

            // Process each row in the result set and create TVSReview objects
            while (rs.next()) {
                reviews.add(new TVSReview(
                    rs.getInt("tv_review_id"),
                    rs.getInt("tv_series_id"),
                    rs.getInt("user_id"),
                    rs.getString("tv_series_name"),
                    rs.getString("username"),
                    rs.getInt("season"),
                    rs.getString("episode"),
                    rs.getInt("rating"),
                    rs.getString("review_title"),
                    rs.getString("comment"),
                    rs.getString("pros"),
                    rs.getString("cons"),
                    rs.getString("recommend"),
                    rs.getDate("review_date"),
                    rs.getString("screenshot"),
                    rs.getBoolean("agree_terms")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            // Log any database errors that occur during retrieval
            e.printStackTrace();
        }
        return reviews;
    }

    /**
     * Retrieves a single review by its unique ID.
     * 
     * @param reviewId The ID of the review to retrieve
     * @return TVSReview object if found, null otherwise
     */
    public static TVSReview getReviewById(int reviewId) {
        TVSReview review = null;
        // SQL query to select a specific review by its ID
        String sql = "SELECT * FROM tv_series_reviews WHERE tv_review_id = ?";
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, reviewId);
            ResultSet rs = stmt.executeQuery();

            // If a review is found, create a TVSReview object from the result
            if (rs.next()) {
                review = new TVSReview(
                    rs.getInt("tv_review_id"),
                    rs.getInt("tv_series_id"),
                    rs.getInt("user_id"),
                    rs.getString("tv_series_name"),
                    rs.getString("username"),
                    rs.getInt("season"),
                    rs.getString("episode"),
                    rs.getInt("rating"),
                    rs.getString("review_title"),
                    rs.getString("comment"),
                    rs.getString("pros"),
                    rs.getString("cons"),
                    rs.getString("recommend"),
                    rs.getDate("review_date"),
                    rs.getString("screenshot"),
                    rs.getBoolean("agree_terms")
                );
            }
            rs.close();
        } catch (SQLException e) {
            // Log any database errors that occur during retrieval
            e.printStackTrace();
        }
        return review;
    }

    /**
     * Updates an existing review in the database.
     * 
     * @param review The TVSReview object with updated values
     * @return true if the update was successful, false otherwise
     */
    public static boolean updateReview(TVSReview review) {
        boolean result = false;
        // SQL query to update specific fields of a review
        String sql = "UPDATE tv_series_reviews SET " +
                     "tv_series_name=?, season=?, episode=?, rating=?, " +
                     "review_title=?, comment=?, pros=?, cons=?, recommend=? " +
                     "WHERE tv_review_id=?";
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            // Set all parameters for the update statement
            stmt.setString(1, review.getTvSeriesName());
            stmt.setInt(2, review.getSeason());
            stmt.setString(3, review.getEpisode());
            stmt.setInt(4, review.getRating());
            stmt.setString(5, review.getReviewTitle());
            stmt.setString(6, review.getComment());
            stmt.setString(7, review.getPros());
            stmt.setString(8, review.getCons());
            stmt.setString(9, review.getRecommend());
            stmt.setInt(10, review.getTvReviewId());

            // Execute the update and check if any rows were affected
            result = (stmt.executeUpdate() > 0);
        } catch (SQLException e) {
            // Log any database errors that occur during update
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes a review from the database by its ID.
     * 
     * @param reviewId The ID of the review to delete
     * @return true if the deletion was successful, false otherwise
     */
    public static boolean deleteReviewById(int reviewId) {
        // SQL query to delete a specific review
        String sql = "DELETE FROM tv_series_reviews WHERE tv_review_id = ?";
        
        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, reviewId);
            // Execute the deletion and return whether it was successful
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            // Log any database errors that occur during deletion
            e.printStackTrace();
        }
        return false;
    }
}