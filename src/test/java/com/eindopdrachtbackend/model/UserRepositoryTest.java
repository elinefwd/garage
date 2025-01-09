package com.eindopdrachtbackend.model;

import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

        User foundUser = userRepository.findByUsername("testUser");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testUser");
    }
}
