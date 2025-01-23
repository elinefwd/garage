package com.eindopdrachtbackend.security;

import com.eindopdrachtbackend.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // For encoding passwords
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for APIs
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Only accessible for ADMIN users
                        .requestMatchers("/auth/login").permitAll() // Allow public access to authentication endpoint
                        .requestMatchers("/stock/**").hasAnyRole("ADMIN", "EMPLOYEE") // ADMIN and EMPLOYEE access for stock operations
                        .requestMatchers("/vehicles/**").hasAnyRole("ADMIN", "EMPLOYEE") // ADMIN and EMPLOYEE access for vehicle operations
                        .requestMatchers("/inspections/**").hasAnyRole("ADMIN", "EMPLOYEE") // ADMIN and EMPLOYEE access for inspection operations
                        .requestMatchers("/users/**").hasRole("ADMIN") // ADMIN can create, view, update, and delete users

                        .requestMatchers(HttpMethod.POST, "/upload").hasRole("ADMIN") // ADMIN can upload documents

                        .requestMatchers(HttpMethod.GET, "/customers/me").hasRole("CUSTOMER") // Customers can access their own profile
                        .requestMatchers(HttpMethod.GET, "/customers/me/documents").hasRole("CUSTOMER") // Customers can view their own documents
                        .requestMatchers("/customers/**").hasAnyRole("ADMIN", "EMPLOYEE", "CUSTOMER") // Allow ADMIN, EMPLOYEE, and CUSTOMER access

                        .anyRequest().denyAll() // Deny access to all other requests
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session for JWT
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before other filters

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // Use custom user details service
        return authenticationManagerBuilder.build();
    }
}
