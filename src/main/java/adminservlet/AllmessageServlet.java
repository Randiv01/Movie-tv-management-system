package adminservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.MessageDBUtil;
import model.ContactUs;

@WebServlet("/AllmessageServlet")
public class AllmessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AllmessageServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Fetch all messages from the DB
        List<ContactUs> sentMessages = MessageDBUtil.getAllMessages();

        // Set to request
        request.setAttribute("sentMessages", sentMessages);

        // Forward to JSP
        request.getRequestDispatcher("Usermessage.jsp").forward(request, response);
    }

    // Allow GET as well
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
