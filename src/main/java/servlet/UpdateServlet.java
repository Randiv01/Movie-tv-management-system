package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.User;
import service.UserDBUtil;

@MultipartConfig
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // path of ProfileImages folder
    private static final String UPLOAD_DIR = "C:/Users/Randiv/Documents/Y2S1 projects/Online_movie_and_tv_series_browsing_system/src/main/webapp/ProfileImages";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Get form data
        String id = request.getParameter("id");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gmail = request.getParameter("email");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");

        // Get uploaded profile image
        Part filePart = request.getPart("profileimage");  // Get the file part
        String profileImageName = null;

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            profileImageName = fileName;

            File uploadsDir = new File(UPLOAD_DIR);
            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            String uploadPath = UPLOAD_DIR + File.separator + fileName;

            // Save the uploaded file to the actual ProfileImages folder
            try (InputStream inputStream = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(uploadPath)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

            } catch (IOException e) {
                e.printStackTrace();
                out.println("<script>alert('File upload failed. Please try again.');location='UserProfile.jsp';</script>");
                return; // Stop further processing if upload failed
            }
        } else {
            // No new file uploaded, keep existing image name from hidden input
            profileImageName = request.getParameter("profileimage");
        }

        // Update user details in DB
        UserDBUtil userDBUtil = new UserDBUtil();
        boolean isUpdated = userDBUtil.updateCustomer(id, fname, lname, gmail, password, country, gender, dob, mobile, profileImageName, nic, address);

        if (isUpdated) {
            User updatedUser = userDBUtil.getCustomerDetails(id).get(0);
            HttpSession session = request.getSession();
            session.setAttribute("user", updatedUser);

            out.println("<script type='text/javascript'>");
            out.println("alert('Profile updated successfully!');");
            out.println("location='UserProfile.jsp';");
            out.println("</script>");
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('Update failed. Please try again.');");
            out.println("location='UserProfile.jsp';");
            out.println("</script>");
        }
    }
}
