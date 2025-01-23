package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
    Optional<ApplicationUser> findByUsername(String username); // Find user by username

    void deleteByUserId(Long userId); // If necessary, keep this

    // New method to delete by username
    void deleteByUsername(String username); // Optional: add this method
}

