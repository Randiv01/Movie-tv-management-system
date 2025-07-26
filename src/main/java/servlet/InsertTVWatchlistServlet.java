package servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.WatchListDBUtil;

@WebServlet("/InsertTVWatchlistServlet")
public class InsertTVWatchlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertTVWatchlistServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String userIdParam = request.getParameter("userId");
        String tvSeriesIdParam = request.getParameter("tvSeriesId");

        if (userIdParam == null || userIdParam.isEmpty() || tvSeriesIdParam == null || tvSeriesIdParam.isEmpty()) {
            response.getWriter().write("Error: Missing userId or tvSeriesId.");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdParam);
            int tvSeriesId = Integer.parseInt(tvSeriesIdParam);

            boolean isAlreadyInWatchlist = WatchListDBUtil.isTVSeriesInWatchlist(userId, tvSeriesId);
            if (isAlreadyInWatchlist) {
                response.sendRedirect("TVSeriesInfoServlet?seriesId=" + tvSeriesId + "&watchlistMessage=This TV-Series is already in your watchlist.");
                return;
            }

            // Retrieve other form parameters
            String tvSeriesTitle = request.getParameter("tvSeriesTitle");
            String genre = request.getParameter("genre");
            String ratingParam = request.getParameter("rating");
            String creator = request.getParameter("creator");
            String releaseDateStr = request.getParameter("releaseDate");
            String tvPoster = request.getParameter("tvPoster");

            // Convert rating to double
            double rating = 0;
            if (ratingParam != null && !ratingParam.isEmpty()) {
                try {
                    rating = Double.parseDouble(ratingParam);
                } catch (NumberFormatException e) {
                    response.getWriter().write("Error: Invalid rating format.");
                    return;
                }
            }

            // Convert release date to java.sql.Date
            Date releaseDate = null;
            if (releaseDateStr != null && !releaseDateStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    releaseDate = new Date(sdf.parse(releaseDateStr).getTime());
                } catch (Exception e) {
                    response.getWriter().write("Error: Invalid date format.");
                    return;
                }
            }

            // Insert the TV series into the watchlist
            boolean isSuccess = WatchListDBUtil.insertIntoTVWatchlist(userId, tvSeriesId, tvSeriesTitle, genre, rating, 
                                                                       creator, releaseDate, tvPoster);

            if (isSuccess) {
                response.sendRedirect("TVSeriesInfoServlet?seriesId=" + tvSeriesId + "&watchlistMessage=TV-Series added to watchlist successfully!");
            } else {
                response.sendRedirect("TVSeriesInfoServlet?seriesId=" + tvSeriesId + "&watchlistMessage=fail");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("TVSeriesInfoServlet?seriesId=" + tvSeriesIdParam + "&watchlistMessage=error");
            e.printStackTrace();
        } catch (Exception e) {
            response.sendRedirect("TVSeriesInfoServlet?seriesId=" + tvSeriesIdParam + "&watchlistMessage=error");
            e.printStackTrace();
        }
    }
}
