package adminservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.ManageMovieDBUTIL;
import model.Movie;

@WebServlet("/DisplayMovieServlet")
public class DisplayMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DisplayMovieServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Movie> movieList = ManageMovieDBUTIL.getMovieDetails();
		request.setAttribute("movieList", movieList);
		request.getRequestDispatcher("ManageMovies.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
