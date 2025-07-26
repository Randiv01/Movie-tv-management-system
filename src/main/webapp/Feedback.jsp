<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="adminmodel.Admin" %>

<%
    // Get message ID and user ID from request
    String messageId = request.getParameter("message_id");
    String userId = request.getParameter("user_id");

    // Get the current session and fetch the logged-in admin's information
    HttpSession sessionObj = request.getSession(false);
    Admin admin1 = (sessionObj != null) ? (Admin) sessionObj.getAttribute("admin") : null;
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reply to Feedback</title>
    <!-- Link to the CSS file for styling -->
    <link rel="stylesheet" href="CSS/Feedback.css">
</head>
<body>

<!-- Include the admin header -->
<%@ include file="AdminHeader.jsp" %>

<%
    // If message ID or user ID is missing, show an error message
    if (messageId == null || userId == null) {
%>
    <div class="un-container">
        <p class="un-error">Missing message ID or user ID. Please go back.</p>
    </div>
<%
    // If no admin is logged in, show login prompt
    } else if (admin == null) {
%>
    <div class="un-container">
        <p class="un-error">You must be logged in as admin to reply. <a href="Login.jsp">Login</a></p>
    </div>
<%
    // Show the feedback reply form
    } else {
        int adminId = admin.getAid(); // Get admin ID
%>
    <div class="un-container">
        <h2 class="un-title">Reply to User Feedback</h2>

        <!-- Feedback reply form -->
        <form action="InsertFeedbackServlet" method="post" enctype="multipart/form-data" class="un-form">
            <!-- Hidden inputs to pass IDs to the server -->
            <input type="hidden" name="message_id" value="<%= messageId %>">
            <input type="hidden" name="user_id" value="<%= userId %>">
            <input type="hidden" name="admin_id" value="<%= adminId %>">

            <!-- Admin name and email -->
            <div class="un-row">
                <div class="un-form-group">
                    <label for="admin_name">Admin Name:</label>
                    <input type="text" name="admin_name" value="<%= admin.getAfullname() %>" required>
                </div>
                <div class="un-form-group">
                    <label for="admin_email">Admin Email:</label>
                    <input type="email" name="admin_email" value="<%= admin.getGmail() %>" required>
                </div>
            </div>

            <!-- Admin mobile number and category dropdown -->
            <div class="un-row">
                <div class="un-form-group">
                    <label for="admin_mobile">Admin Mobile:</label>
                    <input type="text" name="admin_mobile" value="<%= admin.getMobile() %>" pattern="[0-9]{10}" title="Enter 10-digit number" required>
                </div>
                <div class="un-form-group">
                    <label for="category">Category:</label>
                    <select name="category" required>
                        <option value="">--Select--</option>
                        <option value="Technical">Technical</option>
                        <option value="Billing">Billing</option>
                        <option value="Movie Request">Movie Request</option>
                        <option value="TV Series Info">TV Series Info</option>
                        <option value="Subtitle Issue">Subtitle Issue</option>
                    </select>
                </div>
            </div>

            <!-- Language dropdown -->
            <div class="un-row">
                <div class="un-form-group full-width">
                    <label for="language">Language:</label>
                    <select name="language" required>
                        <option value="">--Select--</option>
                        <option value="English">English</option>
                        <option value="Spanish">Spanish</option>
                        <option value="French">French</option>
                        <option value="German">German</option>
                    </select>
                </div>
            </div>

            <!-- Feedback message text area -->
            <div class="un-form-group">
                <label for="feedback_message">Message:</label>
                <textarea name="feedback_message" rows="5" required></textarea>
            </div>

            <!-- Optional file upload -->
            <div class="un-form-group">
                <label for="attachment_file">Attachment (optional):</label>
                <input type="file" name="attachment_file">
            </div>

            <!-- Submit button -->
            <div class="un-form-group">
                <input type="submit" value="Send Reply" class="un-reply-btn">
            </div>
        </form>
    </div>
<%
    }
%>

<!-- Include the admin footer -->
<%@ include file="AdminFooter.jsp" %>

</body>
</html>