package model;

import java.sql.Date;

/**
 * Represents a TV series review submitted by a user.
 * Contains all the details about the review including rating, comments, pros/cons,
 * and metadata like the associated TV series, user, and review date.
 */
public class TVSReview {
    // Unique identifier for the review
    private int tvReviewId;
    
    // ID of the TV series being reviewed
    private int tvSeriesId;
    
    // ID of the user who wrote the review
    private int userId;
    
    // Name of the TV series (might be denormalized for display purposes)
    private String tvSeriesName;
    
    // Username of the reviewer (might be denormalized for display purposes)
    private String username;
    
    // Season number being reviewed
    private int season;
    
    // Episode number or name being reviewed
    private String episode;
    
    // Rating given (typically on a scale, e.g., 1-10)
    private int rating;
    
    // Title of the review
    private String reviewTitle;
    
    // Main review content
    private String comment;
    
    // Positive aspects highlighted by the reviewer
    private String pros;
    
    // Negative aspects highlighted by the reviewer
    private String cons;
    
    // Recommendation status (e.g., "Yes", "No", "With Reservations")
    private String recommend;
    
    // Date when the review was submitted
    private Date reviewDate;
    
    // Path or URL to an associated screenshot
    private String screenshot;
    
    // Flag indicating if user agreed to terms before submitting
    private boolean agreeTerms;

    /**
     * Default constructor.
     * Required for frameworks that use reflection.
     */
    public TVSReview() {
    }

    /**
     * Full constructor with all fields.
     * Useful for creating a complete review object in one operation.
     */
    public TVSReview(int tvReviewId, int tvSeriesId, int userId, String tvSeriesName, String username,
                    int season, String episode, int rating, String reviewTitle, String comment,
                    String pros, String cons, String recommend, Date reviewDate, String screenshot,
                    boolean agreeTerms) {
        this.tvReviewId = tvReviewId;
        this.tvSeriesId = tvSeriesId;
        this.userId = userId;
        this.tvSeriesName = tvSeriesName;
        this.username = username;
        this.season = season;
        this.episode = episode;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.comment = comment;
        this.pros = pros;
        this.cons = cons;
        this.recommend = recommend;
        this.reviewDate = reviewDate;
        this.screenshot = screenshot;
        this.agreeTerms = agreeTerms;
    }

    // Below are standard getters and setters for all fields
    // Each is documented with its purpose and any constraints

    public int getTvReviewId() {
        return tvReviewId;
    }

    public void setTvReviewId(int tvReviewId) {
        this.tvReviewId = tvReviewId;
    }

    public int getTvSeriesId() {
        return tvSeriesId;
    }

    public void setTvSeriesId(int tvSeriesId) {
        this.tvSeriesId = tvSeriesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTvSeriesName() {
        return tvSeriesName;
    }

    public void setTvSeriesName(String tvSeriesName) {
        this.tvSeriesName = tvSeriesName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getEpisode() {
        return episode;
    }

    public void setEpisode(String episode) {
        this.episode = episode;
    }

    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating for the TV series.
     * @param rating Should typically be within a defined range (e.g., 1-10)
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPros() {
        return pros;
    }

    public void setPros(String pros) {
        this.pros = pros;
    }

    public String getCons() {
        return cons;
    }

    public void setCons(String cons) {
        this.cons = cons;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getScreenshot() {
        return screenshot;
    }

    /**
     * Sets the screenshot path/URL.
     * @param screenshot Should be a valid path or URL to an image file
     */
    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }

    public boolean isAgreeTerms() {
        return agreeTerms;
    }

    public void setAgreeTerms(boolean agreeTerms) {
        this.agreeTerms = agreeTerms;
    }
}