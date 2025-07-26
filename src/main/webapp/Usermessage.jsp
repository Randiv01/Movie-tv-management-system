<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- JSTL Core taglib -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> <!-- JSTL Functions taglib -->
<%@ page import="model.ContactUs" %> <!-- Import ContactUs model class -->

<%
    // Get the existing session (don't create a new one)
    HttpSession sessionObj = request.getSession(false);

    // Variable to hold the logged-in admin object
    Admin loggedInAdmin = null;
    if (sessionObj != null) {
        // Get the admin object from session if available
        loggedInAdmin = (Admin) sessionObj.getAttribute("admin");
    }

    // If no admin is logged in, redirect to login page
    if (loggedInAdmin == null) {
        response.sendRedirect("Login.jsp");  // Redirect if not logged in
        return;
    }

    // Optional: Get the admin ID (could be used later)
    int adminId = loggedInAdmin.getAid();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MovieHub - User Messages</title>

    <!-- Link to external CSS for styling -->
    <link rel="stylesheet" href="CSS/Usermessage.css">

    <!-- Font Awesome icons for visual enhancements -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>

<!-- Include the Admin Header layout -->
<%@ include file="AdminHeader.jsp" %>

<div class="un-container">
    <br>
    <h1 class="un-title"><i class="fas fa-paper-plane"></i> All User Messages</h1>

    <div class="un-section">
        <div class="un-subtitle-row">
            <!-- Section Title -->
            <h2 class="un-subtitle"><i class="fas fa-envelope-open-text"></i> Inbox Messages</h2>

            <!-- Button to navigate to feedback history -->
            <form method="get" action="ManageFeedbackServlet">
                <button type="submit" class="un-history-btn global-feedback-btn">
                    <i class="fas fa-database"></i> Show All Feedback History
                </button>
            </form>
        </div>

        <!-- Show total number of messages -->
        <p class="un-debug">Total Messages: ${fn:length(sentMessages)}</p>

        <!-- Check if sentMessages list is not empty -->
        <c:choose>
            <c:when test="${not empty sentMessages}">
                <div class="un-table-wrapper">
                    <!-- Table to display user messages -->
                    <table class="un-table">
                        <thead>
                            <tr>
                                <th>Message ID</th>
                                <th>User ID</th>
                                <th>First Name</th>
                                <th>Email</th>
                                <th>Mobile</th>
                                <th>Message</th>
                                <th>Date &amp; Time</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Loop through each message and display it -->
                            <c:forEach var="sentMessage" items="${sentMessages}">
                                <tr>
                                    <td>${sentMessage.mid}</td>
                                    <td>${sentMessage.cid}</td>
                                    <td>${sentMessage.cfname}</td>
                                    <td>${sentMessage.cgmail}</td>
                                    <td>${sentMessage.cmobile}</td>
                                    <td>${sentMessage.cmessage}</td>
                                    <td>${sentMessage.datetime}</td>
                                    <td>
                                        <!-- Form to reply to a specific message -->
                                        <form method="get" action="Feedback.jsp">
                                            <!-- Hidden fields to pass message and user ID -->
                                            <input type="hidden" name="message_id" value="${sentMessage.mid}" />
                                            <input type="hidden" name="user_id" value="${sentMessage.cid}" />
                                            <button type="submit" class="un-reply-btn">
                                                <i class="fas fa-reply"></i> Reply
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:when>
            <c:otherwise>
                <!-- Display this section if there are no messages -->
                <div class="un-no-messages">
                    <p><i class="fas fa-inbox"></i> No messages found.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<!-- Include the Admin Footer layout -->
<%@ include file="AdminFooter.jsp" %>

</body>
</html>
