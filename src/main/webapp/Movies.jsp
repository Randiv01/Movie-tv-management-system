<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Movie" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MovieHub - Browse Movies</title>
    <link rel="stylesheet" type="text/css" href="CSS/Movies.css">
    <script src="JS/Movies.js" defer></script>
</head>
<body>
    <%@ include file="Header.jsp" %>

    <div class="container">
        <h1>Browse Movies</h1>

        <!-- Search & Filter Section -->
        <div class="search-container">
            <form action="DisplayMServlet" method="get" class="search-form">
                <input type="text" name="search" placeholder="Search movies..." id="searchInput">

                <select id="genreFilter" name="genre">
                    <option value="">All Genres</option>
                    <option value="Action">Action</option>
                    <option value="Comedy">Comedy</option>
                    <option value="Drama">Drama</option>
                    <option value="Horror">Horror</option>
                    <option value="Sci-Fi">Sci-Fi</option>
                </select>

                <select id="ratingFilter" name="imdb rating">
				    <option value="">All Ratings</option>
				    <option value="1">1 Star</option>
				    <option value="2">2 Stars</option>
				    <option value="3">3 Stars</option>
				    <option value="4">4 Stars</option>
				    <option value="5">5 Stars</option>
				    <option value="6">6 Stars</option>
				    <option value="7">7 Stars</option>
				    <option value="8">8 Stars</option>
				    <option value="9">9 Stars</option>
				    <option value="9">10 Stars</option>
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

        <!-- Movies Grid -->
        <div class="movies-grid" id="moviesGrid">
            <c:forEach var="movie" items="${moviesList}">
                <div class="movie-card">
                    <a href="mInfoServlet?movieId=${movie.movieid}" class="movie-link">
                        <img src="Movieimages/${movie.image}" alt="${movie.mname}">
                    </a>
                    <div class="movie-info">
                        <h2>${movie.mname}</h2>
                        <p><strong>Genre:</strong> ${movie.genre}</p>
                        <p><strong>Release Date:</strong> ${movie.release_date}</p>
                        <p><strong>Duration:</strong> ${movie.duration}</p>
                        <p><strong>Language:</strong> ${movie.language}</p>
                        <p><strong>Rating:</strong> ⭐ ${movie.rating}</p>
                        <p><a href="${movie.trailer_url}" target="_blank"  class="trailer-link">🎬 Watch Trailer</a></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <%@ include file="Footer.jsp" %>
</body>
</html>
