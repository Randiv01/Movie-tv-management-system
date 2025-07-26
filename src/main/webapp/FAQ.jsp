<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FAQs - MovieHub</title>
    <link rel="stylesheet" type="text/css" href="CSS/FAQ.css">
    <script src="JS/FAQ.js" defer></script>
</head>
<body>

    <%@ include file="Header.jsp" %>

    <div class="faq-container">
        <h1>Frequently Asked Questions</h1>
        <p>Here are some of the most commonly asked questions about MovieHub.</p>

        <div class="faq">
            <div class="faq-item">
                <button class="faq-question">What is MovieHub? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>MovieHub is an online platform where you can browse movies and TV series, view details, and keep track of your favorite content.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="faq-question">Do I need to create an account to use MovieHub? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>No, you can browse our collection without an account. However, creating an account allows you to save favorites, write reviews, and get personalized recommendations.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="faq-question">Is MovieHub free to use? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>Yes, MovieHub is completely free to browse. However, some external streaming links may require subscriptions to external services.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="faq-question">How can I search for a specific movie or TV series? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>You can use the search bar at the top of the homepage to quickly find movies or TV series by title, genre, or actor name.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="faq-question">Can I watch movies directly on MovieHub? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>No, MovieHub is a browsing and information platform. We provide details about movies and TV series, but you will need to visit streaming platforms to watch them.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="faq-question">How do I report incorrect movie details? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>If you find incorrect information, please contact us via the <a href="ContactUs.jsp">Contact Us</a> page with the correct details, and we will update it as soon as possible.</p>
                </div>
            </div>

            <div class="faq-item">
                <button class="faq-question">Is there a mobile app for MovieHub? <span class="icon">+</span></button>
                <div class="faq-answer">
                    <p>Currently, MovieHub is only available as a web platform, but we are working on a mobile app to enhance your experience.</p>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="Footer.jsp" %>

</body>
</html>
