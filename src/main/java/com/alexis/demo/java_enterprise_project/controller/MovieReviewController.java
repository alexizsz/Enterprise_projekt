package com.alexis.demo.java_enterprise_project.controller;

import com.alexis.demo.java_enterprise_project.model.Movie;
import com.alexis.demo.java_enterprise_project.model.MovieReview;
import com.alexis.demo.java_enterprise_project.repository.MovieRepository;
import com.alexis.demo.java_enterprise_project.repository.MovieReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class MovieReviewController {

    private final MovieReviewRepository movieReviewRepository;
    private final MovieRepository movieRepository;

    public MovieReviewController(MovieReviewRepository movieReviewRepository, MovieRepository movieRepository) {
        this.movieReviewRepository = movieReviewRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<MovieReview>> getReviewByMovieId(@PathVariable Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<MovieReview> reviews = movieReviewRepository.findByMovieId(movieId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping("/movie/{movieId}")
    public String addReviewToMovie(@PathVariable Long movieId, @RequestParam String review, @RequestParam int rating) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isEmpty()) {
            return "redirect:/moviepage?error=MovieNotFound";
        }

        Movie movie = optionalMovie.get();
        MovieReview newReview = new MovieReview();
        newReview.setReview(review);
        newReview.setRating(rating);
        newReview.setMovie(movie);

        movieReviewRepository.save(newReview);
        return "redirect:/moviepage/details?movieId=" + movieId + "&success=ReviewAdded";
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        if (movieReviewRepository.existsById(reviewId)) {
            movieReviewRepository.deleteById(reviewId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
