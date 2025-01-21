-- Insert admin user
INSERT INTO application_user (username, password, role) VALUES
    ('eline', '$2a$10$uBeRC5SWvpG0EdKrEgOleuifrICHp/aNcG1vElPDd921G329t0YWG', 1) -- Password: wachtwoord1
ON CONFLICT (username) DO NOTHING;

-- Insert new user Kate
INSERT INTO application_user (username, password, role) VALUES
    ('kate', '$2a$10$iK4JvbC0Ysu77S6zivX65.fmvkVVvJTOXTIk9Ohc7f1cFdRAbJqKS', 2) -- Password: wachtwoord4
ON CONFLICT (username) DO NOTHING;

-- Insert employee user John
INSERT INTO application_user (username, password, role) VALUES
    ('john_doe', '$2a$10$5KitXxnpXAKTnK4M/yLg7OkES5vFNNXRy00SS3.SsWTYKDxle/B5G', 3) -- Password: wachtwoord2
ON CONFLICT (username) DO NOTHING;

-- Insert customer user Jane
INSERT INTO application_user (username, password, role) VALUES
    ('jane_doe', '$2a$10$1PT2usvDLy1vi62gnatUneB4HY4x9aXTL6iKk95WPRPC1vhVxA59.', 4) -- Password: wachtwoord3
ON CONFLICT (username) DO NOTHING;

-- Insert new employee user Joe
INSERT INTO application_user (username, password, role) VALUES
    ('joe', '$2a$10$hDUGvYU.21uh9EHBMIQgl.Q40Zno7KXy4Ofvla4tvqU7zU7OAUw1.', 3) -- Password: wachtwoord5
ON CONFLICT (username) DO NOTHING;

-- Insert new customer user Jack
INSERT INTO application_user (username, password, role) VALUES
    ('jack', '$2a$10$AMdR76d4DJObDSf9VEUr5OKpy2dbDbp5J7a.3GPWnhtsf2Amlhf2y', 4) -- Password: wachtwoord6
ON CONFLICT (username) DO NOTHING;


-- Continue adding other users as necessary
