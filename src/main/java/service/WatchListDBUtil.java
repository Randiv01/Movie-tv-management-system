package service;

import java.sql.Date;

import model.TVSeriesWatchlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import controller.DBConnect;
import model.Watchlist;

public class WatchListDBUtil {
    
    // Method to insert into the watchlist
    public static boolean insertIntoWatchlist(int userId, int movieId, String movieTitle, String genre, String rating, 
            String director, Date releaseDate, String moviePoster) {
        boolean isSuccess = false;
        try (Connection con = DBConnect.getConnection()) {
            String sql = "INSERT INTO watchlist (user_id, movie_id, movie_title, genre, rating, director, release_date, movie_poster) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            ps.setString(3, movieTitle);
            ps.setString(4, genre);
            ps.setString(5, rating);
            ps.setString(6, director);
            ps.setDate(7, releaseDate);
            ps.setString(8, moviePoster);

            int rows = ps.executeUpdate();
            isSuccess = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Method to check if a movie is already in the user's watchlist
    public static boolean isMovieInWatchlist(int userId, int movieId) {
        boolean isExist = false;
        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT COUNT(*) FROM watchlist WHERE user_id = ? AND movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isExist = true; // Movie is already in the watchlist
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }
    
 // Method to get all movies in the watchlist for a specific user
    public static List<Watchlist> getWatchlistForUser(int userId) {
        List<Watchlist> watchlist = new ArrayList<>();
        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT * FROM watchlist WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Watchlist watch = new Watchlist(
                        rs.getInt("watchlist_id"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id"),
                        rs.getString("movie_title"),
                        rs.getString("genre"),
                        rs.getString("rating"),
                        rs.getString("director"),
                        rs.getDate("release_date"),
                        rs.getTimestamp("added_date"),
                        rs.getString("movie_poster")
                );

                watchlist.add(watch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchlist;
    }
    
 // Method to remove a movie from the user's watchlist
    public static boolean removeFromWatchlist(int userId, int movieId) {
        boolean isSuccess = false;
        try (Connection con = DBConnect.getConnection()) {
            String sql = "DELETE FROM watchlist WHERE user_id = ? AND movie_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, movieId);

            int rows = ps.executeUpdate();
            isSuccess = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    
    //search button
    public static List<Watchlist> searchWatchlistByTitle(int userId, String keyword) {
        List<Watchlist> watchlist = new ArrayList<>();
        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT * FROM watchlist WHERE user_id = ? AND LOWER(movie_title) LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + keyword.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Watchlist watch = new Watchlist(
                        rs.getInt("watchlist_id"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id"),
                        rs.getString("movie_title"),
                        rs.getString("genre"),
                        rs.getString("rating"),
                        rs.getString("director"),
                        rs.getDate("release_date"),
                        rs.getTimestamp("added_date"),
                        rs.getString("movie_poster")
                );
                watchlist.add(watch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchlist;
    }
    
    // Method to insert TV series into the watchlist
    public static boolean insertIntoTVWatchlist(int userId, int tvSeriesId, String tvSeriesTitle, String genre, double rating, 
                                                String creator, Date releaseDate, String tvPoster) {
        boolean isSuccess = false;
        try (Connection con = DBConnect.getConnection()) {
            String sql = "INSERT INTO TVSeriesWatchlist (user_id, tv_series_id, title, genre, rating, creator, release_date, poster_url) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, tvSeriesId);
            ps.setString(3, tvSeriesTitle);
            ps.setString(4, genre);
            ps.setDouble(5, rating);
            ps.setString(6, creator);
            ps.setDate(7, releaseDate);
            ps.setString(8, tvPoster);

            int rows = ps.executeUpdate();
            isSuccess = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Method to check if TV series is already in the user's watchlist
    public static boolean isTVSeriesInWatchlist(int userId, int tvSeriesId) {
        boolean isExist = false;
        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT COUNT(*) FROM TVSeriesWatchlist WHERE user_id = ? AND tv_series_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, tvSeriesId);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                isExist = true; // TV Series is already in the watchlist
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isExist;
    }

    // Method to get all TV series in the watchlist for a specific user
    public static List<TVSeriesWatchlist> getTVWatchlistForUser(int userId) {
        List<TVSeriesWatchlist> watchlist = new ArrayList<>();
        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT * FROM TVSeriesWatchlist WHERE user_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TVSeriesWatchlist watch = new TVSeriesWatchlist(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("tv_series_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("rating"),
                        rs.getString("creator"),
                        rs.getDate("release_date"),
                        rs.getString("poster_url"),
                        rs.getTimestamp("added_on")
                );
                watchlist.add(watch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchlist;
    }

    // Method to remove a TV series from the user's watchlist
    public static boolean removeFromTVWatchlist(int userId, int tvSeriesId) {
        boolean isSuccess = false;
        try (Connection con = DBConnect.getConnection()) {
            String sql = "DELETE FROM TVSeriesWatchlist WHERE user_id = ? AND tv_series_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, tvSeriesId);

            int rows = ps.executeUpdate();
            isSuccess = rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Method to search TV series in the watchlist by title
    public static List<TVSeriesWatchlist> searchTVWatchlistByTitle(int userId, String keyword) {
        List<TVSeriesWatchlist> watchlist = new ArrayList<>();
        try (Connection con = DBConnect.getConnection()) {
            String sql = "SELECT * FROM TVSeriesWatchlist WHERE user_id = ? AND LOWER(title) LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, "%" + keyword.toLowerCase() + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TVSeriesWatchlist watch = new TVSeriesWatchlist(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("tv_series_id"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getDouble("rating"),
                        rs.getString("creator"),
                        rs.getDate("release_date"),
                        rs.getString("poster_url"),
                        rs.getTimestamp("added_on")
                );
                watchlist.add(watch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchlist;
    }


}
