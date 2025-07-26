package adminservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import adminservice.FeedbackDBUtil;

@WebServlet("/InsertFeedbackServlet")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024) // Set maximum file size to 10 MB
public class InsertFeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Handles POST requests (form submission)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
        	// Get hidden input values from the form (IDs)
            int messageId = Integer.parseInt(request.getParameter("message_id"));
            int userId = Integer.parseInt(request.getParameter("user_id"));
            int adminId = Integer.parseInt(request.getParameter("admin_id"));

         // Get admin and feedback details from form inputs
            String adminName = request.getParameter("admin_name");
            String adminEmail = request.getParameter("admin_email");
            String adminMobile = request.getParameter("admin_mobile");
            String category = request.getParameter("category");
            String language = request.getParameter("language");
            String feedbackMessage = request.getParameter("feedback_message");

            // Handle file upload (optional)
            Part filePart = request.getPart("attachment_file");
            String fileName = null;
            
            if (filePart != null && filePart.getSize() > 0) {
            	
            	// Define upload path for saving the file
                String uploadPath = "C:\\Users\\Randiv\\Documents\\Y2S1 projects\\Online_movie_and_tv_series_browsing_system\\src\\main\\webapp\\uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
                
                // Save the file to the specified directory
                filePart.write(uploadPath + File.separator + fileName);
            }

            // Insert feedback into database
            boolean isInserted = FeedbackDBUtil.insertFeedback(
                messageId, userId, adminId,
                adminName, adminEmail, adminMobile,
                category, language, feedbackMessage, fileName
            );
            
            // Show success or failure message using JavaScript alert
            if (isInserted) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Feedback submitted successfully!');");
                out.println("location='allmessageServlet';"); // Redirect after alert
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Failed to submit feedback. Please try again.');");
                out.println("location='AdminHome.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();

            out.println("<script type='text/javascript'>");
            out.println("alert('An error occurred while submitting feedback. Please try again later.');");
            out.println("location='AdminHome.jsp';");
            out.println("</script>");
        } finally {
            out.close();
        }
    }
}
