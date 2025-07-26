// Function to toggle password visibility
function togglePassword() {
    const newPasswordField = document.getElementById("newPassword");
    const confirmPasswordField = document.getElementById("confirmPassword");
    
    // Toggle visibility of both password fields
    if (newPasswordField.type === "password" && confirmPasswordField.type === "password") {
        newPasswordField.type = "text";
        confirmPasswordField.type = "text";
    } else {
        newPasswordField.type = "password";
        confirmPasswordField.type = "password";
    }
}

// Function to validate password on form submission
function validatePasswordForm() {
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;

    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@#$%^&+=!]).{8,}$/;

    // Validate new password
    if (!passwordRegex.test(newPassword)) {
        alert("Password must be at least 8 characters long, contain at least one letter, one number, and one special character.");
        return false;
    }

    // Check if passwords match
    if (newPassword !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }

    return true;
}
