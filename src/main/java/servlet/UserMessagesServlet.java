package servlet;

// Import model classes for User and ContactUs
import model.User;
import model.ContactUs;
// Import admin model class for Feedback
import adminmodel.Feedback;
// Import service utility class to interact with the database
import service.ContactUsDBUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class UserMessagesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Handles GET requests to this servlet
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the current HTTP session
        HttpSession session = request.getSession();
        // Retrieve the logged-in user object from the session
        User user = (User) session.getAttribute("user");

        // If user is not logged in, redirect to the login page
        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Get the user ID from the User object
        int userId = user.getId();

        // Fetch messages sent by the user from the database
        List<ContactUs> sentMessages = ContactUsDBUtil.getMessagesByUserId(userId);
        // Fetch feedbacks given by admin related to user's messages
        List<Feedback> feedbacks = ContactUsDBUtil.getFeedbacksByUserId(userId);

        // Set user ID as a request attribute to be used in the JSP
        request.setAttribute("userId", userId);
        // Set the list of sent messages as a request attribute
        request.setAttribute("sentMessages", sentMessages);
        // Set the list of feedbacks as a request attribute
        request.setAttribute("feedbacks", feedbacks);

        // Forward the request and response for display
        RequestDispatcher dispatcher = request.getRequestDispatcher("UNotification.jsp");
        dispatcher.forward(request, response);
    }
}
