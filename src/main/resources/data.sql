-- Truncate the table to remove existing entries (optional: this will delete all current records)
--TRUNCATE TABLE application_user RESTART IDENTITY CASCADE;

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

-- Insert new customers
INSERT INTO customer (name, address, phone_number, email, user_id)
VALUES ('Anna Blom', 'Dreef 4', '0623456789', 'anna@example.com', (SELECT user_id FROM application_user WHERE username='eline'));

INSERT INTO customer (name, address, phone_number, email, user_id)
VALUES ('Bert de Groot', 'Kerkstraat 10', '0634567890', 'bert@example.com', (SELECT user_id FROM application_user WHERE username='john_doe'));


INSERT INTO stock (part_name, quantity, price)
VALUES ('Wiper blades', 10, 9.99);

-- Update stock
UPDATE stock SET price=24.99 WHERE id=1;

--New user
    INSERT INTO application_user (username, password, role)
VALUES ('mark', '$2a$10$xxxxx', 'EMPLOYEE');


--Add vehicle
INSERT INTO vehicle (customer_id, license_plate, model, year, uploaded_documents)
VALUES (
           (SELECT customer_id FROM customer WHERE name='Anna Blom'), -- of direct id, bv 1
           'ABC-1234',
           'Toyota Corolla',
           2020,
           NULL -- of een string/niet ingevuld
       );


--add inspection
INSERT INTO vehicle (customer_id, license_plate, model, year, uploaded_documents)
VALUES (
           (SELECT customer_id FROM customer WHERE name='Anna Blom'), -- of direct nummer, bv 1
           'XYZ-5678',
           'Honda Civic',
           2018,
           NULL
       );




