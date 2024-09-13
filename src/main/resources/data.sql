INSERT INTO application_user (username, password, role) VALUES ('admin', '$2a$10$hashedAdminPassword', 'ADMIN');
INSERT INTO application_user (username, password, role) VALUES ('employee', '$2a$10$hashedEmployeePassword', 'EMPLOYEE');
INSERT INTO application_user (username, password, role) VALUES ('customer', '$2a$10$hashedCustomerPassword', 'CUSTOMER');

-- Here, replace 'hashedAdminPassword', 'hashedEmployeePassword', and 'hashedCustomerPassword'
-- with actual hashed passwords. You could generate them using BCrypt or any other hashing library.
-- String hashedPassword = BCrypt.hashpw("yourPlainTextPassword", BCrypt.gensalt());  use this where passwords are entered--