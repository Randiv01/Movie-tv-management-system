<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - MovieHub</title>
    <link rel="stylesheet" href="CSS/Register.css">
    <script defer src="JS/Register.js"></script>
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="register-container">
        <div class="register-box">
            <h1>Create an Account</h1>
            <p class="subtitle">Join MovieHub today and explore the world of movies and TV Series!</p>

            <form action="register" method="post" onsubmit="return validateForm()">
                <div class="form-grid">
                    <!-- Left Column -->
                    <div class="left-column">
                        <div class="input-group">
                            <label for="firstname">First Name</label>
                            <input type="text" name="firstname" id="firstname" placeholder="Enter your first name" required>
                        </div>

                        <div class="input-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" placeholder="Enter your Gmail" required>
                        </div>

                        <div class="input-group">
                            <label for="nic">NIC</label>
                            <input type="text" name="nic" id="nic" placeholder="Enter your NIC" pattern="^\d{9}[Vv]|\d{12}$" 
                                   title="Enter a valid NIC (e.g., 123456789V or 200012345678)" required>
                        </div>

                        <div class="input-group password-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" placeholder="Create a password" required>
                            <span class="toggle-password" onclick="togglePassword()">👁</span>
                        </div>
                    </div>

                    <!-- Right Column -->
                    <div class="right-column">
                        <div class="input-group">
                            <label for="lastname">Last Name</label>
                            <input type="text" name="lastname" id="lastname" placeholder="Enter your last name" required>
                        </div>

                        <div class="input-group">
                            <label for="dob">Date of Birth</label>
                            <input type="date" name="dob" id="dob" required>
                        </div>

                        <div class="input-group">
						    <label for="mobile">Mobile</label>
						    <input type="text" name="mobile" id="mobile" placeholder="Enter your mobile number" maxlength="10" required>
						</div>

                        <div class="input-group">
                            <label for="confirm-password">Confirm Password</label>
                            <input type="password" name="confirm-password" id="confirm-password" placeholder="Confirm your password" required>
                        </div>

                        <div class="terms">
                            <input type="checkbox" id="terms" required>
                            <label for="terms">I agree to the <a href="Terms.jsp">Terms and Conditions</a></label>
                        </div>
                    </div>
                </div>

                <!-- Register Button at the bottom -->
                <button type="submit" name="submit" class="register-btn">Register</button>

                <p class="login-link">Already have an account? <a href="Login.jsp">Login here</a></p>
            </form>
        </div>
    </div>

    <%@ include file="Footer.jsp" %>
</body>
</html>
