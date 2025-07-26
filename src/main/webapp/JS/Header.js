document.addEventListener('DOMContentLoaded', function () {
    // Get references to the navigation links
    const navLinks = document.querySelectorAll('.nav-links a');
    const loginRegisterButton = document.querySelector('.btn-login-register');
    const logoutForm = document.querySelector('.logout-form');

    // Add click event listeners to the navigation links
    navLinks.forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default anchor behavior
            const section = this.textContent.trim();

            // Handle navigation based on the clicked link
            switch (section) {
                case 'Home':
                    window.location.href = 'Home.jsp';
                    break;
                case 'Movies':
                    window.location.href = 'DisplayMServlet';
                    break;
                case 'TV Series':
                    window.location.href = 'displayTVSeries';
                    break;
                case 'Contact Us':
                    window.location.href = 'ContactUs.jsp';
                    break;
                case 'About Us':
                    window.location.href = 'AboutUs.jsp';
                    break;
                default:
                    console.error('Unknown section:', section);
            }
        });
    });

    // Add click event listener to the login/register button (if it exists)
    if (loginRegisterButton) {
        loginRegisterButton.addEventListener('click', function (event) {
            event.preventDefault();
            window.location.href = 'Login.jsp'; // Redirect to login page
        });
    }

    // Add submit event listener to the logout form (ensures session is destroyed properly)
    if (logoutForm) {
        logoutForm.addEventListener('submit', function () {
            setTimeout(() => {
                window.location.href = 'Login.jsp'; // Redirect after logout
            }, 500);
        });
    }
});
