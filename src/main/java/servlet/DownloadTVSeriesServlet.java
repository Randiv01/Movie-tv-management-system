package servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import service.TVSriesDBUtil;

@WebServlet("/DownloadTVSeriesServlet")
public class DownloadTVSeriesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tvSeriesId = request.getParameter("tvSeriesId");
        String relativePath = TVSriesDBUtil.getDownloadLinkByTVSeriesId(tvSeriesId); // Example: files/stranger_things.torrent

        // ✅ Replace wrong folder "files" with correct folder "TVSDFiles"
        if (relativePath != null && !relativePath.isEmpty()) {
            relativePath = relativePath.replace("files/", "TVSDFiles/");

            // ✅ Build the absolute path
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
                // ❌ File not found
                response.setContentType("text/html");
                response.getWriter().write(" File not found on server: " + absolutePath);
            }
        } else {
            // ❌ No valid path or ID
            response.setContentType("text/html");
            response.getWriter().write(" Invalid TV Series ID or no download path found.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
