<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="model.TVSReview" %>
<%@ page import="model.TVSeries" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    // Retrieve user from session and determine if logged in
    User us = (User) session.getAttribute("user");
    boolean isLoggedIn = (us != null);
    request.setAttribute("isLoggedIn", isLoggedIn);
    request.setAttribute("loggedUser", us);

    // Get today's date in yyyy-MM-dd format
    request.setAttribute("todayDate", new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>TV Series Details</title>
    <link rel="stylesheet" type="text/css" href="CSS/tv_seriesinfo.css">
    <script src="JS/Movieinfo.js" defer></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <!-- SweetAlert2 for showing alerts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <script>
        $(document).ready(function() {
            // Show alert messages passed through URL
            const urlParams = new URLSearchParams(window.location.search);
            const watchlistMsg = urlParams.get('watchlistMessage');
            const reviewMsg = urlParams.get('reviewMessage');

            if (watchlistMsg) alert(decodeURIComponent(watchlistMsg));
            if (reviewMsg) alert(decodeURIComponent(reviewMsg));

            // Show delete success message from session (if any)
            <%
                String deleteSuccess = (String) session.getAttribute("deleteSuccess");
                if (deleteSuccess != null) {
            %>
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: '<%= deleteSuccess %>',
                        showConfirmButton: true,
                        timer: 3000
                    });
            <%
                    session.removeAttribute("deleteSuccess");
                }
            %>
        });

        // Show confirmation before deleting a review
        function confirmDelete(form) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    form.submit(); // Submit form only if confirmed
                }
            });
            return false; // Prevent default form submission
        }
    </script>

    <script>
        $(document).ready(function() {
            // Show any alert set in session (e.g., after form submission)
            <%
                String alertType = (String) session.getAttribute("alertType");
                String alertMessage = (String) session.getAttribute("alertMessage");

                if (alertType != null && alertMessage != null) {
            %>
                    Swal.fire({
                        icon: '<%= alertType %>',
                        title: '<%= alertMessage %>',
                        showConfirmButton: true,
                        timer: 3000
                    });
            <%
                    // Remove message so it's not shown again
                    session.removeAttribute("alertType");
                    session.removeAttribute("alertMessage");
                }
            %>
        });
    </script>
</head>
<body>

<%@ include file="Header.jsp" %>

