<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %> <!-- Import User model to access user details -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JSTL core taglib -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Link to Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

    <!-- Link to external CSS for Contact Us page styling -->
    <link rel="stylesheet" type="text/css" href="CSS/ContactUs.css">

    <title>Contact Us - MovieHub</title>
</head>
<body>

    <!-- Include the common header/navigation bar -->
    <%@ include file="Header.jsp" %>

    <%
        // Retrieve the logged-in user from the session
        User us = (User) session.getAttribute("user");

        // If the user is not logged in, redirect to the login page
        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
    %>

    <!-- Contact Us content container -->
    <div class="contact-container">
        <h1>Contact Us</h1>
        <p>If you have any questions, feel free to reach out to us using the form below or via the contact information provided.</p>

        <div class="contact-section">
            
            <!-- Static contact information section -->
            <div class="contact-info">
                <div class="info-item">
                    <i class="fas fa-phone-alt icon"></i>
                    <div class="info-text">
                        <h2>Phone</h2>
                        <p>Call us <strong>+94-234567890</strong>.</p>
                    </div>
                </div>

                <div class="info-item">
                    <i class="fas fa-envelope icon"></i>
                    <div class="info-text">
                        <h2>Email</h2>
                        <p>For inquiries, email us at <a href="mailto:support@moviehub.com">support@moviehub.com</a>.</p>
                    </div>
                </div>

                <div class="info-item">
                    <i class="fas fa-map-marker-alt icon"></i>
                    <div class="info-text">
                        <h2>Address</h2>
                        <p>Visit us at <strong>No.456 Movie Street, Colombo 07, Sri Lanka</strong>.</p>
                    </div>
                </div>
            </div>

            <!-- Contact form for users to send messages -->
            <form action="contact" method="post" class="contact-form">
                <!-- Hidden field to pass user ID -->
                <input type="hidden" name="cid" value="<%= user.getId() %>">

                <!-- Full name field (pre-filled from session user data) -->
			    <label for="name">Full Name:</label>
			    <input type="text" id="name" name="cfname" value="<%= user.getFname() %> <%= user.getLname() %>" placeholder="Enter your full name" required>
			    
			    <!-- Email field (pre-filled and read-only) -->
			    <label for="email">Email:</label>
			    <input type="email" id="email" name="cgmail" value="<%= user.getGmail() %>" readonly>
			    
			    <!-- Mobile number field (pre-filled but editable) -->
			    <label for="mobile">Contact Number:</label>
			    <input type="text" id="mobile" name="cmobile" value="<%= user.getMobile() %>" required>
			    
			    <!-- Message text area -->
			    <label for="message">Message:</label>
			    <textarea id="message" name="cmessage" rows="5" placeholder="Type your message here" required></textarea>
			    
			    <!-- Submit button -->
			    <button type="submit">Send Message</button>
			</form>
        </div>
    </div>

    <!-- Include the common footer -->
    <%@ include file="Footer.jsp" %>

    <!-- Optional JavaScript file for additional interactivity -->
    <script src="JS/ContactUs.js"></script>
</body>
</html>
