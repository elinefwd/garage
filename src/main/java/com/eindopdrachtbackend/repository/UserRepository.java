package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // This will fetch a User by its username

    // Optional: Method to delete user by userId
    void deleteByUserId(Long userId);
}
