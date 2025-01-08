package com.alexis.demo.java_enterprise_project.controller;

import com.alexis.demo.java_enterprise_project.model.Movie;
import com.alexis.demo.java_enterprise_project.model.MovieReview;
import com.alexis.demo.java_enterprise_project.repository.MovieRepository;
import com.alexis.demo.java_enterprise_project.repository.MovieReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequestMapping("/moviepage")
public class MoviePageController {

    private final MovieRepository movieRepository;
    private final MovieReviewRepository movieReviewRepository;

    public MoviePageController(MovieRepository movieRepository, MovieReviewRepository movieReviewRepository) {
        this.movieRepository = movieRepository;
        this.movieReviewRepository = movieReviewRepository;
    }

    @GetMapping
    public String showMovies(Model model) {
        List<Movie> moviesList = movieRepository.findAll();
        model.addAttribute("movies", moviesList);
        return "moviepage";
    }

    @GetMapping("/details")
    public String showMovieDetails(@RequestParam Long movieId, Model model) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID"));

        List<MovieReview> reviews = movieReviewRepository.findByMovieId(movieId);
        model.addAttribute("movie", movie);
        model.addAttribute("reviews", reviews);
        return "moviedetailspage";
    }
}

