package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserDBUtil;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Redirect GET to doPost
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    // Handle POST request
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String confirm = request.getParameter("confirm");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (confirm == null || !confirm.equals("true")) {
            // Show confirmation dialog
            out.println("<script type='text/javascript'>");
            out.println("if (confirm('Are you sure you want to delete your account permanently? This action cannot be undone.')) {");
            out.println("window.location.href='DeleteServlet?id=" + id + "&confirm=true';");
            out.println("} else {");
            out.println("window.location.href='UserProfile.jsp';");
            out.println("}");
            out.println("</script>");
        } else {
            try {
                UserDBUtil userDBUtil = new UserDBUtil();

                // 1. Get profile image filename before deleting user
                String profileImageName = userDBUtil.getProfileImageNameByUserId(id);

                // 2. Delete user record from DB
                boolean isDeleted = userDBUtil.deleteCustomer(id);

                if (isDeleted) {
                    // 3. Delete profile image file if it exists and is not default
                    if (profileImageName != null && !profileImageName.trim().isEmpty() && !profileImageName.equalsIgnoreCase("default.png")) {
                      
                        String uploadDir = "C:/Users/Randiv/Documents/Y2S1 projects/Online_movie_and_tv_series_browsing_system/src/main/webapp/ProfileImages";
                        File imageFile = new File(uploadDir + File.separator + profileImageName);
                        if (imageFile.exists()) {
                            boolean deleted = imageFile.delete();
                            if (!deleted) {
                                System.err.println("Failed to delete profile image file: " + profileImageName);
                            }
                        }
                    }

                    // 4. Invalidate session and redirect to login
                    HttpSession session = request.getSession();
                    session.invalidate();

                    out.println("<script type='text/javascript'>");
                    out.println("alert('Account deleted successfully.');");
                    out.println("window.location.href='Login.jsp';");
                    out.println("</script>");
                } else {
                    out.println("<script type='text/javascript'>");
                    out.println("alert('Account deletion failed. Please try again.');");
                    out.println("window.location.href='UserProfile.jsp';");
                    out.println("</script>");
                }
            } catch (Exception e) {
                out.println("<script type='text/javascript'>");
                out.println("alert('An error occurred. Please try again later.');");
                out.println("window.location.href='UserProfile.jsp';");
                out.println("</script>");
                e.printStackTrace();
            }
        }
    }
}
