<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- JSTL core tag library for looping, conditionals, etc. -->

<%
    // Get the current HTTP session without creating a new one
    HttpSession sessionObj = request.getSession(false);

    // Initialize Admin object to null
    Admin loggedInAdmin = null;
    if (sessionObj != null) {
        // Retrieve the logged-in admin from the session
        loggedInAdmin = (Admin) sessionObj.getAttribute("admin");
    }

    // If no admin is logged in, redirect to login page
    if (loggedInAdmin == null) {
        response.sendRedirect("Login.jsp");  // Redirect if not logged in
        return;
    }

    // Optional: Get admin ID for potential future use
    int adminId = loggedInAdmin.getAid();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Feedback</title>

    <!-- Link to external stylesheet for page styling -->
    <link rel="stylesheet" type="text/css" href="CSS/ManageFeedback.css">

    <!-- Font Awesome icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>

<!-- Include the admin header (navigation/menu etc.) -->
<%@ include file="AdminHeader.jsp" %>

<div class="admin-feedback-management">
    <!-- Page title -->
    <div class="page-title">
        <h2><i class="fas fa-comments"></i> Feedback Management Portal</h2>
    </div>

    <h3>All Feedback Entries</h3>

    <!-- Feedback table starts here -->
    <table>
        <thead>
            <tr>
                <!-- Column headings for feedback data -->
                <th>Message ID</th>
                <th>User ID</th>
                <th>Admin ID</th>
                <th>Admin Name</th>
                <th>Admin Email</th>
                <th>Admin Mobile</th>
                <th>Category</th>
                <th>Language</th>
                <th>Message</th>
                <th>Date/Time</th>
                <th>Attachment</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>
        <!-- Loop through the feedback list and display each entry -->
        <c:forEach var="fb" items="${feedbackList}">
            <tr>
                <!-- Start of a form per feedback row for update functionality -->
                <td>
                    <form id="form${fb.feedbackId}" action="update_feedback" method="post" enctype="multipart/form-data">
                        <!-- Hidden inputs to pass data to the server -->
                        <input type="hidden" name="feedback_id" value="${fb.feedbackId}" />
                        <input type="hidden" name="old_attachment" value="${fb.attachmentFile}" />
                        <input type="text" name="message_id" value="${fb.messageId}" readonly/>
                    </form>
                </td>
                
                <!-- Input fields tied to the above form using the "form" attribute -->
                <td><input type="text" form="form${fb.feedbackId}" name="user_id" value="${fb.userId}" readonly/></td>
                <td><input type="text" form="form${fb.feedbackId}" name="admin_id" value="${fb.adminId}" readonly/></td>
                <td><input type="text" form="form${fb.feedbackId}" name="admin_name" value="${fb.adminName}"/></td>
                <td><input type="email" form="form${fb.feedbackId}" name="admin_email" value="${fb.adminEmail}" class="wide-email" readonly/></td>
                <td><input type="text" form="form${fb.feedbackId}" name="admin_mobile" value="${fb.adminMobile}"/></td>

                <!-- Dropdown for selecting feedback category -->
                <td>
                    <select form="form${fb.feedbackId}" name="category">
                        <option value="Technical" ${fb.category == 'Technical' ? 'selected' : ''}>Technical</option>
                        <option value="Billing" ${fb.category == 'Billing' ? 'selected' : ''}>Billing</option>
                        <option value="Movie Request" ${fb.category == 'Movie Request' ? 'selected' : ''}>Movie Request</option>
                        <option value="TV Series Info" ${fb.category == 'TV Series Info' ? 'selected' : ''}>TV Series Info</option>
                        <option value="Subtitle Issue" ${fb.category == 'Subtitle Issue' ? 'selected' : ''}>Subtitle Issue</option>
                    </select>
                </td>

                <!-- Dropdown for selecting language -->
                <td>
                    <select form="form${fb.feedbackId}" name="language">
                        <option value="English" ${fb.language == 'English' ? 'selected' : ''}>English</option>
                        <option value="Spanish" ${fb.language == 'Spanish' ? 'selected' : ''}>Spanish</option>
                        <option value="French" ${fb.language == 'French' ? 'selected' : ''}>French</option>
                        <option value="German" ${fb.language == 'German' ? 'selected' : ''}>German</option>
                    </select>
                </td>

                <!-- Textarea for feedback message -->
                <td><textarea form="form${fb.feedbackId}" name="feedback_message" rows="3">${fb.feedbackMessage}</textarea></td>

                <!-- Feedback timestamp -->
                <td><span class="datetime">${fb.feedbackDatetime}</span></td>

                <!-- Attachment section with upload option -->
                <td>
                    <div class="attachment-box">
                        <c:choose>
                            <c:when test="${not empty fb.attachmentFile}">
                                <!-- Link to view the uploaded file -->
                                <a href="uploads/${fb.attachmentFile}" target="_blank">View</a>
                            </c:when>
                            <c:otherwise>No File</c:otherwise>
                        </c:choose>
                        <!-- File input to upload new attachment -->
                        <input type="file" form="form${fb.feedbackId}" name="attachment_file"/>
                    </div>
                </td>

                <!-- Update and Delete buttons -->
                <td>
                    <!-- Submit the form to update feedback -->
                    <button type="submit" form="form${fb.feedbackId}" class="action-btn edit-btn">Update</button>
                    
                    <!-- Link to delete feedback with confirmation prompt -->
                    <a href="DeleteFeedbackServlet?feedback_id=${fb.feedbackId}" 
                       class="action-btn delete-btn"
                       onclick="return confirm('Are you sure you want to delete this feedback?');">
                       Delete
                    </a>
                </td>
            </tr>
        </c:forEach>

        <!-- Show message when no feedback is available -->
        <c:if test="${empty feedbackList}">
            <tr><td colspan="13">No feedback found.</td></tr>
        </c:if>
        </tbody>
    </table>
</div>

<!-- Include the admin footer -->
<%@ include file="AdminFooter.jsp" %>
</body>
</html>
