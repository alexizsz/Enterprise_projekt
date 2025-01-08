package com.alexis.demo.java_enterprise_project.controller;

import com.alexis.demo.java_enterprise_project.model.Movie;
import com.alexis.demo.java_enterprise_project.model.MovieReview;
import com.alexis.demo.java_enterprise_project.repository.MovieRepository;
import com.alexis.demo.java_enterprise_project.repository.MovieReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovieReviewControllerTest {

    @Mock
    private MovieReviewRepository movieReviewRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Model model;

    @InjectMocks
    private MovieReviewController movieReviewController;

    private Movie movie;
    private MovieReview movieReview;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        movie = new Movie("Inception", "Christopher Nolan", "Sci-Fi", 2010);
        movieReview = new MovieReview(movie, "Amazing movie!", 5);
    }

    @Test
    public void testAddReviewToMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieReviewRepository.findByMovieId(1L)).thenReturn(List.of(movieReview));

        String result = movieReviewController.addReviewToMovie(1L, "Amazing movie!", 5, model);

        assertNotNull(result);
        assertEquals("moviedetailspage", result);
    }

    @Test
    public void testGetReviewByMovieId() {
        when(movieRepository.existsById(1L)).thenReturn(true);
        when(movieReviewRepository.findByMovieId(1L)).thenReturn(List.of(movieReview));

        ResponseEntity<List<MovieReview>> response = movieReviewController.getReviewByMovieId(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Amazing movie!", response.getBody().get(0).getReview());
        assertEquals(5, response.getBody().get(0).getRating());
    }

    @Test
    public void testDeleteReview() {
        when(movieReviewRepository.existsById(1L)).thenReturn(true);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        String result = movieReviewController.deleteReview(1L, 1L, model);

        assertNotNull(result);
        assertEquals("moviedetailspage", result);
    }

}

