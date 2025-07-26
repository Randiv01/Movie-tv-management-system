package adminservlet;

import adminservice.ManageTVSeriesDBUTIL;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/UpdateTVSeriesServlet")
@MultipartConfig
public class UpdateTVSeriesServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "TVSeriesImages";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int tsid = Integer.parseInt(request.getParameter("tsid"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String genre = request.getParameter("genre");
        String language = request.getParameter("language");
        String release_date = request.getParameter("release_date");
        int seasons = Integer.parseInt(request.getParameter("seasons"));
        int episodes = Integer.parseInt(request.getParameter("episodes"));
        String duration = request.getParameter("duration_per_episode");
        double rating = Double.parseDouble(request.getParameter("rating"));
        String trailer_url = request.getParameter("trailer_url");
        String status = request.getParameter("status");
        String cast = request.getParameter("cast");
        String creator = request.getParameter("creator");
        String download_link = request.getParameter("download_link");
        String oldPoster = request.getParameter("oldPoster");

        Part imagePart = request.getPart("imageFile");
        String fileName = getFileName(imagePart);
        String poster_url = oldPoster;

        if (fileName != null && !fileName.isEmpty()) {
            String path = request.getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
            File uploadDir = new File(path);
            if (!uploadDir.exists()) uploadDir.mkdir();

            poster_url = new File(fileName).getName();
            imagePart.write(path + File.separator + poster_url);
        }

        ManageTVSeriesDBUTIL.updateTVSeries(tsid, title, description, genre, language, release_date,
                seasons, episodes, duration, rating, poster_url, trailer_url, status, cast, creator, download_link);

        response.sendRedirect("DisplayTVSeriesServlet");
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
