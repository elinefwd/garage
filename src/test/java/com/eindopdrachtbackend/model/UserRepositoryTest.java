package com.eindopdrachtbackend.model;

import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateAndRetrieveUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("$2a$10$hashedTestPassword"); // Example hashed password
        user.setRole(1); // Role for Admin

        userRepository.save(user);

        Optional<User> foundUserOptional = userRepository.findByUsername("testUser");
        assertThat(foundUserOptional).isPresent(); // Check if the user is present
        User foundUser = foundUserOptional.get(); // Get the user
        assertThat(foundUser.getUsername()).isEqualTo("testUser");
    }
}
