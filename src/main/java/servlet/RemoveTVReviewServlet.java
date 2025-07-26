package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.TVSRBDUtil;

// Define the servlet and map it to the URL pattern /RemoveTVReviewServlet
@WebServlet("/RemoveTVReviewServlet")
public class RemoveTVReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles POST requests for deleting a TV series review
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the reviewId parameter from the request
        String reviewIdParam = request.getParameter("reviewId");
        // Get the current HTTP session
        HttpSession session = request.getSession();

        // Check if reviewId is missing or empty
        if (reviewIdParam == null || reviewIdParam.isEmpty()) {
            // If invalid, respond with a 400 Bad Request
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid review ID");
            return;
        }

        try {
            // Parse the reviewId from String to integer
            int reviewId = Integer.parseInt(reviewIdParam);
            // Attempt to delete the review from the database using the utility class
            boolean deleted = TVSRBDUtil.deleteReviewById(reviewId);

            // Set a session attribute indicating whether deletion was successful
            if (deleted) {
                session.setAttribute("deleteSuccess", "Review deleted successfully.");
            } else {
                session.setAttribute("deleteSuccess", "Failed to delete review. Please try again.");
            }

        } catch (NumberFormatException e) {
            // Handle the case where reviewId is not a valid number
            session.setAttribute("deleteSuccess", "Invalid review ID format.");
        }

        // Redirect the user back to the referring page (previous page)
        response.sendRedirect(request.getHeader("referer"));
    }

    // Reject GET requests for this servlet since deletion should only occur via POST
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not supported for delete.");
    }
}
