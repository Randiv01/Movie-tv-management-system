<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" type="text/css" href="CSS/AboutUs.css">
    <title>About Us - MovieHub</title>
</head>
<body>
    <%@ include file="Header.jsp" %>

    <!-- Hero Section -->
    <div class="about-header">
        <div class="overlay">
            <h1>About Us</h1>
            <p>Bringing you closer to the world of movies and TV shows.</p>
        </div>
    </div>

    <!-- Main Content -->
    <div class="about-container">
        
        <!-- Our Story -->
        <section class="our-story">
            <h2><i class="fas fa-film"></i> Our Story</h2>
            <p>MovieHub was founded with one simple goal in mind: to create a comprehensive platform for movie and TV enthusiasts. We believe that everyone deserves easy access to the latest movie and TV series information, including release dates, reviews, ratings, and trailers.</p>
            <p>Over the years, MovieHub has become a trusted source for movie buffs worldwide, continuously growing to provide the best content and insights into the entertainment industry.</p>
        </section>

        <!-- What We Offer -->
        <section class="what-we-offer">
            <h2><i class="fas fa-star"></i> What We Offer</h2>
            <div class="offer-cards">
                <div class="offer-item">
                    <i class="fas fa-tv"></i>
                    <h3>Latest Movies &amp; TV Shows</h3>
                    <p>Stay updated with the newest releases, trailers, and entertainment news.</p>
                </div>
                <div class="offer-item">
                    <i class="fas fa-heart"></i>
                    <h3>Personalized Recommendations</h3>
                    <p>Discover movies and shows tailored to your taste based on your watch history.</p>
                </div>
                <div class="offer-item">
                    <i class="fas fa-users"></i>
                    <h3>Community Discussions</h3>
                    <p>Engage with fellow movie lovers, share reviews, and discuss your favorite films.</p>
                </div>
                <div class="offer-item">
                    <i class="fas fa-chart-line"></i>
                    <h3>Ratings &amp; Analytics</h3>
                    <p>Get insightful ratings, reviews, and analytics on your favorite movies and TV shows.</p>
                </div>
            </div>
        </section>

        <!-- Meet the Team -->
        <section class="leaderboard-section">
            <h2><i class="fas fa-user-tie"></i> Meet the Leadership Team</h2>
            <div class="leaderboard">
                <div class="leader-item">
                    <img src="Images/developer.jpg" alt="John Doe">
                    <h3>John Doe</h3>
                    <p>Chief Executive Officer</p>
                    <p>Leading MovieHub’s strategic vision and innovation.</p>
                </div>
                <div class="leader-item">
                    <img src="Images/ceo.jpg" alt="Jane Smith">
                    <h3>Jane Smith</h3>
                    <p>Chief Financial Officer</p>
                    <p>Managing financial growth and sustainability.</p>
                </div>
                <div class="leader-item">
                    <img src="Images/coceo.jpg" alt="Mike Johnson">
                    <h3>Mike Johnson</h3>
                    <p>Chief Operations Officer</p>
                    <p>Ensuring smooth day-to-day operations.</p>
                </div>
                <div class="leader-item">
                    <img src="Images/feceo.jpg" alt="Sophia Lee">
                    <h3>Sophia Lee</h3>
                    <p>Chief Creative Officer</p>
                    <p>Overseeing design, content, and marketing strategies.</p>
                </div>
            </div>
        </section>

        <!-- Our Mission -->
        <section class="mission-section">
            <h2><i class="fas fa-bullseye"></i> Our Mission</h2>
            <p>At MovieHub, our mission is to provide an all-in-one destination for movie and TV lovers. Whether you're a casual viewer or a dedicated film buff, we aim to bring the best entertainment experience straight to you.</p>
        </section>
        
        <!-- Our Vision Section -->
		<div class="vision-section">
			<h2><i class="fas fa-lightbulb"></i> Our Vision</h2>
		    <p>At MovieHub, our vision is to revolutionize the way people engage with movies and TV shows. We aim to create a seamless platform where users can discover, review, and share their love for entertainment effortlessly.</p>
		</div>
        
        <!-- Core Values -->
        <section class="values-section">
            <h2><i class="fas fa-heartbeat"></i> Our Values</h2>
            <ul>
                <li><strong><i class="fas fa-check-circle"></i> Integrity:</strong> Honest, unbiased reviews and recommendations.</li>
                <li><strong><i class="fas fa-lightbulb"></i> Innovation:</strong> Cutting-edge features to enhance user experience.</li>
                <li><strong><i class="fas fa-film"></i> Passion:</strong> A deep love for movies and TV shows.</li>
                <li><strong><i class="fas fa-users"></i> Community:</strong> A space for movie lovers to connect and share.</li>
            </ul>
        </section>

        <!-- User Testimonials -->
        <section class="testimonials">
            <h2><i class="fas fa-comments"></i> What Our Users Say</h2>
            <div class="testimonial-box">
                <p><i class="fas fa-quote-left"></i> MovieHub is my go-to platform for movie updates and recommendations! The reviews are spot on! <i class="fas fa-quote-right"></i></p>
                <h4>- Alex Brown</h4>
            </div>
            <div class="testimonial-box">
                <p><i class="fas fa-quote-left"></i> I love the community discussions and the personalized recommendations! <i class="fas fa-quote-right"></i></p>
                <h4>- Lisa Carter</h4>
            </div>
        </section>

    </div>

    <%@ include file="Footer.jsp" %>
</body>
</html>
