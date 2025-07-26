<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MovieHub - Home</title>
    <link rel="stylesheet" href="CSS/Home.css">
    <script src="JS/Home.js" defer></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body>

    <%@ include file="Header.jsp" %>

    <!-- Hero Section -->
    <div class="hero">
        <div class="hero-slider">
            <div class="hero-slide active" style="background-image: url('Images/avatar-2.jpg');">
                <div class="hero-content">
                    <h2>Top Trending Movie</h2>
                    <p>Watch the latest blockbuster movies now!</p>
                    <a href="DisplayMServlet" class="btn">Watch Now</a>
                </div>
            </div>
            <div class="hero-slide" style="background-image: url('Images/brakingbad.jpg');">
                <div class="hero-content">
                    <h2>Exclusive TV Shows</h2>
                    <p>Stream the best TV series with new episodes every week.</p>
                    <a href="displayTVSeries" class="btn">Explore Shows</a>
                </div>
            </div>
            <div class="hero-slide" style="background-image: url('Images/kingkong12.jpg');">
                <div class="hero-content">
                    <h2>Watch Anytime, Anywhere</h2>
                    <p>Enjoy your favorite movies and TV shows on any device.</p>
                    <a href="DisplayMServlet" class="btn">Start Watching</a>
                </div>
            </div>
            <div class="hero-slide" style="background-image: url('Images/the100.jpg');">
                <div class="hero-content">
                    <h2>Epic Sci-Fi Adventure</h2>
                    <p>Experience mind-blowing journeys through space and time.</p>
                    <a href="DisplayMServlet" class="btn">Explore Now</a>
                </div>
            </div>
            <div class="hero-slide" style="background-image: url('Images/sm.jpg');">
                <div class="hero-content">
                    <h2>Top Rated Drama Series</h2>
                    <p>Dive into thrilling stories and unforgettable characters.</p>
                    <a href="displayTVSeries" class="btn">Watch Series</a>
                </div>
            </div>
        </div>

        <!-- Navigation Dots -->
        <div class="dots">
            <div class="dot active" onclick="currentSlide(0)"></div>
            <div class="dot" onclick="currentSlide(1)"></div>
            <div class="dot" onclick="currentSlide(2)"></div>
            <div class="dot" onclick="currentSlide(3)"></div>
            <div class="dot" onclick="currentSlide(4)"></div>
        </div>
    </div>

	   <!-- Welcome Section -->
	<div class="welcome-section">
	  <h1>Welcome to MovieHub!</h1>
	  <p>Your ultimate destination for browsing and streaming the latest movies and TV series. Explore, watch, and enjoy a seamless entertainment experience.<br>
	  We bring you the most up-to-date movies and series, personalized recommendations, and a trustworthy platform to share and read real reviews. Join a community that lives and breathes entertainment!</p>
	</div>

    <!-- Categories Section -->
    <div class="section">
        <h2>Explore by Categories</h2>
        <div class="genre-categories">
            <div class="genre-item"><i class="bi bi-lightning-charge-fill"></i><p>Action</p></div>
            <div class="genre-item"><i class="bi bi-film"></i><p>Comedy</p></div>
            <div class="genre-item"><i class="bi bi-heart-fill"></i><p>Romance</p></div>
            <div class="genre-item"><i class="bi bi-exclamation-triangle-fill"></i><p>Horror</p></div>
            <div class="genre-item"><i class="bi bi-robot"></i><p>Sci-Fi</p></div>
            <div class="genre-item"><i class="bi bi-mortarboard-fill"></i><p>Drama</p></div>
            <div class="genre-item"><i class="bi bi-globe-americas"></i><p>Adventure</p></div>
        </div>
    </div>

