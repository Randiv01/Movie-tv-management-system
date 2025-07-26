package model;

public class Movie extends Media {
    private int movieid;
    private String mname;
    private String duration;
    private String director;
    private String image;
    private String availability;

    public Movie(int movieid, String mname, String description, String genre, String release_date, String duration,
                 String language, String director, String cast, String rating, String image,
                 String trailer_url, String availability, String download_link) {

        super(description, genre, language, release_date, cast, rating, trailer_url, download_link);
        this.movieid = movieid;
        this.mname = mname;
        this.duration = duration;
        this.director = director;
        this.image = image;
        this.availability = availability;
    }

    public int getMovieid() { return movieid; }
    public String getMname() { return mname; }
    public String getDuration() { return duration; }
    public String getDirector() { return director; }
    public String getImage() { return image; }
    public String getAvailability() { return availability; }
}
