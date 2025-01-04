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
    public ResponseEntity<?> addReviewToMovie(@PathVariable Long movieId, @RequestBody MovieReview review) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (optionalMovie.isEmpty()) {
            return new ResponseEntity<>("Movie not found", HttpStatus.NOT_FOUND);
        }
        Movie movie = optionalMovie.get();
        review.setMovie(movie);
        MovieReview savedReview = movieReviewRepository.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
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
