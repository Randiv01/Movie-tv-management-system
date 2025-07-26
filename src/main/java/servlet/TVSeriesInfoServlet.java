package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import model.TVSeries;
import model.TVSReview;
import service.TVSriesDBUtil;
import service.TVSRBDUtil;

// Servlet mapping for handling requests to view a specific TV series and its reviews
@WebServlet("/TVSeriesInfoServlet")
public class TVSeriesInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Default constructor
    public TVSeriesInfoServlet() {
        super();
    }

    // Handles GET requests (main logic for loading TV series details and reviews)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve the "seriesId" parameter from the request
        String seriesId = request.getParameter("seriesId");

        // Validate the seriesId input
        if (seriesId == null || seriesId.isEmpty()) {
            // If seriesId is missing or invalid, show an error message and forward to JSP
            request.setAttribute("error", "Invalid TV Series ID!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("tv_seriesinfo.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            // Retrieve TV series details from the database using the given seriesId
            List<TVSeries> seriesList = TVSriesDBUtil.getTVSeriesInfo(seriesId);
            request.setAttribute("tvSeriesList", seriesList); // Pass the series details to the JSP

            // Convert seriesId to integer for fetching associated reviews
            int seriesIdInt = Integer.parseInt(seriesId);

            // Retrieve list of reviews for the specified TV series
            List<TVSReview> tvReviewList = TVSRBDUtil.getReviewsByTVSeriesId(seriesIdInt);
            request.setAttribute("tvReviewList", tvReviewList); // Pass the reviews to the JSP

        } catch (Exception e) {
            // Handle any exceptions (e.g., DB access or parsing errors)
            e.printStackTrace();
            request.setAttribute("error", "Error occurred while fetching TV series details or reviews.");
        }

        // Forward the request to the JSP page that displays the series details and reviews
        RequestDispatcher dispatcher = request.getRequestDispatcher("tv_seriesinfo.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (redirects to doGet for consistency)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
