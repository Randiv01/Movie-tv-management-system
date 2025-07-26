package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Watchlist {

    private final int watchlistId;
    private final int userId;
    private final int movieId;
    private final String movieTitle;
    private final String genre;
    private final String rating;
    private final String director;
    private final Date releaseDate;
    private final Timestamp addedDate;
    private final String moviePoster;

    // Constructor with all fields
    public Watchlist(int watchlistId, int userId, int movieId, String movieTitle, String genre, String rating,
                     String director, Date releaseDate, Timestamp addedDate, String moviePoster) {
        this.watchlistId = watchlistId;
        this.userId = userId;
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.genre = genre;
        this.rating = rating;
        this.director = director;
        this.releaseDate = releaseDate;
        this.addedDate = addedDate;
        this.moviePoster = moviePoster;
    }


    // Getters only
    public int getWatchlistId() { return watchlistId; }
    public int getUserId() { return userId; }
    public int getMovieId() { return movieId; }
    public String getMovieTitle() { return movieTitle; }
    public String getGenre() { return genre; }
    public String getRating() { return rating; }
    public String getDirector() { return director; }
    public Date getReleaseDate() { return releaseDate; }
    public Timestamp getAddedDate() { return addedDate; }
    public String getMoviePoster() { return moviePoster; }
}
