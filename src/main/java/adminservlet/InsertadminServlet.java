package adminservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminmodel.Admin;
import adminservice.AdminDBUtil;

@WebServlet("/InsertadminServlet")
public class InsertadminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Regex Patterns
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@my\\.moviehub\\.com$";
	private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
	private static final String MOBILE_REGEX = "^[0-9]{10}$";
	private static final String NIC_REGEX = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		// Get parameters
		String fullname = request.getParameter("afullname");
		String gmail = request.getParameter("agmail");
		String password = request.getParameter("apassword");
		String gender = request.getParameter("agender");
		String dob = request.getParameter("adob");
		String mobile = request.getParameter("amobile");
		String address = request.getParameter("aaddress");
		String nic = request.getParameter("anic");
		String admintype = request.getParameter("admintype");

		// ==== Validation ====

		if (!Pattern.matches(EMAIL_REGEX, gmail)) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Invalid admin email! Must be ...@my.moviehub.com');");
			out.println("location='display_admin';");
			out.println("</script>");
			return;
		}

		if (!Pattern.matches(PASSWORD_REGEX, password)) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Password must be at least 8 characters long and include letters, numbers, and symbols.');");
			out.println("location='display_admin';");
			out.println("</script>");
			return;
		}

		if (!Pattern.matches(MOBILE_REGEX, mobile)) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Invalid mobile number! Enter a valid 10-digit number.');");
			out.println("location='display_admin';");
			out.println("</script>");
			return;
		}

		if (!Pattern.matches(NIC_REGEX, nic)) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Invalid NIC format. Must be 9 digits + v/V/x/X or 12 digits.');");
			out.println("location='display_admin';");
			out.println("</script>");
			return;
		}

		// Check if email is already used (Optional, implement if needed)
		if (AdminDBUtil.isAdminEmailExists(gmail)) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Admin email already exists. Use another one.');");
			out.println("location='display_admin';");
			out.println("</script>");
			return;
		}

		// Create admin object
		Admin admin = new Admin(0, gmail, password, fullname, gender, dob, mobile, address, nic, admintype);

		// Insert to DB
		boolean isInserted = AdminDBUtil.insertAdmin(admin);

		if (isInserted) {
			out.println("<script type='text/javascript'>");
			out.println("alert('New Admin added successfully!');");
			out.println("location='display_admin';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Failed to add admin. Please try again.');");
			out.println("location='display_admin';");
			out.println("</script>");
		}
	}
}
