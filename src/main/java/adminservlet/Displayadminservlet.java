package adminservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adminservice.AdminDBUtil;
import adminmodel.Admin;

@WebServlet("/DisplayAdminServlet")
public class Displayadminservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Displayadminservlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Admin> admins = AdminDBUtil.getAllAdmins(); // Fetch all admin data
        request.setAttribute("admins", admins); // Set the data to the request scope
        request.getRequestDispatcher("Manageadmin.jsp").forward(request, response); // Forward to JSP
    }
}
