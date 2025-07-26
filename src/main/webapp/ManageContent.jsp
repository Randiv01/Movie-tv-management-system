<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Movies and TV Series</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #121212;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .manage-container {
            text-align: center;
            background-color: #1f1f1f;
            padding: 40px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(255, 87, 51, 0.1);
        }

        h1 {
            color: #ff5733;
            margin-bottom: 30px;
        }

        .manage-buttons {
            display: flex;
            justify-content: center;
            gap: 40px;
        }

        .manage-buttons a {
            padding: 15px 30px;
            background-color: #ff5733;
            color: white;
            text-decoration: none;
            border-radius: 10px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .manage-buttons a:hover {
            background-color: #e24e2c;
        }
    </style>
</head>
<body>

    <div class="manage-container">
        <h1>Manage Movies and TV Series</h1>
        <div class="manage-buttons">
            <a href="DisplayMovieServlet">Movies</a>
            <a href="DisplayTVSeriesServlet">TV Series</a>
        </div>
    </div>
</body>
</html>
