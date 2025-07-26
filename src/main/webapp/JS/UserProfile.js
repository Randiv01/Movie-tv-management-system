function validateForm() {
    const dob = document.getElementById("dob").value;
    const nic = document.getElementById("nic").value.trim();

    // --- Date of Birth Validation ---
    if (!dob) {
        alert("Please enter your Date of Birth.");
        return false;
    }

    const birthDate = new Date(dob);
    const today = new Date();
    let age = today.getFullYear() - birthDate.getFullYear();
    const m = today.getMonth() - birthDate.getMonth();

    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
        age--;
    }

    if (age < 18) {
        alert("You must be 18 years or older to register.");
        return false;
    }

    // --- NIC Validation ---
    if (nic === "") {
        alert("Please enter your NIC.");
        return false;
    }

    const nicRegex = /^(?:\d{9}[Vv]|\d{12})$/;
    if (!nicRegex.test(nic)) {
        alert("Please enter a valid NIC (e.g., 123456789V or 200012345678).");
        return false;
    }

    return true; // All validations passed
}

// --- Profile Image Preview ---
function previewImage() {
    const fileInput = document.getElementById("profile-img-input");
    const preview = document.getElementById("profile-img");
    const fileNameDisplay = document.getElementById("file-name-display");
    const hiddenInput = document.getElementById("profile-img-hidden");

    const file = fileInput.files[0];

    if (file) {
        const reader = new FileReader();
        reader.onload = function(e) {
            preview.src = e.target.result;
            hiddenInput.value = file.name;
        };
        reader.readAsDataURL(file);
        fileNameDisplay.textContent = file.name;
    } else {
        preview.src = "ProfileImages/dui.jpg";
        fileNameDisplay.textContent = "No image selected";
        hiddenInput.value = "";
    }
}

function redirectToChangePassword() {
    window.location.href = "ChangePassword.jsp";
}

