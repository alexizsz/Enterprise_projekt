package com.alexis.demo.java_enterprise_project.controller;

import com.alexis.demo.java_enterprise_project.model.Movie;
import com.alexis.demo.java_enterprise_project.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/moviepage")
public class MoviePageController {

    private final MovieRepository movieRepository;

    public MoviePageController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping
    public String showMovies(Model model){
        List<Movie> moviesList = movieRepository.findAll();
        model.addAttribute("movies", moviesList);
        return "moviepage";
    }
}
