package com.alexis.demo.java_enterprise_project.repository;

import com.alexis.demo.java_enterprise_project.model.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieReviewRepository extends JpaRepository<MovieReview, Long> {
    List<MovieReview> findByMovieId(Long movieId);
}
