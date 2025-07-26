package adminservlet;

import adminservice.ManageMovieDBUTIL;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteMovieServlet")
public class DeleteMovieServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieid = Integer.parseInt(request.getParameter("id"));
        ManageMovieDBUTIL.deleteMovie(movieid);
        response.sendRedirect("DisplayMovieServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
