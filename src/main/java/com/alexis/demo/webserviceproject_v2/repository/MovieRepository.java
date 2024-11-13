package com.alexis.demo.webserviceproject_v2.repository;

import com.alexis.demo.webserviceproject_v2.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
