package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.WatchListDBUtil;
import model.User;

@WebServlet("/RemoveTVWTServlet")
public class RemoveTVWTSevlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveTVWTSevlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        int userId = user.getId(); // Get user ID from session

        try {
            int tvSeriesId = Integer.parseInt(request.getParameter("tvSeriesId"));
            boolean success = WatchListDBUtil.removeFromTVWatchlist(userId, tvSeriesId);

            if (success) {
                session.setAttribute("message", "TV Series removed from watchlist successfully.");
            } else {
                session.setAttribute("message", "Failed to remove TV Series from watchlist.");
            }

        } catch (NumberFormatException e) {
            session.setAttribute("message", "Invalid TV Series ID.");
        }

        response.sendRedirect("DisplayTVSWServlet");
    }
}
