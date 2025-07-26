<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Change Password - MovieHub</title>
    <link rel="stylesheet" type="text/css" href="CSS/ChangePassword.css">
    <script src="JS/ChangePassword.js" defer></script>
    <!-- Add FontAwesome CDN for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="container">
        <h2>Change Password</h2>

        <!-- Verification Message -->
        <c:if test="${not empty verificationMessage}">
            <div class="message">
                <p>${verificationMessage}</p>
            </div>
        </c:if>

        <!-- Step 1: Verify Email, Mobile, and NIC (Hidden after successful verification) -->
        <c:if test="${not showPasswordForm}">
            <div class="form-box">
                <form action="ValidateUserServlet" method="post">
                    <label>Email:</label>
                    <input type="email" name="email" required>

                    <label>Mobile:</label>
                    <input type="text" name="mobile" required>

                    <label>NIC:</label>
                    <input type="text" name="nic" required>

                    <button type="submit" class="btn">Verify</button>
                </form>
            </div>
        </c:if>

        <!-- Step 2: Change Password Form (Only shown after verification success) -->
        <c:if test="${showPasswordForm}">
            <div class="form-box" id="password-change-form">
                <form action="ResetPasswordServlet" method="post" onsubmit="return validatePasswordForm()">
                    <input type="hidden" name="email" value="${param.email}">

                    <label>New Password:</label>
                    <input type="password" name="newPassword" id="newPassword" required>
                    <span class="toggle-password" onclick="togglePassword()">
                        <i class="fas fa-eye"></i> <!-- Font Awesome eye icon -->
                    </span>

                    <label>Confirm Password:</label>
                    <input type="password" name="confirmPassword" id="confirmPassword" required>

                    <button type="submit" class="btn">Change Password</button>
                </form>
            </div>
        </c:if>
    </div>

</body>
</html>
