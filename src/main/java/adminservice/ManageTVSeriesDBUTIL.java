package adminservice;

import controller.DBConnect;
import model.TVSeries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManageTVSeriesDBUTIL {

    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;

    // Get all TV series for admin list
    public static List<TVSeries> getTVSeriesList() {
        List<TVSeries> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement("SELECT * FROM tv_series");
            rs = ps.executeQuery();
            while (rs.next()) {
                TVSeries ts = new TVSeries(
                    rs.getInt("tsid"), rs.getString("title"), rs.getString("description"),
                    rs.getString("genre"), rs.getString("language"), rs.getString("release_date"),
                    rs.getInt("seasons"), rs.getInt("episodes"), rs.getString("duration_per_episode"),
                    rs.getDouble("rating"), rs.getString("poster_url"), rs.getString("trailer_url"),
                    rs.getString("status"), rs.getString("cast"), rs.getString("creator"),
                    rs.getString("download_link")
                );
                list.add(ts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get single TV series info by tsid
    public static List<TVSeries> getTVSeriesInfo(String id) {
        List<TVSeries> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            String sql = "SELECT * FROM tv_series WHERE tsid = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            rs = ps.executeQuery();

            if (rs.next()) {
                TVSeries ts = new TVSeries(
                    rs.getInt("tsid"), rs.getString("title"), rs.getString("description"),
                    rs.getString("genre"), rs.getString("language"), rs.getString("release_date"),
                    rs.getInt("seasons"), rs.getInt("episodes"), rs.getString("duration_per_episode"),
                    rs.getDouble("rating"), rs.getString("poster_url"), rs.getString("trailer_url"),
                    rs.getString("status"), rs.getString("cast"), rs.getString("creator"),
                    rs.getString("download_link")
                );
                list.add(ts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Update TV series
    public static void updateTVSeries(int tsid, String title, String description, String genre,
                                      String language, String release_date, int seasons, int episodes,
                                      String duration, double rating, String poster_url, String trailer_url,
                                      String status, String cast, String creator, String download_link) {
        try {
            con = DBConnect.getConnection();
            String sql = "UPDATE tv_series SET title=?, description=?, genre=?, language=?, release_date=?, " +
                    "seasons=?, episodes=?, duration_per_episode=?, rating=?, poster_url=?, trailer_url=?, " +
                    "status=?, cast=?, creator=?, download_link=? WHERE tsid=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, genre);
            ps.setString(4, language);
            ps.setString(5, release_date);
            ps.setInt(6, seasons);
            ps.setInt(7, episodes);
            ps.setString(8, duration);
            ps.setDouble(9, rating);
            ps.setString(10, poster_url);
            ps.setString(11, trailer_url);
            ps.setString(12, status);
            ps.setString(13, cast);
            ps.setString(14, creator);
            ps.setString(15, download_link);
            ps.setInt(16, tsid);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete TV series by ID (returns success status)
    public static boolean deleteTVSeries(int tsid) {
        boolean success = false;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement("DELETE FROM tv_series WHERE tsid = ?");
            ps.setInt(1, tsid);
            success = ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
