function showAlert(message, type) {
    if (type === "watchlist") {
        // Handle watchlist-specific messages
        if (message === "success") {
            alert("Movie added to watchlist successfully!");
        } else if (message === "fail") {
            alert("Failed to add movie to watchlist. Please try again.");
        } else if (message === "alreadyInWatchlist") {
            alert("This movie is already in your watchlist.");
        }
    } else if (type === "review") {
        // Handle review-specific messages
        if (message === "success") {
            alert("Review inserted successfully!");
        } else if (message === "fail") {
            alert("Review insertion failed. Please try again.");
        }
    }
}
