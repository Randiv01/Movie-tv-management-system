<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit TV Series Review</title>
    
    <!-- Link to custom CSS file for styling the form -->
    <link rel="stylesheet" href="CSS/EditTVSReview.css">
    
    <!-- SweetAlert2 library for modern alerts -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- Inline CSS styling for the form layout -->
    <style>
        form { max-width: 600px; margin: 20px auto; }
        label { display: inline-block; width: 180px; margin-top: 10px; vertical-align: top; }
        input[type="text"], input[type="number"], select, textarea {
            width: 60%; padding: 5px; margin-top: 10px;
            box-sizing: border-box;
        }
        textarea { height: 120px; resize: vertical; }
        button { margin-top: 20px; padding: 8px 16px; }
        .error-msg { color: red; margin-bottom: 15px; }

        /* Styling for star rating dropdown */
        #rating, #rating option {
            color: #FFD700;
            font-weight: bold;
        }

        /* Layout styles for recommendation radio buttons */
        .recommend-label {
            display: inline-block;
            width: 180px;
            vertical-align: middle;
            margin-top: 10px;
        }
        .recommend-options {
            display: inline-block;
            vertical-align: middle;
        }
        .recommend-option {
            display: inline-block;
            margin-right: 15px;
        }
        .recommend-option label {
            display: inline;
            margin-left: 5px;
        }
    </style>

    <!-- JavaScript for handling SweetAlert notifications and confirmation -->
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            // Show error alert using SweetAlert if there's an error message in the request scope
            <c:if test="${not empty error}">
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: '${error}'
                });
            </c:if>
            
            // If a success parameter exists in the URL, display a success alert
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('success')) {
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: urlParams.get('success')
                });
            }
            
            // Confirm before submitting the review update form
            const form = document.querySelector('form');
            form.addEventListener('submit', function(event) {
                event.preventDefault(); // Prevent form from submitting immediately
                
                // Ask user for confirmation before proceeding
                Swal.fire({
                    title: 'Confirm Update',
                    text: "Are you sure you want to update this review?",
                    icon: 'question',
                    showCancelButton: true,
                    confirmButtonColor: '#3085d6',
                    cancelButtonColor: '#d33',
                    confirmButtonText: 'Yes, update it!'
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Submit the form if user confirms
                        form.submit();
                    }
                });
            });
        });
    </script>
</head>
<body>

<!-- Page header showing which TV series is being reviewed -->
<h2>Edit Your Review for <c:out value="${review.tvSeriesName}" /></h2>

<!-- Form to update the existing review -->
<form method="post" action="EditTVSReviewServlet">

    <!-- Hidden fields to carry over required metadata -->
    <input type="hidden" name="tv_review_id" value="${review.tvReviewId}" />
    <input type="hidden" name="tvSeriesId" value="${review.tvSeriesId}" />
    <input type="hidden" name="tv_series_name" value="${review.tvSeriesName}" />

    <!-- Season number input -->
    <label for="season">Season:</label>
    <input id="season" type="number" name="season" value="${review.season}" min="1" required /><br>

    <!-- Optional episode field -->
    <label for="episode">Episode (optional):</label>
    <input id="episode" type="text" name="episode" value="${review.episode != null ? review.episode : ''}" /><br>

    <!-- Rating dropdown with star symbols -->
    <label for="rating">Rating:</label>
    <select id="rating" name="rating" required>
        <option value="1" ${review.rating == 1 ? 'selected' : ''}>★☆☆☆☆</option>
        <option value="2" ${review.rating == 2 ? 'selected' : ''}>★★☆☆☆</option>
        <option value="3" ${review.rating == 3 ? 'selected' : ''}>★★★☆☆</option>
        <option value="4" ${review.rating == 4 ? 'selected' : ''}>★★★★☆</option>
        <option value="5" ${review.rating == 5 ? 'selected' : ''}>★★★★★</option>
    </select><br>

    <!-- Review title input -->
    <label for="review_title">Review Title:</label>
    <input id="review_title" type="text" name="review_title" maxlength="50" value="${review.reviewTitle}" required /><br>

    <!-- Detailed review textarea -->
    <label for="comment">Detailed Review:</label>
    <textarea id="comment" name="comment" maxlength="1000" required>${review.comment}</textarea><br>

    <!-- Optional pros input -->
    <label for="pros">Pros:</label>
    <input id="pros" type="text" name="pros" maxlength="200" value="${review.pros != null ? review.pros : ''}" /><br>

    <!-- Optional cons input -->
    <label for="cons">Cons:</label>
    <input id="cons" type="text" name="cons" maxlength="200" value="${review.cons != null ? review.cons : ''}" /><br>

    <!-- Recommendation radio button selection -->
    <span class="recommend-label">Would you recommend this series?</span>
    <div class="recommend-options">
        <div class="recommend-option">
            <input type="radio" id="recommend_yes" name="recommend" value="yes" ${review.recommend == 'yes' ? 'checked' : ''} />
            <label for="recommend_yes">Yes</label>
        </div>
        <div class="recommend-option">
            <input type="radio" id="recommend_no" name="recommend" value="no" ${review.recommend == 'no' ? 'checked' : ''} />
            <label for="recommend_no">No</label>
        </div>
    </div><br>

    <!-- Submit button for the form -->
    <button type="submit">Update Review</button>
</form>

</body>
</html>
