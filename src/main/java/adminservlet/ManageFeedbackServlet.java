package adminservlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminservice.FeedbackDBUtil;
import adminmodel.Feedback;

@WebServlet("/ManageFeedbackServlet")
//Inheritance
public class ManageFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ManageFeedbackServlet() {
        super();
    }

    @Override
    //polymophism
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get all feedback from the database
        List<Feedback> feedbackList = FeedbackDBUtil.getAllFeedback();

        // Set the list in request scope to access in JSP
        request.setAttribute("feedbackList", feedbackList);

     // Forward the request to the ManageFeedback.jsp page to display feedback
        request.getRequestDispatcher("ManageFeedback.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
