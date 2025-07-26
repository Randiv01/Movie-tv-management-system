package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.Review;
import service.ReviewDBUtil;

@WebServlet("/EditReviewServlet")
public class EditReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditReviewServlet() {
        super();
    }

    // Handles GET requests, used when the user wants to load the edit form with existing review data
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the reviewId from the URL parameter
        String reviewIdParam = request.getParameter("reviewId");

        // Check if reviewId is not null and not empty
        if (reviewIdParam != null && !reviewIdParam.isEmpty()) {
            try {
                // Convert the reviewId to integer
                int reviewId = Integer.parseInt(reviewIdParam);

                // Fetch the review object from the database
                Review review = ReviewDBUtil.getReviewById(reviewId);

                // Pass the review object to the JSP page for displaying in the form
                request.setAttribute("review", review);
                request.getRequestDispatcher("EditReview.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                // If reviewId is not a valid number, redirect to the user's review list
                response.sendRedirect("MyreviewsServlet");
            }
        } else {
            // If reviewId is missing or empty, redirect to the user's review list
            response.sendRedirect("MyreviewsServlet");
        }
    }

    // Handles POST requests, used when the user submits the updated review form
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve and parse all form parameters needed for the update
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        String movieName = request.getParameter("movieName");
        String reviewTitle = request.getParameter("reviewTitle");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");
        String pros = request.getParameter("pros");
        String cons = request.getParameter("cons");
        String recommend = request.getParameter("recommend");
        String movieId = request.getParameter("movieId"); // Movie ID needed for redirect after update

        // Call DB utility to update the review with new data
        boolean success = ReviewDBUtil.updateReview(reviewId, movieName, reviewTitle, rating, comment, pros, cons, recommend);

        if (success) {
            // If update is successful, redirect to movie info page with success message
            response.sendRedirect("mInfoServlet?movieId=" + movieId + "&reviewMessage=Review+updated+successfully");
        } else {
            // If update fails, show error and forward back to edit form
            request.setAttribute("error", "Failed to update review.");
            request.getRequestDispatcher("EditReview.jsp").forward(request, response);
        }
    }
}
