package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Movie;
import service.MovieDBUTIL;

@WebServlet("/DisplayMServlet")
public class DisplayMServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get search and filter parameters from the request
        String search = request.getParameter("search");
        String genre = request.getParameter("genre");
        String rating = request.getParameter("rating");
        String year = request.getParameter("year");

        List<Movie> movies;

        // If no search or filters are applied, fetch all movies
        if (search == null && genre == null && rating == null && year == null) {
            movies = MovieDBUTIL.getMovieDetails();
        } else {
            // Apply search and filters (optional)
            movies = MovieDBUTIL.getFilteredMovies(search, genre, rating, year);
        }

        // Set the movies list as a request attribute
        request.setAttribute("moviesList", movies);

        // Forward to the Movies.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("Movies.jsp");
        dispatcher.forward(request, response);
    }
}
