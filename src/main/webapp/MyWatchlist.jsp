<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="model.Watchlist" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Hub - My Watchlist</title>
    <link rel="stylesheet" href="CSS/MyWatchlist.css">
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="container">
    
		    <!-- Search bar for watchlist -->
		<div class="search-container">
		    <form action="DisplaywatchlistServlet" method="get">
		        <input type="text" name="search" placeholder="Search your watchlist..." class="search-input" />
		        <button type="submit" class="search-btn">Search</button>
		    </form>
		</div>
    
        <h1>My Watchlist</h1>

        <!-- Use JSTL to check if the watchlist is not empty -->
        <c:if test="${not empty watchlist}">
            <table class="watchlist-table">
                <thead>
                    <tr>
                    	<th>Poster</th>
                        <th>Movie Title</th>
                        <th>Genre</th>
                        <th>Rating</th>
                        <th>Director</th>                       
                        <th>added date</th> 
                        <th>Action</th>
                         
                    </tr>
                </thead>
                <tbody>
                    <!-- Use JSTL to loop through the watchlist -->
                    <c:forEach var="watch" items="${watchlist}">
                        <tr>
                        
                        	<!-- movie Image Handling -->
	                        <td>
	                            <c:choose>
	                                <c:when test="${not empty user.profileimage}">
	                                    <!-- If profile image exists, display it -->
	                                    <img src="Movieimages/${watch.moviePoster}" alt="Movie Poster" class="poster-img"/>
	                                </c:when>
	                                <c:otherwise>
	                                    <!-- If no profile image, display a default icon -->
	                                    <i class="fas fa-user-circle fa-2x"></i>
	                                </c:otherwise>
	                            </c:choose>
	                        </td>
                        
                            <td>${watch.movieTitle}</td>
                            <td>${watch.genre}</td>
                            <td>${watch.rating}</td>
                            <td>${watch.director}</td>                         
                            <td>${watch.addedDate}</td>
                            
                            <td>
						        <form action="RemoveFromWatchlistServlet" method="post">
						            <input type="hidden" name="movieId" value="${watch.movieId}" />
						            <button type="submit" class="remove-btn">Remove</button>
						        </form>
						    </td>
                            
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- If the watchlist is empty, show this message -->
        <c:if test="${empty watchlist}">
            <p>Your watchlist is empty.</p>
        </c:if>
    </div>

    <%@ include file="Footer.jsp" %>
</body>
</html>
