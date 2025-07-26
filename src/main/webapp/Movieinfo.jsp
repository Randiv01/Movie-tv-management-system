<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Movie, model.Review, model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Details</title>
    <link rel="stylesheet" type="text/css" href="CSS/Movieinfo.css">
    <script src="JS/Movieinfo.js" defer></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <!-- Include the external JS file for handling alerts -->
    <script src="JS/alertMessages.js" defer></script>

    <!-- Trigger the JS function to show the correct alert message -->
    <script>
        window.onload = function() {
            // Get the message parameters directly from the URL
            const urlParams = new URLSearchParams(window.location.search);
            const watchlistMsg = urlParams.get('watchlistMessage');
            const reviewMsg = urlParams.get('reviewMessage');

            if (watchlistMsg && watchlistMsg !== "") {
                showAlert(watchlistMsg, 'watchlist');
            }
            if (reviewMsg && reviewMsg !== "") {
                showAlert(reviewMsg, 'review');
            }
        }
    </script>
</head>
<body>

<%@ include file="Header.jsp" %>

<%
    User us = (User) session.getAttribute("user");
    boolean isLoggedIn = (us != null);
    request.setAttribute("isLoggedIn", isLoggedIn);
    request.setAttribute("loggedUser", us);
%>

<div class="movie-details-container">
    <c:if test="${empty moviesList}">
        <p>No movie details available.</p>
    </c:if>

    <c:forEach var="movie" items="${moviesList}">
        <div class="movie-header">
            <img src="Movieimages/${movie.image}" alt="${movie.mname}" class="movie-poster">
            <div class="movie-info">
                <h1>${movie.mname}</h1>
                <p><strong>Genre:</strong> ${movie.genre}</p>
                <p><strong>Release Date:</strong> ${movie.release_date}</p>
                <p><strong>Duration:</strong> ${movie.duration}</p>
                <p><strong>Language:</strong> ${movie.language}</p>
                <p><strong>Director:</strong> ${movie.director}</p>
                <p><strong>Cast:</strong> ${movie.cast}</p>
                <p><strong>IMDb Rating:</strong> ⭐ ${movie.rating}</p>
                <p><strong>Availability:</strong> ${movie.availability}</p>
                <a href="DownloadServlet?movieId=${movie.movieid}" class="download-btn">⬇ Download</a>
                
                <c:if test="${isLoggedIn}">
			    <form method="post" action="InsertWatchlistServlet" style="margin-top: 10px;">
			        <input type="hidden" name="movieId" value="${movie.movieid}">
			        <input type="hidden" name="userId" value="<%= us.getId() %>">
			        <input type="hidden" name="movieTitle" value="${movie.mname}">
			        <input type="hidden" name="genre" value="${movie.genre}">
			        <input type="hidden" name="rating" value="${movie.rating}">
			        <input type="hidden" name="director" value="${movie.director}">
			        <input type="hidden" name="releaseDate" value="${movie.release_date}">			      
			        <input type="hidden" name="moviePoster" value="${movie.image}">
			
			        <button type="submit" class="download-btn" style="background-color: #28a745;">➕ Add to Watchlist</button>
			    </form>
			</c:if>
                
               <div class="watch-trailer">
				  <strong>Watch Trailer:</strong>
				  <div class="trailer-container">
				    <c:if test="${not empty movie.trailer_url}">
				      <!-- Extract video ID from the embed URL -->
				      <c:set var="videoId" value="${movie.trailer_url.substring(movie.trailer_url.lastIndexOf('/') + 1)}" />
				      
				      <iframe
				        src="https://www.youtube.com/embed/${videoId}"
				        title="Trailer">
				      </iframe>
				    </c:if>
				    <c:if test="${empty movie.trailer_url}">
				      <p>Trailer not available</p>
				    </c:if>
				  </div>
				</div>              

            </div>

				<br>
	           <div class="movie-extra-info">
	    <div class="quality">
	        <h3>Available Quality</h3>
	        <ul class="quality-options">
	            <li>
	                <label class="quality-label">
	                    <input type="radio" name="quality" value="720p">
	                    <span class="custom-radio"></span>
	                    🎥 720p
	                </label>
	            </li>
	            <li>
	                <label class="quality-label">
	                    <input type="radio" name="quality" value="1080p">
	                    <span class="custom-radio"></span>
	                    🎥 1080p
	                </label>
	            </li>
	            <li>
	                <label class="quality-label">
	                    <input type="radio" name="quality" value="4k">
	                    <span class="custom-radio"></span>
	                    🎥 4K
	                </label>
	            </li>
	        </ul>
	    </div>
	    
    <div class="subtitles">
        <h3>Subtitles</h3>
        <ul class="subtitle-options">
            <li>
                <label class="subtitle-label">
                    <input type="checkbox" name="subtitle" value="english">
                    <span class="custom-checkbox"></span>
                    📝 English
                </label>
            </li>
        </ul>
    </div>
