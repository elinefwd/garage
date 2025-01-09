package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // This will return an Optional<User>

    void deleteByUserId(Long userId);
}


