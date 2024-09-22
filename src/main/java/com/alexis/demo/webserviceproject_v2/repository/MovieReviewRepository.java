package com.alexis.demo.webserviceproject_v2.repository;

import com.alexis.demo.webserviceproject_v2.model.MovieReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieReviewRepository extends JpaRepository <MovieReview, Long> {
}
