package adminservlet;

import adminservice.ManageMovieDBUTIL;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/UpdateMovieServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, maxFileSize=1024*1024*10, maxRequestSize=1024*1024*50)
public class UpdateMovieServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String IMAGE_UPLOAD_DIR = "Movieimages"; // match case exactly

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int movieid = Integer.parseInt(request.getParameter("movieid"));
        String mname = request.getParameter("mname");
        String description = request.getParameter("description");
        String genre = request.getParameter("genre");
        String release_date = request.getParameter("release_date");
        String duration = request.getParameter("duration");
        String language = request.getParameter("language");
        String director = request.getParameter("director");
        String cast = request.getParameter("cast");
        String rating = request.getParameter("rating");
        String trailer_url = request.getParameter("trailer_url");
        String availability = request.getParameter("availability");
        String download_link = request.getParameter("download_link");
        String oldImage = request.getParameter("oldImage");

        // Handle image upload
        Part filePart = request.getPart("imageFile");
        String fileName = getFileName(filePart);
        String imageFileName = oldImage;

        if (fileName != null && !fileName.isEmpty()) {
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadPath = applicationPath + File.separator + IMAGE_UPLOAD_DIR;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            imageFileName = new File(fileName).getName();
            filePart.write(uploadPath + File.separator + imageFileName);
        }

        ManageMovieDBUTIL.updateMovie(movieid, mname, description, genre, release_date, duration,
                language, director, cast, rating, imageFileName, trailer_url, availability, download_link);

        response.sendRedirect("DisplayMovieServlet");
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
