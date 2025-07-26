package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ReviewDBUtil;

public class ReviewInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewInsertServlet() {
        super();
    }

    // Handles POST requests to insert a new movie review
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Retrieve form data submitted from the review form
        String movieId = request.getParameter("movieId");
        String userId = request.getParameter("userId");
        String movieName = request.getParameter("movieName");
        String username = request.getParameter("username");
        String rating = request.getParameter("rating");
        String reviewTitle = request.getParameter("reviewTitle");
        String comment = request.getParameter("comment");
        String pros = request.getParameter("pros");
        String cons = request.getParameter("cons");
        String recommend = request.getParameter("recommend");
        String reviewDate = request.getParameter("reviewDate");

        try {
            // Insert the review into the database using the utility method
            boolean isInserted = ReviewDBUtil.insertReview(
                movieId, userId, movieName, username, rating,
                reviewTitle, comment, pros, cons, recommend, reviewDate
            );

            // Redirect to movie info page with success or failure message
            if (isInserted) {
                response.sendRedirect("mInfoServlet?movieId=" + movieId + "&reviewMessage=success");
            } else {
                response.sendRedirect("mInfoServlet?movieId=" + movieId + "&reviewMessage=fail");
            }

        } catch (Exception e) {
            // On exception, redirect to the movie info page with error message
            response.sendRedirect("mInfoServlet?movieId=" + movieId + "&reviewMessage=error");
            e.printStackTrace(); // Print exception details in server logs
        }
    }
}
