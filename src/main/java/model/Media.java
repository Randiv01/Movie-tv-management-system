package model;

public abstract class Media {
    protected String description;
    protected String genre;
    protected String language;
    protected String release_date;
    protected String cast;
    protected String rating;
    protected String trailer_url;
    protected String download_link;

    public Media(String description, String genre, String language, String release_date,
                 String cast, String rating, String trailer_url, String download_link) {
        this.description = description;
        this.genre = genre;
        this.language = language;
        this.release_date = release_date;
        this.cast = cast;
        this.rating = rating;
        this.trailer_url = trailer_url;
        this.download_link = download_link;
    }

    // Original getters (unchanged)
    public String getDescription() { return description; }
    public String getGenre() { return genre; }
    public String getLanguage() { return language; }
    public String getRelease_date() { return release_date; }
    public String getCast() { return cast; }
    public String getRating() { return rating; }
    public String getTrailer_url() { return trailer_url; }
    public String getDownload_link() { return download_link; }

    // JavaBean-compatible alias getters (added for JSP EL compatibility)
    public String getReleaseDate() { return release_date; }
    public String getTrailerUrl() { return trailer_url; }
    public String getDownloadLink() { return download_link; }
}
