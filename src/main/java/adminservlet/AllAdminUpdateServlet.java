package adminservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminmodel.Admin;
import adminservice.AdminDBUtil;

@WebServlet("/AllAdminUpdateServlet")
public class AllAdminUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@my\\.moviehub\\.com$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    private static final String MOBILE_REGEX = "^[0-9]{10}$";
    private static final String NIC_REGEX = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Retrieve form data
        String adminId = request.getParameter("aid");
        String fullname = request.getParameter("afullname");
        String gmail = request.getParameter("agmail");
        String password = request.getParameter("apassword");
        String gender = request.getParameter("agender");
        String dob = request.getParameter("adob");
        String mobile = request.getParameter("amobile");
        String address = request.getParameter("aaddress");
        String nic = request.getParameter("anic");
        String admintype = request.getParameter("admintype");

        // Basic null validation
        if (adminId == null || fullname == null || gmail == null || password == null ||
            gender == null || dob == null || mobile == null || address == null ||
            nic == null || admintype == null) {

            out.println("<script type='text/javascript'>");
            out.println("alert('Missing required fields!');");
            out.println("location='display_admin';");
            out.println("</script>");
            return;
        }

        // Validation
        if (!Pattern.matches(EMAIL_REGEX, gmail)) {
            alert(out, "Invalid admin email! Must be ...@my.moviehub.com");
            return;
        }

        if (!Pattern.matches(PASSWORD_REGEX, password)) {
            alert(out, "Password must be at least 8 characters long and include letters, numbers, and symbols.");
            return;
        }

        if (!Pattern.matches(MOBILE_REGEX, mobile)) {
            alert(out, "Invalid mobile number! Must be 10 digits.");
            return;
        }

        if (!Pattern.matches(NIC_REGEX, nic)) {
            alert(out, "Invalid NIC format! Must be 9 digits + v/V/x/X or 12 digits.");
            return;
        }

        try {
            int id = Integer.parseInt(adminId);

            Admin admin = new Admin(id, gmail, password, fullname, gender, dob, mobile, address, nic, admintype);
            boolean success = AdminDBUtil.updateAdmin(admin);

            if (success) {
                alert(out, "Admin details updated successfully!");
            } else {
                alert(out, "Error updating admin details. Please try again later.");
            }

        } catch (NumberFormatException e) {
            alert(out, "Invalid Admin ID format.");
        }
    }

    private void alert(PrintWriter out, String message) {
        out.println("<script type='text/javascript'>");
        out.println("alert('" + message + "');");
        out.println("location='display_admin';");
        out.println("</script>");
    }
}
