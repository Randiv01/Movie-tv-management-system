<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="model.ContactUs, adminmodel.Feedback" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Messages - MovieHub</title>
    <link rel="stylesheet" href="CSS/UNotification.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="un-container">
        <h1 class="un-title"><i class="fas fa-bell"></i> User Notifications</h1>

        <!-- Sent Messages Section -->
        <div class="un-section">
            <div class="un-header" onclick="toggleSection('sentSection', this)">
                <h2 class="un-subtitle"><i class="fas fa-paper-plane"></i> Sent Messages</h2>
                <button class="un-toggle-btn">
                    <i class="fas fa-chevron-down"></i>
                </button>
            </div>
            <div id="sentSection" class="un-content hidden">
                <p class="un-debug">Total Sent: ${fn:length(sentMessages)}</p>
                <c:choose>
                    <c:when test="${not empty sentMessages}">
                        <div class="un-table-wrapper">
                            <table class="un-table">
                                <thead>
                                    <tr>
                                        <th>Message ID</th>
                                        <th>User ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Mobile</th>
                                        <th>Message</th>
                                        <th>Date &amp; Time</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="sentMessage" items="${sentMessages}">
                                        <tr>
                                            <td>${sentMessage.mid}</td>
                                            <td>${sentMessage.cid}</td>
                                            <td>${sentMessage.cfname}</td>
                                            <td>${sentMessage.cgmail}</td>
                                            <td>${sentMessage.cmobile}</td>
                                            <td>${sentMessage.cmessage}</td>
                                            <td>${sentMessage.datetime}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p class="un-no-messages"><i class="fas fa-inbox"></i> No sent messages.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- Admin Feedback Section -->
        <div class="un-section">
            <div class="un-header" onclick="toggleSection('feedbackSection', this)">
                <h2 class="un-subtitle"><i class="fas fa-reply"></i> Admin Feedback</h2>
                <button class="un-toggle-btn">
                    <i class="fas fa-chevron-down"></i>
                </button>
            </div>
            <div id="feedbackSection" class="un-content hidden">
                <p class="un-debug">Total Feedbacks: ${fn:length(feedbacks)}</p>
                <c:choose>
                    <c:when test="${not empty feedbacks}">
                        <div class="un-table-wrapper">
                            <table class="un-table">
                                <thead>
                                    <tr>
                                        <th>Feedback ID</th>
                                        <th>Agent Name</th>
                                        <th>Agent Gmail</th>
                                        <th>Category</th>
                                        <th>Language</th>
                                        <th>Message</th>
                                        <th>Attachment</th>
                                        <th>Date &amp; Time</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="fb" items="${feedbacks}">
                                        <tr>
                                            <td>${fb.feedbackId}</td>
                                            <td>${fb.adminName}</td>
                                            <td>${fb.adminEmail}</td>
                                            <td>${fb.category}</td>
                                            <td>${fb.language}</td>
                                            <td>${fb.feedbackMessage}</td>
                                            <td>
                                                <c:if test="${not empty fb.attachmentFile}">
                                                   <a href="uploads/${fb.attachmentFile}" target="_blank" class="un-view-link">
													    <i class="fas fa-paperclip"></i> View
													</a>

                                                </c:if>
                                            </td>
                                            <td>${fn:replace(fb.feedbackDatetime, 'T', ' ')}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <p class="un-no-messages"><i class="fas fa-inbox"></i> No feedback received yet.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>

    <%@ include file="Footer.jsp" %>

    <script src="JS/UNotification.js" defer></script>
</body>
</html>
