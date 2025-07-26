package adminservlet;

import adminservice.ManageTVSeriesDBUTIL;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteTVSeriesServlet")
public class DeleteTVSeriesServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tsid = Integer.parseInt(request.getParameter("id"));
        ManageTVSeriesDBUTIL.deleteTVSeries(tsid);
        response.sendRedirect("DisplayTVSeriesServlet");
    }
}
