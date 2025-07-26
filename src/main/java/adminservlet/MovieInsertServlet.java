package adminservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import service.MovieDBUTIL;

@MultipartConfig
@WebServlet("/MovieInsertServlet")
public class MovieInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String IMAGE_UPLOAD_DIR = "Movieimages"; // Directory for image upload
    private static final String TORRENT_SAVE_DIR = "files"; // Directory for torrent files

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Retrieve form fields
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

        // Handle image upload
        Part imagePart = request.getPart("image");
        String imageName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
        String imageUploadPath = getServletContext().getRealPath("") + File.separator + IMAGE_UPLOAD_DIR;
        File imageDir = new File(imageUploadPath);
        if (!imageDir.exists()) imageDir.mkdirs();
        imagePart.write(imageUploadPath + File.separator + imageName);

        // Handle torrent file upload
        Part torrentPart = request.getPart("torrent");
        String torrentName = Paths.get(torrentPart.getSubmittedFileName()).getFileName().toString();
        String torrentSavePath = getServletContext().getRealPath("") + File.separator + TORRENT_SAVE_DIR;
        File torrentDir = new File(torrentSavePath);
        if (!torrentDir.exists()) torrentDir.mkdirs();
        torrentPart.write(torrentSavePath + File.separator + torrentName);
        String downloadLink = "files/" + torrentName;

        // Validate required fields
        if (mname == null || mname.isEmpty() ||
            description == null || genre == null || release_date == null ||
            duration == null || language == null || director == null ||
            cast == null || rating == null || trailer_url == null || availability == null ||
            imageName.isEmpty() || torrentName.isEmpty()) {

            out.println("<script type='text/javascript'>");
            out.println("alert('All fields are required. Please fill in all the details.');");
            out.println("location='InsertMovie.jsp';");
            out.println("</script>");
            return;
        }

        // Call DBUTIL method with individual parameters
        try {
            boolean isSuccess = MovieDBUTIL.insertMovie(
                mname, description, genre, release_date, duration, language,
                director, cast, rating, imageName, trailer_url, availability, downloadLink
            );

            if (isSuccess) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Movie inserted successfully!');");
                out.println("location='InsertMovie.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Movie insertion failed. Try again.');");
                out.println("location='InsertMovie.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();  // This will print the error stack trace in the server logs.
            out.println("<script type='text/javascript'>");
            out.println("alert('An error occurred while inserting the movie. Please try again later.');");
            out.println("location='InsertMovie.jsp';");
            out.println("</script>");
        }
    }
}
