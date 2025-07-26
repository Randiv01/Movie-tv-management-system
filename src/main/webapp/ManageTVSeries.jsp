<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="adminmodel.Admin" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="model.TVSeries" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage TV Series - MovieHub Admin</title>
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
            margin: 20px 20px 20px;
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

<h2>Manage TV Series</h2>

<div class="search-bar">
    <form method="get" action="DisplayTVSeriesServlet">
        <input type="text" name="search" placeholder="Search by title..." value="${param.search}" />
        <button type="submit">Search</button>
    </form>
</div>

<table>
    <thead>
        <tr>
            <th>Poster</th>
            <th>ID</th><th>Title</th><th>Description</th><th>Genre</th><th>Language</th>
            <th>Release Date</th><th>Seasons</th><th>Episodes</th><th>Duration</th><th>Rating</th>
            <th>Poster URL</th><th>Trailer URL</th><th>Status</th><th>Cast</th><th>Creator</th><th>Download Link</th><th>Actions</th>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="ts" items="${tvList}">
        <c:if test="${empty param.search || fn:containsIgnoreCase(ts.title, param.search)}">
            <tr>
                <td>
                    <img src="TVSeriesImages/${ts.posterUrl}" alt="${ts.title}" />
                    <input type="file" name="imageFile" accept="image/*" form="form-${ts.tsid}" />
                    <input type="hidden" name="oldPoster" value="${ts.posterUrl}" form="form-${ts.tsid}" />
                </td>
                <td><input type="text" name="tsid" value="${ts.tsid}" readonly form="form-${ts.tsid}" /></td>
                <td><input type="text" name="title" value="${ts.title}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="description" value="${ts.description}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="genre" value="${ts.genre}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="language" value="${ts.language}" form="form-${ts.tsid}" /></td>
                <td><input type="date" name="release_date" value="${ts.releaseDate}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="seasons" value="${ts.seasons}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="episodes" value="${ts.episodes}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="duration_per_episode" value="${ts.durationPerEpisode}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="rating" value="${ts.rating}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="poster_url" value="${ts.posterUrl}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="trailer_url" value="${ts.trailerUrl}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="status" value="${ts.status}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="cast" value="${ts.cast}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="creator" value="${ts.creator}" form="form-${ts.tsid}" /></td>
                <td><input type="text" name="download_link" value="${ts.downloadLink}" form="form-${ts.tsid}" /></td>
                <td>
                    <form id="form-${ts.tsid}" action="UpdateTVSeriesServlet" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="tsid" value="${ts.tsid}" />
                        <button type="submit" class="action-btn edit-btn">Update</button>
                        <a href="DeleteTVSeriesServlet?id=${ts.tsid}" class="action-btn delete-btn"
                           onclick="return confirm('Delete this TV series?');">Delete</a>
                    </form>
                </td>
            </tr>
        </c:if>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="AdminFooter.jsp" />
</body>
</html>