<!-- Upcoming Movies -->
<div class="section">
    <h2>Upcoming Movies</h2>
    <div class="movie-list">

        <div class="movie">
            <img src="Images/thunderbolts.jpg" alt="Thunderbolts">
            <div class="movie-info">
                <p class="movie-title">Thunderbolts</p>
                <span class="release-date">Release: May 2, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/shadow_force.jpg" alt="Shadow Force">
            <div class="movie-info">
                <p class="movie-title">Shadow Force</p>
                <span class="release-date">Release: May 9, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/accountant2.jpg" alt="The Accountant 2">
            <div class="movie-info">
                <p class="movie-title">The Accountant 2</p>
                <span class="release-date">Release: May 9, 2025</span>
            </div>
        </div>
        
        <div class="movie">
            <img src="Images/avatar_fire_and_ash.jpg" alt="Avatar: Fire and Ash">
            <div class="movie-info">
                <p class="movie-title">Avatar: Fire and Ash</p>
                <span class="release-date">Release: December 19, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/final_destination_bloodline.jpg" alt="Final Destination: Bloodline">
            <div class="movie-info">
                <p class="movie-title">Final Destination: Bloodline</p>
                <span class="release-date">Release: May 16, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/until_dawn.jpg" alt="Until Dawn">
            <div class="movie-info">
                <p class="movie-title">Until Dawn</p>
                <span class="release-date">Release: April 25, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/karate_kid_legends.jpg" alt="Karate Kid: Legends">
            <div class="movie-info">
                <p class="movie-title">Karate Kid: Legends</p>
                <span class="release-date">Release: May 30, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/electric_state.jpg" alt="The Electric State">
            <div class="movie-info">
                <p class="movie-title">The Electric State</p>
                <span class="release-date">Release: March 14, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/mission_impossible.jpg" alt="Mission: Impossible – The Final Reckoning">
            <div class="movie-info">
                <p class="movie-title">Mission: Impossible – The Final Reckoning</p>
                <span class="release-date">Release: May 2, 2025</span>
            </div>
        </div>

    </div>
</div>

<!-- Upcoming TV Series -->
<div class="section">
    <h2>Upcoming TV Series</h2>
    <div class="movie-list">

        <div class="movie">
            <img src="Images/the-last-of-us-s2.jpg" alt="The Last of Us: Season 2">
            <div class="movie-info">
                <p class="movie-title">The Last of Us: Season 2</p>
                <span class="release-date">Release: April 13, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/foundation2.jpg" alt="Foundation – Season 2">
            <div class="movie-info">
                <p class="movie-title">Foundation – Season 2</p>
                <span class="release-date">Premiere: October 30, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/poker_face.jpg" alt="Poker Face – Season 2">
            <div class="movie-info">
                <p class="movie-title">Poker Face – Season 2</p>
                <span class="release-date">Release: May 8, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/haunting_bly_manor2.jpg" alt="The Haunting of Bly Manor – Season 2">
            <div class="movie-info">
                <p class="movie-title">The Haunting of Bly Manor – Season 2</p>
                <span class="release-date">Premiere: September 19, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/chief-of-war.jpg" alt="Chief of War">
            <div class="movie-info">
                <p class="movie-title">Chief of War</p>
                <span class="release-date">Release: August 1, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/daredevil-born-again.jpg" alt="Daredevil: Born Again">
            <div class="movie-info">
                <p class="movie-title">Daredevil: Born Again</p>
                <span class="release-date">Release: March 4, 2025</span>
            </div>
        </div>

        <div class="movie">
            <img src="Images/gilded_age.jpg" alt="The Gilded Age – Season 3">
            <div class="movie-info">
                <p class="movie-title">The Gilded Age – Season 3</p>
                <span class="release-date">Release: June 22, 2025</span>
            </div>
        </div>

    </div>
