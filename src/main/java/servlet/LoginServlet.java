package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adminmodel.Admin;
import adminservice.AdminDBUtil;
import model.User;
import service.UserDBUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String gmail = request.getParameter("gmail");
        String password = request.getParameter("password");

        // Check if the email is for an admin (ends with @my.moviehub.com)
        if (gmail.endsWith("@my.moviehub.com")) {
            // Use AdminDBUtil to validate admin credentials
            Admin admin = AdminDBUtil.validateAdmin(gmail, password);

            if (admin != null) {
                // If admin is valid, create session and store the entire Admin object
                HttpSession session = request.getSession(true);
                session.setAttribute("admin", admin); // Store the entire Admin object in session

                out.println("<script type='text/javascript'>");
                out.println("alert('Admin Login Successful');");
                out.println("location='AdminHome.jsp';"); // Redirect to Admin home
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Invalid admin credentials');");
                out.println("location='Login.jsp';"); // Redirect to login page
                out.println("</script>");
            }
        } else {
            // Instantiate UserDBUtil instead of using static method calls
            UserDBUtil userDBUtil = new UserDBUtil(); 

            // Validate user login from regular user table
            boolean isTrue = userDBUtil.validate(gmail, password);

            if (isTrue) {
                List<User> usdetails = userDBUtil.getCustomer(gmail);

                if (usdetails != null && !usdetails.isEmpty()) {
                    User user = usdetails.get(0); // Get the first user object

                    // Create session and store user object
                    HttpSession session = request.getSession(true); // Ensure session is created
                    session.setAttribute("user", user); // Store entire user object in session

                    out.println("<script type='text/javascript'>");
                    out.println("alert('Successfully Logged in');");
                    out.println("location='Home.jsp';"); // Redirect to user home
                    out.println("</script>");
                } else {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('User details not found');");
                    out.println("location='Login.jsp';"); // Redirect to login page
                    out.println("</script>");
                }
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Your username or password is incorrect');");
                out.println("location='Login.jsp';"); // Redirect to login page
                out.println("</script>");
            }
        }
    }
}
