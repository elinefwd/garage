-- Truncate the table to remove existing entries (optional: this will delete all current records)
TRUNCATE TABLE application_user RESTART IDENTITY CASCADE;

-- Insert admin user
INSERT INTO application_user (username, password, role) VALUES
    ('eline', '$2a$10$uBeRC5SWvpG0EdKrEgOleuifrICHp/aNcG1vElPDd921G329t0YWG', 'ADMIN'); -- Password: wachtwoord1

-- Insert employee user John
INSERT INTO application_user (username, password, role) VALUES
    ('john_doe', '$2a$10$5KitXxnpXAKTnK4M/yLg7OkES5vFNNXRy00SS3.SsWTYKDxle/B5G', 'EMPLOYEE'); -- Password: wachtwoord2

-- Insert customer user Jane
INSERT INTO application_user (username, password, role) VALUES
    ('jane_doe', '$2a$10$1PT2usvDLy1vi62gnatUneB4HY4x9aXTL6iKk95WPRPC1vhVxA59.', 'CUSTOMER'); -- Password: wachtwoord3

-- Insert new employee user Joe
INSERT INTO application_user (username, password, role) VALUES
    ('joe', '$2a$10$hDUGvYU.21uh9EHBMIQgl.Q40Zno7KXy4Ofvla4tvqU7zU7OAUw1.', 'EMPLOYEE'); -- Password: wachtwoord5


-- Continue adding other users as necessary
