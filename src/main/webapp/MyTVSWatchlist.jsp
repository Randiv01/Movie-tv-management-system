<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="model.TVSeriesWatchlist" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Hub - My TV Series Watchlist</title>
    <link rel="stylesheet" href="CSS/MyTVSWatchlist.css">
</head>
<body>

<%@ include file="Header.jsp" %>

<div class="container">

    <!-- Search bar for TV series watchlist -->
    <div class="search-container">
        <form action="DisplayTVSWServlet" method="get">
            <input type="text" name="search" placeholder="Search your TV series..." class="search-input" />
            <button type="submit" class="search-btn">Search</button>
        </form>
    </div>

    <h1>My TV Series Watchlist</h1>

    <c:if test="${not empty tvWatchlist}">
        <table class="watchlist-table">
            <thead>
                <tr>
                    <th>Poster</th>
                    <th>Title</th>
                    <th>Genre</th>
                    <th>Rating</th>
                    <th>Creator</th>
                    <th>Release Date</th>
                    <th>Added On</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="watch" items="${tvWatchlist}">
                    <tr>
                        <!-- Poster image -->
                        <td>
                            <c:choose>
                                <c:when test="${not empty watch.posterUrl}">
                                    <img src="TVSeriesImages/${watch.posterUrl}" alt="Poster" class="poster-img" />
                                </c:when>
                                <c:otherwise>
                                    <i class="fas fa-tv fa-2x"></i>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${watch.title}</td>
                        <td>${watch.genre}</td>
                        <td>${watch.rating}</td>
                        <td>${watch.creator}</td>
                        <td>${watch.releaseDate}</td>
                        <td>${watch.addedOn}</td>
                        <td>
                            <form action="RemoveTVWTServlet" method="post">
                                <input type="hidden" name="tvSeriesId" value="${watch.tvSeriesId}" />
                                <button type="submit" class="remove-btn">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty tvWatchlist}">
        <p>Your TV series watchlist is empty.</p>
    </c:if>
</div>

<%@ include file="Footer.jsp" %>

</body>
</html>
