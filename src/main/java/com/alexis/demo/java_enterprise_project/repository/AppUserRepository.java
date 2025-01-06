package com.alexis.demo.java_enterprise_project.repository;

import com.alexis.demo.java_enterprise_project.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByName(String name);
    AppUser findByEmail(String email);
}
