package model;

public class TVSeries extends Media {
    private int tsid;
    private String title;
    private int seasons;
    private int episodes;
    private String durationPerEpisode;
    private String posterUrl;
    private String status;
    private String creator;

    public TVSeries(int tsid, String title, String description, String genre, String language,
                    String releaseDate, int seasons, int episodes, String durationPerEpisode,
                    double rating, String posterUrl, String trailerUrl, String status,
                    String cast, String creator, String downloadLink) {

        super(description, genre, language, releaseDate, cast, String.valueOf(rating), trailerUrl, downloadLink);
        this.tsid = tsid;
        this.title = title;
        this.seasons = seasons;
        this.episodes = episodes;
        this.durationPerEpisode = durationPerEpisode;
        this.posterUrl = posterUrl;
        this.status = status;
        this.creator = creator;
    }

    public int getTsid() { return tsid; }
    public String getTitle() { return title; }
    public int getSeasons() { return seasons; }
    public int getEpisodes() { return episodes; }
    public String getDurationPerEpisode() { return durationPerEpisode; }
    public String getPosterUrl() { return posterUrl; }
    public String getStatus() { return status; }
    public String getCreator() { return creator; }
}
