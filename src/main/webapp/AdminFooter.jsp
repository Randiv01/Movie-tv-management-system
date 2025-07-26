<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: #121212;
            color: white;
            margin: 0;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        footer {
            background: #1d1d1d;
            color: #bbb;
            padding: 15px 25px;
            font-size: 14px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-top: 1px solid #333;
        }

        footer h1 {
            font-size: 18px;
            margin: 0;
            color: #ff5733;
        }

        footer p {
            margin: 3px 0;
        }

        .footer-links a {
            color: #ff5733;
            margin: 0 8px;
            text-decoration: none;
            font-weight: 500;
        }

        .footer-links a:hover {
            color: #e64a19;
        }

        @media (max-width: 600px) {
            footer {
                flex-direction: column;
                text-align: center;
                gap: 10px;
            }
        }
    </style>
</head>
<body>

<!-- Footer -->
<footer>
    <div>
        <h1>MovieHub</h1>
        <p>&copy; 2025 MovieHub Admin Panel</p>
    </div>
    <div class="footer-links">
        <a href="AdminHome.jsp">Home</a> |
        <a href="DisplayAllUserServlet">Manage Users</a> |
        <a href="InsertMovie.jsp">Manage Movies</a>
    </div>
</footer>

</body>
</html>
