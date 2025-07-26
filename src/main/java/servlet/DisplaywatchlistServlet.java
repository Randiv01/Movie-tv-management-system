package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.WatchListDBUtil;
import model.Watchlist;

@WebServlet("/DisplaywatchlistServlet")
public class DisplaywatchlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplaywatchlistServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            model.User user = (model.User) session.getAttribute("user");
            Integer userId = user.getId();

            // Get search keyword
            String keyword = request.getParameter("search");
            List<Watchlist> watchlist;

            if (keyword != null && !keyword.trim().isEmpty()) {
                watchlist = WatchListDBUtil.searchWatchlistByTitle(userId, keyword.trim());
            } else {
                watchlist = WatchListDBUtil.getWatchlistForUser(userId);
            }

            request.setAttribute("watchlist", watchlist);
            request.getRequestDispatcher("MyWatchlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("Login.jsp");
        }
    }
}
