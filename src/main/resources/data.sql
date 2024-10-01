INSERT INTO application_user (username, password, role) VALUES ('admin', '$2a$10$hashedAdminPassword', 'ADMIN');
INSERT INTO application_user (username, password, role) VALUES ('employee', '$2a$10$hashedEmployeePassword', 'EMPLOYEE');
INSERT INTO application_user (username, password, role) VALUES ('customer', '$2a$10$hashedCustomerPassword', 'CUSTOMER');

-- Here, replace 'hashedAdminPassword', 'hashedEmployeePassword', and 'hashedCustomerPassword'
-- with actual hashed passwords. You could generate them using BCrypt or any other hashing library.
-- String hashedPassword = BCrypt.hashpw("yourPlainTextPassword", BCrypt.gensalt());  use this where passwords are entered--

-- Insert admin user
INSERT INTO application_user (username, password, role)
VALUES
    ('eline', '$2a$10$hashedWachtwoord1', 'ADMIN'); -- Password: wachtwoord1 (example)

-- Insert employee user
INSERT INTO application_user (username, password, role)
VALUES
    ('john_doe', '$2a$10$hashedWachtwoord2', 'EMPLOYEE'); -- Password: wachtwoord2 (example)

-- Insert customer user
INSERT INTO application_user (username, password, role)
VALUES
    ('jane_doe', '$2a$10$hashedWachtwoord3', 'CUSTOMER'); -- Password: wachtwoord3 (example)

-- You can repeat or modify similar lines for more users if necessary
