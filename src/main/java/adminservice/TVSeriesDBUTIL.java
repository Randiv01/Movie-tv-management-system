package adminservice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import controller.DBConnect;

public class TVSeriesDBUTIL {

    public static boolean insertTVSeries(
        String title,
        String description,
        String genre,
        String language,
        String release_date,
        int seasons,
        int episodes,
        String duration_per_episode,
        double rating,
        String poster_url,
        String trailer_url,
        String status,
        String cast,
        String creator,
        String download_link
    ) {
        boolean isSuccess = false;

        try {
            Connection con = DBConnect.getConnection();

            String sql = "INSERT INTO tv_series (title, description, genre, language, release_date, seasons, episodes, duration_per_episode, rating, poster_url, trailer_url, status, cast, creator, download_link) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, genre);
            ps.setString(4, language);
            ps.setString(5, release_date);
            ps.setInt(6, seasons);
            ps.setInt(7, episodes);
            ps.setString(8, duration_per_episode);
            ps.setDouble(9, rating);
            ps.setString(10, poster_url);
            ps.setString(11, trailer_url);
            ps.setString(12, status);
            ps.setString(13, cast);
            ps.setString(14, creator);
            ps.setString(15, download_link);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                isSuccess = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
