package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserDBUtil;

@WebServlet("/RegisteerServlet")
public class RegisteerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Regex patterns for validation
    private static final String GMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String fname = request.getParameter("firstname");
        String lname = request.getParameter("lastname");
        String gmail = request.getParameter("email");
        String password = request.getParameter("password");
        String country =""; // Assign default  values 
        String gender ="";  
        String dob =request.getParameter("dob");
        String mobile =request.getParameter("mobile");
        String profileimage ="dui.jpg";
        String nic =request.getParameter("nic");
        String address =""; // Default value for address

        // Validate Gmail format
        if (!Pattern.matches(GMAIL_REGEX, gmail)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Invalid Gmail format! Please enter a valid ...@gmail.com address.');");
            out.println("location='Register.jsp';");
            out.println("</script>");
            return;
        }

        // Validate password strength
        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Password must be at least 8 characters long and contain letters, numbers, and special characters.');");
            out.println("location='Register.jsp';");
            out.println("</script>");
            return;
        }

        // Create an instance of UserDBUtil
        UserDBUtil userDBUtil = new UserDBUtil();

        // Check if email is already registered
        if (userDBUtil.isEmailRegistered(gmail)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Email is already registered. Please use a different email.');");
            out.println("location='Register.jsp';");
            out.println("</script>");
            return;
        }

        boolean isTrue;
        try {
            // Proceed with user registration
            isTrue = userDBUtil.insertUser(fname, lname, gmail, password, country, gender, dob, mobile, profileimage, nic, address);

            if (isTrue) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Successfully Registered');");
                out.println("location='Login.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Registration failed. Please try again.');");
                out.println("location='Register.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            out.println("<script type='text/javascript'>");
            out.println("alert('An error occurred. Please try again later.');");
            out.println("location='Register.jsp';");
            out.println("</script>");
            e.printStackTrace();
        }
    }
}
