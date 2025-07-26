package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DBConnect;
import model.Review;

public class ReviewDBUtil {

    // Method to insert a new review into the database
    public static boolean insertReview(String movieId, String userId, String movieName, String username, String rating,
                                        String reviewTitle, String comment, String pros, String cons, String recommend, String reviewDate) {
        boolean result = false;
        Connection con = DBConnect.getConnection();

        // SQL query to insert a new review
        String query = "INSERT INTO reviews (movie_id, user_id, movie_name, username, rating, review_title, comment, pros, cons, recommend, review_date) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // Set values in the prepared statement using parsed and raw inputs
            ps.setInt(1, Integer.parseInt(movieId));  // movie ID as integer
            ps.setInt(2, Integer.parseInt(userId));   // user ID as integer
            ps.setString(3, movieName);
            ps.setString(4, username);
            ps.setInt(5, Integer.parseInt(rating));   // rating as integer
            ps.setString(6, reviewTitle);
            ps.setString(7, comment);
            ps.setString(8, pros);
            ps.setString(9, cons);
            ps.setString(10, recommend);
            ps.setDate(11, Date.valueOf(reviewDate)); // assuming date format is YYYY-MM-DD

            // Execute insert operation
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                result = true; // insertion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace(); // log any SQL exceptions
        }

        return result;
    }

    // Method to retrieve all reviews for a specific movie using movieId
    public static List<Review> getReviewsByMovieId(int movieId) {
        List<Review> reviews = new ArrayList<>();

        // SQL query to select all reviews by movie ID
        String query = "SELECT * FROM reviews WHERE movie_id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, movieId); // set movieId parameter in query

            try (ResultSet rs = ps.executeQuery()) {
                // Loop through the result set and create Review objects
                while (rs.next()) {
                    int reviewId = rs.getInt("reviewid");
                    int movieIdFromDB = rs.getInt("movie_id");
                    int userId = rs.getInt("user_id");
                    String movieName = rs.getString("movie_name");
                    String username = rs.getString("username");
                    int rating = rs.getInt("rating");
                    String reviewTitle = rs.getString("review_title");
                    String comment = rs.getString("comment");
                    String pros = rs.getString("pros");
                    String cons = rs.getString("cons");
                    String recommend = rs.getString("recommend");
                    Date reviewDate = rs.getDate("review_date");

                    // Create Review object and add to the list
                    Review review = new Review(
                        reviewId, movieIdFromDB, userId, movieName, username,
                        rating, reviewTitle, comment, pros, cons, recommend, reviewDate
                    );

                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // handle any SQL exceptions
        }

        return reviews; // return list of reviews
    }
    
    // Method to retrieve all reviews made by a specific user
    public static List<Review> getReviewsByUserId(int userId) {
        List<Review> reviews = new ArrayList<>();
        String query = "SELECT * FROM reviews WHERE user_id = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, userId); // set userId parameter in query

            try (ResultSet rs = ps.executeQuery()) {
                // Loop through the result set to create Review objects
                while (rs.next()) {
                    int reviewId = rs.getInt("reviewid");
                    int movieId = rs.getInt("movie_id");
                    String movieName = rs.getString("movie_name");
                    String username = rs.getString("username");
                    int rating = rs.getInt("rating");
                    String reviewTitle = rs.getString("review_title");
                    String comment = rs.getString("comment");
                    String pros = rs.getString("pros");
                    String cons = rs.getString("cons");
                    String recommend = rs.getString("recommend");
                    Date reviewDate = rs.getDate("review_date");

                    // Create Review object and add to the list
                    Review review = new Review(
                        reviewId, movieId, userId, movieName, username,
                        rating, reviewTitle, comment, pros, cons, recommend, reviewDate
                    );

                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // handle exceptions
        }

        return reviews; // return list of reviews by user
    }
    
    // Method to delete a review from the database using its ID
    public static boolean deleteReviewById(int reviewId) {
        boolean isDeleted = false;
        String query = "DELETE FROM reviews WHERE reviewid = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, reviewId); // set the review ID
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                isDeleted = true; // deletion was successful
            }
        } catch (SQLException e) {
            e.printStackTrace(); // log errors
        }

        return isDeleted; // return deletion status
    }

    // Method to get a single review by its ID
    public static Review getReviewById(int reviewId) {
        String query = "SELECT * FROM reviews WHERE reviewid = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, reviewId); // set review ID

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return Review object populated with data from the database
                return new Review(
                    rs.getInt("reviewid"),
                    rs.getInt("movie_id"),
                    rs.getInt("user_id"),
                    rs.getString("movie_name"),
                    rs.getString("username"),
                    rs.getInt("rating"),
                    rs.getString("review_title"),
                    rs.getString("comment"),
                    rs.getString("pros"),
                    rs.getString("cons"),
                    rs.getString("recommend"),
                    rs.getDate("review_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // handle SQL exception
        }

        return null; // return null if review not found
    }

    // Method to update an existing review in the database
    public static boolean updateReview(int reviewId, String movieName, String reviewTitle, int rating,
            String comment, String pros, String cons, String recommend) {

        boolean result = false;

        // SQL query to update review data
        String query = "UPDATE reviews SET movie_name = ?, review_title = ?, rating = ?, comment = ?, pros = ?, cons = ?, recommend = ? WHERE reviewid = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Set the updated values
            ps.setString(1, movieName);
            ps.setString(2, reviewTitle);
            ps.setInt(3, rating);
            ps.setString(4, comment);
            ps.setString(5, pros);
            ps.setString(6, cons);
            ps.setString(7, recommend);
            ps.setInt(8, reviewId); // specify which review to update

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                result = true; // update was successful
            }
        } catch (SQLException e) {
            e.printStackTrace(); // handle exceptions
        }

        return result; // return update status
    }

}
