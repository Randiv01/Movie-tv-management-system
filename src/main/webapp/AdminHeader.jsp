<%@ page import="adminmodel.Admin" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieHub - Admin Panel</title>
    <link rel="stylesheet" href="CSS/AdminHeader.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<header>
    <div class="header-container">

        <!-- Logo -->
        <div class="logo">
            <a href="Home.jsp" class="text-logo">
                <i class="fas fa-film"></i>
                <span class="logo-text">Movie<span class="highlight">Hub</span></span>
            </a>
        </div>

        <!-- Navigation Menu -->
        <nav class="nav-middle">
            <ul class="nav-links">
            	 <li><a href="Home.jsp">Movie Hub Home</a></li>
                <li><a href="AdminHome.jsp">Admin Home</a></li>
                
            </ul>
        </nav>

        <!-- Admin Info Section -->
        <div class="auth-section">
            <% if (admin != null) { %>
                <span class="welcome-message">Welcome Admin, <%= admin.getAfullname() %></span>
                <a href="#" class="profile-logo">
                    <i class="fas fa-user-circle profile-icon"></i>
                </a>
                <form action="adminlogoutservlet" method="post" class="logout-form">
                    <button type="submit" class="btn-logout">Logout</button>
                </form>
            <% } else { %>
                <a href="Login.jsp" class="btn-login-register">Login</a>
            <% } %>
        </div>

        <!-- Mobile Menu Icon -->
        <button class="menu-toggle"><i class="fas fa-bars"></i></button>
    </div>
</header>

<script src="JS/AdminHeader.js"></script>
</body>
</html>
