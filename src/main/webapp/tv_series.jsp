<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.TVSeries" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MovieHub - Browse TV Series</title>
    <link rel="stylesheet" type="text/css" href="CSS/tv_series.css">
    <script src="JS/TVSeries.js" defer></script>
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="container">
        <h1>Browse TV Series</h1>

        <!-- Search & Filter Section -->
        <div class="search-container">
            <form action="DisplayTVServlet" method="get" class="search-form">
                <input type="text" name="search" placeholder="Search TV series..." id="searchInput">

                <select id="genreFilter" name="genre">
                    <option value="">All Genres</option>
                    <option value="Action">Action</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Drama">Drama</option>
                    <option value="Horror">Horror</option>
                    <option value="Sci-Fi">Sci-Fi</option>
                </select>

                <select id="ratingFilter" name="rating">
                    <option value="">All Ratings</option>
                    <c:forEach var="i" begin="1" end="10">
                        <option value="${i}">${i} Star</option>
                    </c:forEach>
                </select>

                <select id="yearFilter" name="year">
                    <option value="">All Years</option>
                    <option value="2023">2023</option>
                    <option value="2022">2022</option>
                    <option value="2021">2021</option>
                </select>

                <button type="submit">Search</button>
                <button type="reset" class="clear-filters">Clear Filters</button>
            </form>
        </div>

        <!-- TV Series Grid -->
        <div class="movies-grid" id="tvSeriesGrid">
            <c:forEach var="tv" items="${tvSeriesList}">
                <div class="movie-card">
                    <a href="TVSeriesInfoServlet?seriesId=${tv.tsid}" class="movie-link">
                        <img src="TVSeriesImages/${tv.posterUrl}" alt="${tv.title}">
                    </a>
                    <div class="movie-info">
                        <h2>${tv.title}</h2>
                        <p><strong>Genre:</strong> ${tv.genre}</p>
                        <p><strong>Release Date:</strong> ${tv.releaseDate}</p>
                        <p><strong>Seasons:</strong> ${tv.seasons} | <strong>Episodes:</strong> ${tv.episodes}</p>
                        <p><strong>Duration/Episode:</strong> ${tv.durationPerEpisode}</p>
                        <p><strong>Language:</strong> ${tv.language}</p>
                        <p><strong>Rating:</strong> ⭐ ${tv.rating}</p>
                        <p><a href="${tv.trailerUrl}" target="_blank" class="trailer-link">🎬 Watch Trailer</a></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="Footer.jsp" %>
</body>
</html>
