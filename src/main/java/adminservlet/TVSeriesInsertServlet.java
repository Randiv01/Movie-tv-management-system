package adminservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.TVSeriesDBUTIL;

@MultipartConfig
@WebServlet("/TVSeriesInsertServlet")
public class TVSeriesInsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Folder names (must exist under src/main/webapp/)
    private static final String IMAGE_UPLOAD_DIR = "TVSeriesImages";
    private static final String TORRENT_SAVE_DIR = "TVSDFiles";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET access to the form page
        response.sendRedirect("InsertTVSeries.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            // Retrieve form fields
            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String genre = request.getParameter("genre");
            String language = request.getParameter("language");
            String release_date = request.getParameter("release_date");
            String trailer_url = request.getParameter("trailer_url");
            String status = request.getParameter("status");
            String cast = request.getParameter("cast");
            String creator = request.getParameter("creator");
            String ratingStr = request.getParameter("rating");
            String seasonsStr = request.getParameter("seasons");
            String episodesStr = request.getParameter("episodes");
            String duration = request.getParameter("duration_per_episode");

            // Handle image upload
            Part imagePart = request.getPart("poster_url");
            String posterFileName = Paths.get(imagePart.getSubmittedFileName()).getFileName().toString();
            String imageUploadPath = getServletContext().getRealPath("") + File.separator + IMAGE_UPLOAD_DIR;
            File imageDir = new File(imageUploadPath);
            if (!imageDir.exists()) imageDir.mkdirs();
            imagePart.write(imageUploadPath + File.separator + posterFileName);

            // Handle torrent upload
            Part torrentPart = request.getPart("download_link");
            String torrentName = Paths.get(torrentPart.getSubmittedFileName()).getFileName().toString();
            String torrentSavePath = getServletContext().getRealPath("") + File.separator + TORRENT_SAVE_DIR;
            File torrentDir = new File(torrentSavePath);
            if (!torrentDir.exists()) torrentDir.mkdirs();
            torrentPart.write(torrentSavePath + File.separator + torrentName);
            String downloadLink = TORRENT_SAVE_DIR + "/" + torrentName;

            // Debug output
            System.out.println("Poster uploaded to: " + imageUploadPath + File.separator + posterFileName);
            System.out.println("Torrent uploaded to: " + torrentSavePath + File.separator + torrentName);

            // Validate required fields
            if (title == null || title.isEmpty() ||
                description == null || genre == null || language == null ||
                release_date == null || trailer_url == null || status == null ||
                cast == null || creator == null || ratingStr == null || seasonsStr == null ||
                episodesStr == null || duration == null || posterFileName.isEmpty() || torrentName.isEmpty()) {

                out.println("<script type='text/javascript'>");
                out.println("alert('All fields are required. Please fill in all the details.');");
                out.println("location='InsertTVSeries.jsp';");
                out.println("</script>");
                return;
            }

            // Convert numeric values
            double rating = Double.parseDouble(ratingStr);
            int seasons = Integer.parseInt(seasonsStr);
            int episodes = Integer.parseInt(episodesStr);

            // Insert into database
            boolean isSuccess = TVSeriesDBUTIL.insertTVSeries(
                title, description, genre, language, release_date,
                seasons, episodes, duration, rating,
                posterFileName, trailer_url, status, cast, creator, downloadLink
            );

            // Response
            if (isSuccess) {
                out.println("<script type='text/javascript'>");
                out.println("alert('TV Series inserted successfully!');");
                out.println("location='InsertTVSeries.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('TV Series insertion failed. Try again.');");
                out.println("location='InsertTVSeries.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type='text/javascript'>");
            out.println("alert('An error occurred while inserting the TV Series. Please try again later.');");
            out.println("location='InsertTVSeries.jsp';");
            out.println("</script>");
        }
    }
}
