--- TODO: 1)run some Query plans -> scans vs indexes 2) type of indexes
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
                name VARCHAR(255) NOT NULL,
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
        END IF;
    END
$$;