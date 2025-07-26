package adminservlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import adminservice.AdminDBUtil;

@MultipartConfig
@WebServlet("/AlluserUpdateServlet")
public class AlluserUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String id = request.getParameter("id");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gmail = request.getParameter("gmail");
        String password = request.getParameter("password");
        String country = request.getParameter("country");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String mobile = request.getParameter("mobile");
        String nic = request.getParameter("nic");
        String address = request.getParameter("address");

        // Get uploaded profile image
        Part filePart = request.getPart("profileImage");  // Get the file part
        String profileImageName = null;

        if (filePart != null && filePart.getSize() > 0) {
            String fileName = filePart.getSubmittedFileName();
            String uploadPath = getServletContext().getRealPath("") + File.separator + "ProfileImages" + File.separator + fileName;

            // Create the ProfileImages directory if it doesn't exist
            File uploadsDir = new File(getServletContext().getRealPath("") + File.separator + "ProfileImages");
            if (!uploadsDir.exists()) {
                uploadsDir.mkdirs();
            }

            // Save the uploaded file to the server
            try (InputStream inputStream = filePart.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(uploadPath)) {

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                // Set the file name to be stored in the database
                profileImageName = fileName;

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If no file is selected, retain the last profile image from the database
            profileImageName = request.getParameter("oldImage"); // Get existing image from the hidden input
        }

        // Use AdminDBUtil to update user
        AdminDBUtil util = new AdminDBUtil();
        boolean isSuccess = util.updateCustomer(id, fname, lname, gmail, password, country, gender, dob, mobile, profileImageName, nic, address);

        if (isSuccess) {
            out.println("<script type='text/javascript'>");
            out.println("alert('User updated successfully!');");
            out.println("location='DisplayAllUserServlet';"); // Redirect after alert
            out.println("</script>");
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('Failed to update user!');");
            out.println("location='DisplayAllUserServlet';"); // Redirect after alert
            out.println("</script>");
        }
    }
}