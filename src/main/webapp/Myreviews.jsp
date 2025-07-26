<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Review" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Hub - My Reviews</title>
    <link rel="stylesheet" href="CSS/Myreviews.css">
</head>

<body>
<%@ include file="Header.jsp" %>

<div class="myreviews-container">
    <h1 class="myreviews-title">My Reviews</h1>

    <c:choose>
        <c:when test="${not empty userReviews}">
            <div class="table-wrapper">
                <table class="review-table">
                    <thead>
                        <tr>
                            <th>Movie</th>
                            <th>Title</th>
                            <th>Rating</th>
                            <th>Comment</th>
                            <th>Pros</th>
                            <th>Cons</th>
                            <th>Recommended</th>
                            <th>Review Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="r" items="${userReviews}">
                            <tr>
                                <td>${r.movieName}</td>
                                <td>${r.reviewTitle}</td>
                                <td>${r.rating}/5</td>
                                <td>${r.comment}</td>
                                <td>${r.pros}</td>
                                <td>${r.cons}</td>
                                <td>${r.recommend}</td>
                                <td>${r.reviewDate}</td>
                                <td>
                                    <form action="RemovereviewServlet" method="post">
                                        <input type="hidden" name="reviewId" value="${r.reviewId}" />
                                        <input type="hidden" name="movieId" value="${r.movieId}" /> 
                                        <button type="submit" class="remove-btn">Remove</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>

        <c:otherwise>
            <p class="no-reviews">You haven't written any reviews yet.</p>
        </c:otherwise>
    </c:choose>
</div>

<%@ include file="Footer.jsp" %>

</body>
</html>