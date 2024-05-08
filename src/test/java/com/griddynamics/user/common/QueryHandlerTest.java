package com.griddynamics.user.common;

import com.griddynamics.user.mapper.resultsetToModel.ResultSetUserMapper;
import com.griddynamics.user.model.User;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryHandlerTest {

    AddressQueryHandler addressQueryHandler = new AddressQueryHandler();
    UserQueryHandler userQueryHandler = new UserQueryHandler();
    ResultSetUserMapper resultSetUserMapper = new ResultSetUserMapper();

    @BeforeAll
    static void setUp() {
        Dotenv dotenv = Dotenv.load();
        //setting testing database
        Database.initialize("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from './init_populating_tables.sql'",
                dotenv.get("DB_USER"),
                dotenv.get("DB_PASSWORD"));
    }

    @AfterAll
    static void tearDown() {
        Dotenv dotenv = Dotenv.load();
        //setting back to the original database
        Database.initialize(dotenv.get("DB_URL"),
                dotenv.get("DB_USER"),
                dotenv.get("DB_PASSWORD"));
    }

    @Test
    void execute_insert() {
        int initialSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                "test", "tester", "M", "1990-01-01", "123456789", "test@ex.com", "https://www.google.com", "2021-01-01", "BASIC");
        int finalSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    void execute_overloaded_insert() {
        int initialSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        userQueryHandler.execute("INSERT INTO users (name, surname, gender, birthday, phone_number, email, profile_photo_url, account_creation_date, client_type) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                preparedStatement -> {
                    try {
                        preparedStatement.setString(1, "test2");
                        preparedStatement.setString(2, "tester2");
                        preparedStatement.setString(3, "M");
                        preparedStatement.setString(4, "1990-01-01");
                        preparedStatement.setString(5, "123456789");
                        preparedStatement.setString(6, "test@ex2.com");
                        preparedStatement.setString(7, "https://www.google.com");
                        preparedStatement.setString(8, "2021-01-01");
                        preparedStatement.setString(9, "BASIC");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
        int finalSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        assertEquals(initialSize + 1, finalSize);
    }

    @Test
    void execute_delete() {
        int initialSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        userQueryHandler.execute("DELETE FROM users WHERE id = ?", 3L);
        int finalSize = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper).size();
        assertEquals(initialSize - 1, finalSize);
    }

    @Test
    void findOne() {
        Long id = userQueryHandler.findOne("SELECT * FROM users WHERE id = ?", resultSetUserMapper, 1L).getId();
        assertEquals(id, 1L);
    }

    @Test
    void findMany() {
        List<User> users = userQueryHandler.findMany("SELECT * FROM users", resultSetUserMapper);
        assertEquals("John",users.get(0).getName());
    }
}