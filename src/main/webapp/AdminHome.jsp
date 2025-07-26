<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieHub - Admin Dashboard</title>
    <link rel="stylesheet" href="CSS/AdminHome.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>

    <%@ include file="AdminHeader.jsp" %>

    <div class="admin-home">
        <section class="welcome-section">
            <br><br><br>
            <h1>Welcome to MovieHub Admin Panel</h1>
            <p>Manage users, movies, TV series, messages, and admins efficiently.</p>
            <img src="adminimages/ahome.jpg" alt="Admin Dashboard">
        </section>

        <section class="admin-actions">
            <h2>Admin Controls</h2>
            <div class="action-buttons">
                <a href="DisplayAllUserServlet" class="btn manage-users">
                    <i class="fas fa-users"></i> Manage Users
                </a>
                <a href="InsertMovie.jsp" class="btn manage-movies">
                    <i class="fas fa-film"></i> Insert Movies
                </a>
                <a href="InsertTVSeries.jsp" class="btn manage-tv"> <!-- ✅ fixed link -->
                    <i class="fas fa-tv"></i> Insert TV Series
                </a>
                <a href="ManageContent.jsp" class="btn manage-content">
                    <i class="fas fa-list"></i> Manage Movies and TV Series
                </a>
                <a href="allmessageServlet" class="btn manage-messages">
                    <i class="fas fa-envelope"></i> User Messages
                </a>
                <a href="display_admin" class="btn manage-admins">
                    <i class="fas fa-user-shield"></i> Manage Admins
                </a>
            </div>
        </section>
    </div>

    <%@ include file="AdminFooter.jsp" %>

</body>
</html>
