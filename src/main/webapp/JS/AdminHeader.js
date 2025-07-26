// Admin Header JS functionality

// Mobile Menu Toggle
const menuToggle = document.querySelector('.menu-toggle');
const navLinks = document.querySelector('.nav-links');

// Toggle the visibility of the navigation menu on mobile devices
menuToggle.addEventListener('click', () => {
    navLinks.classList.toggle('active');
});

// Optionally, close the menu when a menu item is clicked
const navItems = document.querySelectorAll('.nav-links li a');
navItems.forEach(item => {
    item.addEventListener('click', () => {
        navLinks.classList.remove('active');
    });
});
