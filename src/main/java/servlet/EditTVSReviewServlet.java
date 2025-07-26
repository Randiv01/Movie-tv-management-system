package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import model.TVSReview;
import service.TVSRBDUtil;

/**
 * Servlet handling editing of TV series reviews.
 * Provides both display of the edit form (GET) and processing of review updates (POST).
 */
@WebServlet("/EditTVSReviewServlet")
public class EditTVSReviewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor required for servlets.
     */
    public EditTVSReviewServlet() {
        super();
    }

    /**
     * Handles GET requests for displaying the review edit form.
     * Retrieves the review to edit based on the provided review ID.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Store referring page in session for later redirection after update
        String referer = request.getHeader("referer");
        if (referer != null) {
            request.getSession().setAttribute("lastVisitedPage", referer);
        }

        // Get the review ID parameter from the request
        String tvReviewIdParam = request.getParameter("tv_review_id");

        // Check if review ID parameter exists and is not empty
        if (tvReviewIdParam != null && !tvReviewIdParam.isEmpty()) {
            try {
                // Parse the review ID to integer
                int tvReviewId = Integer.parseInt(tvReviewIdParam);
                
                // Retrieve the review from database
                TVSReview review = TVSRBDUtil.getReviewById(tvReviewId);

                if (review != null) {
                    // If review exists, forward to edit page with review data
                    request.setAttribute("review", review);
                    request.getRequestDispatcher("EditTVSReview.jsp").forward(request, response);
                } else {
                    // Handle case where review doesn't exist
                    request.getSession().setAttribute("alertType", "error");
                    request.getSession().setAttribute("alertMessage", "Review not found");
                    response.sendRedirect("tv_seriesinfo.jsp");
                }
            } catch (NumberFormatException e) {
                // Handle invalid number format for review ID
                request.getSession().setAttribute("alertType", "error");
                request.getSession().setAttribute("alertMessage", "Invalid review ID format");
                response.sendRedirect("tv_seriesinfo.jsp");
            }
        } else {
            // Handle missing review ID parameter
            request.getSession().setAttribute("alertType", "error");
            request.getSession().setAttribute("alertMessage", "Missing review ID");
            response.sendRedirect("tv_seriesinfo.jsp");
        }
    }

    /**
     * Handles POST requests for submitting review updates.
     * Processes form data and updates the review in the database.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Get the redirect URL stored during GET request or use fallback
        String redirectUrl = (String) session.getAttribute("lastVisitedPage");
        if (redirectUrl == null || redirectUrl.isEmpty()) {
            redirectUrl = "tv_seriesinfo.jsp"; // default fallback page
        }

        try {
            // Retrieve and parse all form parameters
            int tvReviewId = Integer.parseInt(request.getParameter("tv_review_id"));
            int tvSeriesId = Integer.parseInt(request.getParameter("tvSeriesId"));
            String tvSeriesName = request.getParameter("tv_series_name");
            int season = Integer.parseInt(request.getParameter("season"));
            String episode = request.getParameter("episode");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String reviewTitle = request.getParameter("review_title");
            String comment = request.getParameter("comment");
            String pros = request.getParameter("pros");
            String cons = request.getParameter("cons");
            String recommend = request.getParameter("recommend");

            // Validate rating is within acceptable range (1-5)
            if (rating < 1 || rating > 5) {
                session.setAttribute("alertType", "error");
                session.setAttribute("alertMessage", "Rating must be between 1 and 5");
                response.sendRedirect(redirectUrl);
                return;
            }

            // Create updated review object with form data
            TVSReview updatedReview = new TVSReview();
            updatedReview.setTvReviewId(tvReviewId);
            updatedReview.setTvSeriesId(tvSeriesId);
            updatedReview.setTvSeriesName(tvSeriesName);
            updatedReview.setSeason(season);
            updatedReview.setEpisode(episode);
            updatedReview.setRating(rating);
            updatedReview.setReviewTitle(reviewTitle);
            updatedReview.setComment(comment);
            updatedReview.setPros(pros);
            updatedReview.setCons(cons);
            updatedReview.setRecommend(recommend);

            // Attempt to update the review in the database
            boolean isSuccess = TVSRBDUtil.updateReview(updatedReview);

            // Set appropriate success/error message based on update result
            if (isSuccess) {
                session.setAttribute("alertType", "success");
                session.setAttribute("alertMessage", "Review updated successfully!");
            } else {
                session.setAttribute("alertType", "error");
                session.setAttribute("alertMessage", "Failed to update review");
            }
            
            // Redirect back to original page
            response.sendRedirect(redirectUrl);
            
        } catch (NumberFormatException e) {
            // Handle invalid number format in form fields
            session.setAttribute("alertType", "error");
            session.setAttribute("alertMessage", "Invalid input format");
            response.sendRedirect(redirectUrl);
        } catch (Exception e) {
            // Handle any other unexpected errors
            e.printStackTrace();
            session.setAttribute("alertType", "error");
            session.setAttribute("alertMessage", "An unexpected error occurred");
            response.sendRedirect(redirectUrl);
        }
    }
}