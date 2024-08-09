package com.eindopdrachtbackend.repository;

import com.eindopdrachtbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User createUser(User user);
    User getUserById(int userId);
    User updateUser(User user);
    void deleteUser(int userId);
    // You can define custom query methods here if needed
}

