package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserDBUtil;

@WebServlet("/ValidateUserServlet")
public class ValidateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Password regex validation: at least one letter, one number, one special character, and minimum 8 characters
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    @SuppressWarnings("unused")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String nic = request.getParameter("nic");

        // Create an instance of UserDBUtil to use the non-static method
        UserDBUtil userDBUtil = new UserDBUtil();

        // Validate email, mobile, and NIC using the instance
        if (userDBUtil.isUserValidForPasswordReset(email, mobile, nic)) {
            // Show success message and enable password change form
            request.setAttribute("verificationMessage", "User authentication successful. You may now update your password.");
            request.setAttribute("showPasswordForm", true);
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        } else {
            // Show error message and stay on the verification form
            request.setAttribute("verificationMessage", "Verification unsuccessful. Ensure the details entered are correct and try again.");
            request.setAttribute("showPasswordForm", false);
            request.getRequestDispatcher("ChangePassword.jsp").forward(request, response);
        }
    }

    // Helper function to validate the password using regex
    public static boolean isPasswordValid(String password) {
        // Create a PasswordValidator object to use the regex constant
        PasswordValidator passwordValidator = new PasswordValidator();
        return passwordValidator.isValid(password);
    }

    // Inner class to encapsulate password validation logic
    private static class PasswordValidator {
        public boolean isValid(String password) {
            return password != null && password.matches(PASSWORD_REGEX);
        }
    }
}
