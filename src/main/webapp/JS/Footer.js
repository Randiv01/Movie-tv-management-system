document.addEventListener('DOMContentLoaded', function () {
    // Get references to the footer navigation links and buttons
    const footerNavLinks = document.querySelectorAll('.footer-nav a');
    const socialIcons = document.querySelectorAll('.social-icon');
    const footerInfoLinks = document.querySelectorAll('.footer-info a');

    // Add click event listeners to the footer navigation links
    footerNavLinks.forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default anchor behavior
            const section = this.textContent.trim().toLowerCase();

            // Handle navigation based on the clicked link
            switch (section) {
                case 'home':
                    window.location.href = 'Home.jsp';
                    break;
                case 'movies':
                    window.location.href = 'DisplayMServlet'; 
                    break;
                case 'tv series':
                    window.location.href = 'displayTVSeries'; 
                    break;
                case 'about us':
                    window.location.href = 'AboutUs.jsp'; 
                    break;
                case 'contact us':
                    window.location.href = 'ContactUs.jsp'; 
                    break;
                default:
                    console.error('Unknown section:', section);
            }
        });
    });

    // Add click event listeners to the social media icons
    socialIcons.forEach(icon => {
        icon.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default anchor behavior
            const iconClass = this.querySelector('i').classList;

            // Handle social media links based on the icon class
            if (iconClass.contains('fa-facebook-f')) {
                window.open('https://www.facebook.com', '_blank');
            } else if (iconClass.contains('fa-x-twitter')) { // Updated to X logo
                window.open('https://twitter.com', '_blank');
            } else if (iconClass.contains('fa-instagram')) { // Fixed Instagram link
                window.open('https://www.instagram.com', '_blank');
            } else if (iconClass.contains('fa-youtube')) { // Added YouTube
                window.open('https://www.youtube.com', '_blank');
            } else if (iconClass.contains('fa-envelope')) {
                window.open('mailto:example@gmail.com');
            } else {
                console.error('Unknown social media icon:', iconClass);
            }
        });
    });

    // Add click event listeners to the footer info links
    footerInfoLinks.forEach(link => {
        link.addEventListener('click', function (event) {
            event.preventDefault(); // Prevent default anchor behavior
            const section = this.textContent.trim().toLowerCase();

            // Handle navigation based on the clicked info link
            switch (section) {
                case 'faq':
                    window.location.href = 'FAQ.jsp'; // Changed from faq.php
                    break;
                case 'privacy policy':
                    window.location.href = 'PrivacyPolicy.jsp'; // Changed from privacyNote.php
                    break;
                case 'terms of service':
                    window.location.href = 'Terms.jsp'; // Changed from terms.php
                    break;
                default:
                    console.error('Unknown info section:', section);
            }
        });
    });
});
