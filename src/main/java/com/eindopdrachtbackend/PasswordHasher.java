package com.eindopdrachtbackend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Example passwords
        String password1 = "wachtwoord1";
        String password2 = "wachtwoord2";
        String password3 = "wachtwoord3";

        // Generate and print hashed passwords
        String hashedPassword1 = passwordEncoder.encode(password1);
        String hashedPassword2 = passwordEncoder.encode(password2);
        String hashedPassword3 = passwordEncoder.encode(password3);

        System.out.println("Password 1 Hash: " + hashedPassword1);
        System.out.println("Password 2 Hash: " + hashedPassword2);
        System.out.println("Password 3 Hash: " + hashedPassword3);
    }
}
