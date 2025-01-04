package com.alexis.demo.java_enterprise_project.repository;

import com.alexis.demo.java_enterprise_project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
