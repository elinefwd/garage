package com.eindopdrachtbackend.security;

import com.eindopdrachtbackend.model.User; // Import the User model
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) { // Accept User instance in the constructor
        this.username = user.getUsername();
        this.password = user.getPassword();
        // Assuming role is an int, map it to a granted authority
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())); // Example handling
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Define your own logic as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Define your own logic as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Define your own logic as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Define your own logic as needed
    }
}
