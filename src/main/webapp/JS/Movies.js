document.addEventListener("DOMContentLoaded", function () {
    const searchInput = document.getElementById("searchInput");
    const genreFilter = document.getElementById("genreFilter");
    const moviesGrid = document.getElementById("moviesGrid");

    searchInput.addEventListener("input", filterMovies);
    genreFilter.addEventListener("change", filterMovies);

    function filterMovies() {
        const searchText = searchInput.value.toLowerCase();
        const selectedGenre = genreFilter.value.toLowerCase();
        const movieCards = document.querySelectorAll(".movie-card");

        movieCards.forEach(card => {
            const title = card.querySelector("h2").textContent.toLowerCase();
            const genre = card.querySelector("p:nth-of-type(3)").textContent.toLowerCase();

            if ((title.includes(searchText) || searchText === "") &&
                (genre.includes(selectedGenre) || selectedGenre === "")) {
                card.style.display = "block";
            } else {
                card.style.display = "none";
            }
        });
    }
});
