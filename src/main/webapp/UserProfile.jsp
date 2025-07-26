<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile - MovieHub</title>
    <link rel="stylesheet" type="text/css" href="CSS/UserProfile.css">
</head>
<body>
    <%@ include file="Header.jsp" %>

    <% 
        User us = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
    %>
    
    <div class="profile-container">
        <!-- Sidebar -->
        <aside class="sidebar">
            <div class="sidebar-avatar">
                <!-- Profile Image -->
                <img id="profile-img" src="<%= (user.getProfileimage() != null && !user.getProfileimage().trim().isEmpty()) 
			    ? "ProfileImages/" + user.getProfileimage() 
			    : "ProfileImages/dui.jpg" %>" alt="Profile Image">
                <h3><%= user.getFname() %> <%= user.getLname() %></h3>
            </div>
            <ul class="sidebar-links">
                <li><a href="Home.jsp">Home</a></li>
                <li><a href="DisplayTVSWServlet">My TV-Series Watchlist</a></li>
                <li><a href="DisplaywatchlistServlet">My Movie Watchlist</a></li>
                <li><a href="MyreviewsServlet">My Reviews</a></li>
               <li><a href="userMessages">Notifications</a></li> 
                <li><a href="ContactUs.jsp">Contact Support</a></li>
                <li>
                    <form action="LogoutServlet" method="post" class="logout-form">
                        <button type="submit" class="btn-logout1">Logout</button>
                    </form>
                </li>
            </ul>
        </aside>

        <!-- Profile Details -->
        <main class="profile-content">
          
            <h2>Personal Details</h2>
           <form action="update" method="post" enctype="multipart/form-data" onsubmit="return validateForm();">
                <div class="user-info">
				    <label><strong>User ID:</strong></label>
				    <span><%= user.getId() %></span>
				</div>
                <input type="hidden" name="id" value="<%= user.getId() %>">
                
                <label>First Name:</label>
                <input type="text" name="fname" value="<%= user.getFname() %>" required>

                <label>Last Name:</label>
                <input type="text" name="lname" value="<%= user.getLname() %>" required>

                <label>Email:</label>
                <input type="email" name="email" value="<%= user.getGmail() %>" readonly>
                <small class="warning-text">*Get contact support to change your Gmail.</small>
					
				<label>Date of Birth:</label>
					<input type="date" name="dob" id="dob" value="<%= user.getDob() != null ? user.getDob() : "" %>"
					       placeholder="<%= user.getDob() == null || user.getDob().trim().isEmpty() ? "Enter your date of birth" : "" %>" required>
					       
			    <label>NIC:</label>
					<input type="text" name="nic" id="nic" value="<%= user.getNic() != null ? user.getNic() : "" %>" 
					       placeholder="<%= user.getNic() == null || user.getNic().trim().isEmpty() ? "Enter your NIC" : "" %>" required>
					       
				<label>Mobile:</label>
				<small class="warning-text">*A mobile number is required to proceed. Kindly add it before continuing.</small>  
					<input type="text" name="mobile" id="mobile" value="<%= user.getMobile() != null ? user.getMobile() : "" %>"
					       placeholder="<%= user.getMobile() == null || user.getMobile().trim().isEmpty() ? "Enter your mobile number" : "" %>"
					       pattern="\d{10}" maxlength="10" required>
					  
					
				<label for="password">Password:</label>
					<div class="password-container">
					    <input type="password" id="password" name="password" value="<%= user.getPassword() %>" readonly>
					</div>
					<button type="button" class="btn-change-password" onclick="redirectToChangePassword()">Change Password</button>

                <br>
               <h2>Optional Personal Details</h2>

					<label for="profile-img-input">Change Profile Image:</label>
					<input type="file" id="profile-img-input" name="profileimage" accept="image/*" onchange="previewImage()">
					
					<!-- Hidden input to store old profile image -->
					<input type="hidden" id="profile-img-hidden" name="profileimage" value="<%= user.getProfileimage() %>">
					
					<label>Gender:</label>
					<select name="gender">
						<option value="" <%= "".equals(user.getGender()) ? "selected" : "" %>>Select Gender (Optional)</option>
					    <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
					    <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
					    <option value="Other" <%= "Other".equals(user.getGender()) ? "selected" : "" %>>Other</option>
					</select>
					
					<label>Country:</label>
					<input type="text" name="country" 
					       value="<%= user.getCountry() != null ? user.getCountry() : "" %>"
					       placeholder="<%= user.getCountry() == null || user.getCountry().trim().isEmpty() ? "Enter your country" : "" %>">
					
					<label>Address:</label>
					<input type="text" name="address" value="<%= user.getAddress() != null ? user.getAddress() : "" %>"
					       placeholder="<%= user.getAddress() == null || user.getAddress().trim().isEmpty() ? "Enter your address" : "" %>">
		
                <button type="submit" class="btn-update">Update My Details</button>
            </form>

			           <form id="deleteForm" action="delete" method="post">
						    <input type="hidden" name="id" value="<%= user.getId() %>">
						    <button type="submit" class="btn-delete">Delete Profile</button>
						</form>
        </main>
    </div>

    <%@ include file="Footer.jsp" %>

    <!-- Move JS to the end to ensure all DOM elements are loaded -->
    <script src="JS/UserProfile.js"></script>
    
</body>
</html>
