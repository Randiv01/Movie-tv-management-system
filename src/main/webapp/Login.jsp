<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - MovieHub</title>
    <link rel="stylesheet" type="text/css" href="CSS/Login.css">
    <script defer src="JS/Login.js"></script>
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="login-container">
        <div class="login-box">
            <h1>Welcome Back</h1>
            <p class="subtitle">Login to continue exploring MovieHub</p>


            <form action="login" method="post"> <%-- FIXED: Corrected action URL --%>
                <div class="input-group">
                    <label for="gmail">Email</label>
                    <input type="email" name="gmail" id="gmail" placeholder="Enter your Gmail" required>
                </div>

                <div class="input-group password-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" placeholder="Enter your password" required>
                    <span class="toggle-password" onclick="togglePassword()">👁</span>
                </div>

                <div class="forgot-signup">
                    <a href="ChangePassword.jsp">Forgot Password?</a>
                    <a href="Register.jsp">Sign Up</a>
                </div>

                <button type="submit" name="submit" class="login-btn">Login</button>
            </form>
        </div>
    </div>

    <%@ include file="Footer.jsp" %>
</body>
</html>
