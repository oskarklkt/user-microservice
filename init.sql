DO
$$
    BEGIN
        IF EXISTS (SELECT 1
                   FROM information_schema.tables
                   WHERE table_schema = 'public'
                     AND table_name IN ('users', 'addresses')) THEN
            RAISE NOTICE 'Tables already exist';
        ELSE

            CREATE TABLE users
            (
                id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                username VARCHAR(255) NOT NULL,
                surname VARCHAR(255) NOT NULL,
                gender VARCHAR(1),
                birthday VARCHAR(255) NOT NULL,
                phone_number VARCHAR(255) NOT NULL,
                email VARCHAR(255) NOT NULL UNIQUE ,
                profile_photo_url VARCHAR(255),
                account_creation_date VARCHAR(255) NOT NULL,
                client_type VARCHAR(255) NOT NULL
            );

            CREATE TABLE addresses
            (
                id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                user_id BIGINT NOT NULL check ( user_id > 0 ),
                country VARCHAR(255) NOT NULL,
                street_address_1 VARCHAR(255) NOT NULL,
                street_address_2 VARCHAR(255) NOT NULL,
                city VARCHAR(255) NOT NULL,
                state_province_region VARCHAR(255) NOT NULL,
                zip_code VARCHAR(255) NOT NULL,
                phone_number VARCHAR(255) NOT NULL,
                FOREIGN KEY (user_id) REFERENCES users (id)
            );

            --- just for testing purposes - will be deleted in the future
            INSERT INTO users (username, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type)
            VALUES ('John', 'Doe', 'M', '1990-01-01', '123456789', 'john.doe@gmail.com', 'https://www.google.com', '2021-01-01', 'BASIC'),
                   ('Jan', 'Kowalski', 'M', '1999-06-24', '143456789', 'jan.kowalski@gmail.com', 'https://www.gofogle.com', '2023-01-23', 'VIP');
            INSERT INTO addresses (user_id, country, street_address_1, street_address_2, city, state_province_region, zip_code, phone_number)
            VALUES (1, 'USA', 'Times Square', 'street', 'New York', 'NY', '12345', '123456789'),
                   (2, 'Poland', 'Mazowiecka', 'street', 'Warsaw', 'Mazowieckie', '12345', '143456789');
            ---


        END IF;
    END
$$;