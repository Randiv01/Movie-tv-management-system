package servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.TVSReview;
import service.TVSRBDUtil;

/**
 * Servlet for handling TV series review submissions.
 * Supports file uploads for review screenshots and processes all review data.
 */
@WebServlet("/InsertTVSRServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024,  // 1MB threshold before writing to disk
    maxFileSize = 2 * 1024 * 1024,    // 2MB max file size
    maxRequestSize = 5 * 1024 * 1024  // 5MB max total request size
)
public class InsertTVSRSevelet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Directory where uploaded screenshots will be stored
    private static final String UPLOAD_DIR = "uploads/screenshots";

    /**
     * Default constructor required for servlets.
     */
    public InsertTVSRSevelet() {
        super();
    }

    /**
     * Handles POST requests for submitting new TV series reviews.
     * Processes form data including file uploads and inserts the review into database.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Set character encoding to handle UTF-8 input properly
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Retrieve and parse all form parameters
            int tvSeriesId = Integer.parseInt(request.getParameter("tvSeriesId"));
            int userId = Integer.parseInt(request.getParameter("userId"));
            String tvSeriesName = request.getParameter("tvSeriesName");
            String username = request.getParameter("username");
            int season = Integer.parseInt(request.getParameter("season"));
            String episode = request.getParameter("episode");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String reviewTitle = request.getParameter("reviewTitle");
            String comment = request.getParameter("comment");
            String pros = request.getParameter("pros");
            String cons = request.getParameter("cons");
            String recommend = request.getParameter("recommend");
            Date reviewDate = Date.valueOf(request.getParameter("reviewDate"));
            boolean agreeTerms = request.getParameter("agreeTerms") != null;

            // Handle screenshot file upload if present
            Part filePart = request.getPart("screenshot");
            String screenshotFileName = null;

            // Check if a file was actually uploaded (not empty)
            if (filePart != null && filePart.getSize() > 0) {
                String submittedFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String lowerFileName = submittedFileName.toLowerCase();

                // Validate file extension (only allow JPG/JPEG/PNG)
                if (lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".png")) {
                    // Prepare upload directory
                    String appPath = request.getServletContext().getRealPath("");
                    String uploadPath = appPath + File.separator + UPLOAD_DIR;
                    File uploadDir = new File(uploadPath);
                    
                    // Create directory if it doesn't exist
                    if (!uploadDir.exists()) uploadDir.mkdirs();

                    // Generate unique filename using timestamp
                    screenshotFileName = System.currentTimeMillis() + "_" + submittedFileName;
                    String fullPath = uploadPath + File.separator + screenshotFileName;

                    // Save the uploaded file
                    try (InputStream input = filePart.getInputStream()) {
                        Files.copy(input, Paths.get(fullPath));
                    }
                } else {
                    // Show error if invalid file type was uploaded
                    printAlert(response, "Invalid file type for screenshot. Only JPG/PNG allowed.",
                            "TVSeriesInfoServlet?seriesId=" + tvSeriesId);
                    return;
                }
            }

            // Create review object with all collected data
            TVSReview review = new TVSReview(
                0, tvSeriesId, userId, tvSeriesName, username,
                season, episode, rating, reviewTitle, comment, pros, cons,
                recommend, reviewDate, screenshotFileName, agreeTerms
            );

            // Attempt to insert the review into database
            boolean success = TVSRBDUtil.insertReview(review);

            // Show appropriate message based on insertion result
            if (success) {
                printAlert(response, "Review submitted successfully!",
                        "TVSeriesInfoServlet?seriesId=" + tvSeriesId);
            } else {
                printAlert(response, "Failed to submit review. Please try again.",
                        "TVSeriesInfoServlet?seriesId=" + tvSeriesId);
            }

        } catch (Exception e) {
            // Handle any unexpected errors during processing
            e.printStackTrace();
            printAlert(response, "Something went wrong: " + e.getMessage(),
                    "TVSeriesInfoServlet?seriesId=" + request.getParameter("tvSeriesId"));
        }
    }
    
    /**
     * Helper method to display a JavaScript alert and redirect the user.
     * 
     * @param response The HttpServletResponse object
     * @param message The alert message to display
     * @param redirectURL The URL to redirect to after the alert
     * @throws IOException If there's an error writing to the response
     */
    private void printAlert(HttpServletResponse response, String message, String redirectURL) throws IOException {
        response.getWriter().println("<script type='text/javascript'>");
        response.getWriter().println("alert('" + message.replace("'", "\\'") + "');");
        response.getWriter().println("location='" + redirectURL + "';");
        response.getWriter().println("</script>");
    }
}