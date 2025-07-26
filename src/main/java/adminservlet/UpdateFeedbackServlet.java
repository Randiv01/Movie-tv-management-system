package adminservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import adminservice.FeedbackDBUtil;

@WebServlet("/UpdateFeedbackServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024) // 5 MB
public class UpdateFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Define the upload directory path as a constant
    private static final String UPLOAD_DIR = "C:\\Users\\Randiv\\Documents\\Y2S1 projects\\Online_movie_and_tv_series_browsing_system\\src\\main\\webapp\\uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve form data
            int feedbackId = Integer.parseInt(request.getParameter("feedback_id"));
            int messageId = Integer.parseInt(request.getParameter("message_id"));
            int userId = Integer.parseInt(request.getParameter("user_id"));
            int adminId = Integer.parseInt(request.getParameter("admin_id"));
            String adminName = request.getParameter("admin_name");
            String adminEmail = request.getParameter("admin_email");
            String adminMobile = request.getParameter("admin_mobile");
            String category = request.getParameter("category");
            String language = request.getParameter("language");
            String feedbackMessage = request.getParameter("feedback_message");

            // Handle file upload
            Part filePart = request.getPart("attachment_file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String oldFile = request.getParameter("old_attachment");
            
            // Create the uploads directory if it doesn't exist
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String savedFileName = oldFile;
            
            if (fileName != null && !fileName.isEmpty()) {
                // Delete old file if it exists
                if (oldFile != null && !oldFile.isEmpty()) {
                    File oldFileToDelete = new File(UPLOAD_DIR + File.separator + oldFile);
                    if (oldFileToDelete.exists()) {
                        oldFileToDelete.delete();
                    }
                }
                
                // Generate unique filename with timestamp
                String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                
                // Save new file
                String filePath = UPLOAD_DIR + File.separator + uniqueFileName;
                filePart.write(filePath);
                savedFileName = uniqueFileName;
            }

            // Update feedback in database
            boolean success = FeedbackDBUtil.updateFeedback(
                    feedbackId, messageId, userId, adminId,
                    adminName, adminEmail, adminMobile,
                    category, language, feedbackMessage, savedFileName
            );
            
            // Response handling
            if (success) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Feedback updated successfully!');");
                out.println("location='ManageFeedbackServlet';");
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Failed to update feedback. Please try again.');");
                out.println("location='ManageFeedbackServlet';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script type='text/javascript'>");
            out.println("alert('An error occurred while updating feedback.');");
            out.println("location='ManageFeedbackServlet';");
            out.println("</script>");
        } finally {
            out.close();
        }
    }
}