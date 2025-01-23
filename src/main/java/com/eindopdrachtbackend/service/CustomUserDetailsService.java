package com.eindopdrachtbackend.service;

import com.eindopdrachtbackend.model.ApplicationUser; // Update import
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
        Optional<ApplicationUser> userOptional = userRepository.findByUsername(username); // Change to ApplicationUser
        ApplicationUser user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found")); // Extract ApplicationUser object

        return new CustomUserDetails(user); // Create CustomUserDetails from ApplicationUser object
    }
}