</div>
   
	<!-- Trending Movie Trailers Section -->
	<section class="trending-movie-section">
	  <h2><i class="fas fa-fire"></i> Trending Movie &amp; TV Series Trailers</h2>
	  <div class="trending-movie-list">
	  
	    <div class="trending-movie-item">
	      <iframe width="100%" height="180" src="https://www.youtube.com/embed/d6xdV8VAqBY"
	        title="Avatar: The Way of Water"></iframe>
	      <div class="trending-movie-title">Avatar: The Way of Water - Official Trailer</div>
	    </div>
	  
	    <div class="trending-movie-item">
	      <iframe width="100%" height="180" src="https://www.youtube.com/embed/HhesaQXLuRY"
	        title="Breaking Bad Trailer"></iframe>
	      <div class="trending-movie-title">Breaking Bad – Season 1 Official Trailer</div>
	    </div>
	  
	    <div class="trending-movie-item">
	      <iframe width="100%" height="180" src="https://www.youtube.com/embed/DdIHtymX_Fc"
	        title="Love and Monsters Trailer"></iframe>
	      <div class="trending-movie-title">Love and Monsters – Official Trailer</div>
	    </div>
	  
	    <div class="trending-movie-item">
	      <iframe width="100%" height="180" src="https://www.youtube.com/embed/BpJYNVhGf1s" 
	        title="Game of Thrones – Season 1 Trailer"></iframe>
	      <div class="trending-movie-title">Game of Thrones – Season 1 Trailer</div>
	    </div>
	  
	  </div>
	</section>

    <!-- Partner Companies -->
    <div class="section">
        <h2>Our Partners</h2>
        <div class="partner-companies">
            <div class="company-logo"><a href="https://www.paramount.com/" target="_blank"><img src="Images/paramount-pictures.png" alt="Paramount Pictures"></a></div>
            <div class="company-logo"><a href="https://www.disneyplus.com/" target="_blank"><img src="Images/Disney+_logo.svg.png" alt="Disney+"></a></div>
            <div class="company-logo"><a href="https://www.warnerbros.com/" target="_blank"><img src="Images/WarnerBrosLogo.png" alt="Warner Bros"></a></div>
            <div class="company-logo"><a href="https://www.netflix.com/" target="_blank"><img src="Images/netflix.jpg" alt="Netflix"></a></div>
            <div class="company-logo"><a href="https://www.marvel.com/" target="_blank"><img src="Images/marval.png" alt="Marvel Studios"></a></div>
            <div class="company-logo"><a href="https://www.sonypictures.com/" target="_blank"><img src="Images/sonylogo.png" alt="Sony Pictures"></a></div>
        </div>
    </div>

	<!-- Latest Movie & TV Series News Section -->
	<div class="section">
	    <h2>Latest Movie  &amp; TV Series News</h2>
	    <div class="latest-news">
	        <div class="news-item">
	            <div class="news-header">
	                <i class="fas fa-newspaper"></i>
	                <p><strong>Avengers 5 Set for 2026</strong></p>
	            </div>
	            <p>Marvel announces major sequel with returning cast.</p>
	            <span class="news-date">Published on: April 29, 2025</span>
	        </div>
	        <div class="news-item">
	            <div class="news-header">
	                <i class="fas fa-newspaper"></i>
	                <p><strong>Netflix Drops Thriller Series</strong></p>
	            </div>
	            <p>'Shadow Point' becomes instant hit on release.</p>
	            <span class="news-date">Published on: April 25, 2025</span>
	        </div>
	        <div class="news-item">
	            <div class="news-header">
	                <i class="fas fa-newspaper"></i>
	                <p><strong>Warner Bros Plans DC Reboot</strong></p>
	            </div>
	            <p>Upcoming changes expected for superhero lineup.</p>
	            <span class="news-date">Published on: April 20, 2025</span>
	        </div>
	        <div class="news-item">
	            <div class="news-header">
	                <i class="fas fa-newspaper"></i>
	                <p><strong>Apple TV+ Announces New Drama Series</strong></p>
	            </div>
	            <p>'The Silent Witness' set to premiere this fall.</p>
	            <span class="news-date">Published on: April 15, 2025</span>
	        </div>
	        <div class="news-item">
	            <div class="news-header">
	                <i class="fas fa-newspaper"></i>
	                <p><strong>New Star Wars Series in Development</strong></p>
	            </div>
	            <p>Lucasfilm teases more shows for the Star Wars universe.</p>
	            <span class="news-date">Published on: April 10, 2025</span>
	        </div>
	        <div class="news-item">
	            <div class="news-header">
	                <i class="fas fa-newspaper"></i>
	                <p><strong>Game of Thrones Prequel Teaser Released</strong></p>
	            </div>
	            <p>Fans eagerly await the first glimpse of 'House of the Dragon.'</p>
	            <span class="news-date">Published on: April 5, 2025</span>
	        </div>
	    </div>
	</div>
    
	<!-- Enhanced Statistics Section -->
	<div class="section statistics-section">
	    <h2>Platform Statistics</h2>
	    <div class="statistics">
	        <div class="stat-item">
	            <i class="bi bi-people-fill stat-icon"></i>
	            <h3>Registered Users</h3>
	            <p id="userCount">5,000+</p>
	        </div>
	        <div class="stat-item">
	            <i class="bi bi-film stat-icon"></i>
	            <h3>Available Movies</h3>
	            <p>1,500+</p>
	        </div>
	        <div class="stat-item">
	            <i class="bi bi-tv-fill stat-icon"></i>
	            <h3>Available TV Series</h3>
	            <p>350+</p>
	        </div>
	        <div class="stat-item">
	            <i class="bi bi-collection-play-fill stat-icon"></i>
	            <h3>Genres</h3>
	            <p>20+</p>
	        </div>
	    </div>
	</div>
	
			<!-- MovieHub Achievements Section -->
	<div class="section awards">
	  <h2><i class="fas fa-award"></i> MovieHub Achievements</h2>
	  <div class="awards-list">
	    <!-- Achievement 1 -->
	    <div class="award-item">
	      <i class="fas fa-trophy"></i>
	      <div class="award-details">
	        <p class="award-title"><strong>Top Web Project 2025</strong></p>
	        <span class="award-institution">University of SLIIT</span>
	      </div>
	    </div>
	    
	    <!-- Achievement 2 -->
	    <div class="award-item">
	      <i class="fas fa-medal"></i>
	      <div class="award-details">
	        <p class="award-title"><strong>Best UI Design</strong></p>
	        <span class="award-institution">Student Innovation Fair</span>
	      </div>
	    </div>
	
	    <!-- Achievement 3 -->
	    <div class="award-item">
	      <i class="fas fa-cogs"></i>
	      <div class="award-details">
	        <p class="award-title"><strong>Outstanding Technical Achievement</strong></p>
	        <span class="award-institution">Tech Expo 2025</span>
	      </div>
	    </div>
	
	    <!-- Achievement 4 -->
	    <div class="award-item">
	      <i class="fas fa-globe"></i>
	      <div class="award-details">
	        <p class="award-title"><strong>Best Global Impact</strong></p>
	        <span class="award-institution">Global Innovation Summit</span>
	      </div>
	    </div>
	  </div>
	</div>
		
	<!-- Cover Section -->
	<section class="cover-section">
	    <div class="cover-image">
	        <img src="Images/moviewall.jpg" alt="MovieHub Cover">
	    </div>
	    <div class="cover-overlay">
	        <div class="cover-content">
	            <h2>Stay Entertained Anytime, Anywhere!</h2>
	            <p>Join thousands of movie and TV show lovers on MovieHub. Discover new releases, trending content, and exclusive collections.</p>
	            <a href="DisplayMServlet" class="cover-btn btn-secondary">Browse Movies</a>
	            <a href="displayTVSeries" class="cover-btn btn-tertiary">Explore TV Series</a>
	            <br><br>
	            <a href="Register.jsp" class="cover-btn">Join Now</a>
	        </div>
	    </div>
	</section>
	
	<%@ include file="Footer.jsp" %>
    
</body>
</html>
