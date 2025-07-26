<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="adminmodel.Admin" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="model.Movie" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Movies - MovieHub Admin</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="CSS/AdminHeader.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #1a1a1a;
            color: #fff;
            margin: 0;
            padding-top: 100px;
        }
        .page-content {
            padding-top: 200px;
        }
        h2 {
            color: #ff5733;
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #2c2c2c;
        }
        th, td {
            padding: 8px;
            border: 1px solid #444;
            text-align: left;
        }
        th {
            background-color: #333;
            color: #ff5733;
        }
        input[type="text"], input[type="date"], input[type="file"] {
            width: 100%;
            padding: 4px;
        }
        img {
            width: 60px;
            height: 90px;
            object-fit: cover;
            display: block;
            margin-bottom: 4px;
        }
        .action-btn {
            padding: 6px 10px;
            border: none;
            cursor: pointer;
            font-weight: bold;
            border-radius: 4px;
            margin-right: 6px;
        }
        .edit-btn {
            background-color: #28a745;
            color: white;
        }
        .delete-btn {
            background-color: #dc3545;
            color: white;
            text-decoration: none;
        }
        .search-bar {
            margin: 20px;
        }
        .search-bar input[type="text"] {
            width: 300px;
            padding: 6px;
            font-size: 14px;
        }
        .search-bar button {
            padding: 6px 10px;
            font-size: 14px;
            background-color: #ff5733;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<jsp:include page="AdminHeader.jsp" />

<h2>Manage Movies</h2>

<div class="search-bar">
    <form method="get" action="DisplayMovieServlet">
        <input type="text" name="search" placeholder="Search by movie name..." value="${param.search}" />
        <button type="submit">Search</button>
    </form>
</div>

<table>
    <thead>
        <tr>
            <th>Poster</th>
            <th>ID</th><th>Name</th><th>Description</th><th>Genre</th><th>Release Date</th>
            <th>Duration</th><th>Language</th><th>Director</th><th>Cast</th><th>Rating</th>
            <th>Trailer</th><th>Availability</th><th>Download</th><th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="movie" items="${movieList}">
        <c:if test="${empty param.search || fn:containsIgnoreCase(movie.mname, param.search)}">
            <tr>
                <td>
                    <img src="Movieimages/${movie.image}" alt="${movie.mname}" />
                    <form id="form-${movie.movieid}" action="UpdateMovieServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="movieid" value="${movie.movieid}" />
                        <input type="hidden" name="oldImage" value="${movie.image}" />
                        <input type="file" name="imageFile" accept="image/*" />
                    </form>
                </td>
                <td>${movie.movieid}</td>
                <td><input type="text" name="mname" value="${movie.mname}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="description" value="${movie.description}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="genre" value="${movie.genre}" form="form-${movie.movieid}" /></td>
                <td><input type="date" name="release_date" value="${movie.release_date}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="duration" value="${movie.duration}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="language" value="${movie.language}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="director" value="${movie.director}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="cast" value="${movie.cast}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="rating" value="${movie.rating}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="trailer_url" value="${movie.trailer_url}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="availability" value="${movie.availability}" form="form-${movie.movieid}" /></td>
                <td><input type="text" name="download_link" value="${movie.download_link}" form="form-${movie.movieid}" /></td>
                <td>
                    <button type="submit" form="form-${movie.movieid}" class="action-btn edit-btn">Update</button>
                    <a href="DeleteMovieServlet?id=${movie.movieid}" class="action-btn delete-btn"
                       onclick="return confirm('Are you sure you want to delete this movie?');">Delete</a>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="AdminFooter.jsp" />
</body>
</html>