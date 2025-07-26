package adminservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import controller.DBConnect;
import model.Movie;

public class ManageMovieDBUTIL {

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static List<Movie> getMovieDetails() {
        ArrayList<Movie> movies = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM movie";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int movieid = rs.getInt("movieid");
                String mname = rs.getString("mname");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                String release_date = rs.getString("release_date");
                String duration = rs.getString("duration");
                String language = rs.getString("language");
                String director = rs.getString("director");
                String cast = rs.getString("cast");
                String rating = rs.getString("rating");
                String image = rs.getString("image");
                String trailer_url = rs.getString("trailer_url");
                String availability = rs.getString("availability");
                String download_link = rs.getString("download_link");

                Movie movie = new Movie(movieid, mname, description, genre, release_date, duration,
                        language, director, cast, rating, image, trailer_url, availability, download_link);
                movies.add(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static void updateMovie(int movieid, String mname, String description, String genre,
                                   String release_date, String duration, String language,
                                   String director, String cast, String rating, String image,
                                   String trailer_url, String availability, String download_link) {
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE movie SET mname=?, description=?, genre=?, release_date=?, duration=?, language=?, " +
                    "director=?, cast=?, rating=?, image=?, trailer_url=?, availability=?, download_link=? WHERE movieid=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, mname);
            ps.setString(2, description);
            ps.setString(3, genre);
            ps.setString(4, release_date);
            ps.setString(5, duration);
            ps.setString(6, language);
            ps.setString(7, director);
            ps.setString(8, cast);
            ps.setString(9, rating);
            ps.setString(10, image);
            ps.setString(11, trailer_url);
            ps.setString(12, availability);
            ps.setString(13, download_link);
            ps.setInt(14, movieid);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteMovie(int movieid) {
        try {
            con = DBConnect.getConnection();
            String sql = "DELETE FROM movie WHERE movieid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, movieid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
