package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.User;
import com.eindopdrachtbackend.security.CustomUserDetails;
import com.eindopdrachtbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // Repository for user data

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username); // Find user by username
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found")); // Extract User object

        return new CustomUserDetails(user); // Create CustomUserDetails from User object
    }
}
