<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Privacy Policy - MovieHub</title>
    <link rel="stylesheet" type="text/css" href="CSS/PrivacyPolicy.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <%@ include file="Header.jsp" %>
    
    <section class="privacy-hero">
        <div class="overlay">
            <h1>Privacy Policy</h1>
            <p>Your Privacy Matters to Us</p>
        </div>
    </section>
    
    <section class="privacy-content">
        <div class="privacy-container">
            <h2>Introduction</h2>
            <p>At MovieHub, your privacy is our priority. We are committed to safeguarding your personal data and providing transparency on how we collect, use, and protect your information.</p>
            <br>
            <div class="privacy-info">
                <h3><i class="fa fa-user"></i> Information We Collect</h3>
                <ul>
                    <li><strong>Personal Information:</strong> Includes your name, email, and any other information you provide during registration.</li>
                    <li><strong>Payment Information:</strong> Securely collected for any subscriptions or purchases.</li>
                    <li><strong>Browsing Data:</strong> Includes information about your use of MovieHub to enhance your experience.</li>
                    <li><strong>Device Information:</strong> Details like your IP address, device type, and OS for better service optimization.</li>
                </ul>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-cogs"></i> How We Use Your Information</h3>
                <ul>
                    <li><strong>Personalization:</strong> To suggest movies and TV shows based on your preferences.</li>
                    <li><strong>Customer Support:</strong> To address inquiries and provide assistance when needed.</li>
                    <li><strong>Marketing:</strong> To send updates, promotions, and newsletters that are relevant to you.</li>
                    <li><strong>Improvement:</strong> To enhance our services using data-driven insights.</li>
                </ul>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-shield-alt"></i> Security Measures</h3>
                <p>We implement the latest security protocols, including encryption and secure servers, to protect your data from unauthorized access or misuse.</p>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-key"></i> Your Rights &amp; Control</h3>
                <p>You have full control over your personal data. You can view, update, or request deletion of your data, or opt out of marketing communications at any time.</p>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-cookie-bite"></i> Cookies &amp; Tracking</h3>
                <p>We use cookies to improve your experience, customize content, and analyze website traffic. You can manage cookie settings in your browser.</p>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-external-link-alt"></i> Third-Party Links</h3>
                <p>Our website may contain links to third-party websites. We are not responsible for their privacy practices, so we recommend you review their policies.</p>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-child"></i> Children's Privacy</h3>
                <p>We do not knowingly collect information from children under 13. If we learn we have collected such information, we will delete it immediately.</p>
            </div>
            
            <div class="privacy-info">
                <h3><i class="fa fa-refresh"></i> Updates to Privacy Policy</h3>
                <p>We may update this policy periodically. Any changes will be posted on this page, and the updated policy will be effective immediately upon posting.</p>
            </div>
        </div>
    </section>
    
    <%@ include file="Footer.jsp" %>
</body>
</html>
