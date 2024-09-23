package com.alexis.demo.webserviceproject_v2.repository;

import com.alexis.demo.webserviceproject_v2.model.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieReviewRepository extends JpaRepository <MovieReview, Long> {
    List<MovieReview> findByMovieId(Long movieId);

}
