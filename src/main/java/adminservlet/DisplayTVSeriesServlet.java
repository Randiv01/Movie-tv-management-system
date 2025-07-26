package adminservlet;

import adminservice.ManageTVSeriesDBUTIL;
import model.TVSeries;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/DisplayTVSeriesServlet")
public class DisplayTVSeriesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<TVSeries> tvList = ManageTVSeriesDBUTIL.getTVSeriesList();
        request.setAttribute("tvList", tvList);
        RequestDispatcher rd = request.getRequestDispatcher("ManageTVSeries.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
