package adminservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.FeedbackDBUtil;

@WebServlet("/DeleteFeedbackServlet")
public class DeleteFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Handles POST requests to delete feedback
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the feedback ID from the request parameter
		int feedbackId = Integer.parseInt(request.getParameter("feedback_id"));
		
		// Call the database utility to delete the feedback with the given ID
		boolean isDeleted = FeedbackDBUtil.deleteFeedback(feedbackId);
		
		// Redirect to ManageFeedbackServlet with status message
		if (isDeleted) {
			response.sendRedirect("ManageFeedbackServlet?status=deleted");// Deletion successful
		} else {
			response.sendRedirect("ManageFeedbackServlet?status=delete_failed");// Deletion failed
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
