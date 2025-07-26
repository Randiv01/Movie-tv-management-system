package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.TVSeries;
import service.TVSriesDBUtil;

@WebServlet("/DisplayTVServlet")
public class DisplayTVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get search and filter parameters from the request
        String search = request.getParameter("search");
        String genre = request.getParameter("genre");
        String rating = request.getParameter("rating");
        String year = request.getParameter("year");

        List<TVSeries> tvSeriesList;

        // If no search or filters are applied, fetch all TV series
        if (search == null && genre == null && rating == null && year == null) {
            tvSeriesList = TVSriesDBUtil.getAllTVSeries();
        } else {
            // Apply search and filters (optional)
            tvSeriesList = TVSriesDBUtil.getFilteredTVSeries(search, genre, rating, year);
        }

        // Set the TV series list as a request attribute
        request.setAttribute("tvSeriesList", tvSeriesList);

        // Forward to the TVSeries.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("tv_series.jsp");
        dispatcher.forward(request, response);
    }
}
