package servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import service.MovieDBUTIL;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String movieId = request.getParameter("movieId");
        String relativePath = MovieDBUTIL.getDownloadLinkByMovieId(movieId); // Example: files/extraction.torrent

        // ✅ Replace old folder name "files" with "MovieDFiles"
        if (relativePath != null && !relativePath.isEmpty()) {
            relativePath = relativePath.replace("files/", "MovieDFiles/");

            // Get absolute path from the relative path
            String absolutePath = getServletContext().getRealPath("/") + File.separator + relativePath.replace("/", File.separator);

            File file = new File(absolutePath);
            if (file.exists()) {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());

                try (FileInputStream in = new FileInputStream(file);
                     OutputStream out = response.getOutputStream()) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                // File not found
                response.setContentType("text/html");
                response.getWriter().write("File not found on server: " + absolutePath);
            }
        } else {
            // Invalid movieId or no download path
            response.setContentType("text/html");
            response.getWriter().write("Invalid movie ID or no download path found.");
        }
    }
}
