package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.TVSeriesWatchlist;
import model.User;
import service.WatchListDBUtil;

@WebServlet("/DisplayTVSWServlet")
public class DisplayTVSWServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DisplayTVSWServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            int userId = user.getId();

            // Optional: support keyword search
            String keyword = request.getParameter("search");
            List<TVSeriesWatchlist> tvWatchlist;

            if (keyword != null && !keyword.trim().isEmpty()) {
                tvWatchlist = WatchListDBUtil.searchTVWatchlistByTitle(userId, keyword.trim());
            } else {
                tvWatchlist = WatchListDBUtil.getTVWatchlistForUser(userId);
            }

            request.setAttribute("tvWatchlist", tvWatchlist);
            request.getRequestDispatcher("MyTVSWatchlist.jsp").forward(request, response);
        } else {
            response.sendRedirect("Login.jsp");
        }
    }
}
