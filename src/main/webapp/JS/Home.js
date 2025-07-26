// Hero Section Auto-Slide (Unchanged)
let currentIndex = 0;
const slides = document.querySelectorAll(".hero-slide");
const dots = document.querySelectorAll(".dot");

function showSlide(index) {
    slides.forEach((slide, i) => {
        slide.classList.remove("active");
        dots[i].classList.remove("active");
    });

    slides[index].classList.add("active");
    dots[index].classList.add("active");
    currentIndex = index;
}

function nextSlide() {
    currentIndex = (currentIndex + 1) % slides.length;
    showSlide(currentIndex);
}

setInterval(nextSlide, 6000); // Auto-slide every 6 seconds

dots.forEach((dot, i) => {
    dot.addEventListener("click", () => showSlide(i));
});

// Movie List Auto-Scroll with Reverse Direction
const movieList = document.querySelector('.movie-list');
const scrollSpeed = 2; 
const scrollInterval = 30; 
let scrollIntervalID;
let isScrollingRight = true;

// Function to start auto-scrolling
function startAutoScroll() {
    scrollIntervalID = setInterval(autoScroll, scrollInterval);
}

// Function to stop auto-scrolling when reaching the end
function stopAutoScroll() {
    clearInterval(scrollIntervalID); 
}

// Auto-scroll logic with reversing direction
function autoScroll() {
    if (isScrollingRight) {
        if (movieList.scrollLeft + movieList.offsetWidth >= movieList.scrollWidth) {
            // Reached the end, reverse direction
            isScrollingRight = false;
        } else {
            // Scroll the movie list to the right
            movieList.scrollLeft += scrollSpeed;
        }
    } else {
        if (movieList.scrollLeft <= 0) {
            // Reached the start, reverse direction
            isScrollingRight = true;
        } else {
            // Scroll the movie list to the left
            movieList.scrollLeft -= scrollSpeed;
        }
    }
}

// Start auto-scrolling with the set interval
startAutoScroll();
