package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ContactUsDBUtil;

@WebServlet("/ContactUsServlet")
public class ContactUsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String cid = request.getParameter("cid");
        String cfname = request.getParameter("cfname");
        String cgmail = request.getParameter("cgmail");
        String cmobile = request.getParameter("cmobile");
        String cmessage = request.getParameter("cmessage");

        // ✅ Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String submittedAt = now.format(formatter);  // String value like "2025-05-03 17:45:00"

        boolean isTrue;

        try {
            // Call DB method with date and time
            isTrue = ContactUsDBUtil.insertMessage(cid, cfname, cgmail, cmobile, cmessage, submittedAt);

            if (isTrue) {
                out.println("<script type='text/javascript'>");
                out.println("alert('Successfully sent the message');");
                out.println("location='ContactUs.jsp';");
                out.println("</script>");
            } else {
                out.println("<script type='text/javascript'>");
                out.println("alert('Sending failed. Please try again.');");
                out.println("location='ContactUs.jsp';");
                out.println("</script>");
            }
        } catch (Exception e) {
            out.println("<script type='text/javascript'>");
            out.println("alert('An error occurred. Please try again later.');");
            out.println("location='ContactUs.jsp';");
            out.println("</script>");
            e.printStackTrace();
        }
    }
}
