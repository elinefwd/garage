-- Insert admin user
INSERT INTO application_user (username, password, role) VALUES
    ('eline', '$2a$10$hashedWachtwoord1', 1) -- Password: wachtwoord1 (example)
ON CONFLICT (username) DO NOTHING; -- Prevent duplicate usernames

-- Insert employee user
INSERT INTO application_user (username, password, role) VALUES
    ('john_doe', '$2a$10$hashedWachtwoord2', 3) -- Password: wachtwoord2 (example)
ON CONFLICT (username) DO NOTHING; -- Prevent duplicate usernames

-- Insert customer user
INSERT INTO application_user (username, password, role) VALUES
    ('jane_doe', '$2a$10$hashedWachtwoord3', 4) -- Password: wachtwoord3 (example)
ON CONFLICT (username) DO NOTHING; -- Prevent duplicate usernames


-- Continue adding other users as necessary
