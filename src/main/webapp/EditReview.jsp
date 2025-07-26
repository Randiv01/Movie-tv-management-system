<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Review" %>

<%
    // Retrieve the review object from the request scope
    Review review = (Review) request.getAttribute("review");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Review</title>
    <!-- Link to external CSS file for styling the edit review page -->
    <link rel="stylesheet" href="CSS/EditReview.css">
</head>
<body>
<!-- Include the header of the website (common navigation, branding, etc.) -->
<%@ include file="Header.jsp" %>

<div class="edit-review-container">
    <h2>Edit Your Review</h2>

    <!-- Form to update the review details, data will be sent to EditReviewServlet -->
    <form action="EditReviewServlet" method="post">
        <!-- Hidden field to hold the review ID (used for updating in the database) -->
        <input type="hidden" name="reviewId" value="${review.reviewId}" />
        <!-- Hidden field to hold the associated movie ID -->
        <input type="hidden" name="movieId" value="${review.movieId}" />

        <!-- Display the name of the movie (read-only) -->
        <label>Movie Name:</label>
        <input type="text" name="movieName" value="${review.movieName}" readonly />

        <!-- Input for the review title -->
        <label>Review Title:</label>
        <input type="text" name="reviewTitle" value="${review.reviewTitle}" required />

        <!-- Input for the review rating (1 to 5) -->
        <label>Rating (1-5):</label>
        <input type="number" name="rating" min="1" max="5" value="${review.rating}" required />

        <!-- Text area for the review comment -->
        <label>Comment:</label>
        <textarea name="comment" required>${review.comment}</textarea>

        <!-- Input for listing pros -->
        <label>Pros:</label>
        <input type="text" name="pros" value="${review.pros}" />

        <!-- Input for listing cons -->
        <label>Cons:</label>
        <input type="text" name="cons" value="${review.cons}" />

        <!-- Dropdown to select if the user recommends the movie -->
        <label>Recommend:</label>
        <select name="recommend">
            <option value="Yes" ${review.recommend == 'Yes' ? 'selected' : ''}>Yes</option>
            <option value="No" ${review.recommend == 'No' ? 'selected' : ''}>No</option>
        </select>

        <!-- Button to submit the form and update the review -->
        <button type="submit">Update Review</button>
    </form>

    <!-- Display any error message if present -->
    <c:if test="${not empty error}">
        <p style="color:red">${error}</p>
    </c:if>
</div>

<!-- Include the footer of the website (common bottom section) -->
<%@ include file="Footer.jsp" %>
</body>
</html>
