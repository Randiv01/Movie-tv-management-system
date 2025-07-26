function validateForm() {
    const dob = document.getElementById("dob").value;
    const nic = document.getElementById("nic").value.trim();
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm-password").value;
    const mobile = document.getElementById("mobile").value.trim();

    // Validate Date of Birth (DOB)
    if (!dob) {
        alert("Please enter your Date of Birth.");
        return false;
    }

    // Check if user is 18 or older
    const birthDate = new Date(dob);
    const currentDate = new Date();
    let age = currentDate.getFullYear() - birthDate.getFullYear();
    const monthDiff = currentDate.getMonth() - birthDate.getMonth();
    if (monthDiff < 0 || (monthDiff === 0 && currentDate.getDate() < birthDate.getDate())) {
        age--;
    }
    if (age < 18) {
        alert("You must be 18 years or older to register.");
        return false;
    }

    // Validate NIC
    const nicRegex = /^\d{9}[Vv]$|^\d{12}$/;
    if (!nicRegex.test(nic)) {
        alert("Please enter a valid NIC (e.g., 123456789V or 200012345678).");
        return false;
    }

    // Validate Mobile Number
    const mobileRegex = /^[0-9]{10}$/;
    if (!mobileRegex.test(mobile)) {
        alert("Please enter a valid 10-digit mobile number.");
        document.getElementById("mobile").focus();
        return false;
    }

    // Validate Password Match
    if (password !== confirmPassword) {
        alert("Passwords do not match. Please try again.");
        return false;
    }

    return true;
}

function togglePassword() {
    const passwordField = document.getElementById("password");
    passwordField.type = passwordField.type === "password" ? "text" : "password";
}
