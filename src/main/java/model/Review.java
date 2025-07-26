package model;

import java.util.Date;

/**
 * Represents a user review for a movie.
 * This model contains all relevant details including the user, rating, and written feedback.
 */
public class Review {
    // Unique identifier for the review
    private final int reviewId;

    // Identifier of the movie being reviewed
    private final int movieId;

    // Identifier of the user who submitted the review
    private final int userId;

    // Name of the movie being reviewed
    private final String movieName;

    // Username of the reviewer
    private final String username;

    // Rating given to the movie (e.g., from 1 to 5)
    private final int rating;

    // Title or headline of the review
    private final String reviewTitle;

    // Full comment or detailed review by the user
    private final String comment;

    // Pros or positive points mentioned in the review
    private final String pros;

    // Cons or negative points mentioned in the review
    private final String cons;

    // User's recommendation (e.g., "Yes", "No", or "Maybe")
    private final String recommend;

    // Date when the review was submitted
    private final Date reviewDate;

    /**
     * Constructor to initialize all fields of the Review object.
     * 
     * @param reviewId Unique ID of the review
     * @param movieId ID of the movie being reviewed
     * @param userId ID of the user submitting the review
     * @param movieName Name of the movie
     * @param username Name of the user
     * @param rating Rating score
     * @param reviewTitle Title of the review
     * @param comment Review content
     * @param pros Positive points
     * @param cons Negative points
     * @param recommend Recommendation status
     * @param reviewDate Date of submission
     */
    public Review(int reviewId, int movieId, int userId, String movieName, String username, int rating,
                  String reviewTitle, String comment, String pros, String cons, String recommend, Date reviewDate) {
        this.reviewId = reviewId;
        this.movieId = movieId;
        this.userId = userId;
        this.movieName = movieName;
        this.username = username;
        this.rating = rating;
        this.reviewTitle = reviewTitle;
        this.comment = comment;
        this.pros = pros;
        this.cons = cons;
        this.recommend = recommend;
        this.reviewDate = reviewDate;
    }

    // Get the unique ID of the review
    public int getReviewId() { return reviewId; }

    // Get the ID of the movie associated with this review
    public int getMovieId() { return movieId; }

    // Get the ID of the user who submitted the review
    public int getUserId() { return userId; }

    // Get the name of the movie being reviewed
    public String getMovieName() { return movieName; }

    // Get the username of the reviewer
    public String getUsername() { return username; }

    // Get the rating given to the movie
    public int getRating() { return rating; }

    // Get the title or headline of the review
    public String getReviewTitle() { return reviewTitle; }

    // Get the main comment or description in the review
    public String getComment() { return comment; }

    // Get the positive aspects listed in the review
    public String getPros() { return pros; }

    // Get the negative aspects listed in the review
    public String getCons() { return cons; }

    // Get the recommendation status of the review
    public String getRecommend() { return recommend; }

    // Get the date the review was written
    public Date getReviewDate() { return reviewDate; }
}
