package com.eindopdrachtbackend.model;

import com.eindopdrachtbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:postgresql://localhost:5432/garage",
        "spring.datasource.username=postgres",
        "spring.datasource.password=wachtwoord"
})
@Transactional // Automatically rollback after each test
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Optional: clean up users if necessary
        userRepository.deleteByUsername("testUser"); // Ensure the test user doesn't exist before the test
    }

    @Test
    public void testCreateAndRetrieveUser() {
        ApplicationUser user = new ApplicationUser(); // Use ApplicationUser
        user.setUsername("testUser");
        user.setPassword("$2a$10$hashedTestPassword"); // Use your actual hashed password logic
        user.setRole("ADMIN"); // Use a string for the role

        userRepository.save(user);

        Optional<ApplicationUser> foundUserOptional = userRepository.findByUsername("testUser"); // Locate the user
        assertThat(foundUserOptional).isPresent(); // Should find the user
        ApplicationUser foundUser = foundUserOptional.get(); // Get the user
        assertThat(foundUser.getUsername()).isEqualTo("testUser");
        assertThat(foundUser.getRole()).isEqualTo("ADMIN"); // Verify the role
    }

}
