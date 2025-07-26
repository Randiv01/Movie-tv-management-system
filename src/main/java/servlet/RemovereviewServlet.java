package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.ReviewDBUtil;

// Servlet mapped to handle review deletion requests
@WebServlet("/RemovereviewServlet")
public class RemovereviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Default constructor
    public RemovereviewServlet() {
        super();
    }

    // Handles POST requests for deleting a review
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get reviewId and movieId from the request parameters
        String reviewIdParam = request.getParameter("reviewId");
        String movieIdParam = request.getParameter("movieId"); // Get movieId from the request

        // Set response content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if reviewId is provided and not empty
        if (reviewIdParam != null && !reviewIdParam.isEmpty()) {
            try {
                // Parse the reviewId string into an integer
                int reviewId = Integer.parseInt(reviewIdParam);

                // Attempt to delete the review using the database utility method
                boolean success = ReviewDBUtil.deleteReviewById(reviewId);

                // Show success message and redirect back to the movie info page
                if (success) {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Review removed successfully.');");
                    out.println("window.location.href = 'mInfoServlet?movieId=" + movieIdParam + "&reviewMessage=Review+Deleted';");
                    out.println("</script>");
                } else {
                    // Show error message if deletion failed
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Failed to remove review.');");
                    out.println("window.location.href = 'mInfoServlet?movieId=" + movieIdParam + "&reviewMessage=Failed+to+delete';");
                    out.println("</script>");
                }
            } catch (NumberFormatException e) {
                // Handle case where reviewId is not a valid integer
                out.println("<script type='text/javascript'>");
                out.println("alert('Invalid review ID.');");
                out.println("window.location.href = 'mInfoServlet?movieId=" + movieIdParam + "&reviewMessage=Invalid+ID';");
                out.println("</script>");
            }
        } else {
            // Handle case where reviewId is missing in the request
            out.println("<script type='text/javascript'>");
            out.println("alert('Review ID is missing.');");
            out.println("window.location.href = 'mInfoServlet?movieId=" + movieIdParam + "&reviewMessage=Missing+ID';");
            out.println("</script>");
        }
    }
}
