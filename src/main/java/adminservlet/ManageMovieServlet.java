package adminservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminservice.ManageMovieDBUTIL;
import model.Movie;

@WebServlet("/ManageMovieServlet")
public class ManageMovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ManageMovieServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch movies from DBUTIL
            List<Movie> movieList = ManageMovieDBUTIL.getMovieDetails();

            // Set movie list in request
            request.setAttribute("movieList", movieList);

            // Forward to ManageMovies.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("ManageMovies.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMsg", "Error loading movie list.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("ManageMovies.jsp");
            dispatcher.forward(request, response);
        }
    }

    // Optional: handle POST by redirecting to doGet
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