<div class="movie-details-container">
    <!-- Show message if no data is available -->
    <c:if test="${empty tvSeriesList && empty tvReviewList}">
        <p>No TV Series details available.</p>
    </c:if>

    <!-- Loop through TV series list and show details -->
    <c:forEach var="tvSeries" items="${tvSeriesList}">
        <div class="movie-header">
            <!-- Show poster image -->
            <img src="TVSeriesImages/${tvSeries.posterUrl}" alt="${tvSeries.title}" class="movie-poster">
            <div class="movie-info">
                <!-- TV Series basic info -->
                <h1>${tvSeries.title}</h1>
                <p><strong>Genre:</strong> ${tvSeries.genre}</p>
                <p><strong>First Air Date:</strong> ${tvSeries.releaseDate}</p>
                <p><strong>Seasons:</strong> ${tvSeries.seasons}</p>
                <p><strong>Episodes:</strong> ${tvSeries.episodes}</p>
                <p><strong>Language:</strong> ${tvSeries.language}</p>
                <p><strong>Creator:</strong> ${tvSeries.creator}</p>
                <p><strong>Cast:</strong> ${tvSeries.cast}</p>
                <p><strong>IMDb Rating:</strong> ⭐ ${tvSeries.rating}</p>
                <p><strong>Status:</strong> ${tvSeries.status}</p>

                <!-- Download TV series button -->
                <a href="DownloadTVSeriesServlet?tvSeriesId=${tvSeries.tsid}" class="download-btn">⬇ Download</a>

                <!-- Show watchlist button if user is logged in -->
                <c:if test="${isLoggedIn}">
                    <form method="post" action="InsertTVWatchlistServlet" style="margin-top: 10px;">
                        <!-- Hidden values for adding to watchlist -->
                        <input type="hidden" name="tvSeriesId" value="${tvSeries.tsid}">
                        <input type="hidden" name="userId" value="${loggedUser.id}">
                        <input type="hidden" name="tvSeriesTitle" value="${tvSeries.title}">
                        <input type="hidden" name="genre" value="${tvSeries.genre}">
                        <input type="hidden" name="rating" value="${tvSeries.rating}">
                        <input type="hidden" name="creator" value="${tvSeries.creator}">
                        <input type="hidden" name="releaseDate" value="${tvSeries.releaseDate}">
                        <input type="hidden" name="tvPoster" value="${tvSeries.posterUrl}">
                        <button type="submit" class="download-btn" style="background-color: #28a745;">➕ Add to Watchlist</button>
                    </form>
                </c:if>

                <!-- Trailer embedded from YouTube -->
                <div class="watch-trailer">
                    <strong>Watch Trailer:</strong>
                    <div class="trailer-container">
                        <iframe src="https://www.youtube.com/embed/${tvSeries.trailerUrl.split('v=')[1]}" title="${tvSeries.title} Trailer"></iframe>
                    </div>
                </div>
            </div>

            <!-- Display video quality and subtitle options -->
            <div class="movie-extra-info">
                <div class="quality">
                    <h3>Available Quality</h3>
                    <ul class="quality-options">
                        <li><label class="quality-label"><input type="radio" name="quality" value="720p"><span class="custom-radio"></span>🎥 720p</label></li>
                        <li><label class="quality-label"><input type="radio" name="quality" value="1080p"><span class="custom-radio"></span>🎥 1080p</label></li>
                        <li><label class="quality-label"><input type="radio" name="quality" value="4k"><span class="custom-radio"></span>🎥 4K</label></li>
                    </ul>
                </div>

                <div class="subtitles">
                    <h3>Subtitles</h3>
                    <ul class="subtitle-options">
                        <li><label class="subtitle-label"><input type="checkbox" name="subtitle" value="english"><span class="custom-checkbox"></span>📝 English</label></li>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Show description of the series -->
        <div class="movie-description">
            <h2>About the TV Series</h2>
            <p>${tvSeries.description}</p>
        </div>

        <!-- Review form (only visible when logged in) -->
        <div class="reviews-section">
            <c:if test="${isLoggedIn}">
                <h3>Add Your Review</h3>
                <form id="reviewForm" method="post" action="InsertTVSRServlet" enctype="multipart/form-data">
                    <input type="hidden" name="tvSeriesId" value="${tvSeries.tsid}">
                    <input type="hidden" name="userId" value="${loggedUser.id}">

                    <!-- Reviewer & series name -->
                    <div class="form-row">
                        <div class="form-group half">
                            <label>TV Series Name:</label>
                            <input type="text" name="tvSeriesName" value="${tvSeries.title}" readonly>
                        </div>
                        <div class="form-group half">
                            <label>Your Name:</label>
                            <input type="text" name="username" value="${loggedUser.fname} ${loggedUser.lname}" readonly>
                        </div>
                    </div>

                    <!-- Season, episode, and date -->
                    <div class="form-row">
                        <div class="form-group third">
                            <label>Season:</label>
                            <input type="number" name="season" min="1" max="${tvSeries.seasons}" required>
                        </div>
                        <div class="form-group third">
                            <label>Episode (optional):</label>
                            <input type="text" name="episode">
                        </div>
                        <div class="form-group third">
                            <label>Review Date:</label>
                            <input type="text" name="reviewDate" value="${todayDate}" readonly>
                        </div>
                    </div>

                    <!-- Rating and title -->
                    <div class="form-row">
                        <div class="form-group half">
                            <label>Rating:</label>
                            <select name="rating" required>
                                <option value="">-- Select Rating --</option>
                                <option value="1">★☆☆☆☆</option>
                                <option value="2">★★☆☆☆</option>
                                <option value="3">★★★☆☆</option>
                                <option value="4">★★★★☆</option>
                                <option value="5">★★★★★</option>
                            </select>
                        </div>
                        <div class="form-group half">
                            <label>Review Title:</label>
                            <input type="text" name="reviewTitle" maxlength="50" required>
                        </div>
                    </div>

                    <!-- Detailed review -->
                    <div class="form-group">
                        <label>Detailed Review:</label>
                        <textarea name="comment" maxlength="1000" required></textarea>
                    </div>

                    <!-- Pros and Cons -->
                    <div class="form-row">
                        <div class="form-group half">
                            <label>Pros:</label>
                            <input type="text" name="pros" maxlength="200">
                        </div>
                        <div class="form-group half">
                            <label>Cons:</label>
                            <input type="text" name="cons" maxlength="200">
                        </div>
                    </div>

                    <!-- Recommendation radio buttons -->
                    <div class="form-group recommend-group">
                        <div class="inline-options">
                            <span class="option-label">Would you recommend this series?</span>
                            <label class="radio-option">
                                <input type="radio" name="recommend" value="yes" required> Yes
                            </label>
                            <label class="radio-option">
                                <input type="radio" name="recommend" value="no"> No
                            </label>
                        </div>
                    </div>

                    <!-- Screenshot upload -->
                    <div class="form-group">
                        <label>Upload Screenshot (optional):</label>
                        <input type="file" name="screenshot" accept=".jpg,.jpeg,.png">
                        <small>JPEG or PNG only. Max 2MB.</small>
                    </div>

                    <!-- Terms checkbox -->
                    <div class="form-group terms-group">
                        <div class="terms-container">
                            <input type="checkbox" name="agreeTerms" id="agreeTerms" required>
                            <label for="agreeTerms" class="terms-text">I confirm this is my honest opinion and agree to the terms.</label>
                        </div>
                    </div>

                    <!-- Submit button -->
                    <div class="submit-button-container">
                        <button type="submit">Submit Review</button>
                    </div>
                </form>
            </c:if>

            <!-- Display user reviews -->
            <br><h2>User Reviews</h2><br>

            <!-- Logged-in user's own reviews with edit/delete options -->
            <c:if test="${loggedUser != null}">
                <c:forEach var="review" items="${tvReviewList}">
                    <c:if test="${review.userId == loggedUser.id}">
                        <div class="review-card">
                            <h4>${review.reviewTitle} - <small>by ${review.username}</small></h4>
                            <p><strong>Season:</strong> ${review.season} | <strong>Episode:</strong> ${review.episode}</p>
                            <p><strong>Rating:</strong> ${review.rating} / 5</p>
                            <p>${review.comment}</p>
                            <p><strong>Pros:</strong> ${review.pros}</p>
                            <p><strong>Cons:</strong> ${review.cons}</p>
                            <p><strong>Recommend:</strong> ${review.recommend}</p>
                            <p><em>Date:</em> ${review.reviewDate}</p>

                            <!-- Edit review -->
                            <form action="EditTVSReviewServlet" method="get" style="display:inline;">
                                <input type="hidden" name="tv_review_id" value="${review.tvReviewId}" />
                                <button type="submit" class="edit-review">Edit</button>
                            </form>

                            <!-- Delete review -->
                            <form action="RemoveTVReviewServlet" method="post" onsubmit="return confirmDelete(this);">
                                <input type="hidden" name="reviewId" value="${review.tvReviewId}" />
                                <button type="submit" class="delete-review">Delete</button>
                            </form>
                        </div>
                    </c:if>
                </c:forEach>
            </c:if>

            <!-- Other users' reviews -->
            <c:forEach var="review" items="${tvReviewList}">
                <c:if test="${loggedUser == null || review.userId != loggedUser.id}">
                    <div class="review-card">
                        <h4>${review.reviewTitle} - <small>by ${review.username}</small></h4>
                        <p><strong>Season:</strong> ${review.season} | <strong>Episode:</strong> ${review.episode}</p>
                        <p><strong>Rating:</strong> ${review.rating} / 5</p>
                        <p>${review.comment}</p>
                        <p><strong>Pros:</strong> ${review.pros}</p>
                        <p><strong>Cons:</strong> ${review.cons}</p>
                        <p><strong>Recommend:</strong> ${review.recommend}</p>
                        <p><em>Date:</em> ${review.reviewDate}</p>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </c:forEach>
</div>

<!-- Footer -->
<%@ include file="Footer.jsp" %>
</body>
</html>
