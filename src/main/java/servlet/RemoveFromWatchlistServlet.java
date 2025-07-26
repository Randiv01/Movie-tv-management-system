package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.WatchListDBUtil;
import model.User;

@WebServlet("/RemoveFromWatchlistServlet")
public class RemoveFromWatchlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RemoveFromWatchlistServlet() {
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

        int userId = user.getId(); // Get user ID from the User object

        try {
            int movieId = Integer.parseInt(request.getParameter("movieId"));
            boolean success = WatchListDBUtil.removeFromWatchlist(userId, movieId);

            if (success) {
                session.setAttribute("message", "Movie removed from watchlist successfully.");
            } else {
                session.setAttribute("message", "Failed to remove movie from watchlist.");
            }

        } catch (NumberFormatException e) {
            session.setAttribute("message", "Invalid movie ID.");
        }

        // Redirect to DisplayWatchlistServlet
        response.sendRedirect("DisplaywatchlistServlet");
    }
}
