package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserDBUtil;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Check if passwords match
        if (!newPassword.equals(confirmPassword)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Passwords do not match. Please try again.');");
            out.println("location='ChangePassword.jsp';");
            out.println("</script>");
            return;
        }

        // Create an instance of UserDBUtil
        UserDBUtil userDBUtil = new UserDBUtil();

        // Update the password in the database
        if (userDBUtil.updatePassword(email, newPassword)) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Password updated successfully.');");
            out.println("location='LogoutServlet';");
            out.println("</script>");
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('Password update failed. Please try again.');");
            out.println("location='ChangePassword.jsp';");
            out.println("</script>");
        }
    }
}
