package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import controller.DBConnect;
import model.TVSeries;

public class TVSriesDBUtil {

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // Method to get all TV Series details
    public static List<TVSeries> getAllTVSeries() {
        ArrayList<TVSeries> seriesList = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM tv_series";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int tsid = rs.getInt("tsid");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                String language = rs.getString("language");
                String release_date = rs.getString("release_date");
                int seasons = rs.getInt("seasons");
                int episodes = rs.getInt("episodes");
                String duration_per_episode = rs.getString("duration_per_episode");
                double rating = rs.getDouble("rating"); 
                String poster_url = rs.getString("poster_url");
                String trailer_url = rs.getString("trailer_url");
                String status = rs.getString("status");
                String cast = rs.getString("cast");
                String creator = rs.getString("creator");
                String download_link = rs.getString("download_link");

                TVSeries tv = new TVSeries(tsid, title, description, genre, language, release_date,
                        seasons, episodes, duration_per_episode, rating, poster_url,
                        trailer_url, status, cast, creator, download_link);
                seriesList.add(tv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return seriesList;
    }

    // Method to filter TV Series based on user input
    public static List<TVSeries> getFilteredTVSeries(String search, String genre, String rating, String year) {
        ArrayList<TVSeries> seriesList = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();

            StringBuilder query = new StringBuilder("SELECT * FROM tv_series WHERE 1=1");

            if (search != null && !search.isEmpty()) {
                query.append(" AND title LIKE '%" + search + "%'");
            }

            if (genre != null && !genre.isEmpty()) {
                query.append(" AND genre = '" + genre + "'");
            }

            if (rating != null && !rating.isEmpty()) {
                query.append(" AND rating = '" + rating + "'");
            }

            if (year != null && !year.isEmpty()) {
                query.append(" AND release_date LIKE '" + year + "%'");
            }

            rs = stmt.executeQuery(query.toString());

            while (rs.next()) {
                int tsid = rs.getInt("tsid");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genreVal = rs.getString("genre");
                String language = rs.getString("language");
                String release_date = rs.getString("release_date");
                int seasons = rs.getInt("seasons");
                int episodes = rs.getInt("episodes");
                String duration_per_episode = rs.getString("duration_per_episode");
                double ratingVal = rs.getDouble("rating"); 
                String poster_url = rs.getString("poster_url");
                String trailer_url = rs.getString("trailer_url");
                String status = rs.getString("status");
                String cast = rs.getString("cast");
                String creator = rs.getString("creator");
                String download_link = rs.getString("download_link");

                TVSeries tv = new TVSeries(tsid, title, description, genreVal, language, release_date,
                        seasons, episodes, duration_per_episode, ratingVal, poster_url,
                        trailer_url, status, cast, creator, download_link);
                seriesList.add(tv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return seriesList;
    }
    
 // Method to get TV Series details by ID
    public static List<TVSeries> getTVSeriesInfo(String id) {
        int convertedID = Integer.parseInt(id); // Convert ID to int
        ArrayList<TVSeries> seriesList = new ArrayList<>();

        try {
            con = DBConnect.getConnection();
            stmt = con.createStatement();
            String sql = "SELECT * FROM tv_series WHERE tsid = " + convertedID;
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int tsid = rs.getInt("tsid");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String genre = rs.getString("genre");
                String language = rs.getString("language");
                String release_date = rs.getString("release_date");
                int seasons = rs.getInt("seasons");
                int episodes = rs.getInt("episodes");
                String duration_per_episode = rs.getString("duration_per_episode");
                double rating = rs.getDouble("rating");
                String poster_url = rs.getString("poster_url");
                String trailer_url = rs.getString("trailer_url");
                String status = rs.getString("status");
                String cast = rs.getString("cast");
                String creator = rs.getString("creator");
                String download_link = rs.getString("download_link");

                TVSeries tv = new TVSeries(tsid, title, description, genre, language, release_date,
                        seasons, episodes, duration_per_episode, rating, poster_url,
                        trailer_url, status, cast, creator, download_link);

                seriesList.add(tv); // Add to list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return seriesList;
    }

    
 // Method to get download link by TV Series ID
    public static String getDownloadLinkByTVSeriesId(String tvSeriesId) {
        String downloadLink = null;
        String sql = "SELECT download_link FROM tv_series WHERE tsid = ?";

        try (Connection con = DBConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, Integer.parseInt(tvSeriesId));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    downloadLink = rs.getString("download_link");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving TV series download link: " + e.getMessage());
            e.printStackTrace();
        }

        return downloadLink;
    }


}
