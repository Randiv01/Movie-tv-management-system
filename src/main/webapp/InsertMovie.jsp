<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Movie</title>
    <link rel="stylesheet" href="CSS/InsertMovie.css">
</head>
<body>

    <%@ include file="AdminHeader.jsp" %>

    <div class="insert-movie-content">
        <div class="insert-movie-form-container">
            <h2>🎬 Add a New Movie</h2>
            <form action="MovieInsertServlet" method="post" enctype="multipart/form-data">
                <div class="insert-movie-form-grid">
                    <div class="insert-movie-input-group">
                        <label for="mname">Movie Name:</label>
                        <input type="text" id="mname" name="mname" placeholder="Enter movie title" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="genre">Genre:</label>
                        <input type="text" id="genre" name="genre" placeholder="e.g., Action, Drama" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="release_date">Release Date:</label>
                        <input type="date" id="release_date" name="release_date" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="duration">Duration:</label>
                        <input type="text" id="duration" name="duration" placeholder="e.g., 2h 30m" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="language">Language:</label>
                        <input type="text" id="language" name="language" placeholder="e.g., English, Spanish" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="rating">Rating:</label>
                        <input type="number" id="rating" name="rating" placeholder="Out of 10" step="0.1" min="0" max="10" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="director">Director:</label>
                        <input type="text" id="director" name="director" placeholder="Enter director's name" required>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="cast">Cast:</label>
                        <input type="text" id="cast" name="cast" placeholder="Main actors, separated by commas" required>
                    </div>

                    <div class="insert-movie-input-group insert-movie-full-width">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" rows="3" placeholder="Short movie synopsis" required></textarea>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="availability">Availability:</label>
                        <select id="availability" name="availability" required>
                            <option value="">-- Select Availability --</option>
                            <option value="Available">Available</option>
                            <option value="Not Available">Not Available</option>
                        </select>
                    </div>

                    <div class="insert-movie-input-group">
                        <label for="trailer_url">Trailer URL:</label>
                        <input type="url" id="trailer_url" name="trailer_url" placeholder="Paste YouTube/Vimeo link" required>
                    </div>

                    <div class="insert-movie-input-group insert-movie-full-width">
                        <label for="image">Upload Image:</label>
                        <input type="file" id="image" name="image" accept="image/*" required onchange="previewImage(event)">
                        <div class="insert-movie-image-preview">
                            <img id="imagePreview" src="#" alt="Image Preview" style="display: none;">
                        </div>
                    </div>
                </div>     
                <div class="insert-movie-input-group">
				    <label for="torrent">Upload Torrent File:</label>
				    <input type="file" id="torrent" name="torrent" accept=".torrent" required>
				</div>
       
                <button type="submit" class="insert-movie-submit-btn">🎥 Insert Movie</button>
            </form>
        </div>
    </div>

    <script src="JS/InsertMovie.js"></script>
    
    <%@ include file="AdminFooter.jsp" %>
</body>
</html>
