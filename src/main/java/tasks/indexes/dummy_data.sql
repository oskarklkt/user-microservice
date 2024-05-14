DO
$do$
    BEGIN
        FOR i IN 1..2000000 LOOP
                INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date,
                                   client_type)
                VALUES ('John', 'Doe', 'M', '1990-01-01', '123456789', CONCAT(i, '@gmail.com'), 'https://www.google.com', '2021-01-01',
                        'BASIC');
            END LOOP;
    END
$do$;


