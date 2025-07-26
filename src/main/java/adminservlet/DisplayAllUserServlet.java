package adminservlet;

import java.io.IOException;
import java.util.List;

import adminservice.AdminDBUtil;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Displyalluserserclet")
public class DisplayAllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DisplayAllUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> userList = AdminDBUtil.getAllUsers();
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("ManageUsers.jsp").forward(request, response);
	}
}
