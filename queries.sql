---SQL queries for User

--- save
INSERT INTO users (username, surname, gender, birthday, phone_number,
    email, profile_photo_url, account_creation_date, client_type)
VALUES ('Jan', 'Dybski', 'M', '2005-01-01', '123456789',
    'jano@gmail.com', 'https://www.gogole.com', '2021-01-01', 'BASIC');
--- get
--- is User In Database
SELECT *
FROM users
WHERE id = 1;
--- get by email
SELECT *
FROM users
WHERE email = 'jano@gmail.com';
--- get all users
SELECT *
FROM users;
--- is email in database
SELECT email
FROM users
WHERE email = 'jano@gmail.com';
--- get next id
SELECT MAX(id) + 1
FROM users;
--- set user vip
UPDATE users
SET client_type = 'VIP'
WHERE id = 1;
--- delete user
DELETE FROM users
WHERE id = 4;
--- update user = delete user + add user

---SQL queries for Address


--- save
INSERT INTO addresses (user_id, country, street_address_1, street_address_2, city, state_province_region, zip_code, phone_number)
VALUES (1, 'Slovakia', 'Hlavna 1', 'Hlavna 2', 'Bratislava', 'Bratislava', '12345', '123456789');
--- get
SELECT *
FROM addresses
WHERE user_id = 1;
--- delete
DELETE FROM addresses
WHERE id = 3;
--- update = delete + add
---get next id
SELECT MAX(id) + 1
FROM addresses;
