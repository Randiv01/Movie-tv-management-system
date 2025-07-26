package adminservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminlogoutservlet")
public class adminlogoutservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handle only POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        // Get the current session
        HttpSession session = request.getSession(false); // false means do not create a new session

        if (session != null) {
            // Remove admin session attribute
            session.removeAttribute("admin");
            session.invalidate(); // Invalidate the session

            response.sendRedirect("Login.jsp"); // Redirect to the login page after logout
        }
    }
}
