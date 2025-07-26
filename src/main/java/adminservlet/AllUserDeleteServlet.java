package adminservlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.AdminDBUtil;

@WebServlet("/AllUserDeleteServlet")
public class AllUserDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");

        AdminDBUtil util = new AdminDBUtil();
        boolean isDeleted = util.deleteUser(id);

        if (isDeleted) {
            out.println("<script type='text/javascript'>");
            out.println("alert('User deleted successfully!');");
            out.println("location='DisplayAllUserServlet';");
            out.println("</script>");
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('Failed to delete user!');");
            out.println("location='DisplayAllUserServlet';");
            out.println("</script>");
        }
    }
}