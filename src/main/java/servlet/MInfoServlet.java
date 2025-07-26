package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import model.Movie;
import model.Review;
import service.MovieDBUTIL;
import service.ReviewDBUtil;

// Servlet responsible for displaying detailed movie information along with user reviews
@WebServlet("/MovieInfoServlet")
public class MInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Default constructor
    public MInfoServlet() {
        super();
    }

    // Handles GET requests (typically triggered when user clicks on a movie)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the movie ID from the request parameters
        String movieId = request.getParameter("movieId");

        // Fetch movie information from the database using the movie ID
        List<Movie> movies = MovieDBUTIL.getMovieinfo(movieId);

        // Convert movie ID to integer to fetch reviews
        int movieIdInt = Integer.parseInt(movieId);

        // Retrieve all reviews related to this movie
        List<Review> reviews = ReviewDBUtil.getReviewsByMovieId(movieIdInt);

        // If movie details are found, pass them to the JSP
        if (movies != null && !movies.isEmpty()) {
            request.setAttribute("moviesList", movies);  // Set movie list for display
        } else {
            // If movie not found, set error message
            request.setAttribute("error", "Movie not found!");
        }

        // Set the list of reviews to be displayed in the JSP
        request.setAttribute("reviews", reviews);

        // Also pass movieId to the JSP (may be used for forms/links)
        request.setAttribute("movieId", movieId);

        // Forward request and response to Movieinfo.jsp for rendering the page
        RequestDispatcher dispatcher = request.getRequestDispatcher("Movieinfo.jsp");
        dispatcher.forward(request, response);
    }

    // Redirect POST requests to be handled by the doGet method
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