</div>

        </div>

        <div class="movie-description">
            <h2>About the Movie</h2>
            <p>${movie.description}</p>
        </div>

        <div class="reviews-section">
            <c:if test="${isLoggedIn}">
                <h3>Add Your Review</h3>
                <form method="post" action="insert_review">
                    <input type="hidden" name="movieId" value="${movie.movieid}">
                    <input type="hidden" name="userId" value="<%= us.getId() %>">
                    <div class="form-group">
                        <label>Movie/TV Series Name:</label>
                        <input type="text" name="movieName" value="${movie.mname}" readonly>
                    </div>
                    <div class="form-group">
                        <label>User Name:</label>
                        <input type="text" name="username" value="<%= us.getFname() + " " + us.getLname() %>" readonly>
                    </div>
                    <div class="form-group">
                        <label>Rating:</label>
                        <select name="rating" required>
                            <option value="1">1 Star</option>
                            <option value="2">2 Stars</option>
                            <option value="3">3 Stars</option>
                            <option value="4">4 Stars</option>
                            <option value="5">5 Stars</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Review Title:</label>
                        <input type="text" name="reviewTitle" placeholder="Short headline for your review" required>
                    </div>
                    <div class="form-group">
                        <label>Detailed Review:</label>
                        <textarea name="comment" required></textarea>
                    </div>
                    <div class="form-group">
                        <label>Pros:</label>
                        <input type="text" name="pros" placeholder="What you liked">
                    </div>
                    <div class="form-group">
                        <label>Cons:</label>
                        <input type="text" name="cons" placeholder="What you disliked">
                    </div>
                    <div class="form-group">
                        <label>Would you recommend?</label>
                        <input type="radio" name="recommend" value="yes"> Yes
                        <input type="radio" name="recommend" value="no"> No
                    </div>
                    <div class="form-group">
                        <label>Review Date:</label>
                        <input type="text" name="reviewDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" readonly>
                    </div>
                    <button type="submit">Submit Review</button>
                </form>
            </c:if>

            <h2>User Reviews</h2>

            <!-- Display reviews for this movie -->
            <c:forEach var="review" items="${reviews}">
                <c:if test="${review.movieId == movie.movieid && isLoggedIn && review.userId == loggedUser.id}">
                    
                    <!-- Review block for logged-in user’s own review -->
                    <div class="review highlighted-review" data-review-id="${review.reviewId}" data-movie-id="${movie.movieid}">
                        <div class="review-block">
                            <p><strong>Username:</strong> ${review.username}</p>
                            <p><strong>Comment:</strong> ${review.comment}</p>
                            <p><strong>Title:</strong> ${review.reviewTitle}</p>
                            <p><strong>Pros:</strong> ${review.pros}</p>
                            <p><strong>Cons:</strong> ${review.cons}</p>
                            <p><strong>Recommended:</strong> ${review.recommend}</p>
                            <p><strong>Review Date:</strong> ${review.reviewDate}</p>
                            <p><strong>Rating:</strong> ⭐ ${review.rating} Stars</p>
                        </div>
                        <button class="edit-review">Edit</button>
                        <button class="delete-review">Delete</button>
                    </div>
                </c:if>
            </c:forEach>

            <c:forEach var="review" items="${reviews}">
                <c:if test="${review.movieId == movie.movieid && (!isLoggedIn || review.userId != loggedUser.id)}">
                
                    <!-- Other user reviews -->
                    <div class="review" data-review-id="${review.reviewId}" data-movie-id="${movie.movieid}">
                        <p><strong>${review.username}:</strong> ${review.comment}</p>
                        <p><strong>Title:</strong> ${review.reviewTitle}</p>
                        <p><strong>Pros:</strong> ${review.pros}</p>
                        <p><strong>Cons:</strong> ${review.cons}</p>
                        <p><strong>Recommended:</strong> ${review.recommend}</p>
                        <p><strong>Review Date:</strong> ${review.reviewDate}</p>
                        <p>⭐ ${review.rating} Stars</p>
                    </div>
                </c:if>
            </c:forEach>
        </div>

        <script>
            // Handles edit and delete button clicks
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".edit-review").forEach(button => {
                    button.addEventListener("click", function () {
                        const reviewId = this.closest(".review, .highlighted-review").getAttribute("data-review-id");
                        window.location.href = "EditReviewServlet?reviewId=" + reviewId;
                    });
                });

                document.querySelectorAll(".delete-review").forEach(button => {
                    button.addEventListener("click", function () {
                        const reviewElement = this.closest(".review, .highlighted-review");
                        const reviewId = reviewElement.getAttribute("data-review-id");
                        const movieId = reviewElement.getAttribute("data-movie-id");

                        if (confirm("Are you sure you want to delete this review?")) {
                            const form = document.createElement("form");
                            form.method = "POST";
                            form.action = "RemovereviewServlet";

                            const reviewInput = document.createElement("input");
                            reviewInput.type = "hidden";
                            reviewInput.name = "reviewId";
                            reviewInput.value = reviewId;

                            const movieInput = document.createElement("input");
                            movieInput.type = "hidden";
                            movieInput.name = "movieId";
                            movieInput.value = movieId;

                            form.appendChild(reviewInput);
                            form.appendChild(movieInput);
                            document.body.appendChild(form);
                            form.submit();
                        }
                    });
                });
            });
        </script>
    </c:forEach>
</div>

<%@ include file="Footer.jsp" %>
<%-- Includes the footer content --%>

</body>
</html>
