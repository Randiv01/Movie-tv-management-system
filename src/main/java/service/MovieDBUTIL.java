package service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import controller.DBConnect;
import model.Movie;

public class MovieDBUTIL {
    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // Method to get all movie details
    public static List<Movie> getMovieDetails() {
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM movie";
            rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int movieid = rs.getInt(1);
                String mname = rs.getString(2);
                String description = rs.getString(3);
                String genre = rs.getString(4);
                String release_date = rs.getString(5);
                String duration = rs.getString(6);
                String language = rs.getString(7);
                String director = rs.getString(8);
                String cast = rs.getString(9);
                String rating = rs.getString(10);
                String image = rs.getString(11);
                String trailer_url = rs.getString(12);
                String availability = rs.getString(13);
                String download_link = rs.getString(14);

                Movie movie = new Movie(movieid, mname, description, genre, release_date, duration, language, director, cast, rating, image, trailer_url, availability,download_link);
                movies.add(movie); // Add movie to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    // Method to get movie details with filters and search
    public static List<Movie> getFilteredMovies(String search, String genre, String rating, String year) {
        ArrayList<Movie> movies = new ArrayList<>();
        
        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();

            // Base query to select movies
            StringBuilder query = new StringBuilder("SELECT * FROM movie WHERE 1=1");

            // Apply search condition if search text is provided
            if (search != null && !search.isEmpty()) {
                query.append(" AND mname LIKE '%" + search + "%'");
            }

            // Apply genre filter if genre is provided
            if (genre != null && !genre.isEmpty()) {
                query.append(" AND genre = '" + genre + "'");
            }

            // Apply rating filter if rating is provided
            if (rating != null && !rating.isEmpty()) {
                query.append(" AND rating = '" + rating + "'");
            }

            // Apply year filter if year is provided
            if (year != null && !year.isEmpty()) {
                query.append(" AND release_date LIKE '" + year + "%'");
            }

            rs = stmt.executeQuery(query.toString());

            while (rs.next()) {
                int movieid = rs.getInt(1);
                String mname = rs.getString(2);
                String description = rs.getString(3);
                String genreVal = rs.getString(4);
                String release_date = rs.getString(5);
                String duration = rs.getString(6);
                String language = rs.getString(7);
                String director = rs.getString(8);
                String cast = rs.getString(9);
                String ratingVal = rs.getString(10);
                String image = rs.getString(11);
                String trailer_url = rs.getString(12);
                String availability = rs.getString(13);
                String download_link = rs.getString(14);

                Movie movie = new Movie(movieid, mname, description, genreVal, release_date, duration, language, director, cast, ratingVal, image, trailer_url, availability,download_link);
                movies.add(movie);  // Add movie to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }

    // Method to insert movie data into the database
    public static boolean insertMovie(String mname, String description, String genre, 
            String release_date, String duration, String language, String director, 
            String cast, String rating, String image, String trailer_url, String availability, String download_link) {
boolean isSuccess = false;
Connection con = null;
PreparedStatement stmt = null;

try {
con = DBConnect.getConnection();
// Using prepared statement to prevent SQL injection
String sql = "INSERT INTO movie (mname, description, genre, release_date, duration, language, director, cast, rating, image, trailer_url, availability, download_link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
stmt = con.prepareStatement(sql);
stmt.setString(1, mname);
stmt.setString(2, description);
stmt.setString(3, genre);
stmt.setString(4, release_date);
stmt.setString(5, duration);
stmt.setString(6, language);
stmt.setString(7, director);
stmt.setString(8, cast);
stmt.setString(9, rating);
stmt.setString(10, image);
stmt.setString(11, trailer_url);
stmt.setString(12, availability);
stmt.setString(13, download_link);

int result = stmt.executeUpdate(); // Execute the insert statement

if (result > 0) {
isSuccess = true;  // Data successfully inserted
} 
} catch (SQLException e) {
e.printStackTrace();
} finally {
try {
if (stmt != null) stmt.close();
if (con != null) con.close();
} catch (SQLException se) {
se.printStackTrace();
}
}

return isSuccess;
}


    // Method to get movie details by ID
    public static List<Movie> getMovieinfo(String id) {
        int convertedID = Integer.parseInt(id);  // Parse the movie ID from string to int
        ArrayList<Movie> movies = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM movie WHERE movieid=" + convertedID;  // SQL query for the specific movie ID
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int movieid = rs.getInt(1);
                String mname = rs.getString(2);
                String description = rs.getString(3);
                String genre = rs.getString(4);
                String release_date = rs.getString(5);
                String duration = rs.getString(6);
                String language = rs.getString(7);
                String director = rs.getString(8);
                String cast = rs.getString(9);
                String rating = rs.getString(10);
                String image = rs.getString(11);
                String trailer_url = rs.getString(12);
                String availability = rs.getString(13);
                String download_link = rs.getString(14);

                Movie movie = new Movie(movieid, mname, description, genre, release_date, duration, language, director, cast, rating, image, trailer_url, availability,download_link);
                movies.add(movie);  // Add movie to list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movies;
    }
    
 // Method to get download link by movie ID
    public static String getDownloadLinkByMovieId(String movieId) {
        String downloadLink = null;
        String sql = "SELECT download_link FROM movie WHERE movieid = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(movieId));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    downloadLink = rs.getString("download_link");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving download link: " + e.getMessage());
            e.printStackTrace();
        }

        return downloadLink;
    }
}
