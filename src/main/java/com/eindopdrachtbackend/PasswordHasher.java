package com.eindopdrachtbackend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // User passwords
        String passwordEline = "wachtwoord1"; // Admin
        String passwordKate = "wachtwoord4";  // New user
        String passwordJohn = "wachtwoord2";  // Employee
        String passwordJane = "wachtwoord3";  // Customer
        String passwordJoe = "wachtwoord5";    // Password for Joe employee
        String passwordJack = "wachtwoord6";   // Password for Jack customer

        // Generate and print hashed passwords
        String hashedPasswordEline = passwordEncoder.encode(passwordEline);
        String hashedPasswordKate = passwordEncoder.encode(passwordKate);
        String hashedPasswordJohn = passwordEncoder.encode(passwordJohn);
        String hashedPasswordJane = passwordEncoder.encode(passwordJane);
        String hashedPasswordJoe = passwordEncoder.encode(passwordJoe); // Hash for Joe
        String hashedPasswordJack = passwordEncoder.encode(passwordJack); // Hash for Jack

        System.out.println("Eline's Hashed Password: " + hashedPasswordEline);
        System.out.println("Kate's Hashed Password: " + hashedPasswordKate);
        System.out.println("John's Hashed Password: " + hashedPasswordJohn);
        System.out.println("Jane's Hashed Password: " + hashedPasswordJane);
        System.out.println("Joe's Hashed Password: " + hashedPasswordJoe); // Output for Joe
        System.out.println("Jack's Hashed Password: " + hashedPasswordJack); // Output for Jack
    }
}
