package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Review;
import service.ReviewDBUtil;

// Servlet that handles displaying the logged-in user's submitted reviews
@WebServlet("/MyreviewsServlet")
public class MyreviewsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Default constructor
    public MyreviewsServlet() {
        super();
    }

    // Handles GET requests - typically used to display user's reviews
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve current session, if it exists
        HttpSession session = request.getSession(false);

        // Check if the user is logged in by verifying session and user attribute
        if (session != null && session.getAttribute("user") != null) {
            // Get the user object from session
            model.User user = (model.User) session.getAttribute("user");

            // Retrieve the user's ID to fetch their reviews
            int userId = user.getId();

            // Fetch reviews submitted by this user from the database
            List<Review> userReviews = ReviewDBUtil.getReviewsByUserId(userId);

            // Set the reviews list as an attribute for use in the JSP
            request.setAttribute("userReviews", userReviews);

            // Forward the request to Myreviews.jsp to display the user's reviews
            request.getRequestDispatcher("Myreviews.jsp").forward(request, response);
        } else {
            // If user is not logged in, redirect them to the login page
            response.sendRedirect("Login.jsp");
        }
    }

    // Redirect POST requests to be handled by the GET logic
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
