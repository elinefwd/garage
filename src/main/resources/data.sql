-- Insert admin user
INSERT INTO application_user (username, password, role) VALUES
    ('eline', '$2a$10$7gMnER4XU/B2Joaw8kI./uhpJnwfsYxZzAn9qQzYs1FVs7h8zbQTm', 1) -- Password: wachtwoord1
ON CONFLICT (username) DO NOTHING;

-- Insert employee user
INSERT INTO application_user (username, password, role) VALUES
    ('john_doe', '$2a$10$BXqtRRFc7nNB14uI1DBYDOlX3t9RvveTDpeLDDwzyPD0QfwuxCrtm', 3) -- Password: wachtwoord2
ON CONFLICT (username) DO NOTHING;

-- Insert customer user
INSERT INTO application_user (username, password, role) VALUES
    ('jane_doe', '$2a$10$VbARJfVqu0Si3i6HhBuxGOeVzF5xc9t3kYxCKRHT0z/WQe8SqYM2i', 4) -- Password: wachtwoord3
ON CONFLICT (username) DO NOTHING;


-- Continue adding other users as necessary
