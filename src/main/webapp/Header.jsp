<%@ page import="model.User" %>
<%
    // Retrieve user session object
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieHub - Home</title>
    <link rel="stylesheet" href="CSS/Header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <header>
        <div class="header-container">
           
            <div class="logo">
                <a href="Home.jsp" class="text-logo">
                    <i class="fas fa-film"></i>
                    <span class="logo-text">Movie<span class="highlight">Hub</span></span>
                </a>
            </div>

            <nav>
                <ul class="nav-links">
                    <li><a>Home</a></li>
                    <li><a>Movies</a></li>
                    <li><a>TV Series</a></li>
                    <li><a>Contact Us</a></li>
                    <li><a>About Us</a></li>
                </ul>
            </nav>

            <div class="auth-section">
                <% if (user != null) { %>  
                    <div class="user-info">
                        <span class="welcome-message">Welcome, <%= user.getFname() %></span>
                        <a href="UserProfile.jsp" class="profile-logo">
                            <i class="fas fa-user-circle profile-icon"></i>
                        </a>
                        <form action="LogoutServlet" method="post" class="logout-form">
                            <button type="submit" class="btn-logout">Logout</button>
                        </form>
                    </div>
                <% } else { %>
                    <a href="Login.jsp" class="btn-login-register">Login/Register</a>
                <% } %>
            </div>
        </div>
    </header>
    
    <script src="JS/Header.js"></script>
</body>
</html>
