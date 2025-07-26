package model;

import java.sql.Date;
import java.sql.Timestamp;

public class TVSeriesWatchlist {
    private final int id;
    private final int userId;
    private final int tvSeriesId;
    private final String title;
    private final String genre;
    private final double rating;
    private final String creator;
    private final Date releaseDate;
    private final String posterUrl;
    private final Timestamp addedOn;

    // Constructor
    public TVSeriesWatchlist(int id, int userId, int tvSeriesId, String title, String genre, double rating, 
                             String creator, Date releaseDate, String posterUrl, Timestamp addedOn) {
        this.id = id;
        this.userId = userId;
        this.tvSeriesId = tvSeriesId;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.creator = creator;
        this.releaseDate = releaseDate;
        this.posterUrl = posterUrl;
        this.addedOn = addedOn;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTvSeriesId() {
        return tvSeriesId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    public String getCreator() {
        return creator;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public Timestamp getAddedOn() {
        return addedOn;
    }

}
