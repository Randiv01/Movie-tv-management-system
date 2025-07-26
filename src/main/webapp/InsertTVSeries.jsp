<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert TV Series</title>
    <link rel="stylesheet" href="CSS/InsertTVSeries.css"> <!-- Reuse same styling -->
</head>
<body>

    <%@ include file="AdminHeader.jsp" %>

    <div class="insert-movie-content">
        <div class="insert-movie-form-container">
            <h2>📺 Add a New TV Series</h2>
            <form action="TVSeriesInsertServlet" method="post" enctype="multipart/form-data">
                <div class="insert-movie-form-grid">

                    <div class="insert-movie-input-group">
                        <label for="title">Series Title:</label>
                        <input type="text" id="title" name="title" placeholder="Enter series title" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="genre">Genre:</label>
                        <input type="text" id="genre" name="genre" placeholder="e.g., Thriller, Sci-Fi" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="release_date">Release Date:</label>
                        <input type="date" id="release_date" name="release_date" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="seasons">Seasons:</label>
                        <input type="number" id="seasons" name="seasons" min="1" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="episodes">Episodes:</label>
                        <input type="number" id="episodes" name="episodes" min="1" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="duration_per_episode">Duration per Episode:</label>
                        <input type="text" id="duration_per_episode" name="duration_per_episode" placeholder="e.g., 45min" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="language">Language:</label>
                        <input type="text" id="language" name="language" placeholder="e.g., English" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="rating">Rating:</label>
                        <input type="number" step="0.1" id="rating" name="rating" min="0" max="10" placeholder="Out of 10" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="creator">Creator:</label>
                        <input type="text" id="creator" name="creator" placeholder="Series creator" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="cast">Cast:</label>
                        <input type="text" id="cast" name="cast" placeholder="Main cast, comma-separated" required>
                    </div>

                    <div class="insert-movie-input-group insert-movie-full-width">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" rows="3" placeholder="Brief series overview" required></textarea>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="status">Status:</label>
                        <select id="status" name="status" required>
                            <option value="">-- Select Status --</option>
                            <option value="Ongoing">Ongoing</option>
                            <option value="Completed">Completed</option>
                        </select>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="trailer_url">Trailer URL:</label>
                        <input type="url" id="trailer_url" name="trailer_url" placeholder="Paste YouTube/Vimeo link" required>
                    </div>

                    <div class="insert-movie-input-group insert-movie-full-width">
                        <label for="poster_url">Upload Poster:</label>
                        <input type="file" id="poster_url" name="poster_url" accept="image/*" required onchange="previewImage(event)">
                        <div class="insert-movie-image-preview">
                            <img id="imagePreview" src="#" alt="Image Preview" style="display: none;">
                        </div>
                    </div>
                </div>

                <div class="insert-movie-input-group">
                    <label for="download_link">Upload Torrent File:</label>
                    <input type="file" id="download_link" name="download_link" accept=".torrent" required>
                </div>

                <button type="submit" class="insert-movie-submit-btn">📺 Insert TV Series</button>
            </form>
        </div>
    </div>

    <script src="JS/InsertMovie.js"></script> <!-- Reuse preview script -->
    
    <%@ include file="AdminFooter.jsp" %>
</body>
</html>
