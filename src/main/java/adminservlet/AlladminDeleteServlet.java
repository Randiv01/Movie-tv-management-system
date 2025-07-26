package adminservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.AdminDBUtil;

@WebServlet("/AllAdminDeleteServlet")
public class AlladminDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String adminId = request.getParameter("aid");

        // Delete admin from DB
        boolean success = AdminDBUtil.deleteAdmin(Integer.parseInt(adminId));

        if (success) {
            out.println("<script type='text/javascript'>");
            out.println("alert('Admin deleted successfully!');");
            out.println("location='display_admin';");
            out.println("</script>");
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('Error deleting admin. Please try again later.');");
            out.println("location='display_admin';");
            out.println("</script>");
        }
    }
}