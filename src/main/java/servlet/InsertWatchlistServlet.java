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

@WebServlet("/InsertWatchlistServlet")
public class InsertWatchlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertWatchlistServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String userIdParam = request.getParameter("userId");
        String movieIdParam = request.getParameter("movieId");

        if (userIdParam == null || userIdParam.isEmpty() || movieIdParam == null || movieIdParam.isEmpty()) {
            response.getWriter().write("Error: Missing userId or movieId.");
            return;
        }

        try {
            int userId = Integer.parseInt(userIdParam);
            int movieId = Integer.parseInt(movieIdParam);

            boolean isAlreadyInWatchlist = WatchListDBUtil.isMovieInWatchlist(userId, movieId);
            if (isAlreadyInWatchlist) {
                response.sendRedirect("mInfoServlet?movieId=" + movieId + "&watchlistMessage=alreadyInWatchlist");
                return;
            }

            String movieTitle = request.getParameter("movieTitle");
            String genre = request.getParameter("genre");
            String rating = request.getParameter("rating");
            String director = request.getParameter("director");
            String releaseDateStr = request.getParameter("releaseDate");
            String moviePoster = request.getParameter("moviePoster");

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

            boolean isSuccess = WatchListDBUtil.insertIntoWatchlist(userId, movieId, movieTitle, genre, rating, 
                                                                  director, releaseDate, moviePoster);

            if (isSuccess) {
                response.sendRedirect("mInfoServlet?movieId=" + movieId + "&watchlistMessage=success");
            } else {
                response.sendRedirect("mInfoServlet?movieId=" + movieId + "&watchlistMessage=fail");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("mInfoServlet?movieId=" + movieIdParam + "&watchlistMessage=error");
            e.printStackTrace();
        } catch (Exception e) {
            response.sendRedirect("mInfoServlet?movieId=" + movieIdParam + "&watchlistMessage=error");
            e.printStackTrace();
        }
    }
}
